package io.chenpeng.questionbank.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "questionSourceSearchDTO", description = "题目来源查询接口传入参数")
public class QuestionSourceSearchDTO implements Serializable {
    @NotNull(message = "请输入pageIndex参数")
    @ApiModelProperty(name = "pageIndex", value = "当前页", dataType = "Integer", required = true, position = 0, example = "1")
    private Integer pageIndex;

    @NotNull(message = "请输入pageSize参数")
    @ApiModelProperty(name = "pageSize", value = "每页条数", dataType = "Integer", required = true, position = 1, example = "10")
    private Integer pageSize;
}
