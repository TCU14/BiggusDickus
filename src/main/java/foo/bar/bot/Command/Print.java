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

        switch (args[0]) {
            case "!rprint":
                switch (args[1]){
                    case "yes":
                        event.getTextChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(12) + "```").queue();
                        event.getMessage().delete().queue();
                        break;
                    default:
                        event.getTextChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(8) + "```").queue();
                        break;
                }
                break;
            case "!r":
                switch (args[2]){
                    case "yes":
                        event.getTextChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(13) + "```").queue();
                        event.getMessage().delete().queue();
                        break;
                    default:
                        event.getTextChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(9) + "```").queue();
                        break;
                }
                break;
        }
    }
}
