package pl.edu.tirex.github.exception;

public class GithubNotFoundException
        extends GithubException
{
    public GithubNotFoundException()
    {
    }

    public GithubNotFoundException(String message)
    {
        super(message);
    }
}
