package com.example.appnews.entity.template;

import com.example.appnews.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.sql.Timestamp;

@MappedSuperclass
@Data
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;



    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @JoinColumn(updatable = false)
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
}
