package com.example.auditing.models.action;

import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.models.param.ParamModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
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

}
