package ro.unibuc.hello.data;

import org.springframework.data.annotation.Id;

public class PlayerEntity {
    @Id
    public Integer id;
    public String name;
    public String team;
    public double points_per_game;
    public double rebounds_per_game;
    public double assists_per_game;

    public PlayerEntity() {
    }

    public PlayerEntity(Integer id, String name, String team, double points_per_game, double rebounds_per_game,
            double assists_per_game) {
        this.id=id;
        this.name = name;
        this.team = team;
        this.points_per_game = points_per_game;
        this.rebounds_per_game = rebounds_per_game;
        this.assists_per_game = assists_per_game;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return String.format(
                "Player: %s\nTeam: %s\nPoints per game: %.2f\nRebounds per game: %.2f\nAssists per game: %.2f",
                name, team, points_per_game, rebounds_per_game, assists_per_game);
    }

    public Integer getId() {
        return id;
    }

    public double getPpg(){
        return points_per_game;
    }

    public String getName(){
        return name;
    }

    public double getRpg(){
        return rebounds_per_game;
    }

    public double getApg(){
        return assists_per_game;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPointsPerGame(double points_per_game) {
        this.points_per_game = points_per_game;
    }

    public void setReboundsPerGame(double rebounds_per_game) {
        this.rebounds_per_game = rebounds_per_game;
    }

    public void setAssistsPerGame(double assists_per_game) {
        this.assists_per_game = assists_per_game;
    }

}
