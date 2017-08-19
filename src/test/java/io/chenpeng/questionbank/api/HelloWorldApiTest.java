package io.chenpeng.questionbank.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApiTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("custom");

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void findAll() throws Exception {
        this.mockMvc.perform(get("/api/helloWorld?pageIndex=1&pageSize=10", 1, 10).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("/api/helloWorld", responseFields(
                        fieldWithPath("content").description("返回的内容"),
                        fieldWithPath("totalPages").description("总页数"),
                        fieldWithPath("last").description("是否最后一个元素"),
                        fieldWithPath("totalElements").description("所有元素"),
                        fieldWithPath("number").description("数量"),
                        fieldWithPath("size").description("大小"),
                        fieldWithPath("sort").description("排序"),
                        fieldWithPath("numberOfElements").description("元素个数"),
                        fieldWithPath("first").description("是否是第一个元素")
                )));
    }

    @Test
    public void getOne() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}