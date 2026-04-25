package dev.axionize.jedis;

import net.minecraftforge.fml.common.Mod;

@Mod(value = "jedis", modid = "jedis", acceptableRemoteVersions = "*")
public class ForgeMod112
{
    public ForgeMod112() {
        try { new ForgeSetup113(); } catch (Throwable e) {}
        try { new ForgeSetup117(); } catch (Throwable e) {}
    }
}
