package com.prime.opt.dummy.project.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStockBatchRequest {

    @Valid
    @NotEmpty
    private List<NewBookEntryRequest> newBookEntryRequestList;

}
