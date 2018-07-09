# Twasi Songrequest Synchronisation Standard
In order to allow all sources to be played anywhere but in
sync, the TSSS is used.

## Basics
TSSS is based on websocket and used for most music sources.

### Definitions
TSSS defines the following parts:

#### Server
The server manages the playback and the actual state. The 
server either broadcasts it's information to all clients,
or the clients fetch it.

#### Client
Often a browser. Connects to the server and listens for
Broadcast messages - like playback state changes or playback,
or to synchronize playback.

## Principles
TSSS defines the following rules:

### Single source of truth
Only the server has the real data source of truth. Clients
should never copy or cache this state. However, clients
with more permissions are able to control/change it.

### One-Way Synchronisation
The synchronisation is only done by one way, that is
Server to Client. Although the player can request a
resynchronisation, the server has to always provide the
current state to the client.

### Instant Notifications
As soon as an action is done, it should be broadcasted to
all clients at the same time. Example: a client with
permissions requests to stop the playback. Once the playback
on the server is stopped, the server broadcasts the new
state to all clients.