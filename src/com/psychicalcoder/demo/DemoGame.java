package com.psychicalcoder.demo;

import com.psychicalcoder.engine.IGameLogic;
import com.psychicalcoder.engine.Window;
import com.psychicalcoder.engine.graph.Mesh;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

public class DemoGame implements IGameLogic {

    private final Renderer renderer;
    private int direction = 0;
    private float colour = 0.0f;
    private List<Mesh> meshes;

    public DemoGame() {
        meshes = new ArrayList<>();
        renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init();
        float[] positions = new float[]{
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,};
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,};
        meshes.add(new Mesh(positions, indices));
    }

    @Override
    public void input(Window window) {
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            direction = 1;
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            direction = -1;
        } else {
            direction = 0;
        }
    }

    @Override
    public void update(float interval) {
        colour += direction * 0.05f;
//        System.out.println("Colour Value = " + colour);
        if (colour > 1) {
            colour = 1.0f;
        } else if (colour < 0) {
            colour = 0.0f;
        }
    }

    @Override
    public void render(Window window) {
        window.setClearColour(colour, colour, colour, 1.0f);
        for (Mesh mesh : meshes) {
            renderer.render(window, mesh);
        }
    }
}
