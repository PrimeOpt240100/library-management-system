package com.prime.opt.dummy.project.service.impl;

import com.prime.opt.dummy.project.Enum.user_enum.Course;
import com.prime.opt.dummy.project.Enum.user_enum.Designation;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import com.prime.opt.dummy.project.constants.LibrarySystemErrorCodes;
import com.prime.opt.dummy.project.entity.UserEntity;
import com.prime.opt.dummy.project.model.CustomUserDetails;
import com.prime.opt.dummy.project.repository.UserRepository;
import com.prime.opt.dummy.project.request.RegisterUserRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.RegisterUserResponse;
import com.prime.opt.dummy.project.service.CustomIdGeneratorService;
import com.prime.opt.dummy.project.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final CustomIdGeneratorService idGeneratorService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder, CustomIdGeneratorService idGeneratorService, UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.idGeneratorService = idGeneratorService;
    }


    @Override
    public BaseResponse<RegisterUserResponse> addNewUser(RegisterUserRequest registerUserRequest) {
        String newUserId = idGeneratorService.generateCustomUserId(registerUserRequest.getRole());
        UserEntity newUserEntity = getUserEntity(registerUserRequest, newUserId);

        try {
            UserEntity addedUser = userRepository.save(newUserEntity);

            RegisterUserResponse addUserResponse = RegisterUserResponse.builder()
                    .id(addedUser.getUserId())
                    .name(addedUser.getName())
                    .course(addedUser.getCourse())
                    .mailId(addedUser.getEmailId())
                    .phoneNo(addedUser.getPhoneNo())
                    .role(addedUser.getRole())
                    .build();

            return new BaseResponse<>(LibrarySystemErrorCodes.ok_code, LibrarySystemErrorCodes.new_user_msg, addUserResponse);
        } catch (Exception ex) {
            return new BaseResponse<>(LibrarySystemErrorCodes.fail_code, ex.getMessage(), null);
        }
    }

    @Override
    public BaseResponse<CustomUserDetails> getUserDetailsByUserId(String userId) {
        CustomUserDetails customUserDetails = userRepository.fetchUserDetailsByUserId(userId);
        BaseResponse response = BaseResponse.builder()
                .code(LibrarySystemErrorCodes.ok_code)
                .msg(LibrarySystemErrorCodes.ok_msg)
                .data(customUserDetails)
                .build();
        return response;
    }

    private UserEntity getUserEntity(RegisterUserRequest registerUserRequest, String newUserId) {
        UserEntity newUserEntity = new UserEntity();

        newUserEntity.setUserId(newUserId);
        newUserEntity.setName(registerUserRequest.getName());
        newUserEntity.setRole(registerUserRequest.getRole());
        newUserEntity.setDob(registerUserRequest.getDob());
        newUserEntity.setCourse(registerUserRequest.getRole()==Roles.LIBRARIAN ? Course.NA : registerUserRequest.getCourse());
        newUserEntity.setDesignation(registerUserRequest.getRole()==Roles.STAFF ? registerUserRequest.getDesignation() : Designation.NA);
        newUserEntity.setPhoneNo(registerUserRequest.getPhoneNo());
        newUserEntity.setEmailId(registerUserRequest.getMailId());
        newUserEntity.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        return newUserEntity;
    }

}
