package io.chenpeng.questionbank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
