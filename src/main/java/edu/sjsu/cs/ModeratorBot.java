package edu.sjsu.cs;

import edu.sjsu.cs.listener.DiscordEventListener;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import javax.security.auth.login.LoginException;

public class ModeratorBot {
    protected static ModeratorBot moderatorBot;
    private ShardManager shardManager = null;

    // Builds and starts the bot up using the discord bot token
    public ModeratorBot(String token) {
        try {
            shardManager = buildShardManager(token);
        }
        catch (LoginException e) {
            System.out.println("Failed to start bot. Please check for errors");
            System.exit(0);
        }
    }

    // JDA Shardmanager instance that acts as the core of the discord bot
    // Creates self-contained "shards" to help with bot multi-tasking
    private ShardManager buildShardManager(String token) throws LoginException {
        DefaultShardManagerBuilder builder =
                DefaultShardManagerBuilder.createDefault(token)
                        .addEventListeners(new DiscordEventListener(this));

        return builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }
}
