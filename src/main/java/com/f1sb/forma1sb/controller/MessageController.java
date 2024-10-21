package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.Uzenet;
import com.f1sb.forma1sb.model.UzenetDTO;
import com.f1sb.forma1sb.model.UzenetRequest;
import com.f1sb.forma1sb.model.repositories.UzenetRepository;
import com.f1sb.forma1sb.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private UzenetRepository UzenetRepository;
    private final MyUserDetailsService userDetailsService;

    public MessageController(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/message/getall")
    @ResponseBody
    public Iterable<Uzenet> getAllUzenet() {
        return UzenetRepository.findAll();
    }

    public List<UzenetDTO> getAllDTO() {
        Iterable<Uzenet> uzenetek = UzenetRepository.findAll(Sort.by(Sort.Direction.DESC, "rogzites"));
        List<UzenetDTO> utenetekDTO = new ArrayList<>();
        uzenetek.forEach(uzenet -> {
            Integer UserID = uzenet.getFelhasznalo_id();
            String getfelhasznaloNev = "";
            if(UserID != null){
                getfelhasznaloNev = userDetailsService.getUsernameById(UserID);
            }
            String felhasznaloNev = (getfelhasznaloNev != null && !getfelhasznaloNev.isEmpty()) ? getfelhasznaloNev : "Vendég";
            utenetekDTO.add(new UzenetDTO(uzenet.getId(),felhasznaloNev,uzenet.getUzenet(),uzenet.getRogzites()));
        });
        return utenetekDTO;
    }


    @PostMapping("/message/add")
    @ResponseBody
    public RedirectView addUzenet (@RequestBody String uzenet) {
        // URL-dekódolás
        uzenet = URLDecoder.decode(uzenet, StandardCharsets.UTF_8);
        if (uzenet.startsWith("uzenet=")) {
            // Levágjuk az "uzenet=" részt
            uzenet = uzenet.substring(7);
        }
        if (uzenet == null || uzenet.isEmpty()) {
            return new RedirectView("/contact?error=1");
        }
        Integer userId = MyUserDetailsService.getCurrentUserId();

        Uzenet _uzenet = new Uzenet();
        _uzenet.setFelhasznalo_id(userId);
        _uzenet.setUzenet(uzenet);
        UzenetRepository.save(_uzenet);

        return new RedirectView("/contact?success");

    }

    @PostMapping("/message/post")
    @ResponseBody
    public ResponseEntity<String> postUzenet(@RequestBody UzenetRequest postMessageRequest) {
        if (postMessageRequest.getUzenet() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message must be filled");
        }

        try {
            Uzenet _uzenet = new Uzenet();
            _uzenet.setFelhasznalo_id(postMessageRequest.getFelhasznalo_id());
            _uzenet.setUzenet(postMessageRequest.getUzenet());
            UzenetRepository.save(_uzenet);

            return ResponseEntity.status(HttpStatus.OK).body("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Message cannot be sent");
        }
    }

}
