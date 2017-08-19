package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Edition;
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
@ApiModel(value = "editionOutputDTO", description = "版本相关接口输出参数")
public class EditionOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public Edition convertToEdition() {
        EditionOutputDTOConvert editionOutputDTOConvert = new EditionOutputDTOConvert();
        Edition convert = editionOutputDTOConvert.convert(this);
        return convert;
    }

    public EditionOutputDTO convertFor(Edition edition) {
        EditionOutputDTOConvert editionOutputDTOConvert = new EditionOutputDTOConvert();
        EditionOutputDTO convert = editionOutputDTOConvert.reverse().convert(edition);
        return convert;
    }

    private static class EditionOutputDTOConvert extends Converter<EditionOutputDTO, Edition> {
        @Override
        protected Edition doForward(EditionOutputDTO editionOutputDTO) {
            Edition edition = new Edition();
            BeanUtils.copyProperties(editionOutputDTO, edition);
            return edition;
        }

        @Override
        protected EditionOutputDTO doBackward(Edition edition) {
            EditionOutputDTO editionOutputDTO = new EditionOutputDTO();
            BeanUtils.copyProperties(edition, editionOutputDTO);
            return editionOutputDTO;
        }
    }
}
