package com.beetleblog.app.controllers;

import com.beetleblog.app.domains.Article;
import com.beetleblog.app.services.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/articles")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(
            value = "/createArticle",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new article", description = "This endpoint creates a new article in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Article created", content = @Content(schema = @Schema(implementation = Article.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "409", description = "Object already exists")
            }
    )
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        LOGGER.info("Saving article with title {} in the database", article.getTitle());
        Article persistedArticle = articleService.createArticle(article);
        if (persistedArticle != null) {
            return new ResponseEntity<>(persistedArticle, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
