package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import foo.bar.bot.music.GuildMusicManager;
import foo.bar.bot.music.PlayerManager;

public class Clear extends Command {
    public Clear(){
        this.name = "Clear";
        this.aliases = new String[]{"clear"};
        this.help = "Clears the track queue";
    }

    @Override
    protected void execute(CommandEvent event) {
        PlayerManager playerManager2 = PlayerManager.getINSTANCE();
        GuildMusicManager musicManager2 = playerManager2.getGuildMusicManager(event.getGuild());
        musicManager2.scheduler.clear();
        event.getChannel().sendMessage("Queue has been cleared!").queue();
    }
}
