package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class Insult extends Command {
    public Insult(){
        this.name = "Insult";
        this.aliases = new String[]{"insult"};
        this.help = "Sends a random insult from evilinsults.com (crowdsourced!)";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("https://evilinsult.com/generate_insult.php?lang=en&type=json", "insult")).queue();
    }
}
