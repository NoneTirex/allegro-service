package pl.edu.tirex.http.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpUrlUtils
{
    public static Map<String, String> parseUrlParameters(String url)
    {
        Map<String, String> parameters = new LinkedHashMap<>();

        int index = url.indexOf('?');
        if (index < 0)
        {
            return parameters;
        }

        String urlParameter = url.substring(index);
        int lastIndex = 0;
        index = 0;
        do
        {
            index = urlParameter.indexOf('&', index + 1);
            int endIndex = index;
            if (endIndex < 0)
            {
                endIndex = urlParameter.length();
            }
            String value = urlParameter.substring(lastIndex + 1, endIndex);
            int valueSeparatorIndex = value.indexOf('=');
            if (valueSeparatorIndex < 0)
            {
                parameters.put(value, null);
            }
            else
            {
                parameters.put(value.substring(0, valueSeparatorIndex), value.substring(valueSeparatorIndex + 1));
            }
            lastIndex = endIndex;
        } while (index >= 0);
        return parameters;
    }
}
