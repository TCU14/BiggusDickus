package foo.bar.bot;
import com.fasterxml.jackson.core.JsonParser;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.sun.tools.javac.util.List;
import foo.bar.bot.music.GuildMusicManager;
import foo.bar.bot.music.PlayerManager;
import foo.bar.bot.music.TrackScheduler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Track;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import java.awt.*;
import java.util.concurrent.BlockingQueue;

import static foo.bar.bot.image.Cat.getCat;
import static foo.bar.bot.image.Dog.getDog;
import static foo.bar.bot.image.Duck.getDuck;
import static foo.bar.bot.image.Fox.getFox;
import static foo.bar.bot.image.Meme.getMeme;
import static foo.bar.bot.image.Panda.getPanda;
import static foo.bar.bot.image.RedPanda.getRPanda;

public class Commands extends ListenerAdapter {
    public static void GenerateRandomPP(){
        int pplength = 0;
        Random random = new Random();
        pplength = random.nextInt(13);
    }
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
//Add the command triggers, (args[0] = First word in a message, args[1] = second word, etc. Above this comment I used
        // \\s to split up the arguments so that we can differentiate/utilize them properly

        if (args[0].equalsIgnoreCase(main.prefix + "ping")) {
            event.getChannel().sendMessage("pong! Biggus Dickus lives on").queue();
        } else if (args[0].equalsIgnoreCase(main.prefix + "info")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(Color.red);
            info.setTitle("Biggus Dickus Bot version 1.8");
            info.setColor(Color.cyan);
            info.setDescription("Bot is still in active development and as such there may be bugs. Please report any and all issues on my Github." +
                    "But also check for duplicates beforehand in case I was already made aware of your issue");
            info.setAuthor("Red The Moron");
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        } else if (args[0].equalsIgnoreCase(main.prefix + "print")) {
            if (args[1].equalsIgnoreCase("yes")) {
                event.getChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(12) + "```").queue();
                event.getMessage().delete().queue();
            } else if (args[1].equalsIgnoreCase("no")) {
                event.getChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(11) + "```").queue();
            } else {
                event.getChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(8) + "```").queue();

            }
        } else if (args[0].equalsIgnoreCase(main.prefix + "pp")) {
            EmbedBuilder newpp = new EmbedBuilder();
            int pplength = 0;
            String len = "";
            Random random = new Random();
            pplength = random.nextInt(13);
            len = new String(new char[pplength]).replace("\0", "=");
            newpp.setColor(Color.red);
            newpp.setTitle(event.getAuthor().getName() + "'s pp");
            newpp.setDescription("8" + len + "D");
            event.getChannel().sendMessage(newpp.build()).queue();
        } else if (args[0].equalsIgnoreCase(main.prefix + "eightball") || args[0].equalsIgnoreCase(main.prefix + "8ball")) {
            String[] responses = {"It is certain", "Without a doubt", "You may rely on it", "Yes definitely", "It is decidedly so",
                    "As I see it, yes", "Most likely", "Yes", "Outlook good", "Signs point to yes", "Reply hazy try again",
                    "Better not tell you now", "Ask again later", "Cannot predict now", "Concentrate and ask again",
                    "Don’t count on it", "Outlook not so good", "My sources say no", "Very doubtful", "My reply is no"};
            Random random = new Random();
            int answer = 0;
            answer = random.nextInt(20);
            event.getChannel().sendMessage(responses[answer]).queue();
        } else if (args[0].equalsIgnoreCase(main.prefix + "play")) {
            if (args[1].equalsIgnoreCase("search")){
                PlayerManager manager = PlayerManager.getINSTANCE();
                VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
                AudioManager audioManager = event.getGuild().getAudioManager();
                audioManager.openAudioConnection(connectedChannel);
                String Query = "";
                Query = event.getMessage().getContentRaw().substring(14);
                manager.LoadAndPlay(event.getChannel(), "ytsearch:" + Query);
            }
            else {
                PlayerManager manager = PlayerManager.getINSTANCE();
                VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
                AudioManager audioManager = event.getGuild().getAudioManager();
                audioManager.openAudioConnection(connectedChannel);
                manager.LoadAndPlay(event.getChannel(), args[1]);
                manager.getGuildMusicManager(event.getGuild()).player.setVolume(10);
            }
        } else if (args[0].equalsIgnoreCase(main.prefix + "volup")) {
            PlayerManager manager = PlayerManager.getINSTANCE();
            manager.getGuildMusicManager(event.getGuild()).player.setVolume(manager.getGuildMusicManager(event.getGuild()).player.getVolume() + 10);
        } else if (args[0].equalsIgnoreCase(main.prefix + "voldown")) {
            PlayerManager manager = PlayerManager.getINSTANCE();
            manager.getGuildMusicManager(event.getGuild()).player.setVolume(manager.getGuildMusicManager(event.getGuild()).player.getVolume() - 10);
        } else if (args[0].equalsIgnoreCase(main.prefix + "disconnect") || args[0].equalsIgnoreCase(main.prefix + "dis")) {
            AudioManager audioManager = event.getGuild().getAudioManager();
            audioManager.closeAudioConnection();
        } else if (args[0].equalsIgnoreCase(main.prefix + "skip")) {
            TextChannel channel = event.getChannel();
            PlayerManager playerManager = PlayerManager.getINSTANCE();
            GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
            event.getChannel().sendMessage("Skipped " + musicManager.player.getPlayingTrack().getInfo().title).queue();
            musicManager.scheduler.nextTrack();

        } else if (args[0].equalsIgnoreCase(main.prefix + "queue")) {
            PlayerManager playerManager = PlayerManager.getINSTANCE();
            GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
            if (musicManager.player.getPlayingTrack() != null) {
                StringBuilder toSend = new StringBuilder("Biggus Dickus" + ""
                        + " Currently playing:"
                        + "\n"
                        + "```"
                        + (musicManager.player.getPlayingTrack().getInfo().title + "")
                        + "```"
                        + "\n");
                if (musicManager.scheduler.getList().size() > 0) {
                    toSend.append("Upcoming songs:"
                            + "\n"
                            + "```");
                    ArrayList<AudioTrack> list = musicManager.scheduler.getList();
                    for (int i = 0; i < list.size(); i++) {
                        toSend.append("\n" + musicManager.scheduler.GetTrackInfo(list.get(i)));
                    }
                    toSend.append("```");
                    event.getChannel().sendMessage(toSend).queue();
                } else {
                    toSend.append("There are no songs currently queued");
                    event.getChannel().sendMessage(toSend.toString()).queue();
                }
            }

        } else if (args[0].equalsIgnoreCase(main.prefix + "clear")) {
            PlayerManager playerManager = PlayerManager.getINSTANCE();
            GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
            musicManager.scheduler.clear();
            event.getChannel().sendMessage("Queue has been cleared!").queue();
        } else if (args[0].equalsIgnoreCase(main.prefix + "stop")) {
            PlayerManager playerManager = PlayerManager.getINSTANCE();
            GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
            event.getChannel().sendMessage(musicManager.player.getPlayingTrack().getInfo().title + " has been stopped");
            musicManager.player.stopTrack();

        }
        else if (args[0].equalsIgnoreCase(main.prefix + "fox")){
            event.getChannel().sendMessage(getFox()).queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix + "cat")){
            event.getChannel().sendMessage(getCat()).queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix + "dog")){
            event.getChannel().sendMessage(getDog()).queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix + "meme")){
            event.getChannel().sendMessage(getMeme()).queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix + "duck")){
            event.getChannel().sendMessage(getDuck()).queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix + "panda")){
            event.getChannel().sendMessage(getPanda()).queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix + "redpanda")){
            event.getChannel().sendMessage(getRPanda()).queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix)){
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.red);
            help.setTitle("This is the help screen of the bot (1)");
            help.setDescription("**!rping** ```- This will send a ping to Biggus Dickus and it will respond if it is still alive.```" +
                    "**!rinfo** ```- This gives you info about the bot```" +
                    "**!rprint** ```- Prints your message in a code block (!rprint yes YOURMESSAGE will delete your command execution)```" +
                    "**!rpp** ```- Clone of the 'pp' feature from the dank memer bot (Big meme :tm:)```" +
                    "**!reightball or !r8ball** ```- A magic eightball. Seek answers and you shall receive```" +
                    "**!rplay** ```- Plays a song from a specified url (Use !rplay search SEARCHTERM to search YouTube for a specific song)```" +
                    "**!rvolup** ```- Turns volume up by 10```" +
                    "**!rvoldown** ```- Turns volume down by 10```" +
                    "**!rdisconnect or !rdis** ```- Disconnects the bot from the audio channel it is currently connected to```" +
                    "**!rqueue** ```- Checks the currently queued songs (!r2 takes you to page 2)```");
            help.setAuthor("Biggus Dickus");
            event.getChannel().sendMessage(help.build()).queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix + "2")){
                EmbedBuilder help = new EmbedBuilder();
                help.setColor(Color.red);
                help.setTitle("This is the help screen of the bot (2)");
                help.setDescription("**!rclear** ```- Empties the song queue```" +
                        "**!rstop** ```- Stops the currently playing track```" +
                        "**!rdog** ```- Sends a random image of a dog```" +
                        "**!rcat** ```- Sends a random image of a cat```" +
                        "**!rfox** ```- Sends a random image of a fox```" +
                        "**!rduck** ```- Sends a random image of a duck```" +
                        "**!rmeme** ```- Sends a random meme sourced from r/dankmemes```" +
                        "**!rpanda** ```- Sends a random image of a panda```" +
                        "**!rredpanda** ```- Sends a random image of a red panda```");
                help.setAuthor("Biggus Dickus");
                event.getChannel().sendMessage(help.build()).queue();
        }
    }

}