package ro.unibuc.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ro.unibuc.hello.data.TeamEntity;
import ro.unibuc.hello.service.TeamService;

@Controller
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/getTeamInfo")
    @ResponseBody
    public String getTeamInfo(
            @RequestParam(name = "name", required = false, defaultValue = "Brooklyn Nets") String name) {
        return teamService.getTeamInfo(name);
    }

    @GetMapping("/addTeam")
    @ResponseBody
    public String getTeamInfo(
        @RequestParam(name = "id", required = false, defaultValue = "100") String id,
            @RequestParam(name = "name", required = false, defaultValue = "Brooklyn Nets") String name,
            @RequestParam(name = "yearFounded", required = false, defaultValue = "0") int yearFounded,
            @RequestParam(name = "coach", required = false, defaultValue = "Unknown") String coach,
            @RequestParam(name = "players", required = false) List<Integer> playersIds) {
        TeamEntity teamEntity = new TeamEntity(id,name, playersIds, yearFounded, coach);
        return teamService.addTeam(teamEntity);
    }

    @GetMapping("/getTeam")
    @ResponseBody
    public String getTeam(@RequestParam(name="name",required = false,defaultValue = "Los Angeles Lakers") String name){
        return teamService.getTeam(name);
    }

    @GetMapping("/getBestPlayer")
    @ResponseBody
    public String getBestPlayer(@RequestParam(name="name",required = false,defaultValue = "Los Angeles Lakers") String name){
        return teamService.getBestPlayer(name);
    }

    @GetMapping("/updateTeam")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTeam(
        // Nu am asignat noi membrii ai echipei
        @RequestParam(name = "id", required = true) String id,
        @RequestParam(name = "newName", required = false, defaultValue = "") String newName,
        @RequestParam(name = "newYearFounded", required = false, defaultValue = "0") int newYearFounded,
        @RequestParam(name = "newCoach", required = false, defaultValue = "") String newCoach
    ) {
        teamService.updateTeam(id, newName, newYearFounded, newCoach);
    }

    @DeleteMapping("/deleteTeamByName")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeamByName(@RequestParam(name="name",required=true)String name){
        teamService.deleteByName(name);
    }
}
