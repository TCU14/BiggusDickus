package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Disconnect extends Command {
    public Disconnect(){
        this.name = "Disconnect";
        this.aliases = new String[]{"dis"};
        this.help = "Disconnects bot from connected voice channel";
    }

    @Override
    protected void execute(CommandEvent event) {
        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.closeAudioConnection();
    }
}
