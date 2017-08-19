package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.QuestionSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSourceRepository extends JpaRepository<QuestionSource, Integer> {
    List<QuestionSource> findAllByDeleted(boolean delete);
}
