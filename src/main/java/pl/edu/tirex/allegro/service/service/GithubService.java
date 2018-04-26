package pl.edu.tirex.allegro.service.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.tirex.github.GithubRepository;
import pl.edu.tirex.github.exception.GithubRepositoryNotFoundException;
import pl.edu.tirex.github.exception.GithubUserNotFoundException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
        try
        {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(
                    "https://api.github.com/users/" + user + "/repos?sort=updated&direction=desc").openConnection();
            httpConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");

            httpConnection.setConnectTimeout(10000); //TODO add configuration in application.properties
            httpConnection.setReadTimeout(10000); // TODO look above

            httpConnection.setRequestMethod("GET");

            int responseCode = httpConnection.getResponseCode();
            if (responseCode == 404)
            {
                throw new GithubUserNotFoundException("User with name: " + user + " was not found.");
            }

            return this.gson.fromJson(new InputStreamReader(httpConnection.getInputStream()),
                    new TypeToken<ArrayList<GithubRepository>>()
                    {
                    }.getType());
        }
        catch (IOException e)
        {
            // TODO jakis blad, trzbea przetworzyc
            e.printStackTrace();
        }
        return null;
    }


}
