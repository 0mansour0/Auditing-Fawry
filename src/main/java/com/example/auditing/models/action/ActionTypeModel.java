package com.example.auditing.models.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "ACTION_TYPE")
public class ActionTypeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long actionTypeId;

    @Column(name = "CODE")
    private String actionTypeCode;

    @Column(name = "NAME_AR")
    private String nameAr;

    @Column(name = "NAME_EN")
    private String nameEn;

    @Column(name = "MESSAGE_TEMPLATE_AR")
    private String messageTemplateAr;

    @Column(name = "MESSAGE_TEMPLATE_EN")
    private String messageTemplateEn;

    @OneToMany(mappedBy = "action_type")
    @JsonIgnore
    private List<ActionModel> actions;

}
