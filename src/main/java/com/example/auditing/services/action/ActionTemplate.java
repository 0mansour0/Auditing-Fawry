package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionModel;
import com.mysema.commons.lang.Pair;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionTemplate {
    public Map<String, String> generatingDescriptions(ActionModel action, List<Pair<String,String>> paramList){
        String enTemplate = action.getAction_type().getMessageTemplateEn();
        String arTemplate = action.getAction_type().getMessageTemplateAr();

        Map<String, String> replacements = new HashMap<>();

        replacements.put("user.value",action.getUser_name().getUserName());
        replacements.put("app.value",action.getApplication_name().getAppName());
        replacements.put("action.value",action.getApplication_name().getAppName());
        replacements.put("be.value",action.getBe_name().getBeName());

        paramList.forEach(param->{
            String paramType = param.getFirst()+".value";
            replacements.put(paramType,param.getSecond());
        });

        StringSubstitutor stringSubstitutor = new StringSubstitutor(replacements);

        Map<String, String> result = new HashMap<>();

        result.put("en", stringSubstitutor.replace(enTemplate));
        result.put("ar", stringSubstitutor.replace(arTemplate));

        return result;
    }
}
