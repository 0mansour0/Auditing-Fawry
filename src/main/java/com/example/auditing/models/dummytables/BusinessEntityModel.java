package com.example.auditing.models.dummytables;

import com.example.auditing.models.action.ActionModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "BUSINESS_ENTITY")
public class BusinessEntityModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long beId;

    @Column(name = "NAME")
    private String beName;

    @OneToMany(mappedBy = "be_name")
    @JsonIgnore
    private List<ActionModel> actions;

    public Long getBeId() {
        return beId;
    }

    public void setBeId(Long beId) {
        this.beId = beId;
    }

    public String getBeName() {
        return beName;
    }

    public void setBeName(String beName) {
        this.beName = beName;
    }

    public List<ActionModel> getActions() {
        return actions;
    }

    public void setActions(List<ActionModel> actions) {
        this.actions = actions;
    }
}
