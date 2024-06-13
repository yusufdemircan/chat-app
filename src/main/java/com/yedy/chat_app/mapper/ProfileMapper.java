package com.yedy.chat_app.mapper;

import com.yedy.chat_app.dto.ProfileDto;
import com.yedy.chat_app.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    //@Mapping(target = "id" , ignore = true)
    ProfileDto profileToProfileDto(Profile profile);
}
