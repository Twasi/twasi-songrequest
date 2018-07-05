package net.twasiplugin.songrequest;

import net.twasi.core.models.Message.TwasiCommand;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.events.TwasiCommandEvent;
import net.twasi.core.plugin.api.events.TwasiInstallEvent;

public class SongrequestUP extends TwasiUserPlugin {

    @Override
    public void onInstall(TwasiInstallEvent e) {
        e.getDefaultGroup().addKey("songrequest.user.*");
        e.getModeratorsGroup().addKey("songrequest.mod.*");
    }

    @Override
    public void onUninstall(TwasiInstallEvent e) {
        e.getDefaultGroup().removeKey("songrequest.user.*");
        e.getModeratorsGroup().removeKey("songrequest.mod.*");
    }

    @Override
    public void onCommand(TwasiCommandEvent e) {
        TwasiCommand cmd = e.getCommand();

        // Should only listen to registered commands
        cmd.reply("Command received! [songrequest]");
    }
}
