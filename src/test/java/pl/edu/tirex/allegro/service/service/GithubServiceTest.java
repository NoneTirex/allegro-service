package pl.edu.tirex.allegro.service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.tirex.github.GithubRepository;
import pl.edu.tirex.github.exception.GithubRepositoryNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class GithubServiceTest
{
    private MockMvc mockMvc;

    private GithubService githubService;

    @BeforeEach
    public void initMock()
    {
        this.githubService = Mockito.mock(GithubService.class);
    }

    @Test
    @DisplayName("return repository name")
    public void lastModificationRepositoryNameTest()
    {
        List<GithubRepository> repositories = new ArrayList<>();
        repositories.add(new GithubRepository("first_repository"));
        repositories.add(new GithubRepository("second_repository"));

        given(this.githubService.getRepositories("allegro")).willReturn(repositories);
        given(this.githubService.getLastModificationRepository("allegro")).willCallRealMethod();
        given(this.githubService.getLastModificationRepositoryName("allegro")).willCallRealMethod();

        assertEquals("first_repository", this.githubService.getLastModificationRepositoryName("allegro"));
    }

    @Test
    @DisplayName("throw GithubRepositoryNotFoundException")
    public void lastModificationThrowGithubRepositoryNotFoundExceptionTest()
    {
        List<GithubRepository> repositories = Collections.emptyList();

        given(this.githubService.getRepositories("allegro")).willReturn(repositories);
        given(this.githubService.getLastModificationRepository("allegro")).willCallRealMethod();
        given(this.githubService.getLastModificationRepositoryName("allegro")).willCallRealMethod();

        assertThrows(GithubRepositoryNotFoundException.class, () ->
        {
            this.githubService.getLastModificationRepositoryName("allegro");
        });
    }
}
