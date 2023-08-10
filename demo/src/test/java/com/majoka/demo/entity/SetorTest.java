package com.majoka.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SetorTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Setor#Setor()}
     *   <li>{@link Setor#setId(Long)}
     *   <li>{@link Setor#setNome(String)}
     *   <li>{@link Setor#getId()}
     *   <li>{@link Setor#getNome()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Setor actualSetor = new Setor();
        actualSetor.setId(1L);
        actualSetor.setNome("Tecnologia");
        assertEquals(1L, actualSetor.getId().longValue());
        assertEquals("Tecnologia", actualSetor.getNome());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Setor#Setor(String)}
     *   <li>{@link Setor#setId(Long)}
     *   <li>{@link Setor#setNome(String)}
     *   <li>{@link Setor#getId()}
     *   <li>{@link Setor#getNome()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Setor actualSetor = new Setor("Tecnologia");
        actualSetor.setId(1L);
        actualSetor.setNome("Tecnologia!");
        assertEquals(1L, actualSetor.getId().longValue());
        assertEquals("Tecnologia", actualSetor.getNome());
    }
}

