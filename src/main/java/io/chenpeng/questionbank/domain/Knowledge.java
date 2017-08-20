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
 * 知识体系
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Knowledge {
    @Id
    @GeneratedValue
    private Integer id; //主键ID

    private String name; //知识点名称

    private Integer pId; //父节点ID

    private Integer phaseSubjectId; // 学段学科ID

    private Integer sort; // 排序用字段

    private Date createdTime;

    private Date updatedTime;

    private Integer createdUser;

    private Integer updatedUser;

    private Integer state;

    private Boolean deleted;

    private Integer versions;

    private String remarks;
}
