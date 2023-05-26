package com.prokopenko.diploma.enteties;

import jakarta.persistence.*;

@Entity(name="variant")
@Table(name="variant")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="value")
    private String value;

    @Column(name="status",
            nullable = false)
    private Boolean status;

    @ManyToOne()
    @JoinColumn(
            name="task_id",
            referencedColumnName = "id"
    )
    private Task task;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
