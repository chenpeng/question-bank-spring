package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Question;
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
@ApiModel(value = "questionInputDTO", description = "题目相关接口传入参数")
public class QuestionInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    public Question convertToQuestion() {
        QuestionInputDTOConvert questionInputDTOConvert = new QuestionInputDTOConvert();
        Question convert = questionInputDTOConvert.convert(this);
        return convert;
    }

    public QuestionInputDTO convertFor(Question question) {
        QuestionInputDTO.QuestionInputDTOConvert questionInputDTOConvert = new QuestionInputDTO.QuestionInputDTOConvert();
        QuestionInputDTO convert = questionInputDTOConvert.reverse().convert(question);
        return convert;
    }

    private static class QuestionInputDTOConvert extends Converter<QuestionInputDTO, Question> {
        @Override
        protected Question doForward(QuestionInputDTO questionInputDTO) {
            Question question = new Question();
            BeanUtils.copyProperties(questionInputDTO, question);
            return question;
        }

        @Override
        protected QuestionInputDTO doBackward(Question question) {
            QuestionInputDTO questionInputDTO = new QuestionInputDTO();
            BeanUtils.copyProperties(question, questionInputDTO);
            return questionInputDTO;
        }
    }
}
