package pl.edu.tirex.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HttpConnectionTest
{
    private HttpConnection httpConnection;

    @BeforeEach
    public void initHttpConnection()
    {
        this.httpConnection = new HttpConnection("https://github.com/?test1=abc&test2=&test3");
    }

    @Test
    public void separationUrlFromParametersTest()
    {
        assertAll(() ->
        {
            assertEquals("https://github.com/?test1=abc&test2=&test3", this.httpConnection.getUrl());
            assertEquals("https://github.com/", this.httpConnection.getShortUrl());
        });
        assertAll(() ->
        {
            assertTrue(this.httpConnection.getUrlParameters().containsKey("test1"));
            assertTrue(this.httpConnection.getUrlParameters().containsKey("test2"));
            assertTrue(this.httpConnection.getUrlParameters().containsKey("test3"));
        });
    }
}
