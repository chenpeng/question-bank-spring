package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Subject;
import io.chenpeng.questionbank.dto.PhaseOutputDTO;
import io.chenpeng.questionbank.dto.SubjectInputDTO;
import io.chenpeng.questionbank.dto.SubjectOutputDTO;
import io.chenpeng.questionbank.dto.SubjectSearchDTO;
import io.chenpeng.questionbank.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectOutputDTO> findAll() {
        return subjectRepository.findAllByDeleted(false).stream().map(new SubjectOutputDTO()::convertFor).collect(Collectors.toList());
    }

    public SubjectOutputDTO getOne(Integer id) {
        Subject subject = subjectRepository.getOne(id);
        return new SubjectOutputDTO().convertFor(subject);
    }

    public SubjectOutputDTO save(SubjectInputDTO subjectInputDTO) {
        Subject subject = subjectInputDTO.convertToSubject();
        subject.setDeleted(false);
        subject = subjectRepository.save(subject);
        return new SubjectOutputDTO().convertFor(subject);
    }

    public SubjectOutputDTO update(SubjectInputDTO subjectInputDTO) {
        Integer id = subjectInputDTO.getId();
        Subject subject = subjectRepository.getOne(id);
        String name = subjectInputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            subject.setName(name);
        }
        subject = subjectRepository.save(subject);
        return new SubjectOutputDTO().convertFor(subject);
    }

    public int delete(Integer id) {
        Subject subject = subjectRepository.getOne(id);
        subject.setDeleted(true);
        return subjectRepository.save(subject) == null ? 0 : 1;
    }
}
