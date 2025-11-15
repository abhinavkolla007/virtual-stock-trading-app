package com.abhi.virtualstock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.abhi.virtualstock.model.NewsArticle;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewsListResponse {
    private List<NewsArticle> articles;
}
