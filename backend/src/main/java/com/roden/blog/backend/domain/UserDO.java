package com.roden.blog.backend.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "user")
public class UserDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "gmt_create")
    private LocalDateTime gmtCreate;
    @Column(name = "gmt_modified")
    private LocalDateTime gmtModified;

}
