package com.prime.opt.dummy.project.constants;

public interface QueryConstant {

    String FIND_MAX_USER_ID_BY_PREFIX_QUERY = "select count(user_id) from user_details_table where user_id like CONCAT(:prefix, '%')";

    String FIND_MAX_BOOK_ID_BY_PREFIX_QUERY = "select count(book_id) from book_details_table where book_id like CONCAT(:prefix, '%')";

}
