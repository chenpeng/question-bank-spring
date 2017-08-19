package io.chenpeng.questionbank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 学期
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Term {
    @Id
    @GeneratedValue
    private Integer id; // 主键ID

    private String name; // 学期名称

    private Date createdTime;

    private Date updatedTime;

    private Integer createdUser;

    private Integer updatedUser;

    private Integer state;

    private Boolean deleted;

    private Integer versions;

    private String remarks;
}
