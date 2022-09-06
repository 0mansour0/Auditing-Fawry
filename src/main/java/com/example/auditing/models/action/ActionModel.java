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
@Table(name = "action")
public class ActionModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Long actionId;

    @Column(name = "description_ar")
    private String descriptionAr;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "application_name", referencedColumnName = "name")
    private ApplicationModel application_name;

    @ManyToOne
    @JoinColumn(name = "action_type", referencedColumnName = "code")
    private ActionTypeModel action_type;

    @ManyToOne
    @JoinColumn(name = "be_name", referencedColumnName = "name")
    private BusinessEntityModel be_name;

    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "name")
    private UserModel user_name;

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

    public UserModel getUser_name() {
        return user_name;
    }

    public void setUser_name(UserModel user_name) {
        this.user_name = user_name;
    }

    public List<ParamModel> getParams() {
        return params;
    }

    public void setParams(List<ParamModel> params) {
        this.params = params;
    }
}
