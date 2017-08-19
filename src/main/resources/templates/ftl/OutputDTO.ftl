package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.${MODEL};
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
@ApiModel(value = "${LOW_MODEL}OutputDTO", description = "${NOTE}相关接口输出参数")
public class ${MODEL}OutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public ${MODEL} convertTo${MODEL}() {
        ${MODEL}OutputDTOConvert ${LOW_MODEL}OutputDTOConvert = new ${MODEL}OutputDTOConvert();
        ${MODEL} convert = ${LOW_MODEL}OutputDTOConvert.convert(this);
        return convert;
    }

    public ${MODEL}OutputDTO convertFor(${MODEL} ${LOW_MODEL}) {
        ${MODEL}OutputDTOConvert ${LOW_MODEL}OutputDTOConvert = new ${MODEL}OutputDTOConvert();
        ${MODEL}OutputDTO convert = ${LOW_MODEL}OutputDTOConvert.reverse().convert(${LOW_MODEL});
        return convert;
    }

    private static class ${MODEL}OutputDTOConvert extends Converter<${MODEL}OutputDTO, ${MODEL}> {
        @Override
        protected ${MODEL} doForward(${MODEL}OutputDTO ${LOW_MODEL}OutputDTO) {
            ${MODEL} ${LOW_MODEL} = new ${MODEL}();
            BeanUtils.copyProperties(${LOW_MODEL}OutputDTO, ${LOW_MODEL});
            return ${LOW_MODEL};
        }

        @Override
        protected ${MODEL}OutputDTO doBackward(${MODEL} ${LOW_MODEL}) {
            ${MODEL}OutputDTO ${LOW_MODEL}OutputDTO = new ${MODEL}OutputDTO();
            BeanUtils.copyProperties(${LOW_MODEL}, ${LOW_MODEL}OutputDTO);
            return ${LOW_MODEL}OutputDTO;
        }
    }
}
