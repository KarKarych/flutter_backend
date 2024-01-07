package com.example.flutter.service.impl;

import com.example.flutter.entity.Article;
import com.example.flutter.mapper.ArticleMapper;
import com.example.flutter.model.filter.ArticleFilter;
import com.example.flutter.model.get.ArticleModel;
import com.example.flutter.repository.ArticleRepository;
import com.example.flutter.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.flutter.util.Constants.SEARCH_PATTERN;
import static com.example.flutter.util.exception.NotFoundException.Code.ARTICLE_NOT_FOUND;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public List<ArticleModel> getAll(ArticleFilter filterRequest) {
        var query = filterRequest.searchQuery();

        List<Article> articles;
        if (query == null) {
            articles = articleRepository.findAllWithCategories();
        } else {
            articles = articleRepository.findBySearchQuery(SEARCH_PATTERN.formatted(query.strip()));
        }

        return articles.stream()
                .map(articleMapper::toModel)
                .toList();
    }

    @Override
    public ArticleModel getById(UUID id) {
        return Optional.of(id)
                .flatMap(articleRepository::findById)
                .map(articleMapper::toModel)
                .orElseThrow(ARTICLE_NOT_FOUND::get);
    }
}
