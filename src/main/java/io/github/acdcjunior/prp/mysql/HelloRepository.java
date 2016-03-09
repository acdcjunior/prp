package io.github.acdcjunior.prp.mysql;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface HelloRepository extends CrudRepository<Hello, Long> {

}