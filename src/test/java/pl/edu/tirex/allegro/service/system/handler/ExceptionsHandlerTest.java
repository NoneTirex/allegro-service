package pl.edu.tirex.allegro.service.system.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.tirex.github.exception.GithubRepositoryNotFoundException;
import pl.edu.tirex.github.exception.GithubUserNotFoundException;
import pl.edu.tirex.http.exception.HttpResponseCodeException;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ExceptionsHandlerTest
{
    private static final String EXCEPTION_HANDLER_URL = "/exception-handler";

    private MockMvc mockMvc;

    private ExceptionHandlerController exceptionHandlerController;

    @BeforeEach
    public void initMock()
    {
        this.exceptionHandlerController = Mockito.mock(ExceptionHandlerController.class);

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.exceptionHandlerController)
                                      .setControllerAdvice(new ExceptionsHandler()).build();
    }

    @Test
    @DisplayName("handle GithubUserNotFoundException")
    public void exceptionHandlerHandleUserNotFoundExceptionTest() throws Exception
    {
        given(this.exceptionHandlerController.test()).willThrow(GithubUserNotFoundException.class);

        //@formatter:off
        this.mockMvc.perform(get(EXCEPTION_HANDLER_URL))
                    .andExpect(status().is4xxClientError());
        //@formatter:on
    }

    @Test
    @DisplayName("handle GithubRepositoryNotFoundException")
    public void exceptionHandlerHandleRepositoryNotFoundExceptionTest() throws Exception
    {
        given(this.exceptionHandlerController.test()).willThrow(GithubRepositoryNotFoundException.class);

        //@formatter:off
        this.mockMvc.perform(get(EXCEPTION_HANDLER_URL))
                    .andExpect(status().is4xxClientError());
        //@formatter:on
    }

    @Test
    @DisplayName("handle Internal Server Error")
    public void exceptionHandlerHandleAnyExceptionTest() throws Exception
    {
        given(this.exceptionHandlerController.test()).willThrow(RuntimeException.class);

        //@formatter:off
        this.mockMvc.perform(get(EXCEPTION_HANDLER_URL))
                    .andExpect(status().is5xxServerError());
        //@formatter:on
    }

    @Test
    @DisplayName("handle HttpResponseCodeException")
    public void exceptionHandlerHandleHttpResponseCodeExceptionTest() throws Exception
    {
        HttpResponseCodeException responseCodeException = new HttpResponseCodeException(505);

        given(this.exceptionHandlerController.test()).willThrow(responseCodeException);

        //@formatter:off
        this.mockMvc.perform(get(EXCEPTION_HANDLER_URL))
                    .andExpect(status().is5xxServerError());
        //@formatter:on
    }

    @RestController
    class ExceptionHandlerController
    {
        @GetMapping(EXCEPTION_HANDLER_URL)
        public String test()
        {
            return null;
        }
    }
}
