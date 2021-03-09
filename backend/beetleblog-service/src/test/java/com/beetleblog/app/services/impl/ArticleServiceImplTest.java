package com.beetleblog.app.services.impl;

import com.beetleblog.app.domains.Article;
import com.beetleblog.app.repositories.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    private Article article;

    @BeforeEach
    void setUp() {
        article = new Article(
                "101",
                "Test Article",
                "Test Title",
                "Test Content",
                Instant.now().toString(),
                Instant.now().toString(),
                "Test User");
    }

    @Test
    void testCreateArticle() {

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

    @Test
    void testUpdateArticle() {

        when(articleRepository.save(any(Article.class))).thenReturn(article);

        Article persistedArticle = articleService.createArticle(article);
        persistedArticle.setTitle("Updated Test Title");
        persistedArticle.setContent("Updated Test Content");
        persistedArticle.setUsername("Updated Test User");
        persistedArticle.setSummary("Updated Test Summary");

        when(articleRepository.findById(anyString())).thenReturn(Optional.of(persistedArticle));

        Article updatedPersistedArticle = articleService.updateArticle(persistedArticle, persistedArticle.getId());

        assertThat(persistedArticle.getId()).isEqualTo(updatedPersistedArticle.getId());
        assertThat(persistedArticle.getTitle()).isEqualTo(updatedPersistedArticle.getTitle());
        assertThat(persistedArticle.getSummary()).isEqualTo(updatedPersistedArticle.getSummary());
        assertThat(persistedArticle.getContent()).isEqualTo(updatedPersistedArticle.getContent());
        assertThat(persistedArticle.getUsername()).isEqualTo(updatedPersistedArticle.getUsername());
    }

    @Test
    void testDeleteArticle() {
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        Article persistedArticle = articleService.createArticle(article);

        when(articleRepository.findById(anyString())).thenReturn(Optional.of(persistedArticle));

        Article deletedPersistedArticle = articleService.deleteArticle(persistedArticle.getId());

        assertThat(persistedArticle.getId()).isEqualTo(deletedPersistedArticle.getId());
        assertThat(persistedArticle.getTitle()).isEqualTo(deletedPersistedArticle.getTitle());
        assertThat(persistedArticle.getSummary()).isEqualTo(deletedPersistedArticle.getSummary());
        assertThat(persistedArticle.getContent()).isEqualTo(deletedPersistedArticle.getContent());
        assertThat(persistedArticle.getUsername()).isEqualTo(deletedPersistedArticle.getUsername());
    }
}