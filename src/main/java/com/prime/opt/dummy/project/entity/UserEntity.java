package com.prime.opt.dummy.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prime.opt.dummy.project.Enum.user_enum.Course;
import com.prime.opt.dummy.project.Enum.user_enum.Designation;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "user_details_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_dob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @Column(name = "user_role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Roles role;

    @Column(name = "user_course", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Course course;

    @Column(name = "user_position", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Designation designation;

    @Column(name = "phone_no", unique = true, nullable = false, length = 10)
    private String phoneNo;

    @Column(name = "mail_id")
    private String emailId;

}
