package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Knowledge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeOutputDTO {
    private Integer id;

    private String name;

    private Integer pId;

    private List<KnowledgeOutputDTO> children;

    public Knowledge convertToKnowledge() {
        KnowledgeOutputDTO.KnowledgeOutputDTOConvert knowledgeOutputDTOConvert = new KnowledgeOutputDTO.KnowledgeOutputDTOConvert();
        Knowledge convert = knowledgeOutputDTOConvert.convert(this);
        return convert;
    }

    public KnowledgeOutputDTO convertFor(Knowledge knowledge) {
        KnowledgeOutputDTO.KnowledgeOutputDTOConvert knowledgeOutputDTOConvert = new KnowledgeOutputDTO.KnowledgeOutputDTOConvert();
        KnowledgeOutputDTO convert = knowledgeOutputDTOConvert.reverse().convert(knowledge);
        return convert;
    }

    private static class KnowledgeOutputDTOConvert extends Converter<KnowledgeOutputDTO, Knowledge> {
        @Override
        protected Knowledge doForward(KnowledgeOutputDTO knowledgeOutputDTO) {
            Knowledge knowledge = new Knowledge();
            BeanUtils.copyProperties(knowledgeOutputDTO, knowledge);
            return knowledge;
        }

        @Override
        protected KnowledgeOutputDTO doBackward(Knowledge knowledge) {
            KnowledgeOutputDTO knowledgeOutputDTO = new KnowledgeOutputDTO();
            BeanUtils.copyProperties(knowledge, knowledgeOutputDTO);
            return knowledgeOutputDTO;
        }
    }
}
