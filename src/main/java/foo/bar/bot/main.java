package foo.bar.bot;

import foo.bar.bot.Command.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import com.jagrosh.jdautilities.command.*;
import org.jetbrains.annotations.Async;

public class main {

//Create new JDA instance - this is the Java Discord API I will be relying on
    public static JDA jda;
    public static String prefix = "!r";
    public static void main(String[] args) throws LoginException {
        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix("!r");
        builder.setOwnerId("167430999095902208");
        builder.setActivity(Activity.playing("!r help (Life of Brian)"));
        //Add all my dogshit commands here in a way that seems very clunky but whatever
        builder.addCommands(new Eightball(), new pp(), new Play(), new Ping(), new Info(), new VolumeUp(), new VolumeDown(), new Disconnect(), new Skip(), new Queue(), new Clear(), new Stop(), new Fox(), new Cat(), new Dog(), new Meme(), new Duck(), new Panda(), new RedPanda(), new ProfilePic(), new CatFact(), new FourKay());
        CommandClient client = builder.build();
        //You would add a token inside the "" below.
        JDA jda = JDABuilder.createDefault("NzU2NzM2MjU2MTUwMzM5NTg0.X2WLjg.-GvHOThh_hSNzn3avoMpjdx5pXk").addEventListeners(client).build();
        //Sets bots status to be Online and introduces user to command syntax via setting the activity
        jda.getPresence().setStatus(OnlineStatus.ONLINE);

        //The bot is named this way because of Life of brian, which is why that's in the status. There is nothing funny about my good friend's name
    }
}
