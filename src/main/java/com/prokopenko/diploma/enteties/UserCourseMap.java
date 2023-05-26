package com.prokopenko.diploma.enteties;

import com.prokopenko.diploma.dto.UserCoursePK;import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity(name = "userСourse")
@Table(name = "user_course")
public class UserCourseMap {

  @EmbeddedId
  private UserCoursePK pk;

  @Column(name = "status")
  private Boolean status;

  @Column(name = "progress")
  private Double progress;

  @Column(name = "finish_date")
  private LocalDate date;

  public UserCourseMap() {}

  public UserCourseMap(UserCoursePK pk, Boolean status, Double progress) {
    this.pk = pk;
    this.status = status;
    this.progress = progress;
  }

  public UserCourseMap(UserCoursePK pk) {
    this.pk = pk;
  }

  public UserCoursePK getPk() {
    return pk;
  }

  public void setPk(UserCoursePK pk) {
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

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
