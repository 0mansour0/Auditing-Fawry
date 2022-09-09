package com.example.auditing.resources.action;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.action.ActionModel;
import com.example.auditing.rappitmq.QueueSender;
import com.example.auditing.services.action.ActionService;
import com.example.auditing.services.action.ActionWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("action")
public class ActionResource {
    @Autowired
    private ActionService actionService;
    @Autowired
    private QueueSender queueSender;

    @PostMapping
    public void create(@RequestBody ActionWrapper actionWrapper) throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonRequest = objectWriter.writeValueAsString(actionWrapper);
        queueSender.send(jsonRequest);
    }

    @GetMapping
    public ResponseEntity<List<ActionModel>> get(@RequestBody Map<String,String> searchCriteria){
        try {
            return new ResponseEntity<>(actionService.searchForActions(searchCriteria), HttpStatus.OK);
        } catch (ResourceNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }

    }

}
