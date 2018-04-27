package pl.edu.tirex.http.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HttpUrlUtilsTest
{
    @Test
    public void parseUrlParametersTest()
    {
        Map<String, String> parameters = HttpUrlUtils.parseUrlParameters("https://github.com/?test1=abc&test2&test3=");

        assertAll(() ->
        {
            assertEquals("abc", parameters.get("test1"));
            assertNull(parameters.get("test2"));
            assertTrue(parameters.get("test3").isEmpty());
        });
    }
}
