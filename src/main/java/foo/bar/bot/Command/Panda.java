package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class Panda extends Command {
    public Panda(){
        this.name = "Panda";
        this.aliases = new String[]{"panda"};
        this.help = "Sends a random image of a panda";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("https://some-random-api.ml/img/panda", "link")).queue();
    }
}
