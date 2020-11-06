package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class FourKay extends Command {
    public FourKay(){
        this.name = "4K";
        this.aliases = new String[]{"4k"};
        this.help = "Grabs a 4K image from Unsplash and sends it";
    }

    public static String getFinalURL(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setInstanceFollowRedirects(false);
        con.connect();
        con.getInputStream();

        if (con.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || con.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
            String redirectUrl = con.getHeaderField("Location");
            return getFinalURL(redirectUrl);
        }
        return url;
    }

    @Override
    protected void execute(CommandEvent event) {
        try {
            String messageContent = getFinalURL("https://source.unsplash.com/random/3840x2160");
            event.getChannel().sendMessage(messageContent).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
