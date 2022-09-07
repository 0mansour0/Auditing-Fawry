package com.example.auditing.models.param;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
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

    public Long getParamTypeId() {
        return paramTypeId;
    }

    public void setParamTypeId(Long paramTypeId) {
        this.paramTypeId = paramTypeId;
    }

    public String getParamTypeCode() {
        return paramTypeCode;
    }

    public void setParamTypeCode(String paramTypeCode) {
        this.paramTypeCode = paramTypeCode;
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

    public List<ParamModel> getParams() {
        return params;
    }

    public void setParams(List<ParamModel> params) {
        this.params = params;
    }
}
