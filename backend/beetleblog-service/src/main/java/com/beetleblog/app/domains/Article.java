package com.beetleblog.app.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Article {
    private String articleId;
    private String title;
    private String summary;
    private String content;
    private Date createdDate;
    private Date updatedDate;
    private User author;
}
