package edu.sjsu.cs.listener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.jetbrains.annotations.NotNull;
import edu.sjsu.cs.ModeratorBot;

public class DiscordEventListener extends ListenerAdapter {
    public ModeratorBot bot;

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
            CommandListUpdateAction commands = guild.updateCommands();
            commands.addCommands(Commands.slash("hello",
                    "Have the bot say hello to you")).queue();
        }
    }

    // Initiates events according to slash commands the bot receives
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("hello")) {
            event.reply("Hello " + event.getUser().getAsMention() + "!")
                    .setEphemeral(true) // Only the user can see the message
                    .queue();
        }
    }
}
