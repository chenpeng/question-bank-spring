package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Phase;
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
@ApiModel(value = "phaseOutputDTO", description = "学段相关接口输出参数")
public class PhaseOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public Phase convertToPhase() {
        PhaseOutputDTOConvert phaseOutputDTOConvert = new PhaseOutputDTOConvert();
        Phase convert = phaseOutputDTOConvert.convert(this);
        return convert;
    }

    public PhaseOutputDTO convertFor(Phase phase) {
        PhaseOutputDTOConvert phaseOutputDTOConvert = new PhaseOutputDTOConvert();
        PhaseOutputDTO convert = phaseOutputDTOConvert.reverse().convert(phase);
        return convert;
    }

    private static class PhaseOutputDTOConvert extends Converter<PhaseOutputDTO, Phase> {
        @Override
        protected Phase doForward(PhaseOutputDTO phaseOutputDTO) {
            Phase phase = new Phase();
            BeanUtils.copyProperties(phaseOutputDTO, phase);
            return phase;
        }

        @Override
        protected PhaseOutputDTO doBackward(Phase phase) {
            PhaseOutputDTO phaseOutputDTO = new PhaseOutputDTO();
            BeanUtils.copyProperties(phase, phaseOutputDTO);
            return phaseOutputDTO;
        }
    }
}
