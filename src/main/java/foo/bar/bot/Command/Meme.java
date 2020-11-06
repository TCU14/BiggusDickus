package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class Meme extends Command {
    public Meme(){
        this.name = "Meme";
        this.aliases = new String[]{"meme"};
        this.help = "Sends a random meme (r/DankMemes)";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("https://meme-api.herokuapp.com/gimme/dankmemes", "url")).queue();
    }
}
