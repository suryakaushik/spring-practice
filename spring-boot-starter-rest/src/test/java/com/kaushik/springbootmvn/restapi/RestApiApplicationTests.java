package com.kaushik.springbootmvn.restapi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MatUtilsTest {
	
	private MatUtils matUtils;
	
	
	@BeforeEach
	void initEach() {
		matUtils = new MatUtils();
	}

	@Nested
	class AddTest {
		@Test
		void testAddingTwoPositives() {
			assertEquals(2, matUtils.add(1, 1), 
					"Add method should return the sum of two numbers");
		}
		
		@Test
		void testAddingTwoNegatives() {
			assertEquals(-2, matUtils.add(-1, -1), 
					"Add method should return the sum of two numbers");
		}
		
		@Test
		void testAddingAPositiveAndANegative() {
			assertEquals(0, matUtils.add(-1, 1), 
					"Add method should return the sum of two numbers");
		}
	}
	
	@Test 
	void testMultiply() {
		assertAll(
				() -> assertEquals(0, matUtils.multiply(1, 0)),
				() -> assertEquals(1, matUtils.multiply(1, 1)),
				() -> assertEquals(6, matUtils.multiply(2, 3))
				);
	}
	
	@Test 
	void computeCircleArea() {
		assertEquals(314.1592653589793, matUtils.computeCircleArea(10), 
				"Should return right circle area");
	}
	
	@Test
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> matUtils.divide(1, 0), 
				"Divide should throw ArithmeticException when denominator is zero");
	}
	
	
}