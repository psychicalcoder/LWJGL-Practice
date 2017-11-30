package com.psychicalcoder.demo;

import com.psychicalcoder.engine.Utils;
import com.psychicalcoder.engine.Window;
import com.psychicalcoder.engine.graph.Mesh;
import com.psychicalcoder.engine.graph.ShaderProgram;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {

    private int vaoId;

    private int vboId;

    private ShaderProgram shaderProgram;

    public Renderer() {

    }

    public void init() throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("vertex.shader"));
        shaderProgram.createFragmentShader(Utils.loadResource("fragment.shader"));
        shaderProgram.link();
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, Mesh mesh) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();

        // Draw the mesh
        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }

}
