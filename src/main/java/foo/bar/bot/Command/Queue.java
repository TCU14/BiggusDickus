package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import foo.bar.bot.music.GuildMusicManager;
import foo.bar.bot.music.PlayerManager;

import java.util.ArrayList;

public class Queue extends Command {
    public Queue(){
        this.name = "Queue";
        this.aliases = new String[]{"queue", "q"};
        this.help = "Sends the current track queue into text channel";
    }

    @Override
    protected void execute(CommandEvent event) {
        PlayerManager playerManager1 = PlayerManager.getINSTANCE();
        GuildMusicManager musicManager1 = playerManager1.getGuildMusicManager(event.getGuild());
        if (musicManager1.player.getPlayingTrack() != null) {
            StringBuilder toSend = new StringBuilder("Biggus Dickus" + ""
                    + " Currently playing:"
                    + "\n"
                    + "```"
                    + (musicManager1.player.getPlayingTrack().getInfo().title + "")
                    + "```"
                    + "\n");
            if (musicManager1.scheduler.getList().size() > 0) {
                toSend.append("Upcoming songs:"
                        + "\n"
                        + "```");
                ArrayList<AudioTrack> list = musicManager1.scheduler.getList();
                for (int i = 0; i < list.size(); i++) {
                    toSend.append("\n" + musicManager1.scheduler.GetTrackInfo(list.get(i)));
                }
                toSend.append("```");
                event.getChannel().sendMessage(toSend).queue();
            } else {
                toSend.append("There are no songs currently queued");
                event.getChannel().sendMessage(toSend.toString()).queue();
            }
        } else {
            event.getChannel().sendMessage("There are no songs queued or playing right now!").queue();
        }
    }
}
