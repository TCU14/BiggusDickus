package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import foo.bar.bot.music.PlayerManager;

public class VolumeDown extends Command {
    public VolumeDown(){
        this.name = "VolumeDown";
        this.aliases = new String[]{"voldown"};
        this.help = "Turns down the volume by 10";
    }

    @Override
    protected void execute(CommandEvent event) {
        PlayerManager manager = PlayerManager.getINSTANCE();
        manager.getGuildMusicManager(event.getGuild()).player.setVolume(manager.getGuildMusicManager(event.getGuild()).player.getVolume() - 10);
    }
}
