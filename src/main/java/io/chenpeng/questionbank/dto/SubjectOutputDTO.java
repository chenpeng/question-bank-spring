package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Subject;
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
@ApiModel(value = "subjectOutputDTO", description = "学科相关接口输出参数")
public class SubjectOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public Subject convertToSubject() {
        SubjectOutputDTOConvert subjectOutputDTOConvert = new SubjectOutputDTOConvert();
        Subject convert = subjectOutputDTOConvert.convert(this);
        return convert;
    }

    public SubjectOutputDTO convertFor(Subject subject) {
        SubjectOutputDTOConvert subjectOutputDTOConvert = new SubjectOutputDTOConvert();
        SubjectOutputDTO convert = subjectOutputDTOConvert.reverse().convert(subject);
        return convert;
    }

    private static class SubjectOutputDTOConvert extends Converter<SubjectOutputDTO, Subject> {
        @Override
        protected Subject doForward(SubjectOutputDTO subjectOutputDTO) {
            Subject subject = new Subject();
            BeanUtils.copyProperties(subjectOutputDTO, subject);
            return subject;
        }

        @Override
        protected SubjectOutputDTO doBackward(Subject subject) {
            SubjectOutputDTO subjectOutputDTO = new SubjectOutputDTO();
            BeanUtils.copyProperties(subject, subjectOutputDTO);
            return subjectOutputDTO;
        }
    }
}
