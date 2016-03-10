package io.github.acdcjunior.prp.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.domain.mescontabil.MesContabil;
import io.github.acdcjunior.prp.test.InjetarEntityManagerRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static io.github.acdcjunior.prp.test.InjetarEntityManagerRule.InjetarEntityManager;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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