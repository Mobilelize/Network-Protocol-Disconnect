package net.mobilelize.mixin;

import net.minecraft.client.network.ClientCommonNetworkHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientCommonNetworkHandler.class})
public class NetworkMixin {
    private static final Logger LOGGER = LogManager.getLogger("netprodis");

    public NetworkMixin() {
    }

    @Inject(
            method = {"onPacketException"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void onPacketException(CallbackInfo ci) {
        LOGGER.warn("Strict error handling was triggered, but disconnection was prevented");
        ci.cancel();
    }
}