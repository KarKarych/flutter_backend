package com.example.flutter.mapper;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.get.UserModel;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserModel toModel(FlutterUser user);
}
