package com.codebygaurav.lovable_ai.mapper;

import com.codebygaurav.lovable_ai.dto.member.MemberResponse;
import com.codebygaurav.lovable_ai.entity.ProjectMember;
import com.codebygaurav.lovable_ai.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "invitedAt", source = "invitedAt")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);
}
