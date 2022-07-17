package model;
import com.google.gson.Gson;
public class BuildModelJson {
    public  static String parseJsonString (postbody postbody)
    {
        if(postbody == null)
        {
            throw new IllegalArgumentException   ("postbody can not be null");
        }
        return new Gson().toJson(postbody);
    }
}
