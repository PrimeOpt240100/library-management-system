package com.prime.opt.dummy.project.repository;

import com.prime.opt.dummy.project.constants.QueryConstant;
import com.prime.opt.dummy.project.entity.CardEntity;
import com.prime.opt.dummy.project.response.NewCardResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<CardEntity, String> {

    @Query(value = QueryConstant.FIND_MAX_CARD_ID_BY_PREFIX_QUERY, nativeQuery = true)
    Integer findMaxIdWithPrefix(@Param("prefix") String prefix);

    @Query(value = QueryConstant.FETCH_CARD_DETAILS_BY_USER_ID)
    NewCardResponse fetchCardDetailsByUserId(@Param("userId") String userId);
}
