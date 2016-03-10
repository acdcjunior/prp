package io.github.acdcjunior.prp.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.domain.movimentacao.Origem;
import io.github.acdcjunior.prp.domain.movimentacao.OrigemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaOrigemRepositoryImpl extends JpaAbstractRepository<Origem> 
								     implements OrigemRepository {

}