package pl.edu.tirex.allegro.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class ApplicationConfiguration
{
    private Github github = new Github();

    public Github getGithub()
    {
        return github;
    }

    public void setGithub(Github github)
    {
        this.github = github;
    }

    public class Github
    {
        private String oauthToken;

        public String getOauthToken()
        {
            return oauthToken;
        }

        public void setOauthToken(String oauthToken)
        {
            this.oauthToken = oauthToken;
        }
    }
}
