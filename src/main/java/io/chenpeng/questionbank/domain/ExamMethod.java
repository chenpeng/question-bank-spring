package io.chenpeng.questionbank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 考法体系
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamMethod {
    @Id
    @GeneratedValue
    private Integer id; //主键ID

    private String name; // 考法名称

    private Integer pId; //父节点ID
}