package com.example.demo.controller;

import com.example.demo.service.MovieService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static  org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static  org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static  org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static  org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class MovieControllerTest {


    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private RestDocumentationResultHandler document;
    /*private ManualRestDocumentation restDocumentationExtension
            = new ManualRestDocumentation("target/generated-snippets");*/


    @BeforeEach
    public void setup(WebApplicationContext ctx,
                      RestDocumentationContextProvider restDocumentation) {
        this.document = document(
                "{class-name}/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        );
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withResponseDefaults(prettyPrint()))
                .addFilter(new CharacterEncodingFilter("UTF-8",true))

                .alwaysDo(document)
                .build();
    }


    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andDo(document("index",
                            responseFields(
                                    fieldWithPath("msg").description("msg")
                            )));
    }

    @Test
    void findMovie() throws Exception {
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        mockMvc.perform(get("/movie"))
                .andExpect(status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                ;

        /*links(
                linkWithRel("msg").description("Link to the alpha resource")
        ),*/
    }

    @Test
    void findById() {
    }

    @Test
    void findMovies() {
    }

    @Test
    void putMovie() {
    }

    @Test
    void deleteMovie() {
    }
}