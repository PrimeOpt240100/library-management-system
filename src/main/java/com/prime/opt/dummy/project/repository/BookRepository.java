package com.prime.opt.dummy.project.repository;

import com.prime.opt.dummy.project.constants.QueryConstant;
import com.prime.opt.dummy.project.entity.BookEntity;
import com.prime.opt.dummy.project.model.CustomBookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    @Query(value = QueryConstant.FIND_MAX_BOOK_ID_BY_PREFIX_QUERY, nativeQuery = true)
    Integer findMaxIdWithPrefix(@Param("prefix") String prefix);

    @Query(value = QueryConstant.FETCH_BOOK_DETAILS_BY_BOOK_ID)
    CustomBookDetails fetchBookDetailsById(@Param("bookId") String bookId);

    @Modifying
    @Query(value = QueryConstant.UPDATE_AVAILABLE_QTY_BY_BOOK_ID, nativeQuery = true)
    void updateBookQtyById(@Param("bookId") String bookId);

}
