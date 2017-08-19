package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge, Integer> {

    List<Knowledge> findByPId(Integer pId);
}
