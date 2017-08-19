package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Phase;
import io.chenpeng.questionbank.domain.PhaseSubject;
import io.chenpeng.questionbank.domain.Subject;
import io.chenpeng.questionbank.dto.PhaseSubjectInputDTO;
import io.chenpeng.questionbank.dto.PhaseSubjectOutputDTO;
import io.chenpeng.questionbank.dto.PhaseSubjectSearchDTO;
import io.chenpeng.questionbank.repository.PhaseRepository;
import io.chenpeng.questionbank.repository.PhaseSubjectRepository;
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
public class PhaseSubjectService {
    @Autowired
    private PhaseRepository phaseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private PhaseSubjectRepository phaseSubjectRepository;

    public List<PhaseSubjectOutputDTO> findAll() {

        return phaseSubjectRepository.findAll().stream()
                .map((PhaseSubject x) -> {
                    PhaseSubjectOutputDTO dto = new PhaseSubjectOutputDTO();
                    Integer phaseId = x.getPhaseId();
                    Phase phase = phaseRepository.getOne(phaseId);
                    Integer subjectId = x.getSubjectId();
                    Subject subject = subjectRepository.getOne(subjectId);
                    dto.convertFor(x);
                    dto.setName(phase.getName() + subject.getName());
                    return dto;
                }).collect(Collectors.toList());
    }

    public PhaseSubjectOutputDTO getOne(Integer id) {
        PhaseSubject phaseSubject = phaseSubjectRepository.getOne(id);
        return new PhaseSubjectOutputDTO().convertFor(phaseSubject);
    }

    public PhaseSubjectOutputDTO save(PhaseSubjectInputDTO phaseSubjectInputDTO) {
        PhaseSubject phaseSubject = phaseSubjectInputDTO.convertToPhaseSubject();
        phaseSubject = phaseSubjectRepository.save(phaseSubject);
        return new PhaseSubjectOutputDTO().convertFor(phaseSubject);
    }

    public PhaseSubjectOutputDTO update(PhaseSubjectInputDTO phaseSubjectInputDTO) {
        Integer id = phaseSubjectInputDTO.getId();
        PhaseSubject phaseSubject = phaseSubjectRepository.getOne(id);

        phaseSubject = phaseSubjectRepository.save(phaseSubject);
        return new PhaseSubjectOutputDTO().convertFor(phaseSubject);
    }

    public int delete(Integer id) {
        PhaseSubject phaseSubject = phaseSubjectRepository.getOne(id);
        phaseSubject.setDeleted(true);
        return phaseSubjectRepository.save(phaseSubject) == null ? 0 : 1;
    }
}
