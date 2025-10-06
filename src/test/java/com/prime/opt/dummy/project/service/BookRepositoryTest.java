package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.repository.BookRepository;
import com.prime.opt.dummy.project.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AllArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class BookRepositoryTest {
    private final BookRepository bookRepo;
    private final CardRepository cardRepo;

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "ENG1",
            "FIC1",
            "ROM1",
            "HIS2",
            "FNT1"
    })
    public void fetchCustomBookDetails(String bookId){
        assertNotNull(bookRepo.fetchBookDetailsById(bookId));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "STUC1",
            "STFC1"
    })
    public void fetchCardEntityDetails(String cardId){
        assertNotNull(cardRepo.findByCardId(cardId));
    }
}
