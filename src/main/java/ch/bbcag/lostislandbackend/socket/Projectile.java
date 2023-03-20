package ch.bbcag.lostislandbackend.socket;

import java.util.Objects;

public class Projectile {

    private String id;
    private Integer xcor;

    private Integer ycor;

    private String direction;

    private String type;

    private String sessionId;


    private int cycleCounter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getCycleCounter() {
        return cycleCounter;
    }

    public void setCycleCounter(int cycleCounter) {
        this.cycleCounter = cycleCounter;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projectile that = (Projectile) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Projectile{" +
                "xcor=" + xcor +
                ", ycor=" + ycor +
                ", direction='" + direction + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
