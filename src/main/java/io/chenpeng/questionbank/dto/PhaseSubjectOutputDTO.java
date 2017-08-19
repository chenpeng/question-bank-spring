package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.PhaseSubject;
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
@ApiModel(value = "phaseSubjectOutputDTO", description = "学段学科相关接口输出参数")
public class PhaseSubjectOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public PhaseSubject convertToPhaseSubject() {
        PhaseSubjectOutputDTOConvert phaseSubjectOutputDTOConvert = new PhaseSubjectOutputDTOConvert();
        PhaseSubject convert = phaseSubjectOutputDTOConvert.convert(this);
        return convert;
    }

    public PhaseSubjectOutputDTO convertFor(PhaseSubject phaseSubject) {
        PhaseSubjectOutputDTOConvert phaseSubjectOutputDTOConvert = new PhaseSubjectOutputDTOConvert();
        PhaseSubjectOutputDTO convert = phaseSubjectOutputDTOConvert.reverse().convert(phaseSubject);
        return convert;
    }

    private static class PhaseSubjectOutputDTOConvert extends Converter<PhaseSubjectOutputDTO, PhaseSubject> {
        @Override
        protected PhaseSubject doForward(PhaseSubjectOutputDTO phaseSubjectOutputDTO) {
            PhaseSubject phaseSubject = new PhaseSubject();
            BeanUtils.copyProperties(phaseSubjectOutputDTO, phaseSubject);
            return phaseSubject;
        }

        @Override
        protected PhaseSubjectOutputDTO doBackward(PhaseSubject phaseSubject) {
            PhaseSubjectOutputDTO phaseSubjectOutputDTO = new PhaseSubjectOutputDTO();
            BeanUtils.copyProperties(phaseSubject, phaseSubjectOutputDTO);
            return phaseSubjectOutputDTO;
        }
    }
}
