package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Integer> {
    List<Edition> findAllByDeleted(boolean delete);
}
