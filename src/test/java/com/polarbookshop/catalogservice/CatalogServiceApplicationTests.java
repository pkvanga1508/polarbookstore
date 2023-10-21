package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest (
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class CatalogServiceApplicationTests {

	private final WebTestClient webTestClient;

	@Autowired
	CatalogServiceApplicationTests(WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	@Test
	void testPostWhenBookCreated() {

		var book = new Book("1234567891", "Northen lights", "Praveen Vanga", 10.99);
		webTestClient
				.post()
				.uri("/books")
				.bodyValue(book)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(returnedBook -> {
					assertThat(returnedBook).isNotNull();
					assertThat(returnedBook.isbn()).isEqualTo(book.isbn());
				});
	}

}
