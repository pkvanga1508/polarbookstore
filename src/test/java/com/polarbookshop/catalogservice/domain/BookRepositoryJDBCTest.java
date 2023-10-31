package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class) //This is for enabling Auditing purposes
//Disables the default behaviour of relying on of an embedded test database since we are using testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryJDBCTest {

    @Autowired
    private BookRepository bookRepository;

    //JDBCAggregateTemplate is used to prepare the data targeted by the test
    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234567898";
        var book = Book.of(bookIsbn, "Northen Lights", "Praveen Kumar", 12.99, "Manning");
        jdbcAggregateTemplate.insert(book); //Data preparation
        Optional<Book> actualBook = bookRepository.findBookByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(bookIsbn);
    }
}
