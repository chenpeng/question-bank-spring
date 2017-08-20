package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Knowledge;
import io.chenpeng.questionbank.domain.Phase;
import io.chenpeng.questionbank.dto.KnowledgeInputDTO;
import io.chenpeng.questionbank.dto.KnowledgeOutputDTO;
import io.chenpeng.questionbank.dto.PhaseOutputDTO;
import io.chenpeng.questionbank.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnowledgeService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    public List<KnowledgeOutputDTO> findAll() {
        Integer pId = 0;
        return knowledgeRepository.findByPId(pId).stream().map(k -> {
            KnowledgeOutputDTO knowledgeOutputDTO = new KnowledgeOutputDTO().convertFor(k);
            setChildren(knowledgeOutputDTO);
            return knowledgeOutputDTO;
        }).collect(Collectors.toList());
    }

    public void setChildren(KnowledgeOutputDTO knowledgeOutputDTO) {
        Integer id = knowledgeOutputDTO.getId();
        List<Knowledge> list = knowledgeRepository.findByPId(id);
        if (!CollectionUtils.isEmpty(list)) {
            knowledgeOutputDTO.setChildren(list.stream().map(k -> {
                KnowledgeOutputDTO dto = new KnowledgeOutputDTO().convertFor(k);
                setChildren(dto);
                return dto;
            }).collect(Collectors.toList()));
        }
    }

    public KnowledgeOutputDTO getOne(Integer id) {
        Knowledge knowledge = knowledgeRepository.getOne(id);
        KnowledgeOutputDTO knowledgeOutputDTO = new KnowledgeOutputDTO().convertFor(knowledge);
        setChildren(knowledgeOutputDTO);
        return knowledgeOutputDTO;
    }

    public KnowledgeOutputDTO save(KnowledgeInputDTO knowledgeInputDTO) {
        Knowledge knowledge = knowledgeInputDTO.convertToKnowledge();
        knowledge.setDeleted(false);
        knowledge.setState(0);
        knowledge = knowledgeRepository.save(knowledge);
        KnowledgeOutputDTO knowledgeOutputDTO = new KnowledgeOutputDTO().convertFor(knowledge);
        setChildren(knowledgeOutputDTO);
        return knowledgeOutputDTO;
    }

    public KnowledgeOutputDTO update(KnowledgeInputDTO knowledgeInputDTO) {
        Integer id = knowledgeInputDTO.getId();
        Knowledge knowledge = knowledgeRepository.getOne(id);
        String name = knowledgeInputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            knowledge.setName(name);
        }
        knowledge = knowledgeRepository.save(knowledge);
        KnowledgeOutputDTO knowledgeOutputDTO = new KnowledgeOutputDTO().convertFor(knowledge);
        setChildren(knowledgeOutputDTO);
        return knowledgeOutputDTO;
    }

    public Integer delete(Integer id) {
        Knowledge knowledge = knowledgeRepository.getOne(id);
        knowledge.setDeleted(true);
        return knowledgeRepository.save(knowledge) == null ? 0 : 1;
    }
}
