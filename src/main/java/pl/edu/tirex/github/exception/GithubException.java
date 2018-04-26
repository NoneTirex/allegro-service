package pl.edu.tirex.github.exception;

public class GithubException extends RuntimeException
{
    public GithubException()
    {
    }

    public GithubException(String message)
    {
        super(message);
    }

    public GithubException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public GithubException(Throwable cause)
    {
        super(cause);
    }
}
