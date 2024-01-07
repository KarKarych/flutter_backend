package com.example.flutter.controller;

import com.example.flutter.model.basic.ContentModel;
import com.example.flutter.model.create.AuthCreateModel;
import com.example.flutter.model.get.AuthModel;
import com.example.flutter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ContentModel<AuthModel> login(@RequestBody AuthCreateModel request) {
        var response = service.login(request);
        return ContentModel.okContentModel(response);
    }
}

