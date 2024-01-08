package com.example.flutter.service;

import com.example.flutter.model.filter.ArticleFilter;
import com.example.flutter.model.get.ArticleModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface ArticleService {

    List<ArticleModel> getAll(@Valid @NotNull ArticleFilter filterRequest);

    ArticleModel getById(@NotNull UUID id);
}
