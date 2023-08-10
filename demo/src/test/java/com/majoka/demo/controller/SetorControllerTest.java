package com.majoka.demo.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.majoka.demo.entity.Setor;
import com.majoka.demo.repository.SetorRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SetorController.class})
@ExtendWith(SpringExtension.class)
class SetorControllerTest {
    @Autowired
    private SetorController setorController;

    @MockBean
    private SetorRepository setorRepository;

    /**
     * Method under test: {@link SetorController#DeleteAllSetores()}
     */
    @Test
    void testDeleteAllSetores() throws Exception {
        doNothing().when(setorRepository).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/setor");
        MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link SetorController#DeleteAllSetores()}
     */
    @Test
    void testDeleteAllSetores2() throws Exception {
        doNothing().when(setorRepository).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/setor");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link SetorController#DeleteSetor(Long)}
     */
    @Test
    void testDeleteSetor() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");
        Optional<Setor> ofResult = Optional.of(setor);
        doNothing().when(setorRepository).delete(Mockito.<Setor>any());
        when(setorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/setor/{id}", 1L);
        MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link SetorController#DeleteSetor(Long)}
     */
    @Test
    void testDeleteSetor2() throws Exception {
        doNothing().when(setorRepository).delete(Mockito.<Setor>any());
        when(setorRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/setor/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SetorController#GetById(Long)}
     */
    @Test
    void testGetById() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");
        Optional<Setor> ofResult = Optional.of(setor);
        when(setorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/setor/{id}", 1L);
        MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"nome\":\"Nome\"}"));
    }

    /**
     * Method under test: {@link SetorController#GetById(Long)}
     */
    @Test
    void testGetById2() throws Exception {
        when(setorRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/setor/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SetorController#GetSetor()}
     */
    @Test
    void testGetSetor() throws Exception {
        when(setorRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/setor");
        MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SetorController#PostSetor(Setor)}
     */
    @Test
    void testPostSetor() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");
        when(setorRepository.save(Mockito.<Setor>any())).thenReturn(setor);

        Setor setor2 = new Setor();
        setor2.setId(1L);
        setor2.setNome("Nome");
        String content = (new ObjectMapper()).writeValueAsString(setor2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/setor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"nome\":\"Nome\"}"));
    }

    /**
     * Method under test: {@link SetorController#PutSetor(Long, Setor)}
     */
    @Test
    void testPutSetor() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");
        Optional<Setor> ofResult = Optional.of(setor);

        Setor setor2 = new Setor();
        setor2.setId(1L);
        setor2.setNome("Nome");
        when(setorRepository.save(Mockito.<Setor>any())).thenReturn(setor2);
        when(setorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Setor setor3 = new Setor();
        setor3.setId(1L);
        setor3.setNome("Nome");
        String content = (new ObjectMapper()).writeValueAsString(setor3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/setor/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"nome\":\"Nome\"}"));
    }

    /**
     * Method under test: {@link SetorController#PutSetor(Long, Setor)}
     */
    @Test
    void testPutSetor2() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");
        when(setorRepository.save(Mockito.<Setor>any())).thenReturn(setor);
        when(setorRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Setor setor2 = new Setor();
        setor2.setId(1L);
        setor2.setNome("Nome");
        String content = (new ObjectMapper()).writeValueAsString(setor2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/setor/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(setorController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

