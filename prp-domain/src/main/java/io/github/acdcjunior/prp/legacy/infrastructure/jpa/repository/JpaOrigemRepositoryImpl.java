package io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.legacy.domain.movimentacao.Origem;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.OrigemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaOrigemRepositoryImpl extends JpaAbstractRepository<Origem>
								     implements OrigemRepository {

}