package com.lilkhalil.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
class TestApplicationTests {

	private MockMvc mockMvc;

	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	void emptyStringExceptionTesting() throws Exception {
		mockMvc.perform(
			post("/api/v1/characters")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("str", ""))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.detail").value("Empty string has been found!"))
			.andExpect(jsonPath("$.instance").value("/api/v1/characters"));
	}

	@Test
	void whitespaceOnlyStringTesting() throws Exception {
		mockMvc.perform(
			post("/api/v1/characters")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("str", "      ")
		)
		.andExpect(status().isOk())
		.andExpect(content().string("{\" \":6}"));
	}

	@Test
	void dotOnlyStringTesting() throws Exception {
		mockMvc.perform(
			post("/api/v1/characters")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("str", "......")
		)
		.andExpect(status().isOk())
		.andExpect(content().string("{\".\":6}"));
	}

	@Test
	void givenStringTesting() throws Exception {
		mockMvc.perform(
			post("/api/v1/characters")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("str", "aaaaabcccc")
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.a").value("5"))
		.andExpect(jsonPath("$.c").value("4"))
		.andExpect(jsonPath("$.b").value("1"));
	}

	@Test
	void noRequestParameterGivenTesting() throws Exception {
		mockMvc.perform(
			post("/api/v1/characters")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isBadRequest())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException));
	}

}
