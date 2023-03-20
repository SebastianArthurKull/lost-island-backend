package ch.bbcag.lostislandbackend.socket;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Projectiles {
    private List<Projectile> projectiles = new ArrayList<>();

    public Projectiles() {

    }

    public Projectiles(List<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(List<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Projectile projectile : projectiles) {
            stringBuilder.append(projectile.getDirection());
        }
        return stringBuilder.toString();
    }
}
