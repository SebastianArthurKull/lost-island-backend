package ch.bbcag.lostislandbackend.socket;

import ch.bbcag.lostislandbackend.api.data.dto.UserDTO;
import ch.bbcag.lostislandbackend.api.data.dto.UserMapper;
import ch.bbcag.lostislandbackend.api.service.UserService;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Component
public class WebSocketModule {

    private final SocketIONamespace namespace;
    private final UserService userService;
    private List<PlayerData> players = new CopyOnWriteArrayList<>();

    private Map<Integer, List<PlayerData>> playersMap = new HashMap<>();
    private final Set<Projectile> projectiles = new CopyOnWriteArraySet<>();

    @Autowired

    public WebSocketModule(SocketIOServer server, UserService userService) {
        this.namespace = server.addNamespace("/socket");
        this.userService = userService;
        this.namespace.addConnectListener(onConnected());
        this.namespace.addEventListener("leave", Id.class, leave());
    }

    public void AddEventListener(Integer id) {
        playersMap.put(id, new CopyOnWriteArrayList<>());

        this.namespace.addEventListener("data/" + id, PlayerData.class, onDataReceived(id));
        this.namespace.addEventListener("join/" + id, PlayerData.class, join(id));
        this.namespace.addEventListener("fire/" + id, Projectiles.class, onProjectileReceived(id));

        this.namespace.addDisconnectListener(onDisconnected(id));

        System.out.println("Room Id: " + id);
    }


    private DataListener<Id> leave() {
        return ((client, data, ackSender) -> {
            playersMap.forEach((k, playersMap) -> {
                playersMap.forEach(playerData -> {
                    if (playerData.getId() == data.getId()){
                        playersMap.remove(playerData);
                    }
                });
            });
        });
    }

    private DataListener<PlayerData> join(int id) {
        return ((client, data, ackSender) -> {
            this.namespace.getBroadcastOperations().sendEvent("playerList", playersMap);
            System.out.println(data);

            client.sendEvent("dataReturn/" + id, players);
            client.sendEvent("sessionId/" + id, client.getSessionId().toString());

            UserDTO userDTO = userService.getByEmail(data.getEmail());

            userDTO.setSessionId(client.getSessionId().toString());
            data.setSessionId(client.getSessionId().toString());

            userService.update(userDTO, data.getId());

            AtomicReference<Boolean> inList = new AtomicReference<>(false);

            playersMap.get(id).forEach(pMap -> {
                if (Objects.equals(pMap.getSessionId(), client.getSessionId().toString())){
                    inList.set(true);
                }
            });

            if (!inList.get()){
                playersMap.get(id).add(data);
            }

            System.out.println("PlayerList size: " + players.size());
        });
    }

    private DataListener<PlayerData> onDataReceived(int id) {
        return ((client, data, ackSender) -> {
            System.out.println(data);
            players = playersMap.get(id).stream().map(p -> {
                if (Objects.equals(client.getSessionId().toString(), p.getSessionId())) {
                    p.setName(data.getName());
                    p.setXcor(data.getXcor());
                    p.setYcor(data.getYcor());
                    p.setDirection(data.getDirection());
                    p.setRole(data.getRole());
                    p.setMessage(data.getMessage());
                    p.setAnimation(data.getAnimation());
                }

                return p;
            }).collect(Collectors.toList());

            namespace.getBroadcastOperations().sendEvent("dataReturn/" + id, playersMap.get(id));
        });
    }

    private DataListener<Projectiles> onProjectileReceived(int id) {
        return (((client, data, ackSender) -> {

            for (Projectile projectile: data.getProjectiles()) {
                projectile.setSessionId(client.getSessionId().toString());
                projectiles.remove(projectile);
                projectiles.add(projectile);
            }

            projectiles.removeIf(p -> p.getCycleCounter() > 7);

            namespace.getBroadcastOperations().sendEvent("fire2/"+id, projectiles);
        }));
    }

    private ConnectListener onConnected() {
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();

            System.out.printf("Client[%1$s] - Connected to chat module through '%2$s'%n", client.getSessionId().toString(), handshakeData.getUrl());
        };
    }

    private DisconnectListener onDisconnected(int id) {
        return client -> {
            System.out.printf("Client[%1$s] - Disconnected from module", client.getSessionId().toString());
            System.out.println();

            for (PlayerData p : playersMap.get(id)) {

                if (Objects.equals(p.getSessionId(), client.getSessionId().toString())) {
                    userService.mergeUser(UserMapper.fromDto(userService.getBySessionId(p.getSessionId())), UserMapper.fromPersonData(p));
                    playersMap.get(id).remove(p);
                }
            }

            playersMap.forEach((k, pMap) -> {
                if (pMap.size() == 0) {
                    namespace.removeAllListeners("data/" + k);
                    namespace.removeAllListeners("join/" + k);
                    namespace.removeAllListeners("fire/" + k);
            }});

            namespace.getBroadcastOperations().sendEvent("dataReturn/"+id, players);
            System.out.println("PlayerList size: " + players.size());
        };
    }

    public boolean isEventListenerForMapAdded(Integer id) {
        return playersMap.containsKey(id);
    }
}
