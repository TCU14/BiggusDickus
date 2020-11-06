package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class Cat extends Command {
    public Cat(){
        this.name = "Cat";
        this.aliases = new String[]{"cat"};
        this.help = "Sends a random image of a cat.. meow";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("http://aws.random.cat/meow", "file")).queue();
    }
}
