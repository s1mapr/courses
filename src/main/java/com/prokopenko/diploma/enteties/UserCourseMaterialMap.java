package com.prokopenko.diploma.enteties;

import com.prokopenko.diploma.dto.UserCourseMaterialPK;import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "userMaterial")
@Table(name = "user_material")
public class UserCourseMaterialMap {

    @EmbeddedId
    private UserCourseMaterialPK pk;
    @Column(name = "status")
    private Boolean status;

    @Column(name = "progress")
    private Double progress;

    public UserCourseMaterialMap() {
    }

    public UserCourseMaterialMap(UserCourseMaterialPK pk, Boolean status, Double progress) {
        this.pk = pk;
        this.status = status;
        this.progress = progress;
    }



    public UserCourseMaterialPK getPk() {
        return pk;
    }

    public void setPk(UserCourseMaterialPK pk) {
        this.pk = pk;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }
}