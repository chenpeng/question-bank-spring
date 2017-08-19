package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Grade;
import io.chenpeng.questionbank.dto.GradeInputDTO;
import io.chenpeng.questionbank.dto.GradeOutputDTO;
import io.chenpeng.questionbank.dto.GradeSearchDTO;
import io.chenpeng.questionbank.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public List<GradeOutputDTO> findAll() {
        return gradeRepository.findAllByDeleted(false).stream().map(new GradeOutputDTO()::convertFor).collect(Collectors.toList());
    }

    public GradeOutputDTO getOne(Integer id) {
        Grade grade = gradeRepository.getOne(id);
        return new GradeOutputDTO().convertFor(grade);
    }

    public GradeOutputDTO save(GradeInputDTO gradeInputDTO) {
        Grade grade = gradeInputDTO.convertToGrade();
        grade.setDeleted(false);
        grade = gradeRepository.save(grade);
        return new GradeOutputDTO().convertFor(grade);
    }

    public GradeOutputDTO update(GradeInputDTO gradeInputDTO) {
        Integer id = gradeInputDTO.getId();
        Grade grade = gradeRepository.getOne(id);
        String name = gradeInputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            grade.setName(name);
        }
        Integer phaseId = gradeInputDTO.getPhaseId();
        if (phaseId != null) {
            grade.setPhaseId(phaseId);
        }
        grade = gradeRepository.save(grade);
        return new GradeOutputDTO().convertFor(grade);
    }

    public int delete(Integer id) {
        Grade grade = gradeRepository.getOne(id);
        grade.setDeleted(true);
        return gradeRepository.save(grade) == null ? 0 : 1;
    }
}
