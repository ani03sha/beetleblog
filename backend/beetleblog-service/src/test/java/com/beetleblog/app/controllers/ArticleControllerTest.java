package com.beetleblog.app.controllers;

import com.beetleblog.app.domains.Article;
import com.beetleblog.app.services.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ArticleControllerTest {

    @InjectMocks
    private ArticleController articleController;

    @Mock
    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

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

        when(articleService.createArticle(any(Article.class))).thenReturn(article);

        ResponseEntity<Article> responseEntity = articleController.createArticle(article);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getTitle()).isEqualTo(article.getTitle());
    }

    @Test
    void testGetArticles() {
        ResponseEntity<List<Article>> responseEntity = articleController.getArticles();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    void testUpdateArticle() {
        Article article = new Article(
                "101",
                "Test Article",
                "Test Title",
                "Test Content",
                Instant.now().toString(),
                Instant.now().toString(),
                "Test User");

        when(articleService.updateArticle(any(Article.class), anyString())).thenReturn(article);

        ResponseEntity<Article> responseEntity = articleController.updateArticle(article, article.getId());

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getTitle()).isEqualTo(article.getTitle());
    }

    @Test
    void testDeleteArticle() {
        Article article = new Article(
                "101",
                "Test Article",
                "Test Title",
                "Test Content",
                Instant.now().toString(),
                Instant.now().toString(),
                "Test User");

        when(articleService.deleteArticle(anyString())).thenReturn(article);

        ResponseEntity<Article> responseEntity = articleController.deleteArticle(article.getId());

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}