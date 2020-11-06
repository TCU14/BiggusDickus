package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import foo.bar.bot.music.PlayerManager;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class Play extends Command {
    public Play(){
        this.name = "Play";
        this.aliases = new String[]{"play"};
        this.help = "Plays a video from a given URL or search query in the voice chat the user is connected to.";
    }

    @Override
    protected void execute(CommandEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[2].contains("https://")) {
            PlayerManager manager = PlayerManager.getINSTANCE();
            VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
            AudioManager audioManager = event.getGuild().getAudioManager();
            audioManager.openAudioConnection(connectedChannel);
            String Query = "";
            Query = event.getMessage().getContentRaw().substring(8);
            manager.LoadAndPlay(event.getTextChannel(), Query);
            manager.getGuildMusicManager(event.getGuild()).player.setVolume(10);


        } else {
            PlayerManager manager = PlayerManager.getINSTANCE();
            VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
            AudioManager audioManager = event.getGuild().getAudioManager();
            audioManager.openAudioConnection(connectedChannel);
            String Query;
            Query = event.getMessage().getContentRaw().substring(8);
            manager.LoadAndPlay(event.getTextChannel(), "ytsearch:" + Query);
            manager.getGuildMusicManager(event.getGuild()).player.setVolume(10);
        }
    }
}
