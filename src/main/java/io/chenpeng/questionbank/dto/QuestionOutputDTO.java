package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Question;
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
@ApiModel(value = "questionOutputDTO", description = "题目相关接口输出参数")
public class QuestionOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public Question convertToQuestion() {
        QuestionOutputDTOConvert questionOutputDTOConvert = new QuestionOutputDTOConvert();
        Question convert = questionOutputDTOConvert.convert(this);
        return convert;
    }

    public QuestionOutputDTO convertFor(Question question) {
        QuestionOutputDTOConvert questionOutputDTOConvert = new QuestionOutputDTOConvert();
        QuestionOutputDTO convert = questionOutputDTOConvert.reverse().convert(question);
        return convert;
    }

    private static class QuestionOutputDTOConvert extends Converter<QuestionOutputDTO, Question> {
        @Override
        protected Question doForward(QuestionOutputDTO questionOutputDTO) {
            Question question = new Question();
            BeanUtils.copyProperties(questionOutputDTO, question);
            return question;
        }

        @Override
        protected QuestionOutputDTO doBackward(Question question) {
            QuestionOutputDTO questionOutputDTO = new QuestionOutputDTO();
            BeanUtils.copyProperties(question, questionOutputDTO);
            return questionOutputDTO;
        }
    }
}
