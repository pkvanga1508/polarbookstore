package com.polarbookshop.catalogservice.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class BookValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAllFieldValuesCorrect() {
        var book = new Book("1234567890", "Northern Lights", "Praveen Vanga", 15.99);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void testViolationsFieldValuesIncorrect() {
        var book = new Book("12345678901", " ", "", -15.99);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isNotEmpty();
        assertThat(violations).hasSize(4);
    }
}
