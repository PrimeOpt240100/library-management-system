package com.prime.opt.dummy.project.service.impl;

import com.prime.opt.dummy.project.Enum.book_enum.Status;
import com.prime.opt.dummy.project.entity.BookEntity;
import com.prime.opt.dummy.project.repository.BookRepository;
import com.prime.opt.dummy.project.request.AddBookRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.AddBookResponse;
import com.prime.opt.dummy.project.service.BookService;
import com.prime.opt.dummy.project.service.CustomIdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private CustomIdGeneratorService idGeneratorService;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public BaseResponse<AddBookResponse> addNewBook(AddBookRequest addBookRequest) {
        String newBookId = idGeneratorService.generateCustomBookId(addBookRequest.getGenre());
        BookEntity newBookEntity = createNewBookEntity(addBookRequest, newBookId);
        BookEntity addedBook = bookRepository.save(newBookEntity);

        AddBookResponse bookResponse = AddBookResponse.builder()
                .bookId(addedBook.getBookId())
                .name(addedBook.getName())
                .author(addedBook.getAuthor())
                .publisher(addedBook.getPublisher())
                .edition(addedBook.getEdition())
                .language(addedBook.getLanguage())
                .addedDate(addedBook.getBookAdded())
                .build();

        return new BaseResponse<>(0,"OK", bookResponse);
    }

    private BookEntity createNewBookEntity(AddBookRequest request, String bookId){
        BookEntity bookEntity = new BookEntity();

        bookEntity.setBookId(bookId);
        bookEntity.setName(request.getName());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setPublisher(request.getPublisher());
        bookEntity.setEdition(request.getEdition());
        bookEntity.setGenre(request.getGenre());
        bookEntity.setLanguage(request.getLanguage());
        bookEntity.setStatus(Status.AVAILABLE);
        bookEntity.setTotalCopies(request.getTotalCopies());
        bookEntity.setAvailableCopies(request.getTotalCopies());
        bookEntity.setBookAdded(LocalDate.now());

        return bookEntity;
    }

}
