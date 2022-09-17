package com.example.auditing.models.dummytables;

import com.example.auditing.models.action.ActionModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "USERS")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long userId;

    @Column(name = "NAME")
    private String userName;

    @Column(name = "EMAIL")
    private String userEmail;

    @Column(name = "TITLE")
    private String userTitle;

    @Column(name = "PHOTO")
    private String userPhoto;

    @OneToMany(mappedBy = "user_email")
    @JsonIgnore
    private List<ActionModel> actions;

}
