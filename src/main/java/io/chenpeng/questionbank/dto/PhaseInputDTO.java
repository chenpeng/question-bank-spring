package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Phase;
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
@ApiModel(value = "phaseInputDTO", description = "学段相关接口传入参数")
public class PhaseInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    public Phase convertToPhase() {
        PhaseInputDTOConvert phaseInputDTOConvert = new PhaseInputDTOConvert();
        Phase convert = phaseInputDTOConvert.convert(this);
        return convert;
    }

    public PhaseInputDTO convertFor(Phase phase) {
        PhaseInputDTO.PhaseInputDTOConvert phaseInputDTOConvert = new PhaseInputDTO.PhaseInputDTOConvert();
        PhaseInputDTO convert = phaseInputDTOConvert.reverse().convert(phase);
        return convert;
    }

    private static class PhaseInputDTOConvert extends Converter<PhaseInputDTO, Phase> {
        @Override
        protected Phase doForward(PhaseInputDTO phaseInputDTO) {
            Phase phase = new Phase();
            BeanUtils.copyProperties(phaseInputDTO, phase);
            return phase;
        }

        @Override
        protected PhaseInputDTO doBackward(Phase phase) {
            PhaseInputDTO phaseInputDTO = new PhaseInputDTO();
            BeanUtils.copyProperties(phase, phaseInputDTO);
            return phaseInputDTO;
        }
    }
}
