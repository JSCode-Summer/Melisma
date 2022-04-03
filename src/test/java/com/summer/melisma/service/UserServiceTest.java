package com.summer.melisma.service;

import com.summer.melisma.model.dto.UserDto;
import com.summer.melisma.repository.UserRepository;
import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private MockMvc mvc;

    @Test
    public void 로그인_테스트() throws Exception{
        //given
        String username = "users";
        String password = "password";
        UserDto dto = UserDto.builder()
                .id(UUID.randomUUID())
                .username(username)
                .password(passwordEncoder.encode(password))
                .salt(UUID.randomUUID().toString())
                .role("USER")
                .build();
        mvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();
        userService.create(dto);
        //when
        mvc.perform(formLogin().user(username).password(password))
        //then
                .andExpect(authenticated());



    }
}
