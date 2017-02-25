package io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.legacy.domain.mescontabil.MesContabil;
import io.github.acdcjunior.prp.legacy.test.InjetarEntityManagerRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static io.github.acdcjunior.prp.legacy.test.InjetarEntityManagerRule.InjetarEntityManager;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@Ignore("problemas ao criar PU, mas, em tese, o teste funciona 100%! (funcionava no maven)")
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