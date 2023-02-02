package com.example.diploma.enteties;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity(name = "userTask")
@Table(name = "user_task")
public class UserTaskMap {

    @EmbeddedId
    private UserTaskPK pk;
    @Column(name = "status")
    private Boolean status;


    public UserTaskMap() {
    }

    public UserTaskMap(UserTaskPK pk, Boolean status) {
        this.pk = pk;
        this.status = status;
    }

    public UserTaskPK getPk() {
        return pk;
    }

    public void setPk(UserTaskPK pk) {
        this.pk = pk;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
