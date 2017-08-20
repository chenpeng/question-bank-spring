package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Knowledge;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "knowledgeInputDTO", description = "知识点相关接口传入参数")
public class KnowledgeInputDTO {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    @NotNull(message = "请输入pId参数")
    @DecimalMin(value = "0", message = "参数pId格式不正确")
    @ApiModelProperty(name = "pId", value = "父ID", dataType = "int", required = true, example = "0")
    private Integer pId;

    @NotNull(message = "请输入sort参数")
    @DecimalMin(value = "1", message = "参数sort格式不正确")
    @ApiModelProperty(name = "sort", value = "排序字段", dataType = "int", required = true, example = "1")
    private Integer sort;

    public Knowledge convertToKnowledge() {
        KnowledgeInputDTO.KnowledgeInputDTOConvert knowledgeInputDTOConvert = new KnowledgeInputDTO.KnowledgeInputDTOConvert();
        Knowledge convert = knowledgeInputDTOConvert.convert(this);
        return convert;
    }

    public KnowledgeInputDTO convertFor(Knowledge knowledge) {
        KnowledgeInputDTO.KnowledgeInputDTOConvert knowledgeInputDTOConvert = new KnowledgeInputDTO.KnowledgeInputDTOConvert();
        KnowledgeInputDTO convert = knowledgeInputDTOConvert.reverse().convert(knowledge);
        return convert;
    }

    private static class KnowledgeInputDTOConvert extends Converter<KnowledgeInputDTO, Knowledge> {
        @Override
        protected Knowledge doForward(KnowledgeInputDTO knowledgeInputDTO) {
            Knowledge knowledge = new Knowledge();
            BeanUtils.copyProperties(knowledgeInputDTO, knowledge);
            return knowledge;
        }

        @Override
        protected KnowledgeInputDTO doBackward(Knowledge knowledge) {
            KnowledgeInputDTO knowledgeInputDTO = new KnowledgeInputDTO();
            BeanUtils.copyProperties(knowledge, knowledgeInputDTO);
            return knowledgeInputDTO;
        }
    }

}
