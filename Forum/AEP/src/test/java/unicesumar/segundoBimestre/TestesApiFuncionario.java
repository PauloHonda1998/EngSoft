package unicesumar.segundoBimestre;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.NotFoundException;

@WebMvcTest
@AutoConfigureMockMvc
class TestesApiFuncionario {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FuncionarioRepository repo;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void testeGetByIdNãoExistente() throws Exception {
		when(repo.findById("1")).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/api/funcionarios/47623742")).andExpect(status().isNotFound());
	}
	
	@Test
	void testeGetByIdExistente() throws Exception {
		Funcionario luis = new Funcionario("1", "Luis", "4836423242", 1234, "");		
		when(repo.findById("1")).thenReturn(luis);
		
		mockMvc.perform(get("/api/funcionario/1"))
		.andExpect(jsonPath("$.id").value("1"))
		.andExpect(jsonPath("$.nome").value("luis"))
		.andExpect(jsonPath("$.cpf").value("4836423242"))
		.andExpect(jsonPath("$.matricula").value(1234))
		.andExpect(jsonPath("$.email").value(""))
		.andExpect(status().isOk());
	}
	
	@Test
	void testeFindAll() throws Exception {
		Funcionario func1 = new Funcionario("1", "luis", "4238472432", 1234, "");		
		Funcionario func2 = new Funcionario("2", "fernando", "234723842", 3214, "");		
		Funcionario func3 = new Funcionario("3", "fabio", "3462734243", 45354, "");

		when(repo.findAll()).thenReturn(Arrays.asList(func1, func2, func3));
		
		mockMvc.perform(get("/api/funcionarios"))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$.[0].id").value("1"))
		.andExpect(jsonPath("$.[1].id").value("2"))
		.andExpect(jsonPath("$.[2].id").value("3"))
		.andExpect(jsonPath("$.[0].nome").value("luis"))
		.andExpect(jsonPath("$.[1].nome").value("fernando"))
		.andExpect(jsonPath("$.[2].nome").value("fabio"))
		.andExpect(status().isOk());
	}
	@Test
	void testePost() throws Exception {
		when(repo.save(ArgumentMatchers.any(Funcionario.class))).thenReturn("1");
		
		Map<String, String> funcionario = new HashMap<String, String>() {{
		    put("id", "1");
		    put("nome", "luis");
		}

		private void put(String key, double d) {
			
		}};
		
		String json = objectMapper.writeValueAsString(funcionario);
		
		mockMvc.perform(post("/api/funcionarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isCreated())
		.andExpect(content().string("1"));
	}
	
	@Test
	public void testePut() throws Exception {
		
		Map<String, String> funcionario = new HashMap<String, String>() {{
		    put("id", "1");
		    put("nome", "luis");
		}

		private void put(String key, double d) {
			
			
		}};
		
		String json = objectMapper.writeValueAsString(funcionario);
		
	  mockMvc.perform( MockMvcRequestBuilders
	      .put("/funcionarios/{id}", 1)
	      .contentType(json)
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[0]").value("1555"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(5656));
	}
	
	@Test
	public void deleteEmployeAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/funcionarios/{id}", 1) )
	        .andExpect(status().isAccepted());
	}
}
