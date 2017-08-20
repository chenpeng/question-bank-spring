package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByDeleted(boolean delete);
}
