package io.github.acdcjunior.prp.domain.mescontabil;

import java.util.List;

public interface MesContabilRepository {

    List<MesContabil> findAll();

    MesContabil find(int ano, int mes);

}