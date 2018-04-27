package pl.edu.tirex.allegro.service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.tirex.allegro.service.system.handler.ExceptionsHandler;
import pl.edu.tirex.github.exception.GithubRepositoryNotFoundException;
import pl.edu.tirex.github.exception.GithubUserNotFoundException;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LastModificationControllerTest
{
    private static final String LAST_MODIFICATION_URL = "/api/last-modification";

    private MockMvc mockMvc;

    private LastModificationController lastModificationController;

    @BeforeEach
    public void initMock()
    {
        this.lastModificationController = Mockito.mock(LastModificationController.class);

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.lastModificationController).build();
    }

    @Test
    @DisplayName("return repository name")
    public void lastModificationTest() throws Exception
    {
        given(this.lastModificationController.lastModification()).willReturn("test_name");

        //@formatter:off
        this.mockMvc.perform(get(LAST_MODIFICATION_URL))
                    .andExpect(status().isOk())
                    .andExpect(content().string("test_name"))
                    .andExpect(content().contentTypeCompatibleWith(TEXT_PLAIN));
        //@formatter:on
    }
}
