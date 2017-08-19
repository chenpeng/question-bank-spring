package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.PhaseSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseSubjectRepository extends JpaRepository<PhaseSubject, Integer> {

}
