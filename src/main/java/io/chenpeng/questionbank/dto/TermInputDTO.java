package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Term;
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
@ApiModel(value = "termInputDTO", description = "学期相关接口传入参数")
public class TermInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    public Term convertToTerm() {
        TermInputDTOConvert termInputDTOConvert = new TermInputDTOConvert();
        Term convert = termInputDTOConvert.convert(this);
        return convert;
    }

    public TermInputDTO convertFor(Term term) {
        TermInputDTO.TermInputDTOConvert termInputDTOConvert = new TermInputDTO.TermInputDTOConvert();
        TermInputDTO convert = termInputDTOConvert.reverse().convert(term);
        return convert;
    }

    private static class TermInputDTOConvert extends Converter<TermInputDTO, Term> {
        @Override
        protected Term doForward(TermInputDTO termInputDTO) {
            Term term = new Term();
            BeanUtils.copyProperties(termInputDTO, term);
            return term;
        }

        @Override
        protected TermInputDTO doBackward(Term term) {
            TermInputDTO termInputDTO = new TermInputDTO();
            BeanUtils.copyProperties(term, termInputDTO);
            return termInputDTO;
        }
    }
}
