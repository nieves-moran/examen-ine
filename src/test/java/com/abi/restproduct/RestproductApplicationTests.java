package com.abi.restproduct;

import com.abi.restproduct.entity.Product;
import com.abi.restproduct.repository.ProductRepository;
import com.abi.restproduct.service.ProductServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class RestproductApplicationTests {

	// prueba del paquete util 
	@Test 
	public void testFirstElementCorrect(){
		List<Integer> l = new LinkedList<Integer>(); 
		l.add(1); 
		l.add(3); 
		assertEquals(l.get(0),1);
	}
 

	// Prueba de capa controller
	@Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
	
	@Test
	public void testPostEndpointIsOk() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		String jsonProduct = "{\"id\":5,\"name\":\"Tale of two cities\",\"description\":\"Libro de Charles Dickens\",\"price\":20.30,\"quantity\":30}"; 
        this.mockMvc.perform(get("/product",jsonProduct))
            .andExpect(status().isOk()); 
	}


	// Prueba de capa Service 
	@Mock ProductRepository productRepoMock;
	@InjectMocks ProductServiceImpl productServMock; 

	@Test 
	public void testReadFromRepoEqualReadFromService(){
		List<Product> products = new LinkedList<Product>(); 
		when(productRepoMock.findAll()).thenReturn(products);  
		List<Product> result = productServMock.read();  
		assertEquals(products,result); 
	}
	



	void contextLoads() {
		Assertions.assertTrue(true);
	}


}
