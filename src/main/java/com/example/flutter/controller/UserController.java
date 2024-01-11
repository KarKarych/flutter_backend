package com.example.flutter.controller;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.basic.ContentModel;
import com.example.flutter.model.get.UserModel;
import com.example.flutter.model.update.UserUpdateModel;
import com.example.flutter.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SecurityRequirement(name = "bearer")
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService service;

    @GetMapping("/user-info")
    public ContentModel<UserModel> getById(@AuthenticationPrincipal FlutterUser currentUser) {
        var response = service.getByPrincipal(currentUser);
        return ContentModel.okContentModel(response);
    }

    @GetMapping("/{id}")
    public ContentModel<UserModel> getById(@PathVariable UUID id) {
        var response = service.getById(id);
        return ContentModel.okContentModel(response);
    }

    @PutMapping("/user-info")
    public ContentModel<UserModel> update(
            @AuthenticationPrincipal FlutterUser currentUser,
            @RequestBody UserUpdateModel request
    ) {
        var response = service.update(currentUser, request);
        return ContentModel.okContentModel(response);
    }
}

