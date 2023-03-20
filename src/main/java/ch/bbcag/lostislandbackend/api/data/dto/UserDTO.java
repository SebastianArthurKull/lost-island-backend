package ch.bbcag.lostislandbackend.api.data.dto;

public class UserDTO {

    private Integer id;

    public UserDTO() {
    }

    public UserDTO(Integer id) {
        this.id = id;
    }

    private String sessionId;

    private String name;

    private String email;

    private Integer xCor;

    private Integer yCor;

    private String direction;

    private String animation;

    private String role;

    private String message;

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

    public Integer getXCor() {
        return xCor;
    }

    public void setXCor(Integer xCor) {
        this.xCor = xCor;
    }

    public Integer getYCor() {
        return yCor;
    }

    public void setYCor(Integer yCor) {
        this.yCor = yCor;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", xCor=" + xCor +
                ", yCor=" + yCor +
                ", direction='" + direction + '\'' +
                ", animation='" + animation + '\'' +
                ", role='" + role + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}


