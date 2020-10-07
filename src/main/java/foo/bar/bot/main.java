package foo.bar.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class main {

//Create new JDA instance - this is the Java Discord API I will be relying on
    public static JDA jda;
    public static String prefix = "!r";
    public static void main(String[] args) throws LoginException {
        //You would add a token inside the "" below.
        JDA jda = JDABuilder.createDefault("").build();
        //Sets bots status to be Online and introduces user to command syntax via setting the activity
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("!r help (Life of Brian)"));
        //Add my Commands listener event
        jda.addEventListener(new Commands());
        //The bot is named this way because of Life of brian, which is why that's in the status. There is nothing funny about my good friend's name
    }
}
