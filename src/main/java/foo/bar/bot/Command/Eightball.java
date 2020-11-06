package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Random;

public class Eightball extends Command {
    public Eightball() {
        this.name = "eightball";
        this.aliases = new String[]{"8ball"};
        this.help = "Gives a result based on a magic eightball";
    }
    @Override
    protected void execute(CommandEvent event){
        String[] responses = {"It is certain", "Without a doubt", "You may rely on it", "Yes definitely", "It is decidedly so",
                "As I see it, yes", "Most likely", "Yes", "Outlook good", "Signs point to yes", "Reply hazy try again",
                "Better not tell you now", "Ask again later", "Cannot predict now", "Concentrate and ask again",
                "Don’t count on it", "Outlook not so good", "My sources say no", "Very doubtful", "My reply is no"};
        Random r = new Random();
        int answer = 0;
        answer = r.nextInt(20);
        event.getChannel().sendMessage(responses[answer]).queue();
    }
}
