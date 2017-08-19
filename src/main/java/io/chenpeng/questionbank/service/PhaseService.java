package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Phase;
import io.chenpeng.questionbank.dto.PhaseInputDTO;
import io.chenpeng.questionbank.dto.PhaseOutputDTO;
import io.chenpeng.questionbank.repository.PhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhaseService {
    @Autowired
    private PhaseRepository phaseRepository;

    public List<PhaseOutputDTO> findAll() {
        return phaseRepository.findAllByDeleted(false).stream().map(new PhaseOutputDTO()::convertFor).collect(Collectors.toList());
    }

    public PhaseOutputDTO getOne(Integer id) {
        Phase phase = phaseRepository.getOne(id);
        return new PhaseOutputDTO().convertFor(phase);
    }

    public PhaseOutputDTO save(PhaseInputDTO phaseInputDTO) {
        Phase phase = phaseInputDTO.convertToPhase();
        phase.setDeleted(false);
        phase = phaseRepository.save(phase);
        return new PhaseOutputDTO().convertFor(phase);
    }

    public PhaseOutputDTO update(PhaseInputDTO phaseInputDTO) {
        Integer id = phaseInputDTO.getId();
        Phase phase = phaseRepository.getOne(id);
        String name = phaseInputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            phase.setName(name);
        }
        phase = phaseRepository.save(phase);
        return new PhaseOutputDTO().convertFor(phase);
    }

    public int delete(Integer id) {
        Phase phase = phaseRepository.getOne(id);
        phase.setDeleted(true);
        return phaseRepository.save(phase) == null ? 0 : 1;
    }
}
