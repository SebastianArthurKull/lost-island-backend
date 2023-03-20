package ch.bbcag.lostislandbackend.socket;

public class PlayerData {
    private Integer id;

    private String sessionId;

    private String name;

    private String email;

    private Integer xcor;

    private Integer ycor;

    private String direction;

    private String animation;

    private String role;

    private String message;

    private String mapId;

    public PlayerData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getXcor() {
        return xcor;
    }

    public void setXcor(Integer xcor) {
        this.xcor = xcor;
    }

    public Integer getYcor() {
        return ycor;
    }

    public void setYcor(Integer ycor) {
        this.ycor = ycor;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", name='" + name + '\'' +
                ", xcor=" + xcor +
                ", ycor=" + ycor +
                ", direction='" + direction + '\'' +
                ", animation='" + animation + '\'' +
                ", role='" + role + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
