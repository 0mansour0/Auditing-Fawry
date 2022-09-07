package com.example.auditing.models.action;

import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.models.param.ParamModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ACTION")
public class ActionModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long actionId;

    @Column(name = "DESCRIPTION_AR")
    private String descriptionAr;

    @Column(name = "DESCRIPTION_EN")
    private String descriptionEn;

    @Column(name = "TIME")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_NAME", referencedColumnName = "NAME")
    private ApplicationModel application_name;

    @ManyToOne
    @JoinColumn(name = "ACTION_TYPE", referencedColumnName = "CODE")
    private ActionTypeModel action_type;

    @ManyToOne
    @JoinColumn(name = "BE_NAME", referencedColumnName = "NAME")
    private BusinessEntityModel be_name;

    @ManyToOne
    @JoinColumn(name = "USER_EMAIL", referencedColumnName = "EMAIL")
    private UserModel user_email;

    @OneToMany(mappedBy = "action_id")
    @JsonIgnore
    private List<ParamModel> params;

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ApplicationModel getApplication_name() {
        return application_name;
    }

    public void setApplication_name(ApplicationModel application_name) {
        this.application_name = application_name;
    }

    public ActionTypeModel getAction_type() {
        return action_type;
    }

    public void setAction_type(ActionTypeModel action_type) {
        this.action_type = action_type;
    }

    public BusinessEntityModel getBe_name() {
        return be_name;
    }

    public void setBe_name(BusinessEntityModel be_name) {
        this.be_name = be_name;
    }

    public UserModel getUser_email() {
        return user_email;
    }

    public void setUser_email(UserModel user_email) {
        this.user_email = user_email;
    }

    public List<ParamModel> getParams() {
        return params;
    }

    public void setParams(List<ParamModel> params) {
        this.params = params;
    }
}
