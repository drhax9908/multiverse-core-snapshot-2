/******************************************************************************
 * Multiverse 2 Copyright (c) the Multiverse Team 2020.                       *
 * Multiverse 2 is licensed under the BSD License.                            *
 * For more information please check the README.md file included              *
 * with this project.                                                         *
 ******************************************************************************/

package com.onarandombox.MultiverseCore.commandtools;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.HelpEntry;
import co.aikar.commands.PaperCommandManager;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.commandtools.display.ColorAlternator;
import com.onarandombox.MultiverseCore.commandtools.queue.CommandQueueManager;
import com.onarandombox.MultiverseCore.commands.AnchorCommand;
import com.onarandombox.MultiverseCore.commands.BedCommand;
import com.onarandombox.MultiverseCore.commands.CheckCommand;
import com.onarandombox.MultiverseCore.commands.CloneCommand;
import com.onarandombox.MultiverseCore.commands.ConfigCommand;
import com.onarandombox.MultiverseCore.commands.ConfirmCommand;
import com.onarandombox.MultiverseCore.commands.CoordCommand;
import com.onarandombox.MultiverseCore.commands.CreateCommand;
import com.onarandombox.MultiverseCore.commands.DebugCommand;
import com.onarandombox.MultiverseCore.commands.DeleteCommand;
import com.onarandombox.MultiverseCore.commands.EnvironmentCommand;
import com.onarandombox.MultiverseCore.commands.GameRuleCommand;
import com.onarandombox.MultiverseCore.commands.GeneratorCommand;
import com.onarandombox.MultiverseCore.commands.ImportCommand;
import com.onarandombox.MultiverseCore.commands.InfoCommand;
import com.onarandombox.MultiverseCore.commands.ListCommand;
import com.onarandombox.MultiverseCore.commands.LoadCommand;
import com.onarandombox.MultiverseCore.commands.ModifyCommand;
import com.onarandombox.MultiverseCore.commands.PurgeCommand;
import com.onarandombox.MultiverseCore.commands.RegenCommand;
import com.onarandombox.MultiverseCore.commands.ReloadCommand;
import com.onarandombox.MultiverseCore.commands.RemoveCommand;
import com.onarandombox.MultiverseCore.commands.RootCommand;
import com.onarandombox.MultiverseCore.commands.ScriptCommand;
import com.onarandombox.MultiverseCore.commands.SetSpawnCommand;
import com.onarandombox.MultiverseCore.commands.SilentCommand;
import com.onarandombox.MultiverseCore.commands.SpawnCommand;
import com.onarandombox.MultiverseCore.commands.SubModulesCommand;
import com.onarandombox.MultiverseCore.commands.TeleportCommand;
import com.onarandombox.MultiverseCore.commands.UnloadCommand;
import com.onarandombox.MultiverseCore.commands.UsageCommand;
import com.onarandombox.MultiverseCore.commands.VersionCommand;
import com.onarandombox.MultiverseCore.commands.WhoCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Heart of Multiverse Command Handler.
 */
public class MVCommandManager extends PaperCommandManager {

    private final MultiverseCore plugin;
    private final CommandQueueManager commandQueueManager;
    private final Map<String, BaseCommand> subModuleRootCommands;

    private static final Pattern PERMISSION_SPLIT = Pattern.compile(",");

    public MVCommandManager(@NotNull MultiverseCore plugin) {
        super(plugin);
        this.plugin = plugin;
        this.commandQueueManager = new CommandQueueManager(plugin);
        this.subModuleRootCommands = new HashMap<>(3);
        new MVCommandConditions(plugin, getCommandConditions());

        // Setup help command
        enableUnstableAPI("help");
        setDefaultHelpPerPage(6);

        // General plugin command
        registerCommand(new RootCommand(this.plugin));
        registerCommand(new UsageCommand(this.plugin));
        registerCommand(new VersionCommand(this.plugin));

        // World commands
        registerCommand(new CreateCommand(this.plugin));
        registerCommand(new ImportCommand(this.plugin));
        registerCommand(new LoadCommand(this.plugin));
        registerCommand(new UnloadCommand(this.plugin));
        registerCommand(new RemoveCommand(this.plugin));
        registerCommand(new CloneCommand(this.plugin));
        registerCommand(new RegenCommand(this.plugin));
        registerCommand(new DeleteCommand(this.plugin));
        registerCommand(new ConfirmCommand(this.plugin));

        // Properties management commands
        registerCommand(new ConfigCommand(this.plugin));
        registerCommand(new GameRuleCommand(this.plugin));
        registerCommand(new ModifyCommand(this.plugin));
        registerCommand(new SetSpawnCommand(this.plugin));
        registerCommand(new PurgeCommand(this.plugin));

        // Show information commands
        registerCommand(new InfoCommand(this.plugin));
        registerCommand(new CoordCommand(this.plugin));
        registerCommand(new EnvironmentCommand(this.plugin));
        registerCommand(new GeneratorCommand(this.plugin));
        registerCommand(new ListCommand(this.plugin));
        registerCommand(new WhoCommand(this.plugin));

        // Teleport commands
        registerCommand(new TeleportCommand(this.plugin));
        registerCommand(new AnchorCommand(this.plugin));
        registerCommand(new BedCommand(this.plugin));
        registerCommand(new SpawnCommand(this.plugin));

        // Misc commands
        registerCommand(new DebugCommand(this.plugin));
        registerCommand(new CheckCommand(this.plugin));
        registerCommand(new ReloadCommand(this.plugin));
        registerCommand(new SilentCommand(this.plugin));
        registerCommand(new ScriptCommand(this.plugin));

        // Sub-modules
        addAvailableSubModule("mvnp", new SubModulesCommand.NetherPortals());
        addAvailableSubModule("mvp", new SubModulesCommand.Portals());
        addAvailableSubModule("mvinv", new SubModulesCommand.Inventories());
    }

    /**
     * Use for download link suggestion for sub-module
     *
     * @param moduleName  A sub-module for Multiverse.
     * @param cmd         Command to register.
     */
    private void addAvailableSubModule(@NotNull String moduleName,
                                       @NotNull BaseCommand cmd) {

        subModuleRootCommands.put(moduleName, cmd);
        registerCommand(cmd);
    }

    /**
     * Replace suggest download with actual command from sub-module.
     *
     * @param moduleName  A sub-module for Multiverse.
     * @param cmd         Command to register.
     */
    public void registerSubModule(@NotNull String moduleName,
                                  @NotNull BaseCommand cmd) {

        unregisterCommand(subModuleRootCommands.remove(moduleName));
        registerCommand(cmd);
    }

    @Override
    public synchronized MVCommandContexts getCommandContexts() {
        if (this.contexts == null) {
            this.contexts = new MVCommandContexts(this, plugin);
        }
        return (MVCommandContexts) this.contexts;
    }

    @Override
    public synchronized MVCommandCompletions getCommandCompletions() {
        if (this.completions == null) {
            this.completions = new MVCommandCompletions(this, plugin);
        }
        return (MVCommandCompletions) this.completions;
    }

    /**
     * Change default implementation to be able to choose from OR / AND
     */
    @Override
    public boolean hasPermission(@NotNull CommandIssuer issuer,
                                 @Nullable Set<String> permissions) {

        if (permissions == null || permissions.isEmpty()) {
            return true;
        }

        return (permissions.contains("AND"))
                ? andPermissionCheck(issuer, permissions)
                : orPermissionCheck(issuer, permissions);
    }

    private boolean orPermissionCheck(@NotNull CommandIssuer issuer,
                                      @NotNull Set<String> permissions) {

        return permissions.stream()
                .unordered()
                .anyMatch(permission -> hasPermission(issuer, permission));
    }

    private boolean andPermissionCheck(@NotNull CommandIssuer issuer,
                                       @NotNull Set<String> permissions) {

        return permissions.stream()
                .unordered()
                .allMatch(permission -> hasPermission(issuer, permission));
    }

    /**
     * Change default implementation to be able to choose from OR / AND
     */
    @Override
    public boolean hasPermission(@NotNull CommandIssuer issuer,
                                 @Nullable String permission) {

        if (permission == null || permission.isEmpty()) {
            return true;
        }
        if (permission.startsWith("AND:")) {
            return Arrays.stream(PERMISSION_SPLIT.split(permission.substring(4)))
                    .unordered()
                    .allMatch(issuer::hasPermission);
        }
        return Arrays.stream(PERMISSION_SPLIT.split(permission))
                .unordered()
                .anyMatch(issuer::hasPermission);
    }

    /**
     * Gets {@link CommandQueueManager}.
     *
     * @return {@link CommandQueueManager}.
     */
    public CommandQueueManager getQueueManager() {
        return commandQueueManager;
    }

    /**
     * Standardise usage command formatting for all mv modules.
     *
     * @param help The target {@link CommandHelp}.
     */
    public void showUsage(@NotNull CommandHelp help) {
        List<HelpEntry> entries = help.getHelpEntries();

        if (entries.size() == 1) {
            this.plugin.getMVCommandManager().getHelpFormatter().showDetailedHelp(help, entries.get(0));
            return;
        }

        help.showHelp();
    }

    /**
     * Standardise root command for all mv modules.
     *
     * @param sender       Sender to send the info to.
     * @param description  mv-module's description and info.
     * @param color        Special colour for each mv-module.
     * @param rootCmd      mv-module's root command name.
     */
    public void showPluginInfo(@NotNull CommandSender sender,
                               @NotNull PluginDescriptionFile description,
                               @NotNull ColorAlternator color,
                               @NotNull String rootCmd) {

        sender.sendMessage(String.format("%s%s %s| %sv%s",
                color.getThis(), description.getName(), ChatColor.DARK_GRAY, color.getThat(), description.getVersion()));

        sender.sendMessage(String.format("%sSee %s/%s help %sfor commands available.",
                ChatColor.DARK_GREEN, ChatColor.GREEN, rootCmd, ChatColor.DARK_GREEN));
    }
}