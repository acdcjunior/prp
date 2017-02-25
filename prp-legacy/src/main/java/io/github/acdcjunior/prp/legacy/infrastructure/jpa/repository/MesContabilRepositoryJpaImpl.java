package io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.legacy.domain.mescontabil.AnoMes;
import io.github.acdcjunior.prp.legacy.domain.mescontabil.MesContabil;
import io.github.acdcjunior.prp.legacy.domain.mescontabil.MesContabilRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MesContabilRepositoryJpaImpl implements MesContabilRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<MesContabil> findAll() {
        return this.em.createQuery("FROM MesContabil", MesContabil.class).getResultList();
    }

    @Override
    public MesContabil find(int ano, int mes) {
        AnoMes anoMes = new AnoMes(ano, mes);
        return this.em.find(MesContabil.class, anoMes);
    }

}