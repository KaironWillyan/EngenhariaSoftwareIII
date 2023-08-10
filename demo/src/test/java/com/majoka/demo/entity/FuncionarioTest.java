package com.majoka.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class FuncionarioTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Funcionario#Funcionario()}
     *   <li>{@link Funcionario#setCpf(String)}
     *   <li>{@link Funcionario#setId(Long)}
     *   <li>{@link Funcionario#setNome(String)}
     *   <li>{@link Funcionario#setSetores(Setor)}
     *   <li>{@link Funcionario#setTelefone(String)}
     *   <li>{@link Funcionario#setTipoCnh(String)}
     *   <li>{@link Funcionario#getCpf()}
     *   <li>{@link Funcionario#getId()}
     *   <li>{@link Funcionario#getNome()}
     *   <li>{@link Funcionario#getSetor()}
     *   <li>{@link Funcionario#getTelefone()}
     *   <li>{@link Funcionario#getTipoCnh()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Funcionario actualFuncionario = new Funcionario();
        actualFuncionario.setCpf("12103858301");
        actualFuncionario.setId(1L);
        actualFuncionario.setNome("Nome1");
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Tecnologia");
        actualFuncionario.setSetores(setor);
        actualFuncionario.setTelefone("86981830485");
        actualFuncionario.setTipoCnh("AB");
        assertEquals("12103858301", actualFuncionario.getCpf());
        assertEquals(1L, actualFuncionario.getId().longValue());
        assertEquals("Nome1", actualFuncionario.getNome());
        assertSame(setor, actualFuncionario.getSetor());
        assertEquals("86981830485", actualFuncionario.getTelefone());
        assertEquals("AB", actualFuncionario.getTipoCnh());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Funcionario#Funcionario(String, String, String, String)}
     *   <li>{@link Funcionario#setCpf(String)}
     *   <li>{@link Funcionario#setId(Long)}
     *   <li>{@link Funcionario#setNome(String)}
     *   <li>{@link Funcionario#setSetores(Setor)}
     *   <li>{@link Funcionario#setTelefone(String)}
     *   <li>{@link Funcionario#setTipoCnh(String)}
     *   <li>{@link Funcionario#getCpf()}
     *   <li>{@link Funcionario#getId()}
     *   <li>{@link Funcionario#getNome()}
     *   <li>{@link Funcionario#getSetor()}
     *   <li>{@link Funcionario#getTelefone()}
     *   <li>{@link Funcionario#getTipoCnh()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Funcionario actualFuncionario = new Funcionario("Kairon", "07982699367", "AB", "86994401531");
        actualFuncionario.setCpf("07982699367");
        actualFuncionario.setId(1L);
        actualFuncionario.setNome("Kairon");
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");
        actualFuncionario.setSetores(setor);
        actualFuncionario.setTelefone("86994401531");
        actualFuncionario.setTipoCnh("AB");
        assertEquals("07982699367", actualFuncionario.getCpf());
        assertEquals(1L, actualFuncionario.getId().longValue());
        assertEquals("Kairon", actualFuncionario.getNome());
        assertSame(setor, actualFuncionario.getSetor());
        assertEquals("86994401531", actualFuncionario.getTelefone());
        assertEquals("AB", actualFuncionario.getTipoCnh());
    }

    @Test
    void testeConstructor2_2() {
        Funcionario actualFuncionario = new Funcionario("Kairon", "07982699367", "AB", "86994401531");
        actualFuncionario.setCpf("07982699367");
        actualFuncionario.setId(1L);
        actualFuncionario.setNome("Kairon W");
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Tecnologia");
        actualFuncionario.setSetores(setor);
        actualFuncionario.setTelefone("86994401533");
        actualFuncionario.setTipoCnh("AB");
        assertEquals("07982699369", actualFuncionario.getCpf());
        assertEquals(1L, actualFuncionario.getId().longValue());
        assertEquals("Kairon", actualFuncionario.getNome());
        assertSame(setor, actualFuncionario.getSetor());
        assertEquals("86994401531", actualFuncionario.getTelefone());
        assertEquals("AB", actualFuncionario.getTipoCnh());
    }
}

