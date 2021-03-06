package com.beetleblog.app.services.impl;

import com.beetleblog.app.domains.Article;
import com.beetleblog.app.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    void testCreateArticle() {
        Article article = new Article(
                UUID.randomUUID().toString(),
                "Test Article",
                "Test Title",
                "Test Content",
                Instant.now().toString(),
                Instant.now().toString(),
                "Test User");

        when(articleRepository.save(any(Article.class))).thenReturn(article);

        Article persistedArticle = articleService.createArticle(article);

        assertThat(persistedArticle.getId()).isEqualTo(article.getId());
        assertThat(persistedArticle.getTitle()).isEqualTo(article.getTitle());
        assertThat(persistedArticle.getSummary()).isEqualTo(article.getSummary());
        assertThat(persistedArticle.getContent()).isEqualTo(article.getContent());
        assertThat(persistedArticle.getCreatedDate()).isEqualTo(article.getCreatedDate());
        assertThat(persistedArticle.getUpdatedDate()).isEqualTo(article.getUpdatedDate());
        assertThat(persistedArticle.getUsername()).isEqualTo(article.getUsername());
    }

    @Test
    void testGetArticles() {
        List<Article> articleList = articleService.getArticles();
    }
}