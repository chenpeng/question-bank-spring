package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Edition;
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
@ApiModel(value = "editionInputDTO", description = "版本相关接口传入参数")
public class EditionInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    public Edition convertToEdition() {
        EditionInputDTOConvert editionInputDTOConvert = new EditionInputDTOConvert();
        Edition convert = editionInputDTOConvert.convert(this);
        return convert;
    }

    public EditionInputDTO convertFor(Edition edition) {
        EditionInputDTO.EditionInputDTOConvert editionInputDTOConvert = new EditionInputDTO.EditionInputDTOConvert();
        EditionInputDTO convert = editionInputDTOConvert.reverse().convert(edition);
        return convert;
    }

    private static class EditionInputDTOConvert extends Converter<EditionInputDTO, Edition> {
        @Override
        protected Edition doForward(EditionInputDTO editionInputDTO) {
            Edition edition = new Edition();
            BeanUtils.copyProperties(editionInputDTO, edition);
            return edition;
        }

        @Override
        protected EditionInputDTO doBackward(Edition edition) {
            EditionInputDTO editionInputDTO = new EditionInputDTO();
            BeanUtils.copyProperties(edition, editionInputDTO);
            return editionInputDTO;
        }
    }
}
