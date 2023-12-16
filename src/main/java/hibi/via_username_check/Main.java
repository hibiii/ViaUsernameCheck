package hibi.via_username_check;

import net.lenni0451.lambdaevents.EventHandler;
import net.raphimc.viaproxy.ViaProxy;
import net.raphimc.viaproxy.plugins.ViaProxyPlugin;
import net.raphimc.viaproxy.plugins.events.ClientLoggedInEvent;

public class Main extends ViaProxyPlugin {

    @Override
    public void onEnable() {
        ViaProxy.EVENT_MANAGER.register(this);
    }

    @EventHandler
    public void clientLoggedIn(ClientLoggedInEvent ev) {
        final var con = ev.getProxyConnection();
        final var acc = con.getUserOptions().account();
        if (acc == null) {
            return;
        }
        final var expected = acc.getName();
        final var actual = con.getGameProfile().getName();
        if (!actual.equalsIgnoreCase(expected)) {
            con.kickClient("§cUsername mismatch! You are logging in as "+actual+", but the selected account in the proxy is "+expected+".");
        }
    }
}
