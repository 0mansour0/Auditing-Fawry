package com.example.auditing.rappitmq;

import com.example.auditing.services.action.ActionService;
import com.example.auditing.services.action.ActionWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {
    @Autowired
    private ActionService actionService;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String fileBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ActionWrapper actionWrapper = objectMapper.readValue(fileBody, ActionWrapper.class);
        actionService.addAction(actionWrapper);
    }
}
