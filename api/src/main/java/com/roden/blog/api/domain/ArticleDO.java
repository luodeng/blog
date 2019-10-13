package com.roden.blog.api.domain;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDO {
    private Integer id;
    private String content;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
