package br.com.academiadev.financeiro.endpoint;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.academiadev.financeiro.enums.Status;
import br.com.academiadev.financeiro.enums.TipoLancamento;
import br.com.academiadev.financeiro.model.LancamentoFinanceiro;
import br.com.academiadev.financeiro.model.Usuario;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class LancamentoFinanceiroEndpointTest {

    @Autowired
    private MockMvc mvc;

    @Ignore
    @Test
    @Transactional
    public void lancamentoFinceniroTest() throws Exception {
        Usuario usuario = getUsuario();
        ResultActions performCreate = mvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(convertObjectToJsonBytes(usuario))).andExpect(status().isOk());

        ResultActions performGET = mvc.perform(get("/usuario").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
        performGET.andExpect(jsonPath("$[0].nome", is("Augusto da Silva")));
        performGET.andExpect(jsonPath("$[0].email", is("docsbruno@gmail.com")));

        LancamentoFinanceiro lancamentoFinanceiro = new LancamentoFinanceiro();
        lancamentoFinanceiro.setRecebedorPagador("Casas Bahia");
        lancamentoFinanceiro.setDataEmissao(LocalDate.of(2018, 10, 10));
        lancamentoFinanceiro.setStatus(Status.PENDENTE);
        lancamentoFinanceiro.setTipolancamento(TipoLancamento.PAGAR);
        lancamentoFinanceiro.setUsuario(new Usuario(1l));
        lancamentoFinanceiro.setDataCriacao(LocalDate.of(2018, 10, 10));
        lancamentoFinanceiro.setDataVencimento(LocalDate.of(2018, 10, 20));
        ResultActions post = mvc.perform(post("/lancamentofinanceiro").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(convertObjectToJsonBytes(usuario))).andExpect(status().isOk());
    }

    private Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Augusto da Silva");
        usuario.setSenha("123456");
        usuario.setEmail("docsbruno@gmail.com");
        return usuario;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }
}
