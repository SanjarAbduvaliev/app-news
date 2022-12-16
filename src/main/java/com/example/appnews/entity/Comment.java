package com.example.appnews.entity;

import com.example.appnews.entity.template.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends AbstractEntity {
    @Column(nullable = false,columnDefinition = "text")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
