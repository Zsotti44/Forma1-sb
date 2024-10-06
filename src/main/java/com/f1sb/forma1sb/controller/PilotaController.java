package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.Pilota;
import com.f1sb.forma1sb.model.repositories.PilotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api/pilota")
public class PilotaController {
    @Autowired
    private PilotaRepository pilotaRepository;

    @GetMapping(path="/getall")
    public @ResponseBody Iterable<Pilota> getAllPilots() {
        return pilotaRepository.findAll();
    }

    public long countPilots(){
        return pilotaRepository.count();
    }

}

