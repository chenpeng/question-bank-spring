package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Knowledge;
import io.chenpeng.questionbank.dto.KnowledgeOutputDTO;
import io.chenpeng.questionbank.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnowledgeService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    public List<KnowledgeOutputDTO> findAll(){
        Integer pId = 0;
        return knowledgeRepository.findByPId(pId).stream().map(k -> {
            KnowledgeOutputDTO knowledgeOutputDTO = new KnowledgeOutputDTO().convertFor(k);
            setChildren(knowledgeOutputDTO);
            return knowledgeOutputDTO;
        }).collect(Collectors.toList());
    }

    public void setChildren(KnowledgeOutputDTO knowledgeOutputDTO){
        Integer id = knowledgeOutputDTO.getId();
        List<Knowledge> list = knowledgeRepository.findByPId(id);
        if(!CollectionUtils.isEmpty(list)){
            knowledgeOutputDTO.setChildren(list.stream().map(k -> {
                KnowledgeOutputDTO dto = new KnowledgeOutputDTO().convertFor(k);
                setChildren(dto);
                return dto;
            }).collect(Collectors.toList()));
        }
    }
}
