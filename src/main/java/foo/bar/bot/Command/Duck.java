package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class Duck extends Command {
    public Duck(){
        this.name = "Duck";
        this.aliases = new String[]{"duck"};
        this.help = "Sends a random image of a duck.. quack";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("https://random-d.uk/api/random", "url")).queue();
    }
}
