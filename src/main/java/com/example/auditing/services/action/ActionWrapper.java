package com.example.auditing.services.action;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ActionWrapper {
    private String applicationName;
    private String userEmail;
    private String beName;
    private String actionType;

    private List<Map<String,Map<String,String>>> params;

}
