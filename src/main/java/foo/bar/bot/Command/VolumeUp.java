package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import foo.bar.bot.music.PlayerManager;

public class VolumeUp extends Command {
    public VolumeUp(){
        this.name = "VolumeUp";
        this.aliases = new String[]{"volup"};
        this.help = "Turns up the volume by 10";
    }

    @Override
    protected void execute(CommandEvent event) {
        PlayerManager manager = PlayerManager.getINSTANCE();
        manager.getGuildMusicManager(event.getGuild()).player.setVolume(manager.getGuildMusicManager(event.getGuild()).player.getVolume() + 10);
    }
}
