# CS151 Project Group #8 - Discord Moderation Bot

Team Members: 

Johnny Huynh

James Kim 

An Pham



## **Contributions:**

### - Project Proposal Contributions:

  All team members contributed equally. We all participated in a discussion about what our topic would be and what issue we would tackle. We eventually decided to go with creating a Discord Bot to solve Discord server moderation issues. James Kim did additional research on previously existing Discord bots by other users to aid with the team's Discord bot implementation.

### - Project Implementation Contributions:

  All team members played a vital role in the implementation of the Discord bot. Creating a discord bot requires only a single user to register a bot to their account, create a discord token (which is private and should not be shared publicly), and use the terminal console to keep the bot running. Johnny Huynh was the main programmer and driver for Discord bot testing with the remaining team members assisting with feature suggestions and error troubleshooting.

### - Project Presentation Contributions

  All team members had their own section of the project to present and share with their class. Each section was roughly the same length so it would be fair during presentations. Credits to An Pham for creating the slides and planning out how the presentations will go.



## **Problem/Issue:** 

As the uses of Discord bots become more diverse, the integration of these bots raises significant concerns about security and server moderation. While useful for automating tasks and enhancing user experience, Discord bot can be exploited for malicious intentions, such as scams, unauthorized data access to users, and others. The objective of this project is to develop a server moderation Discord bot that addresses these security concerns by implementing security measures in compliance with Discord’s safety guidelines.



## **Previous work(s):**

Currently, ProBot and MEE6 are two of the more commonly used Discord moderation bots. Probot is capable of a wide range of moderation functions, including censorship, anti-spamming filters, and automatic responses. It can automatically restrict and handle users that violate the privacy of other users. MEE6, on the other hand, is known for its ability to detect rule violations and apply punishments respectively. While both these bots and others have significantly contributed to the moderation and security capabilities of Discord servers, they have not fully addressed the security concerns relating to user data privacy and protection.



## **Assumptions/Environment/Intended Use:**

- Will be designed to provide security in various server environments. Such as educational, gaming, and social communities.
- Can be written in Java
- Used to help moderate servers with simple commands



## **Description:**

A Discord bot is a customizable bot that can be used to help enhance Discord servers. Discord bots can keep track of every user’s messages and perform actions through commands or general messages. This particular project will focus on Discord moderation bots and how they regulate Discord servers.
We plan on creating a Discord bot and using the Java Discord API (JDA) to achieve this. After analyzing previous Discord bots and how they are implemented, we will use that information to create our own Discord bot that would act similarly to a Discord moderation bot if it were to be applied to the official Discord app. 



## Discord Bot Features:
- Content moderation using text filters and keyword detection
- Multipurpose commands to warn or issue timeouts to server members
- Simple commands to prevent message spamming on servers



## **Functionality/Solution:**

Our Discord moderation bot can help secure the server members’ privacies and automatically address and regulate rules. It can help filter words and prevent spam messages to create a more positive environment. Certain messages would be sent to individual server members or punishments would be placed upon the offending user if any rule violations were sent. 



## **Operations (Server Owners and Permitted Moderators):**

- Cooldown Command:

    Usage - "/Cooldown [duration]"

    Sets a cooldown between each message.
    > *Replace the duration with the number of seconds (decimals allowed) to add a cooldown between each message.
    The bot will prevent the message from being sent and notify the user if a message is before the cooldown timer is over. To remove the cooldown feature, set the duration to 0 seconds*

- Warn Command:

    Usage - "/warn [user] [reason] [duration]"

     *Sends a warning to a specific user with a stated reason*
    > Additionally, the command includes an optional duration parameter to put them on timeout (unable to send any messages) for the stated duration (in seconds).

- Blacklisted Words:
  
    *Enables a blacklist feature which prevents users from sending a message containing those blacklisted words.*
    > Only server owners and moderators can modify the blacklisted words*



## **How To Set Up**

Use this link to add the Discord Moderation Bot to any server:
[Discord Moderation Bot Add](https://discord.com/oauth2/authorize?client_id=1233912257763872829&permissions=8&scope=applications.commands%20bot)

> [!IMPORTANT]
> Discord bots will only work when their status is "Online". As of now, the Discord bot will only be "Online" when the main programmer (Johnny Huynh) runs the java.jar file using the private Discord bot token on their own device's terminal. Most Discord bots require a paid service provider, some kind of cloud platforming service, or a special computer that is always on in order to keep their Discord bots active at all times (which we do not have). In order to see visual demonstration of the features, we have prepared short animated recordings which are in the [Project Presentation Slides](https://docs.google.com/presentation/d/1EkAxPFJ7hBW_-RhbZKyagVuueEsJMRrLn_KdeKTiw88/edit#slide=id.g26ff9382086_0_11).



## **Diagrams**

Class diagram, case diagram, sequence diagram, and state diagram can be quickly accessed here:

[Diagrams](diagrams/README.md)



## **Project Presentation Slides (Includes Snapshots and Demonstrations of the Running Program)**

Summary of the Discord Bot, external tools used, animated demonstrations of features, and diagrams are shown in here:

[Project Presentation Slides](https://docs.google.com/presentation/d/1EkAxPFJ7hBW_-RhbZKyagVuueEsJMRrLn_KdeKTiw88/edit#slide=id.g2d139d6b451_0_1088)





## **References:**

[1] “ProBot - Discord Multipurpose bot,” ProBot. https://probot.io/

[2] “Discord Bot | MEE6,” mee6.xyz. https://mee6.xyz/en/‌

[3] “Privacy Policy | Discord,” Discord. https://discord.com/privacy/

[4] “Safety Library | Discord,” Discord. https://discord.com/safety-library/

