package com.hoon.cashcocoon.member;

import com.hoon.cashcocoon.adapter.in.web.MemberController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MemberController.class)
public class 회원가입컨트롤러 {


    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser
    void 컨트롤러호출() throws Exception {
        // Given

        // When
        ResultActions actions = mvc.perform(post("/api/members/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"email\" : \"lhjhoon@gmail.com\", \"password\" : \"1234\"}")
                .with(csrf()));

        // Then
        actions
                .andExpect(status().isOk())
        /*.andExpect(jsonPath("email").value("lhjhoon@gmail.com"))*/;
    }
}
