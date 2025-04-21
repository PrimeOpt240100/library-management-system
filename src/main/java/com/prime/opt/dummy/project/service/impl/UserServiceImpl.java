package com.prime.opt.dummy.project.service.impl;

import com.prime.opt.dummy.project.Enum.user_enum.Course;
import com.prime.opt.dummy.project.Enum.user_enum.Designation;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import com.prime.opt.dummy.project.entity.UserEntity;
import com.prime.opt.dummy.project.repository.UserRepository;
import com.prime.opt.dummy.project.request.AddUserRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.AddUserResponse;
import com.prime.opt.dummy.project.service.CustomIdGeneratorService;
import com.prime.opt.dummy.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomIdGeneratorService idGeneratorService;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseResponse<AddUserResponse> addNewUser(AddUserRequest addUserRequest) {
        String newUserId = idGeneratorService.generateCustomUserId(addUserRequest.getRole());
        UserEntity newUserEntity = getUserEntity(addUserRequest, newUserId);
        UserEntity addedUser = userRepository.save(newUserEntity);

        AddUserResponse addUserResponse = AddUserResponse.builder()
                .id(addedUser.getUserId())
                .name(addedUser.getName())
                .course(addedUser.getCourse())
                .mailId(addedUser.getEmailId())
                .phoneNo(addedUser.getPhoneNo())
                .role(addedUser.getRole())
                .build();

        return new BaseResponse<>(0,"OK",addUserResponse);
    }

    private UserEntity getUserEntity(AddUserRequest addUserRequest, String newUserId) {
        UserEntity newUserEntity = new UserEntity();

        newUserEntity.setUserId(newUserId);
        newUserEntity.setName(addUserRequest.getName());
        newUserEntity.setRole(addUserRequest.getRole());
        newUserEntity.setDob(addUserRequest.getDob());
        newUserEntity.setCourse(addUserRequest.getRole()==Roles.LIBRARIAN ? Course.NA : addUserRequest.getCourse());
        newUserEntity.setDesignation(addUserRequest.getRole()==Roles.STAFF ? addUserRequest.getDesignation() : Designation.NA);
        newUserEntity.setPhoneNo(addUserRequest.getPhoneNo());
        newUserEntity.setEmailId(addUserRequest.getMailId());
        return newUserEntity;
    }

}
