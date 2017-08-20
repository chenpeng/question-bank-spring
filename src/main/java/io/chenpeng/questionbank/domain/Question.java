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
 * 题目
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    private Integer id;

    // 录题人添加属性
    private Integer subjectId; // 学科ID

    private Integer gradeId; // 年级ID

    private Integer termId; // 学期ID

    private Integer regionId; // 地区ID

    private Integer year; // 年份

    private Integer typeId; // 题型ID

    private Integer score; // 分值

    private Integer sourceId; // 来源

    // 贴标签人添加属性
    // 数字维度
    private Integer difficulty; // 难度

    private Integer distinction; // 区分度

    private Integer recommendationIndex; // 推荐指数

    private Integer rating; // 评级

    // 内容维度
    private Integer knowledgeId; // 知识点ID

    private Integer examPointId; // 考点ID

    private Integer subjectCharacteristicId; // 学科特性点ID

    private Integer abilityId; // 能力ID

    private String keyword; // 关键字

    // 坐标维度
    private Integer editionId; // 版本ID

    private Integer chapterId; // 章节ID

    // 系统记录----统计属性
    private Integer costTime; // 做题时间

    private Integer browseCount; // 浏览次数

    private Integer quoteCount; // 引用次数

    private Integer examCount; // 练习次数

    private Double correctRate; // 正确率

    private Integer selectUserId; // 选题人

    private Integer entryUserId; // 录题人

    private Integer imageUserId; // 图形处理人

    private Integer checkUserId; // 审题人

    private Integer stickerUserId; // 贴标签人

    private Integer confirmUserId; // 确认人

    private Date storageTime; // 入库时间

    // 通用属性
    private Date createdTime;

    private Date updatedTime;

    private Integer createdUser;

    private Integer updatedUser;

    private Integer state;

    private Boolean deleted;

    private Integer versions;

    private String remarks;
}
