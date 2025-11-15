package com.abhi.virtualstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.virtualstock.model.NewsArticleComment;

@Repository
public interface NewsArticleCommentRepository extends JpaRepository<NewsArticleComment, Long> {
}
