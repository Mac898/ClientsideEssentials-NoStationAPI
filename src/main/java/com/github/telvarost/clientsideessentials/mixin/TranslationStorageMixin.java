package com.github.telvarost.clientsideessentials.mixin;

import com.github.telvarost.clientsideessentials.ModHelper;
import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Mixin(TranslationStorage.class)
public abstract class TranslationStorageMixin {
    @Shadow
    private Properties translations;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    void onInit(CallbackInfo ci) {
        try {
            InputStream lang = ModHelper.class.getResourceAsStream("/assets/clientsideessentials/stationapi/lang/en_US.lang");
            if (lang != null) {
                translations.load(lang);
            } else {
                System.err.println("Could not load en_US.lang");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
