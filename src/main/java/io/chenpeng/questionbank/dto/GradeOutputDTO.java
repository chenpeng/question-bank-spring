package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Grade;
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
@ApiModel(value = "gradeOutputDTO", description = "年级相关接口输出参数")
public class GradeOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public Grade convertToGrade() {
        GradeOutputDTOConvert gradeOutputDTOConvert = new GradeOutputDTOConvert();
        Grade convert = gradeOutputDTOConvert.convert(this);
        return convert;
    }

    public GradeOutputDTO convertFor(Grade grade) {
        GradeOutputDTOConvert gradeOutputDTOConvert = new GradeOutputDTOConvert();
        GradeOutputDTO convert = gradeOutputDTOConvert.reverse().convert(grade);
        return convert;
    }

    private static class GradeOutputDTOConvert extends Converter<GradeOutputDTO, Grade> {
        @Override
        protected Grade doForward(GradeOutputDTO gradeOutputDTO) {
            Grade grade = new Grade();
            BeanUtils.copyProperties(gradeOutputDTO, grade);
            return grade;
        }

        @Override
        protected GradeOutputDTO doBackward(Grade grade) {
            GradeOutputDTO gradeOutputDTO = new GradeOutputDTO();
            BeanUtils.copyProperties(grade, gradeOutputDTO);
            return gradeOutputDTO;
        }
    }
}
