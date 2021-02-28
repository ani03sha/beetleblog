package com.beetleblog.app.repositories;


import com.beetleblog.app.domains.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
}
