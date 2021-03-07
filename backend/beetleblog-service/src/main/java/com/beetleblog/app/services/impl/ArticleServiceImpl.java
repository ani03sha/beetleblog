package com.beetleblog.app.services.impl;

import com.beetleblog.app.domains.Article;
import com.beetleblog.app.exceptions.CustomMongoException;
import com.beetleblog.app.repositories.ArticleRepository;
import com.beetleblog.app.services.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(Article article) {
        LOGGER.info("Saving the article with title {} in the database", article.getTitle());
        try {
            Article persistedArticle = articleRepository.save(article);
            LOGGER.info("Article with title: {} saved successfully", persistedArticle.getTitle());
            return persistedArticle;
        } catch (UncategorizedMongoDbException e) {
            LOGGER.error("Article could not be saved in the database", e);
            throw new CustomMongoException(e.getMessage());
        }
    }

    public List<Article> getArticles() {
        LOGGER.info("Fetching all articles");
        try {
            List<Article> articles = articleRepository.findAll();
            LOGGER.info("Articles retrieved successfully");
            return articles;
        } catch (UncategorizedMongoDbException e) {
            LOGGER.error("Not able to fetch articles from database", e);
            throw new CustomMongoException(e.getMessage());
        }
    }

    public Article updateArticle(Article article, String id) {
        LOGGER.info("Updating the article with title {} in the database", article.getTitle());
        try {
            LOGGER.info("abc {}",articleRepository.findById(id));
            if (articleRepository.findById(id).isEmpty()) {
                return null;
            }
            Article persistedArticle = articleRepository.findById(id).get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            persistedArticle.setId(id);
            persistedArticle.setTitle(article.getTitle());
            persistedArticle.setSummary(article.getSummary());
            persistedArticle.setContent(article.getContent());
            persistedArticle.setUpdatedDate(formatter.format(LocalDateTime.now()));
            persistedArticle.setUsername(article.getUsername());
            articleRepository.save(persistedArticle);
            LOGGER.info("Article with title: {} updated successfully", persistedArticle.getTitle());
            return persistedArticle;
        } catch (UncategorizedMongoDbException e) {
            LOGGER.error("Article could not be updated in the database", e);
            throw new CustomMongoException(e.getMessage());
        }
    }
}
