package net.acdcjunior.prp.infrastructure.repository.jpa;

import net.acdcjunior.prp.domain.movimentacao.Origem;
import net.acdcjunior.prp.domain.movimentacao.OrigemRepository;

import org.springframework.stereotype.Repository;

@Repository
public class JpaOrigemRepositoryImpl extends JpaAbstractRepository<Origem> 
								     implements OrigemRepository {

}