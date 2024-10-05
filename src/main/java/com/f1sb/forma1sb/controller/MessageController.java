package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.MessageDTO;
import com.f1sb.forma1sb.service.MessageService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public ResponseEntity<String> postMessage(@RequestBody MessageDTO postMessageRequest) {
        if (postMessageRequest.getMessage() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message must be filled");
        }

        int result = messageService.addMessage(postMessageRequest);

        if (result == 0) {
            return ResponseEntity.status(400).body("Message cannot be sent");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Message sent successfully");
        }
    }

    @GetMapping("/message")
    public ResponseEntity<String> getMessages() {
        try {
            List<MessageDTO> messages = messageService.showMessages();
            if (messages == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No messages found");
            }
            List<JSONObject> jsonList = new ArrayList<>();
            for (MessageDTO messageDTO : messages) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", messageDTO.getId());
                jsonObject.put("userId", messageDTO.getUserId());
                jsonObject.put("message", messageDTO.getMessage());
                jsonObject.put("createdDate", messageDTO.getCreatedDate());

                jsonList.add(jsonObject);
            }

            return ResponseEntity.ok(jsonList.toString());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
