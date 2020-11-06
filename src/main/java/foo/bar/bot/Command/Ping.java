package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Ping extends Command {
    public Ping(){
        this.name = "Ping";
        this.aliases = new String[]{"ping"};
        this.help = "Pings the bot to ensure it's online";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage("pong! Biggus Dickus lives on").queue();
    }
}
