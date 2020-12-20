package com.onarandombox.MultiverseCore.commands_acf;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Conditions;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import com.onarandombox.MultiverseCore.enums.EnglishChatColor;
import com.onarandombox.MultiverseCore.exceptions.PropertyDoesNotExistException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;


public class ModifyCommand extends MultiverseCommand {

    public ModifyCommand(MultiverseCore plugin) {
        super(plugin);
    }


    //TODO: AddProperties Validation

    // LINK: https://tinyurl.com/nehhzp6

    @CommandAlias("mv")
    @Subcommand("modify")
    public class Modify extends BaseCommand {

        @Subcommand("set")
        @CommandPermission("multiverse.core.modify.set")
        @Syntax("<property> <value> [world]")
        @CommandCompletion("@setProperties  @MVWorlds")
        @Description("Modify various aspects of worlds by setting a property. For more info; https://tinyurl.com/nehhzp6")
        public void onModifySetCommand(@NotNull CommandSender sender,
                                       @NotNull @Conditions("validAddProperty:set") String property,
                                       @NotNull String value,
                                       @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifySet(sender, property, value, world);
        }

        //TODO: Think why previously it was {VALUE} {PROPERTY}
        @Subcommand("add")
        @CommandPermission("multiverse.core.modify.add")
        @Syntax("<property> <value> [world]")
        @CommandCompletion("@addProperties  @MVWorlds")
        @Description("Modify various aspects of worlds by adding a property. For more info: https://tinyurl.com/nehhzp6")
        public void onModifyAddCommand(@NotNull CommandSender sender,
                                       @NotNull @Conditions("validAddProperty:add") String property,
                                       @NotNull String value,
                                       @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifyAdd(sender, property, value, world);
        }

        @Subcommand("remove")
        @CommandPermission("multiverse.core.modify.remove")
        @Syntax("<property> <value> [world]")
        @CommandCompletion("@addProperties  @MVWorlds")
        @Description("Modify various aspects of worlds by removing a property. For more info: https://tinyurl.com/nehhzp6")
        public void onModifyRemoveCommand(@NotNull CommandSender sender,
                                          @NotNull @Conditions("validAddProperty:remove") String property,
                                          @NotNull String value,
                                          @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifyRemove(sender, property, value, world);
        }

        @Subcommand("clear")
        @CommandPermission("multiverse.core.modify.clear")
        @Syntax("<property> <value> [world]")
        @CommandCompletion("@addProperties  @MVWorlds")
        @Description("Modify various aspects of worlds by clearing a property. For more info: https://tinyurl.com/nehhzp6")
        public void onModifyClearCommand(@NotNull CommandSender sender,
                                         @NotNull @Conditions("validAddProperty:clear") String property,
                                         @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifyClear(sender, property, world);
        }

        @Subcommand("list")
        @CommandPermission("multiverse.core.modify.list")
        @Syntax("[world]")
        @CommandCompletion("@MVWorlds")
        @Description("Show properties available to set.")
        public void onModifyClearCommand(@NotNull CommandSender sender,
                                         @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifyList(sender, world);
        }
    }

    @CommandAlias("mvm")
    public class AliasModify extends BaseCommand {

        @Subcommand("set")
        @CommandPermission("multiverse.core.modify.set")
        @Syntax("<property> <value> [world]")
        @CommandCompletion("@setProperties  @MVWorlds")
        @Description("Modify various aspects of worlds by setting a property. For more info; https://tinyurl.com/nehhzp6")
        public void onModifySetCommand(@NotNull CommandSender sender,
                                       @NotNull @Conditions("validAddProperty:set") String property,
                                       @NotNull String value,
                                       @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifySet(sender, property, value, world);
        }

        //TODO: Think why previously it was {VALUE} {PROPERTY}
        @Subcommand("add")
        @CommandPermission("multiverse.core.modify.add")
        @Syntax("<property> <value> [world]")
        @CommandCompletion("@addProperties  @MVWorlds")
        @Description("Modify various aspects of worlds by adding a property. For more info: https://tinyurl.com/nehhzp6")
        public void onModifyAddCommand(@NotNull CommandSender sender,
                                       @NotNull @Conditions("validAddProperty:add") String property,
                                       @NotNull String value,
                                       @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifyAdd(sender, property, value, world);
        }

        @Subcommand("remove")
        @CommandPermission("multiverse.core.modify.remove")
        @Syntax("<property> <value> [world]")
        @CommandCompletion("@addProperties  @MVWorlds")
        @Description("Modify various aspects of worlds by removing a property. For more info: https://tinyurl.com/nehhzp6")
        public void onModifyRemoveCommand(@NotNull CommandSender sender,
                                          @NotNull @Conditions("validAddProperty:remove") String property,
                                          @NotNull String value,
                                          @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifyRemove(sender, property, value, world);
        }

        @Subcommand("clear")
        @CommandPermission("multiverse.core.modify.clear")
        @Syntax("<property> <value> [world]")
        @CommandCompletion("@addProperties  @MVWorlds")
        @Description("Modify various aspects of worlds by clearing a property. For more info: https://tinyurl.com/nehhzp6")
        public void onModifyClearCommand(@NotNull CommandSender sender,
                                         @NotNull @Conditions("validAddProperty:clear") String property,
                                         @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifyClear(sender, property, world);
        }

        @Subcommand("list")
        @CommandPermission("multiverse.core.modify.list")
        @Syntax("[world]")
        @CommandCompletion("@MVWorlds")
        @Description("Show properties available to set.")
        public void onModifyClearCommand(@NotNull CommandSender sender,
                                         @NotNull @Flags("other,defaultself") MultiverseWorld world) {

            doModifyList(sender, world);
        }
    }

    //TODO: Think why properties method for MultiverseWorld is deprecated.
    private void doModifySet(@NotNull CommandSender sender,
                             @NotNull String property,
                             @NotNull String value,
                             @NotNull MultiverseWorld world) {

        if ((property.equalsIgnoreCase("aliascolor")
                || property.equalsIgnoreCase("color"))
                && !EnglishChatColor.isValidAliasColor(value)) {
            sender.sendMessage(value + " is not a valid color. Please pick one of the following:");
            sender.sendMessage(EnglishChatColor.getAllColors());
            return;
        }

        try {
            if (!world.setPropertyValue(property, value)) {
                sender.sendMessage(ChatColor.RED + world.getPropertyHelp(property));
                return;
            }
        }
        catch (PropertyDoesNotExistException e) {
            sender.sendMessage(ChatColor.RED + "Sorry, You can't set '" + ChatColor.GRAY + property + ChatColor.RED + "'");
            sender.sendMessage("Valid world-properties: " + world.getAllPropertyNames());
            return;
        }

        sender.sendMessage(ChatColor.GREEN + "Success!" + ChatColor.WHITE + " Property " + ChatColor.AQUA
                + property + ChatColor.WHITE + " was set to " + ChatColor.GREEN + value + ChatColor.WHITE + ".");
        saveWorldConfig(sender);
    }

    private void doModifyAdd(@NotNull CommandSender sender,
                             @NotNull String property,
                             @NotNull String value,
                             @NotNull MultiverseWorld world) {

        if (!world.addToVariable(property, value)) {
            sender.sendMessage(value + " could not be added to " + property);
            return;
        }

        sender.sendMessage(ChatColor.GREEN + "Success! " + ChatColor.AQUA + value + ChatColor.WHITE + " was "
                + ChatColor.GREEN + "added to " + ChatColor.GREEN + property);
        saveWorldConfig(sender);
    }

    private void doModifyRemove(@NotNull CommandSender sender,
                                @NotNull String property,
                                @NotNull String value,
                                @NotNull MultiverseWorld world) {

        if (!world.removeFromVariable(property, value)) {
            sender.sendMessage(ChatColor.RED + "There was an error removing " + ChatColor.GRAY
                    + value + ChatColor.WHITE + " from " + ChatColor.GOLD + property);
            return;
        }

        sender.sendMessage(ChatColor.GREEN + "Success! " + ChatColor.AQUA + value + ChatColor.WHITE + " was "
                + ChatColor.RED + "removed from " + ChatColor.GREEN + property);
        saveWorldConfig(sender);
    }

    private void doModifyClear(@NotNull CommandSender sender,
                               @NotNull String property,
                               @NotNull MultiverseWorld world) {

        if (!world.clearList(property)) {
            sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + property
                    + ChatColor.WHITE + " was " + ChatColor.GOLD + "NOT" + ChatColor.WHITE + " cleared.");
        }

        sender.sendMessage(property + " was cleared. It contains 0 values now.");
        sender.sendMessage(ChatColor.GREEN + "Success! " + ChatColor.AQUA + property + ChatColor.WHITE + " was "
                + ChatColor.GREEN + "CLEARED" + ChatColor.WHITE + ". It contains " + ChatColor.LIGHT_PURPLE + "0" + ChatColor.WHITE + " values now.");
        saveWorldConfig(sender);
    }

    private void doModifyList(@NotNull CommandSender sender,
                              @NotNull MultiverseWorld world) {

        //TODO: Think, all worlds should have the same properties?
        sender.sendMessage("===[ Properties Values ]===");
        sender.sendMessage(world.getAllPropertyNames());
    }

    private void saveWorldConfig(@NotNull CommandSender sender) {
        if (!this.plugin.saveWorldConfig()) {
            sender.sendMessage(ChatColor.RED + "There was an issue saving worlds.yml!  Your changes will only be temporary!");
        }
    }
}