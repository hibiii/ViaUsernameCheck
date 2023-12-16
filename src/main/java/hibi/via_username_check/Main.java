package hibi.via_username_check;

import joptsimple.OptionSpec;
import net.lenni0451.lambdaevents.EventHandler;
import net.raphimc.viaproxy.ViaProxy;
import net.raphimc.viaproxy.plugins.ViaProxyPlugin;
import net.raphimc.viaproxy.plugins.events.ClientLoggedInEvent;
import net.raphimc.viaproxy.plugins.events.PostOptionsParseEvent;
import net.raphimc.viaproxy.plugins.events.PreOptionsParseEvent;

public class Main extends ViaProxyPlugin {

    boolean disabled;
    OptionSpec<Void> disableUsernameCheck;

    @Override
    public void onEnable() {
        disabled = false;
        ViaProxy.EVENT_MANAGER.register(this);
    }

    @EventHandler
    public void clientLoggedIn(ClientLoggedInEvent ev) {
        if (disabled) {
            return;
        }
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

    @EventHandler
    public void addCliOption(PreOptionsParseEvent ev) {
        disableUsernameCheck = ev.getParser().accepts("disable_username_check", "Disables username verification of the connecting client against the selected account.");
    }

    @EventHandler
    public void applyOption(PostOptionsParseEvent ev) {
        final var opts = ev.getOptions();
        if (opts.has(disableUsernameCheck)) {
            disabled = true;
        }
    }
}
