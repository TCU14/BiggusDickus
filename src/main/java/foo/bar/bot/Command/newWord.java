package foo.bar.bot.Command;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import foo.bar.bot.API.getResponse;
import org.json.JSONObject;

import java.io.IOException;

class Word {
    @SerializedName("word")
    @Expose
    public String word;
    @SerializedName("definition")
    @Expose
    public String definition;
}

public class newWord extends Command {
    public newWord(){
        this.name = "word";
        this.aliases = new String[]{"Word","newword","NewWord","newWord"};
        this.help = "Command to fetch a machine-generated word from thisworddoesnotexist";
    }

    @Override
    protected void execute(CommandEvent event){
        Gson gson = new Gson();
        try {
            JSONObject json = getResponse.readJsonFromUrl("https://www.thisworddoesnotexist.com/api/random_word.json");
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(String.valueOf(json));
            JsonObject object = jsonTree.getAsJsonObject();
            JsonObject newword = object.getAsJsonObject("word");
            Word yikes = gson.fromJson(newword, Word.class);
            event.getChannel().sendMessage(yikes.word + ": " + yikes.definition).queue();
            parser = null;
            json = null;
            jsonTree = null;
            yikes = null;
            object = null;
            newword = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
