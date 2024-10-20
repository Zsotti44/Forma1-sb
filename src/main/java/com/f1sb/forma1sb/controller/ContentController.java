package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.UzenetDTO;
import com.f1sb.forma1sb.model.repositories.PilotaRepository;
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

    public ContentController(GPController gpController, PilotaController pilotaController, MessageController messageController) {
        this.pilotaController = pilotaController;
        this.gpController = gpController;
        this.messageController = messageController;
    }

    @GetMapping({"","/"})
    public String home() {

        return "home";
    }
    @GetMapping("/contact")
    public String Contact() {

        return "contact";
    }

    @GetMapping("/contact_messages")
    public String ContactMessages(Model model) {
        List<UzenetDTO> uzenetekDTO = messageController.getAllDTO();
        model.addAttribute("uzenetek",uzenetekDTO);
        return "contact_messages";
    }

    @GetMapping("/stats")
    public String stats(Model model){
        model.addAttribute("countPilots",pilotaController.countPilots());
        model.addAttribute("countGP",gpController.countGPs());
        return "stats";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }
}
