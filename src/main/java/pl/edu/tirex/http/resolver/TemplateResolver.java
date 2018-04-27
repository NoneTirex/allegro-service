package pl.edu.tirex.http.resolver;

import java.io.InputStream;

@FunctionalInterface
public interface TemplateResolver<R, E extends Throwable>
{
    R apply(InputStream inputStream) throws E;
}
