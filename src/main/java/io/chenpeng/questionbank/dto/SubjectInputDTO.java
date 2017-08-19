package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Subject;
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
@ApiModel(value = "subjectInputDTO", description = "学科相关接口传入参数")
public class SubjectInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    public Subject convertToSubject() {
        SubjectInputDTOConvert subjectInputDTOConvert = new SubjectInputDTOConvert();
        Subject convert = subjectInputDTOConvert.convert(this);
        return convert;
    }

    public SubjectInputDTO convertFor(Subject subject) {
        SubjectInputDTO.SubjectInputDTOConvert subjectInputDTOConvert = new SubjectInputDTO.SubjectInputDTOConvert();
        SubjectInputDTO convert = subjectInputDTOConvert.reverse().convert(subject);
        return convert;
    }

    private static class SubjectInputDTOConvert extends Converter<SubjectInputDTO, Subject> {
        @Override
        protected Subject doForward(SubjectInputDTO subjectInputDTO) {
            Subject subject = new Subject();
            BeanUtils.copyProperties(subjectInputDTO, subject);
            return subject;
        }

        @Override
        protected SubjectInputDTO doBackward(Subject subject) {
            SubjectInputDTO subjectInputDTO = new SubjectInputDTO();
            BeanUtils.copyProperties(subject, subjectInputDTO);
            return subjectInputDTO;
        }
    }
}
