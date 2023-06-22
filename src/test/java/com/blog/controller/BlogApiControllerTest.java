package com.blog.controller;

import com.blog.domain.Article;
import com.blog.dto.AddArticleRequest;
import com.blog.dto.UpdateArticleRequest;
import com.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext content;
    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(content).build(); // json serialization
        blogRepository.deleteAll();
    }

    @Test
    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    void addArticle() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(userRequest);
        //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        //then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공한다.")
    void findAllArticles() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article.builder()
                        .title(title)
                        .content(content)
                        .build());
        //when
        ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));
        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @Test
    @DisplayName("findArticle: 블로그 글 조회에 성공")
    void findArticle() throws Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        //when
        ResultActions result = mockMvc.perform(get(url, savedArticle.getId()));
        //then

        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }

    @Test
    @DisplayName("deleteArticle: 블로그 글 삭제에 성공")
    void deleteArticle() throws Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        //when
        ResultActions result = mockMvc.perform(delete(url, savedArticle.getId()));

        //then
        result.andExpect(status().isOk());

        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();
    }

    @Test
    @DisplayName("updateArticle: 블로그 글 수정에 성공")
    void updateArticle() throws Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final String updateTitle = "update title";
        final String updateContent = "update Content";

        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest(updateTitle, updateContent);

        String requestBody = objectMapper.writeValueAsString(updateArticleRequest);

        //when
        ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updateTitle))
                .andExpect(jsonPath("$.content").value(updateContent));
    }
}
