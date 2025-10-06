package com.prime.opt.dummy.project.entity;

import com.prime.opt.dummy.project.Enum.card_enum.FineType;
import com.prime.opt.dummy.project.utils.FineTypeListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "fine_details_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fineId;

    private String cardId;

    @Convert(converter = FineTypeListConverter.class)
    private List<FineType> fineList;

}
