package net.acdcjunior.prp.infrastructure.jpa.repository;

import net.acdcjunior.prp.domain.categoria.CategoriaRepository;
import net.acdcjunior.prp.domain.mescontabil.MesContabil;
import net.acdcjunior.prp.test.InjetarEntityManagerRule;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static net.acdcjunior.prp.test.InjetarEntityManagerRule.InjetarEntityManager;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MesContabilRepositoryJpaImplTest {

    @Rule
    public InjetarEntityManagerRule emRule = new InjetarEntityManagerRule();

    @InjetarEntityManager
    MesContabilRepositoryJpaImpl mesContabilRepositoryJpa = new MesContabilRepositoryJpaImpl();

    @Test
    public void findAll() throws Exception {
        List<MesContabil> all = mesContabilRepositoryJpa.findAll();
        assertThat(all, hasSize(2));
    }

    @Test
    public void find() throws Exception {
        MesContabil julho = mesContabilRepositoryJpa.find(2011, 7);
        assertThat(julho, hasProperty("comentario", is("Tudo de boas")));
    }

}