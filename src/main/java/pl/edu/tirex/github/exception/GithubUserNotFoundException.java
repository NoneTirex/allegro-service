package pl.edu.tirex.github.exception;

public class GithubUserNotFoundException
        extends GithubNotFoundException
{
    public GithubUserNotFoundException()
    {
    }

    public GithubUserNotFoundException(String message)
    {
        super(message);
    }
}
