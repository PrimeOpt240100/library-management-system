package com.prime.opt.dummy.project.model;

import com.prime.opt.dummy.project.Enum.user_enum.Course;
import com.prime.opt.dummy.project.Enum.user_enum.Designation;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetails {
    private String name;
    private Roles roles;
    private Course course;
    private Designation designation;
    private String phoneNo;

    public UserDetails(String name, Roles roles, Course course, Designation designation, String phoneNo) {
        this.name = name;
        this.roles = roles;
        this.course = course;
        this.designation = designation;
        this.phoneNo = phoneNo;
    }

}
