package com.example.auditing.models.param;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "PARAM_TYPE")
public class ParamTypeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long paramTypeId;

    @Column(name = "CODE")
    private String paramTypeCode;

    @Column(name = "NAME_AR")
    private String nameAr;

    @Column(name = "NAME_EN")
    private String nameEn;

    @OneToMany(mappedBy = "param_type")
    @JsonIgnore
    private List<ParamModel> params;

}
