package pl.edu.tirex.allegro.service.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.tirex.github.GithubRepository;
import pl.edu.tirex.github.exception.GithubRepositoryNotFoundException;
import pl.edu.tirex.github.exception.GithubUserNotFoundException;
import pl.edu.tirex.http.HttpConnection;
import pl.edu.tirex.http.exception.HttpResponseCodeException;
import pl.edu.tirex.http.resolver.JsonTypeResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GithubService
{
    private final Gson gson;

    @Autowired
    public GithubService(Gson gson)
    {
        this.gson = gson;
    }

    public String getLastModificationRepositoryName(String user)
    {
        GithubRepository repository = this.getLastModificationRepository(user);
        if (repository == null)
        {
            return null;
        }
        return repository.getName();
    }

    public GithubRepository getLastModificationRepository(String user)
    {
        List<GithubRepository> repositories = this.getRepositories(user);
        if (repositories.size() < 1)
        {
            throw new GithubRepositoryNotFoundException("Not found any repository.");
        }
        return repositories.get(0);
    }

    public List<GithubRepository> getRepositories(String user)
    {
        HttpConnection httpConnection = new HttpConnection("https://api.github.com/users/" + user + "/repos");
        httpConnection.setUrlParameter("sort", "updated");
        httpConnection.setUrlParameter("direction", "desc");
        httpConnection.setHeader("Accept", "application/vnd.github.v3+json");
        try
        {
            return httpConnection.execute(new JsonTypeResolver<>(this.gson, new TypeToken<ArrayList<GithubRepository>>()
            {
            }.getType()));
        }
        catch (HttpResponseCodeException e)
        {
            if (e.getResponseCode() == 404)
            {
                throw new GithubUserNotFoundException("User with name: " + user + " was not found.");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
