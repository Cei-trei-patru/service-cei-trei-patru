package ro.unibuc.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.unibuc.hello.data.PlayerEntity;
import ro.unibuc.hello.data.PlayerRepository;
import ro.unibuc.hello.exception.EntityNotFoundException;
import java.util.List;
import io.micrometer.core.instrument.MeterRegistry;


@Component
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MeterRegistry metricsRegistry;

    public PlayerEntity createPlayer(PlayerEntity newPlayer) {
        PlayerEntity createdPlayer = playerRepository.save(newPlayer);
        
        // Increment metrics for player creation
        metricsRegistry.counter("Create_player_entities").increment();
        
        return createdPlayer;
    }
    public String getPlayerTeam(String name)throws EntityNotFoundException{
        PlayerEntity playerEntity=playerRepository.findByName(name);
        if (playerEntity==null){
            throw new EntityNotFoundException(name);
        }
        // metricsRegistry.counter("GetPlayerTeam").increment();

        return playerEntity.getTeam();
    }

    public PlayerEntity updatePlayer(String id, PlayerEntity newPlayer) {
        PlayerEntity dbPlayer = playerRepository.findById(id).get();
    
        if (newPlayer.getName() != null && !newPlayer.getName().isEmpty()) {
            dbPlayer.setName(newPlayer.getName());
        }
        if (newPlayer.getTeam() != null && !newPlayer.getTeam().isEmpty()) {
            dbPlayer.setTeam(newPlayer.getTeam());
        }
        if (newPlayer.getPoints_per_game() != 0) {
            dbPlayer.setPoints_per_game(newPlayer.getPoints_per_game());
            metricsRegistry.counter("Increment_player_entities_points_per_game", "endpoint", "hello").increment(dbPlayer.getPoints_per_game());
        }
        if (newPlayer.getRebounds_per_game() != 0) {
            dbPlayer.setRebounds_per_game(newPlayer.getRebounds_per_game());
            metricsRegistry.counter("Increment_player_entities_rebounds_per_game", "endpoint", "hello").increment(dbPlayer.getRebounds_per_game());
        }
        if (newPlayer.getAssists_per_game() != 0) {
            dbPlayer.setAssists_per_game(newPlayer.getAssists_per_game());
            metricsRegistry.counter("Increment_player_entities_assists_per_game", "endpoint", "hello").increment(dbPlayer.getAssists_per_game());
        }
        
        // metricsRegistry.counter("Update_Player").increment();

        return playerRepository.save(dbPlayer);
    }
    

    public String getPlayer(String name)throws EntityNotFoundException{
        PlayerEntity playerEntity=playerRepository.findByName(name);
        if (playerEntity==null){
            throw new EntityNotFoundException(name);
        }
        metricsRegistry.counter("Increment_player_entities_assists_per_game", "endpoint", "hello").increment(playerEntity.assists_per_game);
        return playerEntity.toString();
    }

    public List<PlayerEntity> getAllPlayers(){
        return playerRepository.findAll();

    }

    public String deleteByName(String name)throws EntityNotFoundException{
        PlayerEntity playerEntity=playerRepository.findByName(name);
        if (playerEntity==null){
            throw new EntityNotFoundException(name);
        }
        playerRepository.deleteById(playerEntity.getId());
        metricsRegistry.counter("Delete_player_entities").increment();

        return "Player deleted succesfully";
    }

    // public String deleteByName(String name)throws EntityNotFoundException{
    //     PlayerEntity playerEntity=playerRepository.findByName(name);
    //     if (playerEntity==null){
    //         throw new EntityNotFoundException(name);
    //     }
    //     playerRepository.deleteById(playerEntity.getId());
    //     return "Player deleted succesfully";
    // }
}
