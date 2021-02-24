package com.beetleblog.app.domains;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private String articleId;
    private String title;
    private String summary;
    private String content;
    private Date createdDate;
    private Date updatedDate;
    private User author;
}
