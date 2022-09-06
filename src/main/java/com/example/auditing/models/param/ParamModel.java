package com.example.auditing.models.param;

import com.example.auditing.models.action.ActionModel;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "param")
public class ParamModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id")
    private Long paramId;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "param_type", referencedColumnName = "code")
    private ParamTypeModel param_type;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "action_id", referencedColumnName = "action_id")
    private ActionModel action_id;

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ParamTypeModel getParam_type() {
        return param_type;
    }

    public void setParam_type(ParamTypeModel param_type) {
        this.param_type = param_type;
    }

    public ActionModel getAction_id() {
        return action_id;
    }

    public void setAction_id(ActionModel action_id) {
        this.action_id = action_id;
    }
}
