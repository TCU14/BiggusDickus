package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Random;

public class pp extends Command {
    public pp(){
        this.name = "pp";
        this.arguments = "user@";
        this.help = "generates a pp";
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder newpp = new EmbedBuilder();
        int pplength = 0;
        String len = "";
        Random random = new Random();
        pplength = random.nextInt(13);
        len = new String(new char[pplength]).replace("\0", "=");
        newpp.setColor(Color.red);
        newpp.setTitle(event.getAuthor().getName() + "'s pp");
        newpp.setDescription("8" + len + "D");
        event.getChannel().sendMessage(newpp.build()).queue();
    }
}
