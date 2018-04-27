package pl.edu.tirex.http.resolver;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTypeResolverTest
{
    private JsonTypeResolver<ArrayList<String>> jsonTypeResolver;

    @BeforeEach
    public void init()
    {
        this.jsonTypeResolver = new JsonTypeResolver<>(new GsonBuilder().create(), new TypeToken<ArrayList<String>>()
        {
        }.getType());
    }

    @Test
    public void parseFromJsonToObjectResolverTest()
    {
        InputStream inputStream = new ByteArrayInputStream("[\"test1\", \"test2\"]".getBytes());
        ArrayList<String> strings = this.jsonTypeResolver.apply(inputStream);

        assertEquals(2, strings.size());
        assertArrayEquals(new String[] {"test1", "test2"}, strings.toArray(new String[0]));
    }
}
