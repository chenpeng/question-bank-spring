package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term, Integer> {
    List<Term> findAllByDeleted(boolean delete);
}
