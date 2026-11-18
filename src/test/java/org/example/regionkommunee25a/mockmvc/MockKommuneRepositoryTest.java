package org.example.regionkommunee25a.mockmvc;

import org.example.regionkommunee25a.model.Kommune;
import org.example.regionkommunee25a.repositories.KommuneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MockKommuneRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private KommuneRepository kommuneRepository;

    @Test
    void getHello2() {
        assertEquals("ABCD", "ABCD");
    }

    @Test
    void getKommunerStartsWith_returnsList() throws Exception {

        // Arrange
        List<Kommune> mockList = List.of(
                new Kommune("0001", "Aarhus"),
                new Kommune("0002", "Aalborg"),
                new Kommune("0003", "Roskilde"),
                new Kommune("0004","Herning"),
                new Kommune("0005", "Horsens")
        );

        when(kommuneRepository.findAll()).thenReturn(mockList);

        // Act + Assert
        ResultActions result = mockMvc.perform(get("http://localhost:8080/api/kommuner/kommunestart/A")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].navn").value("Aarhus"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].navn").value("Aalborg"));

        // Verify repository was called exactly once
        verify(kommuneRepository).findAll();

        result.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

    }
}
