package com.example.auditing.resources;

import com.example.auditing.services.action.ActionTypeService;
import com.example.auditing.services.dummytables.ApplicationService;
import com.example.auditing.services.param.ParamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/searchLists")
public class SearchResource {
    @Autowired
    ParamTypeService paramTypeService;

    @Autowired
    ActionTypeService actionTypeService;

    @Autowired
    ApplicationService applicationService;

    @GetMapping
    public Map<String,List<String>> get() {
        Map<String,List<String>> searchLists = new HashMap<>();
        searchLists.put("params",paramTypeService.getParamTypes());
        searchLists.put("actions",actionTypeService.getActionTypes());
        searchLists.put("applications",applicationService.getApplications());
        return searchLists;
    }
}
