package com.example.auditing.models.dummytables;

import com.example.auditing.models.action.ActionModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
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

}
