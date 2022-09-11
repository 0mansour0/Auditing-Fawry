package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionServiceTest {

    private ActionService actionService;

    @BeforeEach
    void setUp() {
        actionService = new ActionService();
    }
    @Test
    public void isActionAdded(){
        ActionWrapper actionWrapper = new ActionWrapper();
        ActionModel actionModel = actionService.addAction(null);
    }
}