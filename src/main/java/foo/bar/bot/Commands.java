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
import net.dv8tion.jda.api.requests.RestAction;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.sound.midi.Track;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
import static foo.bar.bot.main.jda;

public class Commands extends ListenerAdapter {
    public static void GenerateRandomPP() {
        int pplength = 0;
        Random random = new Random();
        pplength = random.nextInt(13);
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
//Add the command triggers, (args[0] = First word in a message, args[1] = second word, etc. Above this comment I used
        // \\s to split up the arguments so that we can differentiate/utilize them properly
//And here we have a series of if statements/else-if's to detect commands.
        if (args[0].equalsIgnoreCase(main.prefix)) {
            switch (args[1]) {
                case "help":
                    if (args.length >= 3) {
                        if (args[2].equalsIgnoreCase("2")) {
                            EmbedBuilder pg2 = new EmbedBuilder();
                            pg2.setColor(Color.red);
                            pg2.setTitle("This is the help screen of the bot (2)");
                            pg2.setDescription("**!r clear** ```- Empties the song queue```" +
                                    "**!r stop** ```- Stops the currently playing track```" +
                                    "**!r dog** ```- Sends a random image of a dog```" +
                                    "**!r cat** ```- Sends a random image of a cat```" +
                                    "**!r fox** ```- Sends a random image of a fox```" +
                                    "**!r duck** ```- Sends a random image of a duck```" +
                                    "**!r meme** ```- Sends a random meme sourced from r/dankmemes```" +
                                    "**!r panda** ```- Sends a random image of a panda```" +
                                    "**!r redpanda** ```- Sends a random image of a red panda```");
                            pg2.setAuthor("Biggus Dickus");
                            event.getChannel().sendMessage(pg2.build()).queue();
                            break;
                        }
                        else {
                            break;
                        }
                    } else {
                        EmbedBuilder help = new EmbedBuilder();
                        help.setColor(Color.red);
                        help.setTitle("This is the help screen of the bot (1)");
                        help.setDescription("**!r ping** ```- This will send a ping to Biggus Dickus and it will respond if it is still alive.```" +
                                "**!r info** ```- This gives you info about the bot```" +
                                "**!r print** ```- Prints your message in a code block (!r print yes YOURMESSAGE will delete your command execution)```" +
                                "**!r pp** ```- Clone of the 'pp' feature from the dank memer bot (Big meme :tm:)```" +
                                "**!r eightball or !r8ball** ```- A magic eightball. Seek answers and you shall receive```" +
                                "**!r play** ```- Plays a song from a specified url (Use !rplay search SEARCHTERM to search YouTube for a specific song)```" +
                                "**!r volup** ```- Turns volume up by 10```" +
                                "**!r voldown** ```- Turns volume down by 10```" +
                                "**!r disconnect or !r dis** ```- Disconnects the bot from the audio channel it is currently connected to```" +
                                "**!r queue** ```- Checks the currently queued songs ('!r help 2' takes you to page 2)```");
                        help.setAuthor("Biggus Dickus");
                        event.getChannel().sendMessage(help.build()).queue();
                        break;
                    }

                case "ping":
                    event.getChannel().sendMessage("pong! Biggus Dickus lives on").queue();
                    break;
                case "info":
                    EmbedBuilder info = new EmbedBuilder();
                    info.setColor(Color.red);
                    info.setTitle("Biggus Dickus Bot version 2.0");
                    info.setColor(Color.cyan);
                    info.setDescription("Bot is still in active development and as such there may be bugs. Please report any and all issues on my Github." +
                            "But also check for duplicates beforehand in case I was already made aware of your issue. [Github can be found here](https://github.com/TCU14/BiggusDickus)");
                    info.setAuthor("Red The Moron");
                    event.getChannel().sendMessage(info.build()).queue();
                    info.clear();
                    break;
                case "print":
                    if (args[2].equalsIgnoreCase("yes")) {
                        event.getChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(13) + "```").queue();
                        event.getMessage().delete().queue();
                        break;
                    } else {
                        event.getChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(9) + "```").queue();
                        break;
                    }
                case "pp":
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
                    break;
                case "eightball":
                case "8ball":
                    String[] responses = {"It is certain", "Without a doubt", "You may rely on it", "Yes definitely", "It is decidedly so",
                            "As I see it, yes", "Most likely", "Yes", "Outlook good", "Signs point to yes", "Reply hazy try again",
                            "Better not tell you now", "Ask again later", "Cannot predict now", "Concentrate and ask again",
                            "Don’t count on it", "Outlook not so good", "My sources say no", "Very doubtful", "My reply is no"};
                    Random r = new Random();
                    int answer = 0;
                    answer = r.nextInt(20);
                    event.getChannel().sendMessage(responses[answer]).queue();
                    break;
                case "play":
                    if (args[2].equalsIgnoreCase("search")) {
                        PlayerManager manager = PlayerManager.getINSTANCE();
                        VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
                        AudioManager audioManager = event.getGuild().getAudioManager();
                        audioManager.openAudioConnection(connectedChannel);
                        String Query = "";
                        Query = event.getMessage().getContentRaw().substring(14);
                        manager.LoadAndPlay(event.getChannel(), "ytsearch:" + Query);
                        break;

                    } else {
                        PlayerManager manager = PlayerManager.getINSTANCE();
                        VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
                        AudioManager audioManager = event.getGuild().getAudioManager();
                        audioManager.openAudioConnection(connectedChannel);
                        manager.LoadAndPlay(event.getChannel(), args[2]);
                        manager.getGuildMusicManager(event.getGuild()).player.setVolume(10);
                        break;
                    }
                case "volup":
                    PlayerManager manager = PlayerManager.getINSTANCE();
                    manager.getGuildMusicManager(event.getGuild()).player.setVolume(manager.getGuildMusicManager(event.getGuild()).player.getVolume() + 10);
                    break;
                case "voldown":
                    PlayerManager manager1 = PlayerManager.getINSTANCE();
                    manager1.getGuildMusicManager(event.getGuild()).player.setVolume(manager1.getGuildMusicManager(event.getGuild()).player.getVolume() - 10);
                    break;
                case "disconnect":
                case "dis":
                    AudioManager audioManager = event.getGuild().getAudioManager();
                    audioManager.closeAudioConnection();
                    break;
                case "skip":
                    TextChannel channel = event.getChannel();
                    PlayerManager playerManager = PlayerManager.getINSTANCE();
                    GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
                    event.getChannel().sendMessage("Skipped " + musicManager.player.getPlayingTrack().getInfo().title).queue();
                    musicManager.scheduler.nextTrack();
                    break;
                case "queue":
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
                            break;
                        } else {
                            toSend.append("There are no songs currently queued");
                            event.getChannel().sendMessage(toSend.toString()).queue();
                            break;
                        }
                    }
                    else {
                        event.getChannel().sendMessage("There are no songs queued or playing right now!").queue();
                        break;
                    }
                case "clear":
                    PlayerManager playerManager2 = PlayerManager.getINSTANCE();
                    GuildMusicManager musicManager2 = playerManager2.getGuildMusicManager(event.getGuild());
                    musicManager2.scheduler.clear();
                    event.getChannel().sendMessage("Queue has been cleared!").queue();
                    break;
                case "stop":
                    PlayerManager playerManager3 = PlayerManager.getINSTANCE();
                    GuildMusicManager musicManager3 = playerManager3.getGuildMusicManager(event.getGuild());
                    event.getChannel().sendMessage(musicManager3.player.getPlayingTrack().getInfo().title + " has been stopped");
                    musicManager3.player.stopTrack();
                    break;
                case "fox":
                    event.getChannel().sendMessage(getFox()).queue();
                    break;
                case "cat":
                    event.getChannel().sendMessage(getCat()).queue();
                    break;
                case "dog":
                    event.getChannel().sendMessage(getDog()).queue();
                    break;
                case "meme":
                    event.getChannel().sendMessage(getMeme()).queue();
                    break;
                case "duck":
                    event.getChannel().sendMessage(getDuck()).queue();
                    break;
                case "panda":
                    event.getChannel().sendMessage(getPanda()).queue();
                    break;
                case "redpanda":
                    event.getChannel().sendMessage(getRPanda()).queue();
                    break;

            }

            }
        }
    }