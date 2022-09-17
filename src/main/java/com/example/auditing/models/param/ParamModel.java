package com.example.auditing.models.param;

import com.example.auditing.models.action.ActionModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "PARAM")
public class ParamModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long paramId;

    @Column(name = "PARAM_VALUE")
    private String value;

    @ManyToOne
    @JoinColumn(name = "PARAM_TYPE", referencedColumnName = "CODE")
    private ParamTypeModel param_type;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ACTION_ID", referencedColumnName = "ID")
    private ActionModel action_id;

}
