package com.ramesh.shopping.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramesh.shopping.dto.AccountInfomation;
import com.ramesh.shopping.dto.CartDto;
import com.ramesh.shopping.entity.Cart;
import com.ramesh.shopping.dto.Credentials;
import com.ramesh.shopping.dto.OrderDetails;
import com.ramesh.shopping.dto.Register;
import com.ramesh.shopping.entity.OrderSummery;
import com.ramesh.shopping.entity.Product;
import com.ramesh.shopping.entity.ProductType;
import com.ramesh.shopping.service.ProductService;

@RestController
@RequestMapping("shopping")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@PostMapping("/{register}")
	public ResponseEntity<Credentials> register(@RequestBody Register register){
		return new ResponseEntity<>(service.addUser(register), HttpStatus.CREATED);
	}
	@PostMapping("/payment/{fromAccountId}")
	public ResponseEntity<OrderDetails> makePayment(@PathVariable("fromAccountId") long fromAccountId){
		return new ResponseEntity<>(service.makePayment(fromAccountId), HttpStatus.OK);
	}
	@PostMapping("/{login}")
	public ResponseEntity<AccountInfomation> login(@RequestBody Credentials dto){
		return new ResponseEntity<>(service.login(dto), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> findAllProducts(){
		return new ResponseEntity<>(service.findAllProducts(),HttpStatus.OK);
	}
	@GetMapping("/{productType}")
	public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable("productType") ProductType productType ){
		return new ResponseEntity<>(service.findProductsByCategory(productType),HttpStatus.OK);
	}
	@GetMapping("/history")
	public ResponseEntity<List<OrderSummery>> findMyOrders(){
		return new ResponseEntity<>(service.findMyOrders(),HttpStatus.OK);
	}
	@GetMapping("/{fromDate}/{toDate}")
	public ResponseEntity<List<OrderSummery>> findOrdersBetweenGivenDates(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate){
		return new ResponseEntity<>(service.findOrdersBetweenGivenDates(LocalDate.parse(fromDate),
				LocalDate.parse(toDate)),HttpStatus.OK);
	}
	@PostMapping("/cart/{productId}")
	public ResponseEntity<CartDto> addProductToCart(@PathVariable("productId")String productId){
		return new ResponseEntity<>(service.addProductToCart(productId),HttpStatus.CREATED);
	}
	@DeleteMapping("{cartId}/{productId}")
	public ResponseEntity<String> removeProductFromCart(@PathVariable("cartId") long cartId,
			@PathVariable("productId") long productId){
		return new ResponseEntity<>(service.removeProductFromCart(cartId, productId),HttpStatus.OK);
	}
	@PutMapping("/{cartId}/{productId}")
	public ResponseEntity<String> updateCart(@PathVariable("cartId") long cartId,
			@PathVariable("productId") long productId){
		return new ResponseEntity<>(service.updateCart(cartId, productId),HttpStatus.OK);
	}
	

}
