package com.codebygaurav.lovable_ai.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

//@Entity
//@Table(name = "plan")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String stripePriceId;
    Integer maxProjects;
    Integer maxTokensPerDay;
    Integer maxPreviews;
    Boolean unlimitedAi; //unlimited access to LLM maxTokenPerDay if true

    @Column(columnDefinition = "jsonb")
    String features;

    Boolean active;
}
