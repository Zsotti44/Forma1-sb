package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.Uzenet;
import com.f1sb.forma1sb.model.UzenetRequest;
import com.f1sb.forma1sb.model.repositories.UzenetRepository;
import com.f1sb.forma1sb.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private UzenetRepository UzenetRepository;

    @GetMapping("/message/getall")
    @ResponseBody
    public Iterable<Uzenet> getAllUzenet() {
        return UzenetRepository.findAll();
    }

    @PostMapping("/message/add")
    @ResponseBody
    public String addUzenet (@RequestBody String uzenet) {
        if (uzenet == null || uzenet.isEmpty()) {
            return "Message must be filled";
        }
        Integer userId = MyUserDetailsService.getCurrentUserId();

        Uzenet _uzenet = new Uzenet();
        _uzenet.setFelhasznalo_id(userId);
        _uzenet.setUzenet(uzenet);
        UzenetRepository.save(_uzenet);

        return "Saved";
    }

    @PostMapping("/message/post")
    @ResponseBody
    public ResponseEntity<String> postMessage(@RequestBody UzenetRequest postMessageRequest) {
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
