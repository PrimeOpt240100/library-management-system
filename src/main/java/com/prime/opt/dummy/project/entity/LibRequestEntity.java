package com.prime.opt.dummy.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prime.opt.dummy.project.Enum.request_enum.LibRequestType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "library_requests_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibRequestEntity {

    @Id
    @Column(name = "request_id")
    private String requestId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "request_type")
    private LibRequestType requestType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "requested_date")
    private LocalDate requestedDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "processed_date")
    private LocalDate resolvedDate;

    @Column(name = "processed_by")
    private String processedBy;

}
