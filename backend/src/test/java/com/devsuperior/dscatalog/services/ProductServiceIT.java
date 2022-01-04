package com.devsuperior.dscatalog.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundExeception;

@SpringBootTest
@Transactional
public class ProductServiceIT {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	private Long CountTotalProducts;
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 1000L;
		CountTotalProducts = 25L;				
	}
	
	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {
		
		service.delete(existingId);
		
		Assertions.assertEquals(CountTotalProducts - 1, repository.count());
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExeceptionWhenDoesNotIdExists() {
		
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.delete(nonExistingId);
		});
	}
	
	/*
	@Test
	public void findAllPagedShouldReturnPageWhenPage0Size10(){
		PageRequest pageRequest = PageRequest.of(0, 10);
		
		//Page<ProductDTO> result =  service.findAllPaged(pageRequest);
		Page<ProductDTO> result = service.findAllPaged(0L, "", pageRequest);
		
		assertFalse(result.isEmpty());
		assertEquals(0, result.getNumber());
		assertEquals(10, result.getSize());
		assertEquals(CountTotalProducts, result.getTotalElements());
		
	}
	
	@Test
	public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist(){
		PageRequest pageRequest = PageRequest.of(50, 10);
		
		//Page<ProductDTO> result =  service.findAllPaged(pageRequest);
		Page<ProductDTO> result = service.findAllPaged(0L, "", pageRequest);
		
		assertTrue(result.isEmpty());
		
	}
	
	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortByName(){
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));
		
		//Page<ProductDTO> result =  service.findAllPaged(pageRequest);
		Page<ProductDTO> result = service.findAllPaged(0L, "", pageRequest);
		
		assertFalse(result.isEmpty());
		assertEquals("Macbook Pro", result.getContent().get(0).getName());
		assertEquals("PC Gamer", result.getContent().get(1).getName());
		assertEquals("PC Gamer Alfa", result.getContent().get(2).getName());
		
	}*/
	

}
