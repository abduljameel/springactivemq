package com.jameel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringMQTest extends AbstractTest {

	@Autowired
	private OrderedProductService service;

	@MockBean
	private ProductRepository repository;

	@Test
	public void getProductbyIdTest() throws RecordNotFoundException{
		Long id = 1L;
		OrderedProduct prd1 = new OrderedProduct();
		prd1.setId(1L);
		prd1.setProductName("Cable");
		prd1.setPrice(2);
		prd1.setPrdCount(2);
		Optional<OrderedProduct> optprd1 = Optional.of(prd1);
		
		OrderedProduct prd2 = new OrderedProduct();
		prd1.setId(2L);
		prd1.setProductName("Desktop");
		prd1.setPrice(2);
		prd1.setPrdCount(4000);
		Optional<OrderedProduct> optprd2 = Optional.of(prd2);
		
		when(repository.findById(id)).thenReturn(optprd1);
		assertEquals(prd1.getId(), service.getProductById(1L).getId());
	}

	@Test
	public void saveProductTest() throws RecordNotFoundException{
		Long id = 1L;
		OrderedProduct prd1 = new OrderedProduct();
		prd1.setId(1L);
		prd1.setProductName("Cable");
		prd1.setPrice(2);
		prd1.setPrdCount(2);
		Optional<OrderedProduct> optprd1 = Optional.of(prd1);
		
		OrderedProduct prd2 = new OrderedProduct();
		prd1.setId(2L);
		prd1.setProductName("Desktop");
		prd1.setPrice(2);
		prd1.setPrdCount(4000);
		Optional<OrderedProduct> optprd2 = Optional.of(prd2);
		
		when(repository.findById(id)).thenReturn(optprd2);
		when(repository.save(prd2)).thenReturn(prd2);
		assertEquals(prd2, service.saveProduct(prd2));
		
	}
	
	@Test
	public void createProduct() throws Exception {
		String uri = "/product/send";
		OrderedProduct product = new OrderedProduct();
		product.setId(3L);
		product.setProductName("Cable");
		product.setPrice(2);
		product.setPrdCount(2);

		String inputJson = super.mapToJson(product);
		System.out.println("The JSON sent is : " + product.toString());
		super.setUp();
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


}
