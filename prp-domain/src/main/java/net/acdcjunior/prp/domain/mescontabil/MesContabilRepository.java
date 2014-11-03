package net.acdcjunior.prp.domain.mescontabil;

import net.acdcjunior.prp.domain.BaseRepository;

import java.util.List;

public interface MesContabilRepository {

    List<MesContabil> findAll();

    MesContabil find(int ano, int mes);

}