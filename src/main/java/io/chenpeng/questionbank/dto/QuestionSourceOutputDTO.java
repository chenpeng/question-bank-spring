package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.QuestionSource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "questionSourceOutputDTO", description = "题目来源相关接口输出参数")
public class QuestionSourceOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public QuestionSource convertToQuestionSource() {
        QuestionSourceOutputDTOConvert questionSourceOutputDTOConvert = new QuestionSourceOutputDTOConvert();
        QuestionSource convert = questionSourceOutputDTOConvert.convert(this);
        return convert;
    }

    public QuestionSourceOutputDTO convertFor(QuestionSource questionSource) {
        QuestionSourceOutputDTOConvert questionSourceOutputDTOConvert = new QuestionSourceOutputDTOConvert();
        QuestionSourceOutputDTO convert = questionSourceOutputDTOConvert.reverse().convert(questionSource);
        return convert;
    }

    private static class QuestionSourceOutputDTOConvert extends Converter<QuestionSourceOutputDTO, QuestionSource> {
        @Override
        protected QuestionSource doForward(QuestionSourceOutputDTO questionSourceOutputDTO) {
            QuestionSource questionSource = new QuestionSource();
            BeanUtils.copyProperties(questionSourceOutputDTO, questionSource);
            return questionSource;
        }

        @Override
        protected QuestionSourceOutputDTO doBackward(QuestionSource questionSource) {
            QuestionSourceOutputDTO questionSourceOutputDTO = new QuestionSourceOutputDTO();
            BeanUtils.copyProperties(questionSource, questionSourceOutputDTO);
            return questionSourceOutputDTO;
        }
    }
}
