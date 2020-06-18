package aula20200616;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mySpringBootApp.livro.Livro;
import mySpringBootApp.livro.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TesteApiLivro {

	final String BASE_PATH = "http://localhost:8080/api/livros";

	@Autowired
	private LivroRepository repository;
	
    private RestTemplate restTemplate;
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void setUp() throws Exception {
        repository.deleteAll();

        repository.save(new Livro("1", "A volta dos que não foram", 100));
        repository.save(new Livro("2", "As tranças de um careca", 250));
        repository.save(new Livro("3", "Poeira em alto mar", 50));        
       
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void testPostLivro() throws JsonProcessingException {
        restTemplate.delete(BASE_PATH + "/livros");

        Livro livro1 = new Livro("1", "A volta dos que não foram", 100);
        Livro response = restTemplate.postForObject(BASE_PATH, livro1, Livro.class);
        Assert.assertEquals("A volta dos que não foram", response.getTitulo());
    }
    
    @Test
    public void testFindOne() throws IOException {
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Livro> livros = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Livro.class));
        Livro livro1 = restTemplate.getForObject(BASE_PATH + "/" + livros.get(0).getId(), Livro.class);
        Assert.assertNotNull(livro1);
    	Assert.assertEquals("A volta dos que não foram", livro1.getTitulo());
    	Assert.assertEquals(100, livro1.getNumeroDePaginas());
    }
    
    @Test
    public void testUpdateLivro() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Livro> livros = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Livro.class));
        
        Livro livro2 = restTemplate.getForObject(BASE_PATH + "/" + livros.get(1).getId(), Livro.class);
        livro2.setTitulo("A Origem");
        restTemplate.put(BASE_PATH, livro2);
        
        Livro livroUpdate = restTemplate.getForObject(BASE_PATH + "/" + livros.get(1).getId(), Livro.class);
        Assert.assertNotNull(livroUpdate);
    	Assert.assertEquals("A Origem", livroUpdate.getTitulo());
    	Assert.assertEquals(250, livroUpdate.getNumeroDePaginas());
    }
    
    @Test
    public void testFindAll() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Livro> livros = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Livro.class));
    	Assert.assertEquals("A Origem", livros.get(1).getTitulo());
    	Assert.assertEquals(250, livros.get(1).getNumeroDePaginas());
    }
}
