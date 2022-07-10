package com.yans.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yans.dto.AppUserData;
import com.yans.dto.ResponseData;
import com.yans.models.entities.AppUser;
import com.yans.services.AppUserService;

@RestController
@RequestMapping("api/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData userData) {

        ResponseData<AppUser> response = new ResponseData<>();
        AppUser AppUser = modelMapper.map(userData, AppUser.class);
        System.out.println(AppUser);
        response.setPayload(appUserService.registeAppUser(AppUser));
        response.setStatus(true);
        response.getMessages().add("User saved");
        return ResponseEntity.ok(response);
    }
}
