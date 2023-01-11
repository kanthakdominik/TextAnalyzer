package pl.dominik.textanalyzer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.dominik.textanalyzer.payload.TextResponse;
import pl.dominik.textanalyzer.serviceImpl.TextServiceImpl;
import pl.dominik.textanalyzer.services.TextService;

import java.util.List;

@WebMvcTest(controllers = TextController.class)
@Import(TextServiceImpl.class)
public class TextControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private List<TextResponse> analyzedWords;

    private final String text = "word1, word2; word3. word4-word2? (word1) ** word1 /\\ word2+word3:word2!word2";

    private String URI = "/api/texts";

    @BeforeEach
    void setUp() {
        analyzedWords = List.of(
                new TextResponse("word1", 3, List.of(0, 5, 6)),
                new TextResponse("word2", 5, List.of(1, 4, 7, 9, 10)),
                new TextResponse("word3", 2, List.of(2, 8)),
                new TextResponse("word4", 1, List.of(3)));
    }

    @Test
    void analyzeTextTest() throws Exception {
        URI += "/analyze";

        TextService mockTextService = org.mockito.Mockito.mock(TextService.class);

        Mockito.when(mockTextService.getAnalyzedWords(text)).thenReturn(analyzedWords);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .content(mapper.writeValueAsString(text))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String resultJson = result.getResponse().getContentAsString();
        Assertions.assertThat(resultJson).isEqualToIgnoringCase(mapper.writeValueAsString(analyzedWords));
    }
}
