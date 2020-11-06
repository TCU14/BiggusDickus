package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import foo.bar.bot.music.GuildMusicManager;
import foo.bar.bot.music.PlayerManager;

public class Stop extends Command {
    public Stop(){
        this.name = "Stop";
        this.aliases = new String[]{"stop"};
        this.help = "Stops the track that's currently playing in the queue";
    }

    @Override
    protected void execute(CommandEvent event) {
        PlayerManager playerManager3 = PlayerManager.getINSTANCE();
        GuildMusicManager musicManager3 = playerManager3.getGuildMusicManager(event.getGuild());
        event.getChannel().sendMessage(musicManager3.player.getPlayingTrack().getInfo().title + " has been stopped");
        musicManager3.player.stopTrack();
    }
}
