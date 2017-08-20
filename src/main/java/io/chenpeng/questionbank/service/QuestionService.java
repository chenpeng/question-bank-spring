package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Question;
import io.chenpeng.questionbank.dto.QuestionInputDTO;
import io.chenpeng.questionbank.dto.QuestionOutputDTO;
import io.chenpeng.questionbank.dto.QuestionSearchDTO;
import io.chenpeng.questionbank.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Page<QuestionOutputDTO> findAll(QuestionSearchDTO questionSearchDTO) {
        Page<Question> page = questionRepository.findAll(new PageRequest(questionSearchDTO.getPageIndex() - 1, questionSearchDTO.getPageSize()));
        return new PageImpl<>(page.getContent().stream().map(new QuestionOutputDTO()::convertFor).collect(Collectors.toList()));
    }

    public QuestionOutputDTO getOne(Integer id) {
        Question question = questionRepository.getOne(id);
        return new QuestionOutputDTO().convertFor(question);
    }

    public QuestionOutputDTO save(QuestionInputDTO questionInputDTO) {
        Question question = questionInputDTO.convertToQuestion();
        question.setDeleted(false);
        question.setState(0);
        question = questionRepository.save(question);
        return new QuestionOutputDTO().convertFor(question);
    }

    public QuestionOutputDTO update(QuestionInputDTO questionInputDTO) {
        Integer id = questionInputDTO.getId();
        Question question = questionRepository.getOne(id);
        String name = questionInputDTO.getName();
        question = questionRepository.save(question);
        return new QuestionOutputDTO().convertFor(question);
    }

    public int delete(Integer id) {
        Question question = questionRepository.getOne(id);
        question.setDeleted(true);
        return questionRepository.save(question) == null ? 0 : 1;
    }
}
