package net.twasiplugin.songrequest.tsss.communication;

public enum PacketType {

    // Client -> Server
    selectChannel,
    signIn,

    startPlayback,
    stopPlayback,
    togglePlayback,

    seek,
    skip,
    revert,

    requestStatus,

    // Server -> Client
    confirmExecution,
    status,

    // Both
    ping,
    pong

}
