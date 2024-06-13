package com.yedy.chat_app.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public abstract class BaseEntity {

    private boolean deleted;

    @CreatedDate
    @Field("created_at")
    private Date createdAt = new Date();

    @Field("created_by")
    private String createdBy;

    @LastModifiedDate
    @Field("updated_at")
    private Date updatedAt;

    @Field("updated_by")
    private String updatedBy;
}
