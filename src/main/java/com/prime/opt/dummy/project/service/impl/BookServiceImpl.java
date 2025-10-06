package com.prime.opt.dummy.project.service.impl;

import com.prime.opt.dummy.project.Enum.book_enum.Genre;
import com.prime.opt.dummy.project.Enum.book_enum.Status;
import com.prime.opt.dummy.project.constants.LibrarySystemErrorCodes;
import com.prime.opt.dummy.project.entity.BookEntity;
import com.prime.opt.dummy.project.repository.BookRepository;
import com.prime.opt.dummy.project.request.NewBookEntryRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.NewBookEntryResponse;
import com.prime.opt.dummy.project.service.BookService;
import com.prime.opt.dummy.project.service.CustomIdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private CustomIdGeneratorService idGeneratorService;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public BaseResponse<List<NewBookEntryResponse>> registerBookStock(List<NewBookEntryRequest> newBookEntryRequest) {
        List<NewBookEntryResponse> responseList = new ArrayList<>();

        for (NewBookEntryRequest request : newBookEntryRequest) {
            Genre bookGenre = request.getGenre();
            String prefix = bookGenre.getBookPrefix(bookGenre);
            int lastCount = bookRepository.findMaxIdWithPrefix(prefix);
            List<BookEntity> bookEntityList = new ArrayList<>();
            List<String> bookIdList = new ArrayList<>();
            int totalQtyAdd = request.getQty();
            NewBookEntryResponse bookResponse = prepareResponseFromRequest(request);

            for (int i = 1; i <= totalQtyAdd; i++) {
                String bookId = prefix + (lastCount + i);
                BookEntity entity = prepareEntityFromRequest(request, bookGenre);
                entity.setBookId(bookId);
                bookEntityList.add(entity);
                bookIdList.add(bookId);
            }

            try {
                bookRepository.saveAll(bookEntityList);
                bookResponse.getBookIdList().addAll(bookIdList);
                responseList.add(bookResponse);
            } catch (Exception ex) {
                // log error while saving book entities
            }
        }
        return new BaseResponse<>(LibrarySystemErrorCodes.ok_code, LibrarySystemErrorCodes.ok_msg, responseList);
    }

    private BookEntity prepareEntityFromRequest(NewBookEntryRequest request, Genre bookGenre){
        return BookEntity.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .edition(request.getEdition())
                .publisher(request.getPublisher())
                .genre(bookGenre)
                .language(request.getLanguage())
                .bookAdded(LocalDate.now())
                .status(Status.AVAILABLE)
                .build();
    }

    private NewBookEntryResponse prepareResponseFromRequest(NewBookEntryRequest request){
        return NewBookEntryResponse.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .language(request.getLanguage())
                .edition(request.getEdition())
                .publisher(request.getPublisher())
                .addedDate(LocalDate.now())
                .bookIdList(new ArrayList<>())
                .build();
    }

}
