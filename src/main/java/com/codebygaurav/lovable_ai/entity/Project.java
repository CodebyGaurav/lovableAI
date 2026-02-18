package com.codebygaurav.lovable_ai.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "project")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    User owner;

    Boolean isPublic;

    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;
}
