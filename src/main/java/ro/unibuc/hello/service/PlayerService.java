package ro.unibuc.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.unibuc.hello.data.PlayerEntity;
import ro.unibuc.hello.data.PlayerRepository;
import ro.unibuc.hello.exception.EntityNotFoundException;

@Component
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public String getPlayerTeam(String name)throws EntityNotFoundException{
        PlayerEntity playerEntity=playerRepository.findByName(name);
        if (playerEntity==null){
            throw new EntityNotFoundException(name);
        }
        return playerEntity.getTeam();
    }

    public String addPlayer(PlayerEntity newPlayer){
        playerRepository.save(newPlayer);
        return "Player added";
    }

    public String getPlayer(String name)throws EntityNotFoundException{
        PlayerEntity playerEntity=playerRepository.findByName(name);
        if (playerEntity==null){
            throw new EntityNotFoundException(name);
        }
        return playerEntity.toString();
    }

    public String deleteByName(String name)throws EntityNotFoundException{
        PlayerEntity playerEntity=playerRepository.findByName(name);
        if (playerEntity==null){
            throw new EntityNotFoundException(name);
        }
        playerRepository.deleteById(playerEntity.getId());
        return "Player deleted succesfully";
    }
}
