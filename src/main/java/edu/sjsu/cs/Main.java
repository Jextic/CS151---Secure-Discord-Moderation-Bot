package edu.sjsu.cs;

import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption(new Option("h", "help", false, "Displays this help menu"));
        options.addOption(new Option("t", "token", true, "Provides the token during startup"));

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            // Checks if help was provided
            if (cmd.hasOption("help")) {
                formatter.printHelp("Help Menu", options);
                System.exit(0);
            }

            // Checks if discord bot token argument is provided
            String token = cmd.hasOption("token") ? cmd.getOptionValue("token") : null;
            if (token == null) {
                System.out.println("ERROR: No token provided, please provide a token using the -t or --token flag");
                formatter.printHelp("", options);
                System.exit(0);
            }

            // If it passes through everything, it starts the bot and sends the token to our second class.
            ModeratorBot.moderatorBot = new ModeratorBot(token);
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("", options);
            System.exit(0);
        }
    }
}