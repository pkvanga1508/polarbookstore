package com.polarbookshop.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

//record - immutable object
public record Book (
        @NotBlank(message = "Book ISBN must be provided")
        @Pattern(
                regexp = "^([0-9]{10}| [0-9]{13})$",
                message = "ISBN should be 10/13 Chars in length"
        )
        String isbn,
        @NotBlank(message = "Book Title must be provided")
        String title,
        @NotBlank(message = "Book Author must be provided")
        String author,
        @NotNull(message = "Book Price must be provided")
        @Positive(message = "Book Value should be positive")
        Double price
) {}


