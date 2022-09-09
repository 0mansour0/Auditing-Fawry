package com.example.auditing.models.dummytables;

import com.example.auditing.models.action.ActionModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "APPLICATION")
public class ApplicationModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long appId;

    @Column(name = "NAME")
    private String appName;

    @OneToMany(mappedBy = "application_name")
    @JsonIgnore
    private List<ActionModel> actions;

}
