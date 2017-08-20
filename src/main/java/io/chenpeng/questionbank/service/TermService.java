package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Term;
import io.chenpeng.questionbank.dto.TermInputDTO;
import io.chenpeng.questionbank.dto.TermOutputDTO;
import io.chenpeng.questionbank.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TermService {
    @Autowired
    private TermRepository termRepository;

    public List<TermOutputDTO> findAll() {
        return termRepository.findAllByDeleted(false).stream().map(new TermOutputDTO()::convertFor).collect(Collectors.toList());
    }

    public TermOutputDTO getOne(Integer id) {
        Term term = termRepository.getOne(id);
        return new TermOutputDTO().convertFor(term);
    }

    public TermOutputDTO save(TermInputDTO termInputDTO) {
        Term term = termInputDTO.convertToTerm();
        term.setDeleted(false);
        term.setState(0);
        term = termRepository.save(term);
        return new TermOutputDTO().convertFor(term);
    }

    public TermOutputDTO update(TermInputDTO termInputDTO) {
        Integer id = termInputDTO.getId();
        Term term = termRepository.getOne(id);
        String name = termInputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            term.setName(name);
        }
        term = termRepository.save(term);
        return new TermOutputDTO().convertFor(term);
    }

    public int delete(Integer id) {
        Term term = termRepository.getOne(id);
        term.setDeleted(true);
        return termRepository.save(term) == null ? 0 : 1;
    }
}
