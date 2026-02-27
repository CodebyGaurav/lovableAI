package com.codebygaurav.lovable_ai.entity;



import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

//@Entity
//@Table(name = "project_file")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

    @Column(unique = true)
    String path;

    String minioObjectKey;

    Instant createdAt;

    Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    User updatedBy;

}