package net.circl3s.richmc;

import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.github.psnrigner.discordrpcjava.DiscordRpc;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import com.github.psnrigner.discordrpcjava.DiscordEventHandler;
import net.circl3s.richmc.DiscordEvents;


@Mod(modid = RichMC.ModID, name = RichMC.ModName, version = RichMC.Version, clientSideOnly = true)
public class RichMC
{
  public static final String ModID = "richmc";
  public static final String ModName = "RichMC";
  public static final String Version = "1.0";

  public static int tickCounter = 0;

  public static Logger log;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent ev)
  {
    log = ev.getModLog();
    //dlDiscordLib.checkDiscordLib();
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent ev)
  {
    DiscordRpc.init("459772197364301825", DiscordEvents.discordEventHandler, true);
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
      DiscordRpc.runCallbacks();
    }

    @SubscribeEvent
    public static void LogIn(TickEvent.PlayerTickEvent ev)
    {
      if(tickCounter < 20) {
        tickCounter++;
      } else {
        DiscordStates.inGame();
        tickCounter = 0;
      }
    }

    @SubscribeEvent
    public static void LogOut(PlayerEvent.PlayerLoggedOutEvent ev)
    {
      DiscordStates.inMenu();
    }
  }

  public static void shutdown(){
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
      public void run(){
        System.out.println("RichMC shutting down... Bye-bye!");
        DiscordRpc.shutdown();
      }
    });
  }
}
