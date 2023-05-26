package com.prokopenko.diploma.enteties;

import com.prokopenko.diploma.dto.Complexity;
import com.prokopenko.diploma.dto.CourseStatus;
import com.prokopenko.diploma.dto.Subjects;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "course")
@Table(name = "course")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(name = "course_title", nullable = false)
  private String courseTitle;

  @Column(name = "price", nullable = false)
  private Double price;

  @Column(name = "teacher_name", nullable = false)
  private String teacherName;

  @Column(name = "description")
  private String description;

  @Column(name = "intro_url")
  private String introUrl;

  @Column(name = "picture_url", columnDefinition = "TEXT(1000)")
  private String pictureUrl;

  @Column(name = "complexity", columnDefinition = "VARCHAR(20)")
  @Enumerated(EnumType.STRING)
  private Complexity complexity;

  @Column(name = "date")
  private LocalDate date = LocalDate.now();

  @Column(name = "state", columnDefinition = "VARCHAR(20)")
  @Enumerated(EnumType.STRING)
  private CourseStatus state = CourseStatus.PLANNED;

  @Column(name = "subject", columnDefinition = "VARCHAR(20)")
  @Enumerated(EnumType.STRING)
  private Subjects subject;


  @Override
  public String toString() {
    return "Course{"
        + "id="
        + id
        + ", courseTitle='"
        + courseTitle
        + '\''
        + ", price="
        + price
        + ", teacherName='"
        + teacherName
        + '\''
        + '}';
  }
}
