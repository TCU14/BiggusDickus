package foo.bar.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static foo.bar.bot.API.getResponse.getAPI;

public class ProfilePic extends Command {
    public ProfilePic(){
        this.name = "ProfilePic";
        this.aliases = new String[]{"fakeface", "pfp", "profilepic"};
        this.help = "Sends a fake profile pic/face (https://thispersondoesnotexist.com)";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(getAPI("https://fakeface.rest/face/json", "image_url")).queue();
    }
}
