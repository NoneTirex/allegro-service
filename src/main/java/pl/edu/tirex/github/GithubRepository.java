package pl.edu.tirex.github;

public class GithubRepository
{
    private String name;

    public GithubRepository(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
