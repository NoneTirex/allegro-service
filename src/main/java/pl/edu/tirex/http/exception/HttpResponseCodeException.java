package pl.edu.tirex.http.exception;

public class HttpResponseCodeException extends RuntimeException
{
    private int responseCode;

    public HttpResponseCodeException(int responseCode)
    {
        this.responseCode = responseCode;
    }

    public int getResponseCode()
    {
        return responseCode;
    }
}
