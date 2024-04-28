package edu.sjsu.cs;

import edu.sjsu.cs.listener.DiscordEventListener;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import javax.security.auth.login.LoginException;

public class ModeratorBot {
    protected static ModeratorBot moderatorBot;
    private ShardManager shardManager = null;

    public ModeratorBot(String token) {
        try {
            shardManager = buildShardManager(token);
        }
        catch (LoginException e) {
            System.out.println("Failed to start bot! Please check the console for any errors.");
            System.exit(0);
        }
    }

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
