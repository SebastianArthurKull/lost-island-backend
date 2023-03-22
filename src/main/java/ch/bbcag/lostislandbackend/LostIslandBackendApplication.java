package ch.bbcag.lostislandbackend;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LostIslandBackendApplication {

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration  config = new Configuration();
//        config.setHostname("192.168.52.48");
        config.setHostname("0.0.0.0");
        config.setPort(9092);
        return new SocketIOServer(config);
    }

    public static void main(String[] args) {
        SpringApplication.run(LostIslandBackendApplication.class, args);
    }

}
