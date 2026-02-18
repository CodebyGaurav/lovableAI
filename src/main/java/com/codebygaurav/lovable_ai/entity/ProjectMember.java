package com.codebygaurav.lovable_ai.entity;

import com.codebygaurav.lovable_ai.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "project_member")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {

    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId")
    Project project;

    @ManyToOne
    @MapsId("userId")
    User user;

    @Enumerated(EnumType.STRING)
    ProjectRole projectRole;

    @ManyToOne
    @JoinColumn(name = "invited_by")
    User invitedBy;

    Instant invitedAt;
}
