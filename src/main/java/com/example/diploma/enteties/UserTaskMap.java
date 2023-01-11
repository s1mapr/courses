package com.example.diploma.enteties;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "userTask")
@Table(name = "user_task")
public class UserTaskMap {

    @EmbeddedId
    private UserTaskPK pk;
    @Column(name = "status")
    private Boolean status;
}
