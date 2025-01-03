# AlwaysPlugin

Mit **AlwaysPlugin** kannst du deinen Minecraft-Server effizient verwalten und anpassen.

## Funktionen:
- **/alwaysplugin reload**
  - Lädt die Konfigurationsdatei `config.yml` neu, ohne den Server neu zu starten.
  - **Erfordert die Berechtigung:** `alwaysplugin.reload`

- **/setalwaysspawn**
  - Setzt den AlwaysSpawn-Punkt an der aktuellen Position des Spielers.
  - Speichert die Position in der Datei `location.db`.
  - **Erfordert die Berechtigung:** `alwaysplugin.setalwaysspawn`


## Konfigurationsdatei: `config.yml`

```yaml
Activation:
  AlwaysDay: true # Hält die Tageszeit konstant.
  NoMobs: true    # Verhindert das Spawnen von Mobs.
  ShowTeleportMessage: true # Zeigt eine Nachricht bei Teleport an.

Messages:
  AlwaysSpawn:
    SetAlwaysSpawn: "&aAlwaysSpawn-Position gesetzt und gespeichert!"
    Teleport-Message: "&aDu wurdest zum Spawnpoint teleportiert!"
    NotSetMessage: "&cSpawnpoint wurde nicht gesetzt! Bitte benutze /setalwaysspawn."
  AlwaysJoinMessages:
    Join: "&a[&a+&f] {UserName} hat den Server betreten!"
    Leave: "&c[&c-&f] {UserName} hat den Server verlassen!"
    FirstJoin: "&aWillkommen {UserName} auf {ServerName}!"


````


---

## **English: AlwaysPlugin**


With **AlwaysPlugin**, you can efficiently manage and customize your Minecraft server.

## Features:
- **/alwaysplugin reload**
  - Reloads the configuration file `config.yml` without restarting the server.
  - **Requires permission:** `alwaysplugin.reload`

- **/setalwaysspawn**
  - Sets the AlwaysSpawn point at the player's current location.
  - Saves the position in the `location.db` file.
  - **Requires permission:** `alwaysplugin.setalwaysspawn`

## Configuration File: `config.yml`

```yaml
Activation:
  AlwaysDay: true # Keeps the time permanently daytime.
  NoMobs: true    # Prevents mobs from spawning.
  ShowTeleportMessage: true # Shows a message when teleporting.

Messages:
  AlwaysSpawn:
    SetAlwaysSpawn: "&aAlwaysSpawn point set and saved!"
    Teleport-Message: "&aYou have been teleported to the spawn point!"
    NotSetMessage: "&cSpawn point not set! Please use /setalwaysspawn."
  AlwaysJoinMessages:
    Join: "&a[&a+&f] {UserName} joined the server!"
    Leave: "&c[&c-&f] {UserName} left the server!"
    FirstJoin: "&aWelcome {UserName} to {ServerName}!"




