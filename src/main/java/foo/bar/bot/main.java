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


    public static JDA jda;
    public static String prefix = "!r";
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("!r (Life of Brian)"));
        jda.addEventListener(new Commands());

    }
}
