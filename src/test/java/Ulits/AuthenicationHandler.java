package Ulits;

import org.apache.commons.codec.binary.Base64;

public class AuthenicationHandler {
    public static String encodeCreStr(String email, String token)
    {
        if(email == null || token ==null)
        {
            throw new IllegalArgumentException("email or token is null");
        }
        String cred = email.concat(":").concat(token);
        byte[] encodeCred = Base64.encodeBase64(cred.getBytes());
        String encodeCredString = new String(encodeCred);
        return encodeCredString;
    }
}
