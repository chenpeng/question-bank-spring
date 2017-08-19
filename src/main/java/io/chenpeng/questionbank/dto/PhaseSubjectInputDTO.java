package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.PhaseSubject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "phaseSubjectInputDTO", description = "学段学科相关接口传入参数")
public class PhaseSubjectInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotNull(message = "请输入phaseId参数")
    @ApiModelProperty(name = "phaseId", value = "学段ID", dataType = "int", required = true, example = "1")
    private Integer phaseId;

    @NotNull(message = "请输入subjectId参数")
    @ApiModelProperty(name = "subjectId", value = "学科ID", dataType = "int", required = true, example = "1")
    private Integer subjectId;

    public PhaseSubject convertToPhaseSubject() {
        PhaseSubjectInputDTOConvert phaseSubjectInputDTOConvert = new PhaseSubjectInputDTOConvert();
        PhaseSubject convert = phaseSubjectInputDTOConvert.convert(this);
        return convert;
    }

    public PhaseSubjectInputDTO convertFor(PhaseSubject phaseSubject) {
        PhaseSubjectInputDTO.PhaseSubjectInputDTOConvert phaseSubjectInputDTOConvert = new PhaseSubjectInputDTO.PhaseSubjectInputDTOConvert();
        PhaseSubjectInputDTO convert = phaseSubjectInputDTOConvert.reverse().convert(phaseSubject);
        return convert;
    }

    private static class PhaseSubjectInputDTOConvert extends Converter<PhaseSubjectInputDTO, PhaseSubject> {
        @Override
        protected PhaseSubject doForward(PhaseSubjectInputDTO phaseSubjectInputDTO) {
            PhaseSubject phaseSubject = new PhaseSubject();
            BeanUtils.copyProperties(phaseSubjectInputDTO, phaseSubject);
            return phaseSubject;
        }

        @Override
        protected PhaseSubjectInputDTO doBackward(PhaseSubject phaseSubject) {
            PhaseSubjectInputDTO phaseSubjectInputDTO = new PhaseSubjectInputDTO();
            BeanUtils.copyProperties(phaseSubject, phaseSubjectInputDTO);
            return phaseSubjectInputDTO;
        }
    }
}
