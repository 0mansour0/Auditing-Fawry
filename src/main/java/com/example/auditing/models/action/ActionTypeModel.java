package com.example.auditing.models.action;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "action_type")
public class ActionTypeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actiontype_id")
    private Long actionTypeId;

    @Column(name = "code")
    private String actionTypeCode;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "message_template_ar")
    private String messageTemplateAr;

    @Column(name = "message_template_en")
    private String messageTemplateEn;

    @OneToMany(mappedBy = "action_type")
    @JsonIgnore
    private List<ActionModel> actions;

    public Long getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(Long actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getMessageTemplateAr() {
        return messageTemplateAr;
    }

    public void setMessageTemplateAr(String messageTemplateAr) {
        this.messageTemplateAr = messageTemplateAr;
    }

    public String getMessageTemplateEn() {
        return messageTemplateEn;
    }

    public void setMessageTemplateEn(String messageTemplateEn) {
        this.messageTemplateEn = messageTemplateEn;
    }

    public List<ActionModel> getActions() {
        return actions;
    }

    public void setActions(List<ActionModel> actions) {
        this.actions = actions;
    }
}
