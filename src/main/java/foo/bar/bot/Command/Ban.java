package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Ban extends Command {
    public Ban(){
        this.name = "Ban";
        this.aliases = new String[]{"ban", "bean"};
        this.help = "Spoof ban command (Not for banning, only for memes)";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/783424149464285185/785594666447536168/red-bean_1946230.jpg").queue();
    }
}
