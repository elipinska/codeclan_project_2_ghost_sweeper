package com.example.ewa.ghostsweeper;

import java.util.HashMap;

public class ApplicationState {

  private HashMap<String, Integer> gameDifficulty;



  public ApplicationState(){
    this.gameDifficulty = new HashMap<>();
    gameDifficulty.put("rowNo", 15);
    gameDifficulty.put("ghostCount", 20);
  }

  public HashMap<String, Integer> getGameDifficulty() {
    return gameDifficulty;
  }

  public void setGameDifficulty(HashMap<String, Integer> gameDifficulty) {
    this.gameDifficulty = gameDifficulty;
  }
}
