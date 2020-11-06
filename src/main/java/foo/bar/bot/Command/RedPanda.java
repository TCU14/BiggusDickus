package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class RedPanda extends Command {
    public RedPanda(){
        this.name = "RedPanda";
        this.aliases = new String[]{"redpanda", "rpanda"};
        this.help = "Sends a random image of a red panda";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("https://some-random-api.ml/img/red_panda", "link")).queue();
    }
}
