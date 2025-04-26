package com.prime.opt.dummy.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prime.opt.dummy.project.Enum.user_enum.Course;
import com.prime.opt.dummy.project.Enum.user_enum.Designation;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
    private Roles role;
    private Course course;
    private Designation designation;
    private String phoneNo;
    private String mailId;
}
