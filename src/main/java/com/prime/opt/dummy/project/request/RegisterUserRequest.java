package com.prime.opt.dummy.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prime.opt.dummy.project.Enum.user_enum.Course;
import com.prime.opt.dummy.project.Enum.user_enum.Designation;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "Name can't be blank or null")
    private String name;

    @NotNull(message = "Role must not be null")
    private Roles role;

    @NotNull(message = "Course must not be null")
    private Course course;

    @NotNull(message = "Designation must not be null")
    private Designation designation;

    @NotBlank(message = "Phone number can't be blank")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phoneNo;

    private String mailId;

    @NotBlank(message = "Set the password")
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "DOB must be a past date")
    private LocalDate dob;

}
