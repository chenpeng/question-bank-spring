package io.chenpeng.questionbank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 题目
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionContent {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer question_id; // 题目对应id

    private String question_title; // 题干

    private String question_answer; // 答案

    private String question_analysis; // 解析
}
