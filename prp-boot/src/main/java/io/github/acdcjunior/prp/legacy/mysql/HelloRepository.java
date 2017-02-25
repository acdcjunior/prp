package io.github.acdcjunior.prp.legacy.mysql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface HelloRepository extends CrudRepository<Hello, Long> {

}