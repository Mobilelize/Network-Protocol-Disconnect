package net.mobilelize.netprodis;

import net.fabricmc.api.ClientModInitializer;
import net.mobilelize.netprodis.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkProtocolDisconnect implements ClientModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("netprodis");

    @Override
    public void onInitializeClient() {
        ConfigManager.loadConfig();
    }
}
