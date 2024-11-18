package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.*;
import com.f1sb.forma1sb.model.repositories.NyertesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContentController {
    @Autowired private  GPController gpController;
    @Autowired private  PilotaController pilotaController;
    @Autowired private  MessageController messageController;
    @Autowired private  NyertesRepository nyertesRepository;

    public ContentController(GPController gpController, PilotaController pilotaController, MessageController messageController) {
        this.pilotaController = pilotaController;
        this.gpController = gpController;
        this.messageController = messageController;
    }

    @GetMapping({"","/"})
    public String home(Model model) {
        model.addAttribute("activePage","home");
        return "home";
    }
    @GetMapping("/contact")
    public String Contact(Model model) {
        model.addAttribute("activePage","contact");
        return "contact";
    }

    @GetMapping("/contact_messages")
    public String ContactMessages(Model model) {
        List<UzenetDTO> uzenetekDTO = messageController.getAllDTO();
        model.addAttribute("uzenetek",uzenetekDTO);
        model.addAttribute("activePage","contact_messages");
        return "contact_messages";
    }

    @GetMapping("/stats")
    public String stats(Model model){
        List<NationWinsResponse> nationWins = nyertesRepository.findNationWins();
        List<PilotaWinsReponse> pilotaWins = nyertesRepository.findPilotaWins();
        List<TeamWinsResponse>   csapatWins = nyertesRepository.findTeamWins();
        List<GPReponse> helyszinek = nyertesRepository.findGPs();

        model.addAttribute("countPilots",pilotaController.countPilots());
        model.addAttribute("countGP",gpController.countGPs());
        model.addAttribute("nationWins",nationWins);
        model.addAttribute("pilotaWins",pilotaWins);
        model.addAttribute("csapatWins",csapatWins);
        model.addAttribute("helyszinek",helyszinek);
        model.addAttribute("activePage","stats");

        return "stats";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("activePage","login");
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("activePage","register");
        return "auth/register";
    }
}
