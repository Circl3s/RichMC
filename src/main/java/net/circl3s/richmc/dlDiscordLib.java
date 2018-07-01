package net.circl3s.richmc;

import java.net.URL;
import java.nio.channels.Channels;
import java.io.FileOutputStream;
import java.io.File;

public class dlDiscordLib {
  public static final String libDir = System.getProperty("user.dir") + "/mods/Discord";
  public static final String downUri = "https://github.com/Vatuu/discord-rpc/releases/download/1.5.0/discord-rpc.jar";

  public static void checkDiscordLib() {
    if(!new File(libDir).exists() || !new File(libDir + "/discord-rpc.jar").exists()) {
      RichMC.log.info("Can't find the discord-rpc lib, gonna download it for you now. No need to thank me. :)");
      new File(libDir).mkdir();
      try {
        FileOutputStream fos = new FileOutputStream(libDir + "/discord-rpc.jar");
        fos.getChannel().transferFrom(Channels.newChannel(new URL(downUri).openStream()), 0, Long.MAX_VALUE);
        fos.close();
      }
      catch(Exception e) {
        RichMC.log.error("An Exception was caught when trying to download the discord-rpc library!");
        RichMC.log.error(e.toString());
      }
      return;
    } else {
      RichMC.log.info("Discord-rpc already present, you're ready to go!");
      return;
    }
  }
}
