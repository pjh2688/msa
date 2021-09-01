package com.senchaMsa.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.senchaMsa.domain.Product;
import com.senchaMsa.domain.ProductSearch;
import com.senchaMsa.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductApiController {

	private final ProductService productService;
	
	/* 1-1. 상품 전체 조회  */
	@GetMapping("/products")
	public ResponseEntity<Map<String, Object>> readProducts(@ModelAttribute("searchValue") ProductSearch productSearch) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 1-2. 기본
//		List<Product> products = productService.findProductsService();
		
		// 1-3. 검색 조건 추가
		List<Product> products = productService.searchProductsService(productSearch);
		
		result.put("totalCount", products.size());
		result.put("data", products);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
	/* 2-1. 상품 정보 생성 */
	@PostMapping("/products")
	public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {  // 2-2. 폼에서 날라온 데이터를 member에서 받는다.(json 데이터이느 @RequestBody 어노테이션으로 받는다.)
		Map<String, Object> result = new HashMap<String, Object>();
		
		Product createdProduct = productService.saveProductService(product);
		
		result.put("data", createdProduct);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
	/* 3. 상품 단건 조회 by id */
	@GetMapping("/products/{id}")
	public ResponseEntity<Map<String, Object>> readProductById(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Product product = productService.findProductService(id).get();
		
		result.put("data", product);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
	/* 4. 상품 정보 수정 */
	@PutMapping("/products/{id}")
	public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {	
		Map<String, Object> result = new HashMap<String, Object>();
		
		Product product = productService.findProductService(id).get();
		
		product.setName(productDetails.getName());
		product.setPrice(productDetails.getPrice());
		product.setStock(productDetails.getStock());
		
		Product updateProduct = productService.saveProductService(product);
		
		result.put("data", updateProduct);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
	
	/* 5. 상품 정보 삭제 */
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Object>> deleteProductById(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Product product = productService.findProductService(id).get();
		
		productService.deleteProductService(product);
		
		result.put("deleted", Boolean.TRUE);
		result.put("code", 200);
		
		return ResponseEntity.ok(result);
	}
}
