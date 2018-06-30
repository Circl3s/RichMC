package net.circl3s.richmc;

import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.arikia.dev.drpc.DiscordEventHandlers;


@Mod(modid = RichMC.ModID, name = RichMC.ModName, version = RichMC.Version, clientSideOnly = true)
public class RichMC
{
  public static final String ModID = "richmc";
  public static final String ModName = "RichMC";
  public static final String Version = "1.0.0";

  DiscordEventHandlers handler;

  public static Logger log;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent ev)
  {
    log = ev.getModLog();
    handler = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
      System.out.println("Welcome " + user.username + "#" + user.discriminator + "! Thanks for using RichMC.");
    }).build();
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent ev)
  {
    DiscordRPC.discordInitialize("459772197364301825", handler, true);
  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent ev)
  {
    DiscordStates.inMenu();
  }

  @Mod.EventBusSubscriber
  public static class EventHandlers
  {
    @SubscribeEvent
    public static void Tick(TickEvent.RenderTickEvent ev)
    {
      DiscordRPC.discordRunCallbacks();
    }

    @SubscribeEvent
    public static void WorldEnter(PlayerEvent.PlayerLoggedInEvent ev)
    {
      DiscordStates.inGame();
    }

    @SubscribeEvent
    public static void WorlodLeave(PlayerEvent.PlayerLoggedOutEvent ev)
    {
      DiscordStates.inMenu();
    }
  }

  public static void shutdown(){
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
      public void run(){
        System.out.println("RichMC shutting down... Bye-bye!");
        DiscordRPC.discordShutdown();
      }
    });
  }
}
