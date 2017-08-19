package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Term;
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
@ApiModel(value = "termOutputDTO", description = "学期相关接口输出参数")
public class TermOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public Term convertToTerm() {
        TermOutputDTOConvert termOutputDTOConvert = new TermOutputDTOConvert();
        Term convert = termOutputDTOConvert.convert(this);
        return convert;
    }

    public TermOutputDTO convertFor(Term term) {
        TermOutputDTOConvert termOutputDTOConvert = new TermOutputDTOConvert();
        TermOutputDTO convert = termOutputDTOConvert.reverse().convert(term);
        return convert;
    }

    private static class TermOutputDTOConvert extends Converter<TermOutputDTO, Term> {
        @Override
        protected Term doForward(TermOutputDTO termOutputDTO) {
            Term term = new Term();
            BeanUtils.copyProperties(termOutputDTO, term);
            return term;
        }

        @Override
        protected TermOutputDTO doBackward(Term term) {
            TermOutputDTO termOutputDTO = new TermOutputDTO();
            BeanUtils.copyProperties(term, termOutputDTO);
            return termOutputDTO;
        }
    }
}
