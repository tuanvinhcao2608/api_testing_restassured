package model;
import  io.restassured.http.Header;

import java.util.function.Function;

public interface RequestCapacity {

    Header defaultHeader = new Header("Content-type","application/json; charset=UTF-8");
    Header acceptJsonHeader = new Header("Accept","application/json");
     static Header getAuthorizationHeader(String encodeCredString)

    {
        if(encodeCredString == null)
        {
            throw new IllegalArgumentException("encodeCredString is null");
        }
        return new Header("Authorization", "Basic " + encodeCredString);
    }

    Function<String, Header> getAuthorizationHeader = encodeCredString ->
    {
        if(encodeCredString == null)
        {
            throw new IllegalArgumentException("encodeCredString is null");
        }
        return new Header("Authorization", "Basic " + encodeCredString);
    };
}
