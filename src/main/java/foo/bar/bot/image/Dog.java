package foo.bar.bot.image;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class Dog {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            JSONObject json = new JSONObject(readAll(rd));
            return json;
        } finally {
            is.close();
        }
    }

    public static String getDog() {

        String outputMessage = null; //Might be redudant
        JSONObject json = null; //Might be redudant
        try {
            json = readJsonFromUrl("https://dog.ceo/api/breeds/image/random");
            outputMessage = json.getString("message");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return outputMessage;
    }
}
