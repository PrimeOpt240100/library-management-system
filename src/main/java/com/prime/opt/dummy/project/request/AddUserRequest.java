package com.prime.opt.dummy.project.request;

import com.prime.opt.dummy.project.Enum.user_enum.Course;
import com.prime.opt.dummy.project.Enum.user_enum.Designation;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String name;
    private Date dob;
    private Roles role;
    private Course course;
    private Designation designation;
    private String phoneNo;
    private String mailId;
}
