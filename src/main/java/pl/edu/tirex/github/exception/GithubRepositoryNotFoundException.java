package pl.edu.tirex.github.exception;

public class GithubRepositoryNotFoundException
        extends GithubNotFoundException
{
    public GithubRepositoryNotFoundException()
    {
    }

    public GithubRepositoryNotFoundException(String message)
    {
        super(message);
    }
}
