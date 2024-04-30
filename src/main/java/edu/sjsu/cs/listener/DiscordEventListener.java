package edu.sjsu.cs.listener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.jetbrains.annotations.NotNull;
import edu.sjsu.cs.ModeratorBot;

import java.time.Duration;

public class DiscordEventListener extends ListenerAdapter {
    public ModeratorBot bot;
    private String[] blacklistedWords = {
            "ugly",
            "stupid",
            "idiot",
    };

    // DiscordEventListener allows the bot to listen to events and messages sent to the bot
    public DiscordEventListener(ModeratorBot bot) {
        this.bot = bot;
    }

    // Loads all guilds to prevent errors
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        registerCommands(bot.getShardManager());
    }

    // Registers all slash commands to the bot
    private void registerCommands(ShardManager jda) {
        // Allows only this server to register the commands
        Guild guild = jda.getGuildById("1234228780260917300");
        if (guild != null) {
            guild.updateCommands().addCommands(
                    Commands.slash("hello", "Have the bot say hello to you"),
                    Commands.slash("warn", "Issues a warning to a member of the server")
                            .addOption(OptionType.USER, "user", "Warn this user", true)
                            .addOption(OptionType.STRING, "reason", "Reason for warning issue", true)
                            .addOption(OptionType.INTEGER, "duration", "Optionally set timeout duration in seconds", false)
            ).queue();
        }
    }

    // Initiates events according to slash commands the bot receives
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("hello")) {
            event.reply("Hello " + event.getUser().getAsMention() + "!!!!")
                    .setEphemeral(true);
        }
        else if (event.getName().equals("warn")) {
            if (event.getOption("duration") != null) {
                String messageWithDuration = "**" + event.getUser().getEffectiveName() + "** has timed out **" +
                        event.getOption("user").getAsMember().getEffectiveName() + "** for **" +
                        event.getOption("duration").getAsString() + "** seconds for: **" +
                        event.getOption("reason").getAsString() + "**";

                event.getGuild().timeoutFor(event.getOption("user").getAsUser(),
                        Duration.ofSeconds(event.getOption("duration").getAsLong())).queue();
                event.getChannel().sendMessage(messageWithDuration).queue();
                event.reply("Warning has been successfully issued")
                        .setEphemeral(true)
                        .queue();
            }
            else {
                String messageWithoutDuration = "**" + event.getUser().getEffectiveName() + "** has issued a warning to **" +
                        event.getOption("user").getAsMember().getEffectiveName() + "** for: **" +
                        event.getOption("reason").getAsString() + "**";

                event.getChannel().sendMessage(messageWithoutDuration).queue();
                event.reply("Timeout has been successfully issued")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        for (String word: blacklistedWords) {
            if (event.getMessage().getContentRaw().equalsIgnoreCase(word)) {
                event.getChannel().sendMessage("Please refrain from using offensive language").queue();
                event.getMessage().delete().queue();
            }
        }
    }
}
