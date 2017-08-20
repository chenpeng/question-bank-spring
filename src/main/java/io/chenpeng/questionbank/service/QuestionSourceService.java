package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.QuestionSource;
import io.chenpeng.questionbank.dto.QuestionSourceInputDTO;
import io.chenpeng.questionbank.dto.QuestionSourceOutputDTO;
import io.chenpeng.questionbank.dto.QuestionSourceSearchDTO;
import io.chenpeng.questionbank.repository.QuestionSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionSourceService {
    @Autowired
    private QuestionSourceRepository questionSourceRepository;

    public List<QuestionSourceOutputDTO> findAll() {
        return questionSourceRepository.findAllByDeleted(false).stream().map(new QuestionSourceOutputDTO()::convertFor).collect(Collectors.toList());
    }

    public QuestionSourceOutputDTO getOne(Integer id) {
        QuestionSource questionSource = questionSourceRepository.getOne(id);
        return new QuestionSourceOutputDTO().convertFor(questionSource);
    }

    public QuestionSourceOutputDTO save(QuestionSourceInputDTO questionSourceInputDTO) {
        QuestionSource questionSource = questionSourceInputDTO.convertToQuestionSource();
        questionSource.setDeleted(false);
        questionSource.setState(0);
        questionSource = questionSourceRepository.save(questionSource);
        return new QuestionSourceOutputDTO().convertFor(questionSource);
    }

    public QuestionSourceOutputDTO update(QuestionSourceInputDTO questionSourceInputDTO) {
        Integer id = questionSourceInputDTO.getId();
        QuestionSource questionSource = questionSourceRepository.getOne(id);
        String name = questionSourceInputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            questionSource.setName(name);
        }
        questionSource = questionSourceRepository.save(questionSource);
        return new QuestionSourceOutputDTO().convertFor(questionSource);
    }

    public int delete(Integer id) {
        QuestionSource questionSource = questionSourceRepository.getOne(id);
        questionSource.setDeleted(true);
        return questionSourceRepository.save(questionSource) == null ? 0 : 1;
    }
}
