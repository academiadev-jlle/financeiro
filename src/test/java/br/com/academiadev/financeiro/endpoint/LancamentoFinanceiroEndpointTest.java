package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.FinanceiroApplication;
import br.com.academiadev.financeiro.enums.Status;
import br.com.academiadev.financeiro.enums.TipoLancamento;
import br.com.academiadev.financeiro.model.LancamentoFinanceiro;
import br.com.academiadev.financeiro.model.Usuario;
import br.com.academiadev.financeiro.repository.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinanceiroApplication.class)
public class LancamentoFinanceiroEndpointTest {

    @Autowired
    private MockMvc mvc;

    @Value("${security.oauth2.client.client-id}")
    private String CLIENT_ID;

    @Value("${security.oauth2.client.client-secret}")
    private String CLIENT_SECRET;

    @Mock
    private UsuarioRepository repository;

    @Test
    @Transactional
    @Autowired
    public void loginTest() throws Exception {
        String token = getToken("admin@admin.com", "adminadmin");
        Assertions.assertThat(token).isNotNull();
    }

    @Test
    @Transactional
    public void lancamentoFinceniroTest() throws Exception {
        Usuario usuario = getUsuario();

        mvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(usuario)))
                .andExpect(status().isOk());

        String token = getToken("docsbruno@gmail.com", "123456");

        mvc.perform(get("/usuario")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is("Administrador do sistema")))
                .andExpect(jsonPath("$[0].email", is("admin@admin.com")));

        LancamentoFinanceiro lancamentoFinanceiro = getLancamentoFinanceiro();

        mvc.perform(post("/lancamentofinanceiro")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(lancamentoFinanceiro)))
                .andExpect(status().isOk());
    }

    private LancamentoFinanceiro getLancamentoFinanceiro() {
        LancamentoFinanceiro lancamentoFinanceiro = new LancamentoFinanceiro();
        lancamentoFinanceiro.setRecebedorPagador("Casas Bahia");
        lancamentoFinanceiro.setDataEmissao(LocalDate.of(2018, 10, 10));
        lancamentoFinanceiro.setStatus(Status.PENDENTE);
        lancamentoFinanceiro.setTipolancamento(TipoLancamento.PAGAR);
        lancamentoFinanceiro.setUsuario(new Usuario(1l));
        lancamentoFinanceiro.setDataVencimento(LocalDate.of(2018, 10, 20));
        return lancamentoFinanceiro;
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

    private String getToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mvc.perform(post("/oauth/token")
                .params(params)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(CLIENT_ID, CLIENT_SECRET))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}
