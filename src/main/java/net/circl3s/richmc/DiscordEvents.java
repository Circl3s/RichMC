package net.circl3s.richmc;

import com.github.psnrigner.discordrpcjava.DiscordJoinRequest;
import com.github.psnrigner.discordrpcjava.ErrorCode;
import com.github.psnrigner.discordrpcjava.DiscordEventHandler;

public class DiscordEvents {

  DiscordEventHandler discordEventHandler = new DiscordEventHandler(){

    @Override
    public void spectateGame(String spectateSecret) {
      System.out.println("test");
    }

    @Override
    public void ready() {
      System.out.println("Thanks for using RichMC! The Discord RPC is ready!");
    }

    @Override
    public void joinRequest(DiscordJoinRequest joinRequest) {
      System.out.println("Join request: " + joinRequest);
    }

    @Override
    public void joinGame(String joinSecret) {
      System.out.println("Join game: " + joinSecret);
    }

    @Override
    public void errored(ErrorCode errorCode, String message) {
      System.out.println("ERROR " + errorCode + ": " + message);
    }

    @Override
    public void disconnected(ErrorCode errorCode, String message) {
      System.out.println("Disconnected - " + errorCode + ": " + message);
    }
  };

}
