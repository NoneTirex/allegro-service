package pl.edu.tirex.http.resolver;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class JsonTypeResolver<T>
        implements TemplateResolver<T, RuntimeException>
{
    private final Gson gson;
    private final Type type;

    public JsonTypeResolver(Gson gson, Type type)
    {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T apply(InputStream inputStream)
    {
        return this.gson.fromJson(new InputStreamReader(inputStream), this.type);
    }
}
