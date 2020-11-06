package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class CatFact extends Command {
    public CatFact(){
        this.name = "CatFact";
        this.aliases = new String[]{"catfact", "cf"};
        this.help = "Sends a random image of a cat.. meow";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("https://catfact.ninja/fact", "fact")).queue();
    }
}
