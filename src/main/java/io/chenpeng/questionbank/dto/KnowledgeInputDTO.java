package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Knowledge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeInputDTO {
    private Integer id;

    private String name;

    private Integer pId;

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
