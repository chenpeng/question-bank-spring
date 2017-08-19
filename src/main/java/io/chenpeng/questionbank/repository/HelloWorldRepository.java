package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.HelloWorld;
import io.chenpeng.questionbank.domain.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelloWorldRepository extends JpaRepository<HelloWorld, Integer> {

}
