package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Print extends Command {
    public Print(){
        this.name = "Print";
        this.aliases = new String[]{"print"};
        this.help = "Prints whatever input you decide to feed it ('!r print yes' will delete your command execution)";
    }

    @Override
    protected void execute(CommandEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[2].equalsIgnoreCase("yes")) {
            event.getChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(13) + "```").queue();
            event.getMessage().delete().queue();

        } else {
            event.getChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(9) + "```").queue();

        }
    }
}
