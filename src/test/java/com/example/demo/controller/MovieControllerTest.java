package com.example.demo.controller;

import com.example.demo.dto.movie.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;
import com.example.demo.service.impl.MovieServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class MovieControllerTest{

    private MockMvc mockMvc;

    @MockBean
    private MovieServiceImpl movieService;

    @MockBean
    private MovieRepository movieRepository;

    private RestDocumentationResultHandler document;

    @Autowired
    private ObjectMapper obj;

    @BeforeEach
    public void setup(WebApplicationContext ctx,
                      RestDocumentationContextProvider restDocumentation) throws Exception {
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
        dataTest();

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        mockMvc.perform(get("/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("/movie",
                        responseFields(
                                fieldWithPath("movie[].id")                 .description("pk"),
                                fieldWithPath("movie[].title")              .description("제목"),
                                fieldWithPath("movie[].runningTime")        .description("상영시간"),
                                fieldWithPath("movie[].openDate")           .description("방영일"),
                                fieldWithPath("movie[].distributor")        .description("배급사"),
                                fieldWithPath("movie[].description")        .description("설명"),
                                fieldWithPath("movie[].cumulativeAudience") .description("누적 관객"),
                                fieldWithPath("movie[].rank")               .description("관람연령제한"),
                                fieldWithPath("movie[].category")           .description("카테고리")
                        ))
                );
    }

    @Test
    void findById() {
    }

    @Test
    void putMovie() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("" + 1);
        movie.setDirector("" + 1);
        movie.setRunningTime(1);
        movie.setOpenDate(LocalDate.now());
        mockMvc.perform(post("/movie")
                .content(obj.writeValueAsString(movie))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }

    public void dataTest() {
        for(int i=0;i<100;i++){
            Movie movie = new Movie();
            movie.setTitle(""+i);
            movie.setDirector(""+i);
            movie.setRunningTime(i);
            movie.setOpenDate(LocalDate.now());
            movieService.saveAndFlush(movie);
            System.out.println("movie = " + movie.toString());
        }
    }
}