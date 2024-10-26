package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.*;
import com.f1sb.forma1sb.model.repositories.GPRepository;
import com.f1sb.forma1sb.model.repositories.NyertesRepository;
import com.f1sb.forma1sb.model.repositories.PilotaRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api")
public class StatisticController {

    @Autowired
    private NyertesRepository nyertesRepository;
    @Autowired
    private GPRepository gpRepository;
    @Autowired
    private PilotaRepository pilotaRepository;

    @GetMapping("/stat/winner")
    @ResponseBody
    public Iterable<Nyertes> getNyertes() {
        return nyertesRepository.findAll();
    }

    @GetMapping("/stat/statistics")
    @ResponseBody
    public ResponseEntity<String> getStatistics() {
        long races = gpRepository.count();
        long drivers = pilotaRepository.count();
        List<NationWinsResponse> nationWins = nyertesRepository.findNationWins();
        List<PilotaWinsReponse> pilotaWins = nyertesRepository.findPilotaWins();
        List<TeamWinsResponse> csapatWins = nyertesRepository.findTeamWins();
        List<GPReponse> helyszinek = nyertesRepository.findGPs();

        Map<String, Object> response = new HashMap<>();
        response.put("countOfRaces", races);
        response.put("countOfDrivers", drivers);
        response.put("NationWins", nationWins);
        response.put("pilotaWins", pilotaWins);
        response.put("csapatWins", csapatWins);
        response.put("helyszinek", helyszinek);

        JSONObject jsonObject = new JSONObject(response);
        return ResponseEntity.ok(jsonObject.toString());
    }
}
