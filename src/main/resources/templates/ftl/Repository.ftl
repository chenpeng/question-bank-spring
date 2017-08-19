package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.${MODEL};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ${MODEL}Repository extends JpaRepository<${MODEL}, Integer> {
    List<${MODEL}> findAllByDeleted(boolean delete);
}
