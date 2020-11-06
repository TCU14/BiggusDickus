package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class Fox extends Command {
    public Fox(){
        this.name = "Fox";
        this.aliases = new String[] {"fox"};
        this.help = "Sends a random picture of a fox (what does the fox say?)";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("https://randomfox.ca/floof/", "image")).queue();

    }
}
