package com.example.diploma.enteties;

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

    @ManyToOne
    @JoinColumn(
            name="task_id",
            referencedColumnName = "id"
    )
    private Task task;
}
