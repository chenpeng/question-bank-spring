package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.${MODEL};
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
@ApiModel(value = "${LOW_MODEL}InputDTO", description = "${NOTE}相关接口传入参数")
public class ${MODEL}InputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    public ${MODEL} convertTo${MODEL}() {
        ${MODEL}InputDTOConvert ${LOW_MODEL}InputDTOConvert = new ${MODEL}InputDTOConvert();
        ${MODEL} convert = ${LOW_MODEL}InputDTOConvert.convert(this);
        return convert;
    }

    public ${MODEL}InputDTO convertFor(${MODEL} ${LOW_MODEL}) {
        ${MODEL}InputDTO.${MODEL}InputDTOConvert ${LOW_MODEL}InputDTOConvert = new ${MODEL}InputDTO.${MODEL}InputDTOConvert();
        ${MODEL}InputDTO convert = ${LOW_MODEL}InputDTOConvert.reverse().convert(${LOW_MODEL});
        return convert;
    }

    private static class ${MODEL}InputDTOConvert extends Converter<${MODEL}InputDTO, ${MODEL}> {
        @Override
        protected ${MODEL} doForward(${MODEL}InputDTO ${LOW_MODEL}InputDTO) {
            ${MODEL} ${LOW_MODEL} = new ${MODEL}();
            BeanUtils.copyProperties(${LOW_MODEL}InputDTO, ${LOW_MODEL});
            return ${LOW_MODEL};
        }

        @Override
        protected ${MODEL}InputDTO doBackward(${MODEL} ${LOW_MODEL}) {
            ${MODEL}InputDTO ${LOW_MODEL}InputDTO = new ${MODEL}InputDTO();
            BeanUtils.copyProperties(${LOW_MODEL}, ${LOW_MODEL}InputDTO);
            return ${LOW_MODEL}InputDTO;
        }
    }
}
