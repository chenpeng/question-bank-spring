package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.QuestionSource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "questionSourceInputDTO", description = "题目来源相关接口传入参数")
public class QuestionSourceInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    public QuestionSource convertToQuestionSource() {
        QuestionSourceInputDTOConvert questionSourceInputDTOConvert = new QuestionSourceInputDTOConvert();
        QuestionSource convert = questionSourceInputDTOConvert.convert(this);
        return convert;
    }

    public QuestionSourceInputDTO convertFor(QuestionSource questionSource) {
        QuestionSourceInputDTO.QuestionSourceInputDTOConvert questionSourceInputDTOConvert = new QuestionSourceInputDTO.QuestionSourceInputDTOConvert();
        QuestionSourceInputDTO convert = questionSourceInputDTOConvert.reverse().convert(questionSource);
        return convert;
    }

    private static class QuestionSourceInputDTOConvert extends Converter<QuestionSourceInputDTO, QuestionSource> {
        @Override
        protected QuestionSource doForward(QuestionSourceInputDTO questionSourceInputDTO) {
            QuestionSource questionSource = new QuestionSource();
            BeanUtils.copyProperties(questionSourceInputDTO, questionSource);
            return questionSource;
        }

        @Override
        protected QuestionSourceInputDTO doBackward(QuestionSource questionSource) {
            QuestionSourceInputDTO questionSourceInputDTO = new QuestionSourceInputDTO();
            BeanUtils.copyProperties(questionSource, questionSourceInputDTO);
            return questionSourceInputDTO;
        }
    }
}
