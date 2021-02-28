package com.beetleblog.app.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Article {

    @Id
    private String id;

    private String title;
    private String summary;
    private String content;
    private String createdDate;
    private String updatedDate;
    private String username;
}
