package net.mobilelize.netprodis.mixin;

import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.mobilelize.netprodis.NetworkProtocolDisconnect;
import net.mobilelize.netprodis.config.ConfigManager;
import net.mobilelize.netprodis.config.ConfigMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientCommonNetworkHandler.class})
public class NetworkMixin {
    public NetworkMixin() {
    }

    @Inject(
            method = {"onPacketException"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void onPacketException(Packet<?> packet, Exception exception, CallbackInfo ci) {
        if (!ConfigManager.configData.modEnabled) return;
        if (ConfigManager.configData.showLogs) {
            NetworkProtocolDisconnect.LOGGER.warn("Strict error handling was triggered, but disconnection was prevented");
            NetworkProtocolDisconnect.LOGGER.error("Failed to handle packet {}", packet, exception);
        }
        ci.cancel();
    }
}