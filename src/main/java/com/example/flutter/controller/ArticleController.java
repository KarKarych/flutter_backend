package com.example.flutter.controller;

import com.example.flutter.model.basic.ContentModel;
import com.example.flutter.model.filter.ArticleFilter;
import com.example.flutter.model.get.ArticleModel;
import com.example.flutter.service.ArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "bearer")
@RequiredArgsConstructor
@RequestMapping("/articles")
@RestController
public class ArticleController {

    private final ArticleService service;

    @GetMapping
    public ContentModel<List<ArticleModel>> getAll(@RequestParam(required = false) String searchQuery) {
        var response = service.getAll(new ArticleFilter(searchQuery));
        return ContentModel.okContentModel(response);
    }

    @GetMapping("/{id}")
    public ContentModel<ArticleModel> getById(@PathVariable UUID id) {
        var response = service.getById(id);
        return ContentModel.okContentModel(response);
    }
}

