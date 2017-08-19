package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    List<Chapter> findAllByDeleted(boolean delete);
}
