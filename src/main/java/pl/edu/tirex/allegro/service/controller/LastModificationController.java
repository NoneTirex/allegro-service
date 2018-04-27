package pl.edu.tirex.allegro.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.tirex.allegro.service.service.GithubService;

@RestController
public class LastModificationController
{
    private final GithubService githubService;

    @Autowired
    public LastModificationController(GithubService githubService)
    {
        this.githubService = githubService;
    }

    @GetMapping("/api/last-modification")
    public String lastModification()
    {
        return this.githubService.getLastModificationRepositoryName("allegro");
    }
}
