package io.github.acdcjunior.prp.extratos.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface OrigemRepository extends Repository<Origem, Long> {

	Page<Origem> findAll(Pageable pageable);

	List<Origem> findAll();

	Page<Origem> findByNomeContainingIgnoringCase(String nome, Pageable pageable);

	Origem findById(Long id);

}
