package com.majoka.demo.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.majoka.demo.entity.Funcionario;
import com.majoka.demo.entity.Setor;
import com.majoka.demo.repository.FuncionarioRepository;

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

@ContextConfiguration(classes = {FuncionarioController.class})
@ExtendWith(SpringExtension.class)
class FuncionarioControllerTest {
    @Autowired
    private FuncionarioController funcionarioController;

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    /**
     * Method under test: {@link FuncionarioController#DeleteAllFuncionario()}
     */
    @Test
    void testDeleteAllFuncionario() throws Exception {
        doNothing().when(funcionarioRepository).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/funcionario");
        MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FuncionarioController#DeleteAllFuncionario()}
     */
    @Test
    void testDeleteAllFuncionario2() throws Exception {
        doNothing().when(funcionarioRepository).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/funcionario");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FuncionarioController#Deletefuncionario(Long)}
     */
    @Test
    void testDeletefuncionario() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("Cpf");
        funcionario.setId(1L);
        funcionario.setNome("Nome");
        funcionario.setSetores(setor);
        funcionario.setTelefone("Telefone");
        funcionario.setTipoCnh("Tipo Cnh");
        Optional<Funcionario> ofResult = Optional.of(funcionario);
        doNothing().when(funcionarioRepository).delete(Mockito.<Funcionario>any());
        when(funcionarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/funcionario/{id}", 1L);
        MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FuncionarioController#Deletefuncionario(Long)}
     */
    @Test
    void testDeletefuncionario2() throws Exception {
        doNothing().when(funcionarioRepository).delete(Mockito.<Funcionario>any());
        when(funcionarioRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/funcionario/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link FuncionarioController#GetFuncionario()}
     */
    @Test
    void testGetFuncionario() throws Exception {
        when(funcionarioRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/funcionario");
        MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FuncionarioController#GetFuncionarioById(Long)}
     */
    @Test
    void testGetFuncionarioById() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("Cpf");
        funcionario.setId(1L);
        funcionario.setNome("Nome");
        funcionario.setSetores(setor);
        funcionario.setTelefone("Telefone");
        funcionario.setTipoCnh("Tipo Cnh");
        Optional<Funcionario> ofResult = Optional.of(funcionario);
        when(funcionarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/funcionario/{id}", 1L);
        MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"setor\":{\"id\":1,\"nome\":\"Nome\"},\"nome\":\"Nome\",\"cpf\":\"Cpf\",\"tipoCnh\":\"Tipo Cnh\",\"telefone\":"
                                        + "\"Telefone\"}"));
    }

    /**
     * Method under test: {@link FuncionarioController#GetFuncionarioById(Long)}
     */
    @Test
    void testGetFuncionarioById2() throws Exception {
        when(funcionarioRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/funcionario/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link FuncionarioController#PostFuncionario(Funcionario)}
     */
    @Test
    void testPostFuncionario() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("Cpf");
        funcionario.setId(1L);
        funcionario.setNome("Nome");
        funcionario.setSetores(setor);
        funcionario.setTelefone("Telefone");
        funcionario.setTipoCnh("Tipo Cnh");
        when(funcionarioRepository.saveAndFlush(Mockito.<Funcionario>any())).thenReturn(funcionario);

        Setor setor2 = new Setor();
        setor2.setId(1L);
        setor2.setNome("Nome");

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setCpf("Cpf");
        funcionario2.setId(1L);
        funcionario2.setNome("Nome");
        funcionario2.setSetores(setor2);
        funcionario2.setTelefone("Telefone");
        funcionario2.setTipoCnh("Tipo Cnh");
        String content = (new ObjectMapper()).writeValueAsString(funcionario2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/funcionario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"setor\":{\"id\":1,\"nome\":\"Nome\"},\"nome\":\"Nome\",\"cpf\":\"Cpf\",\"tipoCnh\":\"Tipo Cnh\",\"telefone\":"
                                        + "\"Telefone\"}"));
    }

    /**
     * Method under test: {@link FuncionarioController#PutFuncionario(Long, Funcionario)}
     */
    @Test
    void testPutFuncionario() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("Cpf");
        funcionario.setId(1L);
        funcionario.setNome("Nome");
        funcionario.setSetores(setor);
        funcionario.setTelefone("Telefone");
        funcionario.setTipoCnh("Tipo Cnh");
        Optional<Funcionario> ofResult = Optional.of(funcionario);

        Setor setor2 = new Setor();
        setor2.setId(1L);
        setor2.setNome("Nome");

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setCpf("Cpf");
        funcionario2.setId(1L);
        funcionario2.setNome("Nome");
        funcionario2.setSetores(setor2);
        funcionario2.setTelefone("Telefone");
        funcionario2.setTipoCnh("Tipo Cnh");
        when(funcionarioRepository.save(Mockito.<Funcionario>any())).thenReturn(funcionario2);
        when(funcionarioRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Setor setor3 = new Setor();
        setor3.setId(1L);
        setor3.setNome("Nome");

        Funcionario funcionario3 = new Funcionario();
        funcionario3.setCpf("Cpf");
        funcionario3.setId(1L);
        funcionario3.setNome("Nome");
        funcionario3.setSetores(setor3);
        funcionario3.setTelefone("Telefone");
        funcionario3.setTipoCnh("Tipo Cnh");
        String content = (new ObjectMapper()).writeValueAsString(funcionario3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/funcionario/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"setor\":{\"id\":1,\"nome\":\"Nome\"},\"nome\":\"Nome\",\"cpf\":\"Cpf\",\"tipoCnh\":\"Tipo Cnh\",\"telefone\":"
                                        + "\"Telefone\"}"));
    }

    /**
     * Method under test: {@link FuncionarioController#PutFuncionario(Long, Funcionario)}
     */
    @Test
    void testPutFuncionario2() throws Exception {
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Nome");

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("Cpf");
        funcionario.setId(1L);
        funcionario.setNome("Nome");
        funcionario.setSetores(setor);
        funcionario.setTelefone("Telefone");
        funcionario.setTipoCnh("Tipo Cnh");
        when(funcionarioRepository.save(Mockito.<Funcionario>any())).thenReturn(funcionario);
        when(funcionarioRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Setor setor2 = new Setor();
        setor2.setId(1L);
        setor2.setNome("Nome");

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setCpf("Cpf");
        funcionario2.setId(1L);
        funcionario2.setNome("Nome");
        funcionario2.setSetores(setor2);
        funcionario2.setTelefone("Telefone");
        funcionario2.setTipoCnh("Tipo Cnh");
        String content = (new ObjectMapper()).writeValueAsString(funcionario2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/funcionario/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(funcionarioController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

