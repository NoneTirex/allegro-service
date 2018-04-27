package pl.edu.tirex.http;

import pl.edu.tirex.http.exception.HttpResponseCodeException;
import pl.edu.tirex.http.resolver.TemplateResolver;
import pl.edu.tirex.http.util.HttpUrlUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HttpConnection
{
    private String              url;
    private Map<String, String> urlParameters = new LinkedHashMap<>();
    private Map<String, String> headers       = new LinkedHashMap<>();

    private HttpMethod method = HttpMethod.GET;

    public HttpConnection(String url)
    {
        this.url = url;
        this.urlParameters.putAll(HttpUrlUtils.parseUrlParameters(url));

        int index = this.url.indexOf('?');
        if (index >= 0)
        {
            this.url = this.url.substring(0, index);
        }
    }

    public String getShortUrl()
    {
        return this.url;
    }

    public Map<String, String> getUrlParameters()
    {
        return urlParameters;
    }

    public void setUrlParameter(String key, String value)
    {
        this.urlParameters.put(key, value);
    }

    public Map<String, String> getHeaders()
    {
        return headers;
    }

    public void setHeader(String key, String value)
    {
        this.headers.put(key, value);
    }

    public HttpMethod getMethod()
    {
        return method;
    }

    public void setMethod(HttpMethod method)
    {
        this.method = method;
    }

    public void execute() throws IOException
    {
        this.execute(null);
    }

    public <R, E extends Throwable> R execute(TemplateResolver<R, E> resolver) throws E, IOException
    {
        HttpURLConnection httpConnection = (HttpURLConnection) new URL(this.getUrl()).openConnection();
        this.headers.forEach(httpConnection::setRequestProperty);

        httpConnection.setConnectTimeout(10000);
        httpConnection.setReadTimeout(10000);

        httpConnection.setRequestMethod(this.method.name());

        int responseCode = httpConnection.getResponseCode();
        if (responseCode / 100 != 2)
        {
            throw new HttpResponseCodeException(responseCode);
        }

        if (resolver == null)
        {
            return null;
        }

        return resolver.apply(httpConnection.getInputStream());
    }

    public String getUrl()
    {
        String url = this.url;

        if (this.urlParameters.size() > 0)
        {
            List<String> parameters = new ArrayList<>();

            this.urlParameters.forEach((key, value) ->
            {
                if (value == null)
                {
                    parameters.add(key);
                    return;
                }
                parameters.add(key + "=" + value);
            });

            url += "?" + String.join("&", parameters);
        }

        return url;
    }
}
