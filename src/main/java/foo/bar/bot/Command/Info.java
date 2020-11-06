package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class Info extends Command {
    public Info(){
        this.name = "Info";
        this.aliases = new String[]{"info"};
        this.help = "Sends bot info";
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder info = new EmbedBuilder();
        info.setColor(Color.red);
        info.setTitle("Biggus Dickus Bot version 3.0");
        info.setColor(Color.cyan);
        info.setDescription("Bot is still in active development and as such there may be bugs. Please report any and all issues on my Github." +
                " But also check for duplicates beforehand in case I was already made aware of your issue. [Github can be found here](https://github.com/TCU14/BiggusDickus)");
        info.setAuthor("Red The Moron");
        event.getChannel().sendMessage(info.build()).queue();
        info.clear();
    }
}
