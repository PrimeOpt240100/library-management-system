package com.prime.opt.dummy.project.response;

import com.prime.opt.dummy.project.Enum.user_enum.Course;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserResponse {
    private String id;
    private String name;
    private Roles role;
    private Course course;
    private String phoneNo;
    private String mailId;
}
