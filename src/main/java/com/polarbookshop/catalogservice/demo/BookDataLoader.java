package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@ConditionalOnProperty(value = "polar.testdata.enabled", havingValue = "true")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class) //Application Start phase is complete
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234675764", "Northern Lights", "Praveen Vanga", 10.99, "Manning");
        var book2 =  Book.of("1234673764", "Northern Lights 2", "Praveen Kumar Vanga", 12.99, "Pearson");
        bookRepository.saveAll(List.of(book1, book2)); //List.of, Set.of, Map.of only works JDK 11 and above
    }
}
