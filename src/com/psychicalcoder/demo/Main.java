package com.psychicalcoder.demo;

import com.psychicalcoder.engine.GameEngine;
import com.psychicalcoder.engine.IGameLogic;

public class Main {
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DemoGame();
            GameEngine gameEngine = new GameEngine("Demo", 1280, 720, vSync, gameLogic);
            gameEngine.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
