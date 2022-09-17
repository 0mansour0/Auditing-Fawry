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

import java.util.HashMap;
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
    public ResponseEntity<List<ActionModel>> get(@RequestParam String be,@RequestParam String user,@RequestParam String app,
    @RequestParam String action,@RequestParam String param,@RequestParam String pValue){
        try {
            Map<String,String> searchCriteria = new HashMap<>();
            searchCriteria.put("beName",be);
            searchCriteria.put("appName",app);
            searchCriteria.put("userEmail",user);
            searchCriteria.put("actionType",action);
            searchCriteria.put("paramType",param);
            searchCriteria.put("paramValue",pValue);
            return new ResponseEntity<>(actionService.searchForActions(searchCriteria), HttpStatus.OK);
        } catch (ResourceNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }

    }

}
