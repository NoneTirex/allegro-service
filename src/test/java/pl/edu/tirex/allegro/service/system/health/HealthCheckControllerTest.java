package pl.edu.tirex.allegro.service.system.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.tirex.allegro.service.system.handler.ExceptionsHandler;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HealthCheckControllerTest
{
    private static final String STATUS_URL = "/api/status";

    private MockMvc mockMvc;

    private HealthCheckController healthCheckController;

    @BeforeEach
    public void initMock()
    {
        this.healthCheckController = new HealthCheckController();

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.healthCheckController)
                                      .setControllerAdvice(new ExceptionsHandler()).build();
    }

    @Test
    @DisplayName("return api status")
    public void lastModificationTest() throws Exception
    {
        //@formatter:off
        this.mockMvc.perform(get(STATUS_URL))
                    .andExpect(status().isOk())
                    .andExpect(content().string("ok"))
                    .andExpect(content().contentTypeCompatibleWith(TEXT_PLAIN));
        //@formatter:on
    }
}
