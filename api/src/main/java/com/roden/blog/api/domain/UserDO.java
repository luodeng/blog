package com.roden.blog.api.domain;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class UserDO {
    private Integer id;
    private String userName;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
