package com.prime.opt.dummy.project.constants;

public interface QueryConstant {

    String FIND_MAX_USER_ID_BY_PREFIX_QUERY = "select count(user_id) from user_details_table where user_id like CONCAT(:prefix, '%')";

    String FIND_MAX_BOOK_ID_BY_PREFIX_QUERY = "select count(book_id) from book_details_table where book_id like CONCAT(:prefix, '%')";

    String FIND_MAX_CARD_ID_BY_PREFIX_QUERY = "select count(card_id) from card_details_table where card_id like CONCAT(:prefix, '%')";

    String FETCH_USER_DETAILS_BY_USER_ID =
            "SELECT new com.prime.opt.dummy.project.model.UserDetails(" +
                    "u.name, " +
                    "u.role, " +
                    "u.course, " +
                    "u.designation, " +
                    "u.phoneNo" +
                    ") " +
                    "FROM UserEntity u " +
                    "WHERE u.userId = :userId";


}
