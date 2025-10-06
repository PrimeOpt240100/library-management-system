package com.prime.opt.dummy.project.constants;

public interface QueryConstant {

    // user query
    String FIND_MAX_USER_ID_BY_PREFIX_QUERY = "select count(user_id) from user_details_table where user_id like CONCAT(:prefix, '%')";
    String FETCH_USER_DETAILS_BY_USER_ID = "SELECT new com.prime.opt.dummy.project.model.CustomUserDetails(" + "u.name, " + "u.role, " + "u.course, " + "u.designation, " + "u.phoneNo" + ") " + "FROM UserEntity u " + "WHERE u.userId = :userId";

    // card query
    String FIND_MAX_CARD_ID_BY_PREFIX_QUERY = "select count(card_id) from card_details_table where card_id like CONCAT(:prefix, '%')";
    String FETCH_CARD_DETAILS_BY_USER_ID = " SELECT new com.prime.opt.dummy.project.response.NewCardResponse(c.cardId, c.name, c.cardType, c.issuedDate, c.validDate) FROM CardEntity c WHERE c.userId = :userId";
    String IS_HAVING_FINE_ON_CARD = "select fine from card_details_table where card_id = :cardId";

    // book query
    String FIND_MAX_BOOK_ID_BY_PREFIX_QUERY = "select count(book_id) from book_details_table where book_id like CONCAT(:prefix, '%')";
    String FETCH_BOOK_DETAILS_BY_BOOK_ID = "SELECT new com.prime.opt.dummy.project.model.CustomBookDetails(b.bookId, b.name, b.author, b.edition, b.language, b.status) FROM BookEntity b WHERE b.bookId = :bookId";
    String UPDATE_AVAILABLE_QTY_BY_BOOK_ID = "UPDATE book_details_table SET available_qty = available_qty-1 WHERE book_id = :bookId AND available_qty>0";

    // request query
    String FIND_MAX_REQUEST_ID_BY_PREFIX_QUERY = "select count(request_id) from library_requests_table where request_id like CONCAT(:prefix, '%')";

}
