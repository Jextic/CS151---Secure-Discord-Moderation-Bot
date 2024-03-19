CS151 Project Group #4 
Team Members: Johnny Huynh, James Kim, An Pham

**Problem/Issue:** 

As the uses of Discord bots become more diverse, the integration of these bots raise significant concerns about security and server moderation. While useful for automating tasks and enhancing user experience, Discord bot can be exploited for malicious intentions, such as scams, unauthorized data access to users, and others. The objective of this project is to develop a server moderation Discord bot that addresses these security concerns by implementing security measures in compliance with Discord’s safety guidelines.

**Previous work(s):**

Currently, ProBot and MEE6 are two of the more commonly used Discord moderation bots. Probot is capable of a wide range of moderation functions, including censorship, anti-spamming filter, and automatic responses. It can automatically restrict and handle users that violate the privacy of other users. MEE6, on the other hand, is known for its ability to detect rule violations and apply punishments respectively. While both these bots and others have significantly contributed to the moderation and security capabilities of Discord servers, they have not fully addressed the security concerns relating to user data privacy and protection.


**Assumptions/Environment/Intended Use:**

Will be designed to provide security in various server environments. Such as educational, gaming, and social communities.
Can be written in Java
Focus on user privacy, securely handling user data according to Discord’s privacy policies
Easy to understand so people that are not as familiar with technology are able to easily work with and modify the bot as they please.


**Description:**

A Discord bot is a customizable bot that can be used to help enhance Discord servers. Discord bots can keep track of every user’s messages and perform actions through commands or general messages. This particular project will focus on Discord moderation bots and how they regulate Discord servers.
We plan on replicating Discord’s interface including friends, discord channels, direct messages, profile statuses, profile information, etc. After analyzing previous Discord bots and how they are implemented, we will use that information to create our own Discord bot that would act similarly to a Discord moderation bot if it were to be applied to the official Discord app. 

Discord Bot Features:
- Content moderation using text filters and keyword detection
- User verification to prevent unauthorized access
- Automated detection and response to scams
- Privacy protection measures
- Data encryption and secure handling of user information


**Functionality:**

Our Discord moderation bot can help secure the server members’ privacies and automatically address and regulate rules. It can help filter words and prevent spam messages to create a more positive environment. Certain messages would be sent to individual server members or punishments would be placed upon the offending user if any rule violations were recorded in the message logs. Additional features can include poll making or fun entertainment features.


**Operations (list operations for each intended user):**

Server Administrators:

- Setup and configuration of the bot with customized rules.
- Manage bot’s activities to check if bot is functioning as intended.
- Receive and respond to scam reports or attempts at unauthorized access.
- 
Moderators:

- Utilize automation of moderating tasks such as filtering
- Report and manage suspicious activity detected by the bot
  
Users/Members:
- Interact with the bot using preset/customizable commands in order to verify accounts or to report suspicious activity.
- If caught doing anything that is against server rules, the user will be punished accordingly
  


**References:**

[1] “ProBot - Discord Multipurpose bot,” ProBot. https://probot.io/ ‌
[2] “Discord Bot | MEE6,” mee6.xyz. https://mee6.xyz/en/ ‌
[3] “Privacy Policy | Discord,” Discord. https://discord.com/privacy/
[4] “Safety Library | Discord,” Discord. https://discord.com/safety-library/

