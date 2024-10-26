package me.jaskowicz.mindustrydiscordrelay;

import arc.*;
import arc.net.Server;
import arc.util.*;
import me.jaskowicz.mindustrydiscordrelay.Utils.DiscordUtils;
import me.jaskowicz.mindustrydiscordrelay.Utils.FormatUtils;
import me.jaskowicz.mindustrydiscordrelay.Utils.User;
import mindustry.*;
import mindustry.game.EventType;
import mindustry.mod.Mods;
import mindustry.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main extends Plugin {

    // This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
    // To view a copy of this license,
    // visit http://creativecommons.org/licenses/by-nc-sa/4.0/ or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.

    // Created by: Archie Jaskowicz.

    private long serverTimeStart;
    private long serverTimeFinish;

    // Replace "<INSERT_DISCORD_WEBHOOK_HERE>" with your discord webhook url.
    private String discordWebhook = "https://discord.com/api/webhooks/1299458318313721856/mSyy19GqgfE-FhFy7C2wlf0MdlDVUdzcZJpY7LttJI7wJYHHtHTWuQC0WyxJzc7p_hLb";

    public static HashMap<String, User> USERS = new HashMap<>();

    //register event handlers and create variables in the constructor
    public Main(){
        Events.on(EventType.ServerLoadEvent.class, event -> {
            DiscordUtils.sendMessage(discordWebhook, "🟢 Server started 🟢");
        });

    //register commands that run on the server
    @Override
    public void registerServerCommands(CommandHandler handler){
        handler.register("senddiscordmessage", "<text...>", "Send a message to your server's discord via webhook.", (args) -> {
            StringBuilder message = new StringBuilder();
            for(String str : args) {
                message.append(str).append(" ");
            }
            Log.debug("Sending " + message + " to discord...");
            DiscordUtils.sendMessage(discordWebhook, message.toString());
            Log.debug("Sent the message to discord!");
        });

        handler.register("setdiscordwebhook", "<text>", "Set the webhook for this plugin!", (args) -> {
           if(args.length == 0) {
               Log.debug("Insufficient arguments.");
           } else if(args.length == 1) {
               discordWebhook = args[0];
               Log.debug("Updated the discord webhook!");
           } else {
               Log.debug("Too many arguments.");
           }
        });
    }

    //register commands that player can invoke in-game
    //@Override
    //public void registerClientCommands(CommandHandler handler){
    //}

    /*

    // This just throws some stuff about how it failed to create the file because it's just not doing it. No idea why but oh well.
    // In my opinion, this is really dumb that this doesn't work and just makes everything worse for people so I may end up making a video
    // on how to get this setup for people.

    private void readConfigFile() {
        File file = new File("/MindustryDiscordRelayConfig/config/DiscordStuff.txt");

        if(file.exists()) {
            System.out.println("Config found! Reading config...");

            Scanner scanner = null;

            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException exception) {
                System.out.println("Config not found... generating then disabling plugin. You will need to change the config and restart the server.");
                return;
            }

            if(!scanner.hasNextLine()) {
                System.out.println("Nothing found inside the config. Preventing plugin from running as plugin will cause errors.");

                for(Mods.LoadedMod loadedMod : Vars.mods.list()) {
                    if(loadedMod.name.equals("Mindustry-Discord-Relay")) {
                        Vars.mods.setEnabled(loadedMod, false);
                    }
                }

                return;
            }

            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                String[] args = text.split(":");

                if(args.length != 0) {
                    if(args.length == 2) {
                        discordWebhook = args[1];
                    } else if (args.length < 2) {
                        System.out.println("Line has too little arguments (colons) on it. Ignoring line.");
                    } else {
                        System.out.println("Line has too many arguments (colons) on it. Ignoring line.");
                    }
                } else {
                    System.out.println("Config does not have any information.");
                    return;
                }
            }

            if(discordWebhook == null) {
                System.out.println("Could not find a discord webhook. Disabling plugin.");

                for(Mods.LoadedMod loadedMod : Vars.mods.list()) {
                    if(loadedMod.name.equals("Mindustry-Discord-Relay")) {
                        Vars.mods.setEnabled(loadedMod, false);
                    }
                }

                return;
            }

            System.out.println("Finished file loading.");

        } else {

            System.out.println("Config not found... generating then disabling plugin. You will need to change the config and restart the server.");

            try {
                //file.mkdirs();
                boolean created = file.createNewFile();

                if(created) {
                    System.out.println("File created.");
                } else {
                    System.out.println("File failed to be created.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(Mods.LoadedMod loadedMod : Vars.mods.list()) {
                if(loadedMod.name.equals("Mindustry-Discord-Relay")) {
                    Vars.mods.setEnabled(loadedMod, false);
                }
            }

            return;
        }
    }

     */
}
