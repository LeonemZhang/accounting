package com.leonem.accounting.module.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author 张宗涵
 * @date 2023/9/20
 */
@Entity
@Getter
@Setter
@FieldNameConstants
@EntityListeners(AuditingEntityListener.class)
public class DbUserInfo {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账号
     */
    @Column(length = 512, unique = true)
    private String loginName;

    /**
     * 密码
     */
    @JsonIgnore
    @Column(length = 512)
    private String password;

    /**
     * 用户名
     */
    @Column(length = 512)
    private String userName;

    /**
     * 创建时间
     **/
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    private LocalDateTime updateTime;
}
