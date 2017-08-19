package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Grade;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "gradeInputDTO", description = "年级相关接口传入参数")
public class GradeInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    @NotNull(message = "请输入phaseId参数")
    @ApiModelProperty(name = "phaseId", value = "学段ID", dataType = "int", required = true, example = "1")
    private Integer phaseId;

    public Grade convertToGrade() {
        GradeInputDTOConvert gradeInputDTOConvert = new GradeInputDTOConvert();
        Grade convert = gradeInputDTOConvert.convert(this);
        return convert;
    }

    public GradeInputDTO convertFor(Grade grade) {
        GradeInputDTO.GradeInputDTOConvert gradeInputDTOConvert = new GradeInputDTO.GradeInputDTOConvert();
        GradeInputDTO convert = gradeInputDTOConvert.reverse().convert(grade);
        return convert;
    }

    private static class GradeInputDTOConvert extends Converter<GradeInputDTO, Grade> {
        @Override
        protected Grade doForward(GradeInputDTO gradeInputDTO) {
            Grade grade = new Grade();
            BeanUtils.copyProperties(gradeInputDTO, grade);
            return grade;
        }

        @Override
        protected GradeInputDTO doBackward(Grade grade) {
            GradeInputDTO gradeInputDTO = new GradeInputDTO();
            BeanUtils.copyProperties(grade, gradeInputDTO);
            return gradeInputDTO;
        }
    }
}
