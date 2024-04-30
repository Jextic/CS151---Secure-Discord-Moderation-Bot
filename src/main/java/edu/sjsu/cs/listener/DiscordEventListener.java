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
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import edu.sjsu.cs.ModeratorBot;

import java.time.Duration;
import java.util.HashMap;

public class DiscordEventListener extends ListenerAdapter {
    public ModeratorBot bot;
    private final HashMap<String, Long> cooldowns;
    private double cooldownPeriod;
    private final String[] blacklistedWords = {
            "ugly",
            "stupid",
            "idiot",
    };

    // DiscordEventListener allows the bot to listen to events and messages sent to the bot
    public DiscordEventListener(ModeratorBot bot) {
        this.bot = bot;
        cooldowns = new HashMap<>();
        cooldownPeriod = 0;
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
                    Commands.slash("warn", "Issues a warning to a member of the server")
                            .addOption(OptionType.USER, "user", "Warn this user", true)
                            .addOption(OptionType.STRING, "reason", "Reason for warning issue", true)
                            .addOption(OptionType.INTEGER, "duration", "Optionally set timeout duration (seconds)", false),
                    Commands.slash("cooldown", "Set the cooldown period (seconds)")
                            .addOption(OptionType.NUMBER, "duration", "Set the cooldown duration (Set to 0 to disable)", true)
            ).queue();
        }
    }

    // Initiates events according to slash commands the bot receives
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("warn")) {
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
        else if(event.getName().equals("cooldown")) {
            cooldownPeriod = event.getOption("duration").getAsDouble();
            event.reply("Cooldown has been successfully set")
                    .setEphemeral(true)
                    .queue();
            event.getChannel().sendMessage("Cooldown period has been set to: **" +
                    event.getOption("duration").getAsString() + "** seconds").queue();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String userId = event.getAuthor().getId();

            if (cooldowns.containsKey(userId)) {
                long lastMessageTime = cooldowns.get(userId);
                long currentTime = System.currentTimeMillis();
                long timeSinceLastMessage = currentTime - lastMessageTime;

                if (timeSinceLastMessage < cooldownPeriod * 1000) {
                    event.getChannel().sendMessage("Please refrain from spamming, " +
                            event.getAuthor().getEffectiveName()).queue();
                    event.getMessage().delete().queue();
                    return;
                }
            }

            cooldowns.put(userId, System.currentTimeMillis());

            for (String word : blacklistedWords) {
                if (event.getMessage().getContentRaw().equalsIgnoreCase(word)) {
                    event.getChannel().sendMessage("Please refrain from using offensive language, " +
                            event.getAuthor().getEffectiveName()).queue();
                    event.getMessage().delete().queue();
                }
            }
        }
    }
}