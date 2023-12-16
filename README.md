ViaUsernameCheck
================

A plugin for [ViaProxy][vp] that makes sure the client account is the same as
the proxy account.

The idea behind it is to prevent joining a server accidentally with the wrong
account: for example, bouncing around an outdated creative server and a
survival server on the latest version.

Usage
-----

To install, simply drop the jar in the `plugins` directory of your ViaProxy
installation.

On the command line, you can pass the `--disable_username_check` to quickly
disable it. On the GUI, there are no options to set, and the username check is
always active.

If the Minecraft client is using a different account than the one the proxy is
configured to use, it will kick the player without connecting to the remote
server.

The username check will only happen if the proxy is configured to use an
account. The check is case-insensitive, as per vanilla Minecraft.

License
-------

This project is licensed under the MIT license.

[vp]: https://github.com/ViaVersion/ViaProxy
