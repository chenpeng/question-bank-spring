package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Integer> {

    List<Phase> findAllByDeleted(boolean deleted);
}
