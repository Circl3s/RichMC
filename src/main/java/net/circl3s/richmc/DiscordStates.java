package net.circl3s.richmc;

import net.minecraft.client.Minecraft;
import java.time.Instant;
import com.github.psnrigner.discordrpcjava.DiscordRichPresence;
import com.github.psnrigner.discordrpcjava.DiscordRpc;

public class DiscordStates {

  public static long inGameStartTime;

  public static void inMenu() {
    DiscordRpc discordrpc = new DiscordRpc();
    DiscordRichPresence drp = new DiscordRichPresence();

    drp.setDetails("In Menu");
    drp.setState("Just chillin'");
    drp.setLargeImageKey("logo_large");
    drp.setLargeImageText("Made possible by RichMC!");
    discordrpc.updatePresence(drp);
  }

  public static void inGame() {
    DiscordRichPresence drp = new DiscordRichPresence();
    DiscordRpc discordrpc = new DiscordRpc();

    boolean isSingleplayer = Minecraft.getMinecraft().isSingleplayer();
    boolean isOffline = Minecraft.getMinecraft().isIntegratedServerRunning();
    String ign = Minecraft.getMinecraft().player.getDisplayNameString();
    String gamemode = Minecraft.getMinecraft().playerController.getCurrentGameType().getName();

    drp.setDetails("IGN: " + ign);
    drp.setState("Playing " + gamemode);
    if(isOffline == true) {
      if(isSingleplayer == true) {
        drp.setLargeImageKey("sp");
        drp.setLargeImageText("Playing Singleplayer");
      } else {
        drp.setLargeImageKey("mp");
        drp.setLargeImageText("Hosting a LAN game");
      }
    } else {
      drp.setLargeImageKey("mp");
      drp.setLargeImageText("Playing Multiplayer");
    }
    drp.setSmallImageKey("logo_small");
    drp.setSmallImageText("Made possible by RichMC!");
    if(inGameStartTime == 0)
    {
      Instant instant = Instant.now();
      inGameStartTime = instant.getEpochSecond();
    }
    drp.setStartTimestamp(inGameStartTime);
    discordrpc.updatePresence(drp);
  }

}
