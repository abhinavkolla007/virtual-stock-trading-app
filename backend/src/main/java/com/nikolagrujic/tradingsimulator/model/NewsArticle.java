package com.nikolagrujic.tradingsimulator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class NewsArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String author;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    @Column(length = 2048)
    private String description;

    @NotNull
    @NotBlank
    @Column(length = 1024)
    private String url;

    @NotNull
    @NotBlank
    @Column(length = 1024)
    private String urlToImage;

    @NotNull
    private LocalDateTime publishedAt;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "news_article_like",
            joinColumns = { @JoinColumn(name = "news_article_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> likedBy = new HashSet<>();

    @Transient
    public int getNumberOfLikes() {
        return likedBy.size();
    }

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsArticleComment> comments = new ArrayList<>();
}
