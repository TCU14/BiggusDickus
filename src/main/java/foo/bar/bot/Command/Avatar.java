package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import jdk.internal.org.jline.utils.Colors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Avatar extends Command {
    public Avatar() {
        this.name = "Avatar";
        this.aliases = new String[]{"avatar", "av"};
        this.arguments = "@User";
        this.help = "Brings up the avatar of a specified user";
    }
    private static Member searchGuildForMember(CommandEvent event) {
        //sort the list in alphabetical order
        List<Member> guildMembers = new ArrayList<>(event.getGuild().getMembers());

        guildMembers.sort((m1, m2) -> m1.getEffectiveName().compareToIgnoreCase(m2.getEffectiveName()));

        //trim the string down to just the first argument
        String firstArg = event.getArgs().substring(event.getArgs().indexOf(" ") + 1);

        //go through every member in the guild
        for (Member i : guildMembers) {
            //go through nicknames first
            if (i.getEffectiveName().startsWith(firstArg))
                return i;
            else if (i.getEffectiveName().toLowerCase().startsWith(firstArg.toLowerCase()))
                return i;
            else if (i.getEffectiveName().contains(firstArg))
                return i;
            else if (i.getEffectiveName().toLowerCase().contains(firstArg.toLowerCase()))
                return i;
                //then check everyone's username
            else if (i.getUser().getName().startsWith(firstArg))
                return i;
            else if (i.getUser().getName().toLowerCase().startsWith(firstArg.toLowerCase()))
                return i;
            else if (i.getUser().getName().contains(firstArg))
                return i;
            else if (i.getUser().getName().toLowerCase().contains(firstArg.toLowerCase()))
                return i;
        }
        return null;
    }

    public static Member getTargetMember (CommandEvent event)
    {
        //if they mentioned somebody then use them
        if (!event.getMessage().getMentionedMembers().isEmpty()) {
            return event.getEvent().getMessage().getMentionedMembers().get(0);
        }
        //if nobody is mentioned then see if they put in a search query
        else if (!event.getArgs().isEmpty() && !event.getArgs().equalsIgnoreCase("help")) {
            if (searchGuildForMember(event) != null)
                return searchGuildForMember(event);
            else
                event.replyWarning("Nobody with that name could be found.");
        }
        //if there's no search just do the person who sent the commmand
        else if (!event.getArgs().equalsIgnoreCase("help"))
            return event.getMember();
        return null;
    }

    @Override
    protected void execute(CommandEvent event) {
        //find a target
        Member targetMember = getTargetMember(event);

        if (!event.getAuthor().isBot())
        {
            if (targetMember != null)
            {
                EmbedBuilder pfp = new EmbedBuilder()
                        .setColor(targetMember.getColor())
                        .setTitle(targetMember.getUser().getName() + "#" + targetMember.getUser().getDiscriminator())
                        .setImage(targetMember.getUser().getEffectiveAvatarUrl() + "?size=2048");

                event.reply(pfp.build());
            }
        }
    }
}
