package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.GP;
import com.f1sb.forma1sb.model.repositories.GPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;

@Controller
@RequestMapping(path="/api/gp")
public class GPController {
    @Autowired
    private GPRepository GPRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addGP (@RequestParam Date datum, @RequestParam String nev, @RequestParam String helyszin) {

        GP gp = new GP();
        gp.setHelyszin(helyszin);
        gp.setDatum(datum);
        gp.setNev(nev);
        GPRepository.save(gp);
        return "Saved";
    }

    @GetMapping(path="/getall")
    public @ResponseBody Iterable<GP> getAllGP() {
        return GPRepository.findAll();
    }

    public long countGPs(){
        return GPRepository.count();
    }
}
