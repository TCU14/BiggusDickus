package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class Dog extends Command {
    public Dog(){
        this.name = "Dog";
        this.aliases = new String[]{"dog"};
        this.help = "Sends a random image of a dog.. woof";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("https://dog.ceo/api/breeds/image/random", "message")).queue();
    }
}
