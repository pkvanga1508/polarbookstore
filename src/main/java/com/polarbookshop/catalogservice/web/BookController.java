package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable("isbn") String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("isbn") String isbn) {
        bookService.removeFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    public Book update(@PathVariable("isbn") String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }
}