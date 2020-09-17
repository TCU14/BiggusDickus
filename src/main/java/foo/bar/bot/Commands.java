package foo.bar.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Random;

import java.awt.*;

public class Commands extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(main.prefix + "ping")) {
            event.getChannel().sendMessage("pong! Biggus Dickus lives on").queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix + "info")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(Color.red);
            info.setTitle("Biggus Dickus Bot version 1.0");
            info.setColor(Color.cyan);
            info.setDescription("Insert unrelated fuckshit here");
            info.setAuthor("Red The Moron");
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
        else if (args[0].equalsIgnoreCase(main.prefix + "print")){
            event.getChannel().sendMessage("```" + event.getMessage().getContentRaw().substring(8) + "```").queue();
            event.getMessage().delete().queue();
        }
        else if (args[0].equalsIgnoreCase(main.prefix)){
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.red);
            help.setTitle("This is the help screen of the bot.");
            help.setDescription("```!rping - This will send a ping to Biggus Dickus and it will respond if it is still alive.```"  +
                    "```!rinfo - This gives you info about the bot```" +
                    "```!rprint - Prints your message in a code block and then removes your original message. This action will still show up in logs of course```");
            help.setAuthor("Biggus Dickus");
            event.getChannel().sendMessage(help.build()).queue();
        }
    }
}
