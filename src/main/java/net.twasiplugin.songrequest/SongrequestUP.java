package net.twasiplugin.songrequest;

import net.twasi.core.models.Message.TwasiCommand;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.events.TwasiCommandEvent;
import net.twasi.core.plugin.api.events.TwasiEnableEvent;
import net.twasi.core.plugin.api.events.TwasiInstallEvent;
import net.twasi.core.services.ServiceRegistry;
import net.twasiplugin.songrequest.requestlist.RequestList;
import net.twasiplugin.songrequest.requestlist.RequestListService;
import net.twasiplugin.songrequest.tsss.api.PlayerStatus;

public class SongrequestUP extends TwasiUserPlugin {
    private RequestList list;

    @Override
    public void onEnable(TwasiEnableEvent e) {
        list = ServiceRegistry.get(RequestListService.class).getRequestListForUser(getTwasiInterface().getStreamer().getUser().getId());
        //System.out.println(getTwasiInterface().getStreamer());
    }

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

        String[] splitted = cmd.getMessage().split(" ", 3);
        if (splitted.length == 1) {
            cmd.reply(getCurrentStatusDescription());
            return;
        }
        if (splitted.length == 2) {
            if (splitted[1].equalsIgnoreCase("list")) {
                cmd.reply(getSongList());
                return;
            }
            if (splitted[1].equalsIgnoreCase("current") ||
                    splitted[1].equalsIgnoreCase("playing") ||
                    splitted[1].equalsIgnoreCase("status")) {
                cmd.reply(getCurrentStatusDescription());
                return;
            }
            if (splitted[1].equalsIgnoreCase("next")) {
                cmd.reply(getNextSongDescription());
                return;
            }
            if (splitted[1].equalsIgnoreCase("skip")) {
                cmd.reply(getSkipSongResponse());
                return;
            }
            if (splitted[1].equalsIgnoreCase("clear")) {
                cmd.reply(getClearResponse());
                return;
            }
            if (splitted[1].equalsIgnoreCase("revert") ||
                    splitted[1].equalsIgnoreCase("wrongsong") ||
                    splitted[1].equalsIgnoreCase("oops")) {
                cmd.reply(getLastSongRemoveResponse());
                return;
            }
            if (splitted[1].equalsIgnoreCase("voteskip")) {
                cmd.reply(getVoteSkipResponse());
                return;
            }
            cmd.reply(requestSong(splitted[1]));
            return;
        }
        if (splitted.length == 3) {
            if (splitted[1].equalsIgnoreCase("info")) {
                cmd.reply(getInfoResponse(splitted[2]));
                return;
            }
            if (splitted[1].equalsIgnoreCase("delete")) {
                cmd.reply(getDeleteResponse(splitted[2]));
                return;
            }
            cmd.reply(requestSong(splitted[1] + " " + splitted[2]));
            return;
        }
    }

    String requestSong(String reference) {
        return "Not implemented: request(" + reference + ")";
    }

    String getSongList() {
        return "Not implemented: songlist";
    }

    String getCurrentStatusDescription() {
        if (list.getStatus() == PlayerStatus.WAITING) {
            return "Waiting for requests";
        }
        return "Status: " + list.getStatus().toString() + ", Seconds: " + list.getSeconds() + ", Title: " + list.getCurrentSong().getTitle();
    }

    String getNextSongDescription() {
        return "Not implemented: next song";
    }

    String getSkipSongResponse() {
        return "Not implemented: skip song";
    }

    String getClearResponse() {
        return "Not implemented: clear";
    }

    String getLastSongRemoveResponse() {
        return "Not implemented: removed last";
    }

    String getVoteSkipResponse() {
        return "Not implemented: voteskip";
    }

    String getInfoResponse(String requestId) {
        return "Not implemented: info(" + requestId + ")";
    }

    String getDeleteResponse(String requestId) {
        return "Not implemented: delete(" + requestId + ")";
    }
}
