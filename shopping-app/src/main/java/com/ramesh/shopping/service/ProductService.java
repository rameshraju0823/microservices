package com.ramesh.shopping.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramesh.shopping.client.Payment;
import com.ramesh.shopping.dto.AccountInfomation;
import com.ramesh.shopping.dto.CartDto;
import com.ramesh.shopping.dto.Credentials;
import com.ramesh.shopping.dto.OrderDetails;
import com.ramesh.shopping.dto.Products;
import com.ramesh.shopping.dto.Register;
import com.ramesh.shopping.entity.OrderSummery;
import com.ramesh.shopping.entity.PaymentStatus;
import com.ramesh.shopping.entity.Product;
import com.ramesh.shopping.entity.ProductType;
import com.ramesh.shopping.entity.Status;
import com.ramesh.shopping.entity.User;
import com.ramesh.shopping.entity.Cart;
import com.ramesh.shopping.exceptions.AuthenticationException;
import com.ramesh.shopping.exceptions.DuplicateUserException;
import com.ramesh.shopping.exceptions.NoSuchUserException;
import com.ramesh.shopping.exceptions.ProductExistsException;
import com.ramesh.shopping.exceptions.ProductNotExistsException;
import com.ramesh.shopping.exceptions.TransactionFailedException;
import com.ramesh.shopping.exceptions.UnHandledCartException;
import com.ramesh.shopping.repository.CartRepository;
import com.ramesh.shopping.repository.OrderHistoryRepository;
import com.ramesh.shopping.repository.ProductRepository;
import com.ramesh.shopping.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private OrderHistoryRepository historyRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private Payment payment;
	@Autowired
	private UserRepository userRepository;
	
	 public User getUserByEmail(String email) {
	        if (userRepository.existsUserByEmail(email))
	            return userRepository.findByEmail(email);
	        else {
	            log.info("User doesn't exist");
	            throw new NoSuchUserException("User cannot be found");
	        }
	    }

	public Credentials addUser(Register register) {
		if (userRepository.existsUserByEmail(register.getEmail()))
			throw new DuplicateUserException("User already registered");
		User user = User.builder()
				.name(register.getName())
				.email(register.getEmail())
				.gender(register.getGender())
				.password(register.getPassword())
				.build();
		userRepository.save(user);
		return Credentials.builder().email(user.getEmail()).password(user.getPassword()).build();

	}
	
	public AccountInfomation login(Credentials credentials) {
		User user = getUserByEmail(credentials.getEmail());
        if (user.getPassword().equals(credentials.getPassword()))
            return AccountInfomation.builder()
                    .email(user.getEmail())
                    .userName(user.getName())
                    .genderType(user.getGender())
                    .build();
        else {
            log.info("Authentication failed");
            throw new AuthenticationException("Password is wrong");
        }
    }

	public List<Product> findAllProducts() {
		log.info("Got all products");
		return repository.findAll();
	}

	public List<Product> findProductsByCategory(ProductType productType) {
		log.info("Got all products based on type");
		return repository.findByProductType(productType);
	}

	public List<OrderSummery> findMyOrders() {
		log.info("Got all orders");
		return historyRepository.findAll();
	}

	public List<OrderSummery> findOrdersBetweenGivenDates(LocalDate fromDate, LocalDate toDate) {
		log.info("Got all orders between " + fromDate + " and " + toDate);
		return historyRepository.findByOrderedDateBetween(fromDate, toDate);
	}

	public CartDto addProductToCart(String productId) {
		if (cartRepository.findAll().isEmpty()) {
			List<Product> products = new ArrayList<>();
			products.add(repository.getById(Long.parseLong(productId)));
			double total = repository.getById(Long.parseLong(productId)).getPrice();
			Cart cart = Cart.builder().products(products).total(total).build();
			cartRepository.save(cart);
			log.info("added products to cart");
			return CartDto.builder().productId(Long.parseLong(productId)).total(total).build();
		} else {
			throw new UnHandledCartException("Please remove items from old cart or make payment to continue");
		}

	}

	public String updateCart(long cartId, long productId) {
		Cart cart = cartRepository.findById(cartId).get();
		List<Product> products = cart.getProducts();
		double total = cart.getTotal();
		if (!products.contains(repository.getById(productId))) {
			products.add(repository.getById(productId));
			total += repository.getById(productId).getPrice();
			cart.setProducts(products);
			cart.setTotal(total);
			cartRepository.save(cart);
			log.info("added products to cart");
			return "UpdatedSucessfully";
		} else {
			throw new ProductExistsException("Product Already exists in cart");
		}
	}

	public String removeProductFromCart(long cartId, long productId) {
		Cart cart = cartRepository.findById(cartId).get();
		List<Product> products = cart.getProducts();
		double total = cart.getTotal();
		if (products.contains(repository.getById(productId))) {
			products.remove(repository.getById(productId));
			total = total - repository.getById(productId).getPrice();
			cart.setProducts(products);
			cart.setTotal(total);
			cartRepository.save(cart);
			if (total == 0) {
				cartRepository.delete(cart);
			}
			log.info("removed product from cart");
			return "RemovedSucessfully";
		} else {
			throw new ProductNotExistsException("Product not exists in cart");
		}
	}

	public OrderDetails makePayment(long fromAccountId) {
		List<Cart> carts = cartRepository.findAll();
		Cart cart = carts.get(0);
		double amount = cart.getTotal();
		long toAccountId = 4;
		String message = payment.payment(fromAccountId, toAccountId, amount).getBody();
		List<Product> products = cart.getProducts();
		List<Product> productsForOrder = new ArrayList<>();
		for (Product product : products)
			productsForOrder.add(product);
		if (message.equals("transaction successful")) {
			OrderSummery history = OrderSummery.builder().products(productsForOrder).orderedDate(LocalDate.now())
					.orderedTime(LocalTime.now()).orderStatus(Status.OrderConfirmed)
					.paymentStatus(PaymentStatus.PaymentSuccess).total(cart.getTotal()).build();
			historyRepository.save(history);
			cartRepository.delete(cart);
			List<Products> productDtos = new ArrayList<>();
			for (Product product : history.getProducts()) {
				productDtos.add(Products.builder().brand(product.getBrand()).modelName(product.getModelName())
						.price(product.getPrice()).build());
			}
			return OrderDetails.builder().products(productDtos).total(history.getTotal()).build();
		} else {
			throw new TransactionFailedException("transaction failed");
		}
	}
}
