package com.example.flutter.mapper;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.get.UserModel;
import com.example.flutter.model.update.UserUpdateModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface UserMapper {

    UserModel toModel(FlutterUser user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "login", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "pictureUrl", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void merge(UserUpdateModel model, @MappingTarget FlutterUser user);
}
