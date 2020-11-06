package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import foo.bar.bot.music.GuildMusicManager;
import foo.bar.bot.music.PlayerManager;
import net.dv8tion.jda.api.entities.TextChannel;

public class Skip extends Command {
    public Skip(){
        this.name = "Skip";
        this.aliases = new String[]{"skip"};
        this.help = "Skips current track in the queue";
    }

    @Override
    protected void execute(CommandEvent event) {
        PlayerManager playerManager = PlayerManager.getINSTANCE();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        event.getChannel().sendMessage("Skipped " + musicManager.player.getPlayingTrack().getInfo().title).queue();
        musicManager.scheduler.nextTrack();
    }
}
