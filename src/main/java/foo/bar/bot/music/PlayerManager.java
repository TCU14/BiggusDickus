package foo.bar.bot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    private final AudioPlayerManager playermanager;
    private final Map<Long, GuildMusicManager> musicManagers;

    public PlayerManager() {
        this.musicManagers = new HashMap<>();
        this.playermanager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playermanager);
        AudioSourceManagers.registerLocalSource(playermanager);
    }

    public synchronized GuildMusicManager getGuildMusicManager(Guild guild) {
        long guildID = guild.getIdLong();
        GuildMusicManager musicManager = musicManagers.get(guildID);
        if (musicManager == null) {
            musicManager = new GuildMusicManager(playermanager);
            musicManagers.put(guildID, musicManager);
        }
        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
        return musicManager;
    }

    public void LoadAndPlay(TextChannel channel, String TrackURL) {
        GuildMusicManager musicManager = INSTANCE.getGuildMusicManager(channel.getGuild());
        playermanager.loadItemOrdered(musicManager, TrackURL, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                channel.sendMessage("Adding to queue.. " + track.getInfo().title).queue();
                play(musicManager, track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();
                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().remove(0);
                }
                channel.sendMessage("Adding to queue... " + firstTrack.getInfo().title).queue();
                play(musicManager, firstTrack);
                if (playlist.isSearchResult()) {
                    int arraysize = playlist.getTracks().size();
                    for (int i = 0; i <= arraysize; i++){
                        playlist.getTracks().remove(i);
                    }
                }
                playlist.getTracks().forEach(musicManager.scheduler::queue);

            }

            @Override
            public void noMatches() {
                channel.sendMessage("Nothing found at " + TrackURL).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                channel.sendMessage("Could not play " + exception.getMessage()).queue();
            }
        });
    }

    private void play(GuildMusicManager musicManager, AudioTrack track) {
        musicManager.scheduler.queue(track);
    }

    public static synchronized PlayerManager getINSTANCE() {
        if (INSTANCE == null) {

            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }
}
