package ro.unibuc.hello.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ro.unibuc.hello.data.TeamEntity;
import ro.unibuc.hello.data.TeamRepository;
import ro.unibuc.hello.data.PlayerEntity;
import ro.unibuc.hello.data.PlayerRepository;
import ro.unibuc.hello.exception.EntityNotFoundException;

@SpringBootTest
public class TeamServiceTest1 {

    @Autowired
    private TeamService teamService;

    @MockBean
    private TeamRepository teamRepository;

    @MockBean
    private PlayerRepository playerRepository;

    @Test
    public void testGetTeamInfo_ExistingTeam_ReturnsInfo() {
        String teamName = "Test Team";
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(teamName);
        teamEntity.getTeamInfo("Team " +  " was founded in "  + ". The coach is " +  ".");
        
        when(teamRepository.findByName(teamName)).thenReturn(teamEntity);

        String result = teamService.getTeamInfo(teamName);
        assertEquals("Test Team Info", result);
    }

    @Test
    public void testGetTeamInfo_NonExistingTeam_ThrowsException() {
        String teamName = "NonExisting Team";
        when(teamRepository.findByName(teamName)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            teamService.getTeamInfo(teamName);
        });
    }
}