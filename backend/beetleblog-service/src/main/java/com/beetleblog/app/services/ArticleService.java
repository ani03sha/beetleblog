package com.beetleblog.app.services;

import com.beetleblog.app.domains.Article;

import java.util.List;

public interface ArticleService {

    Article createArticle(Article article);

    List<Article> getArticles();
}
