package com.opengl.android.androidopenglsample;

/**
 * Created by hklee on 2017. 5. 4..
 */

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {
    private Triangle m_triangle;

    // once do, When create GLSurfaceView
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        m_triangle = new Triangle();
    }

    public void onDrawFrame(GL10 unused) {
        // Color buffer (GL_COLOR_BUFFER_BIT)
        // Depth buffer (GL_DEPTH_BUFFER_BIT)
        // Stencil buffer (GL_STENCIL_BUFFER_BIT)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        m_triangle.draw();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // viewport
        GLES20.glViewport(0, 0, width, height);
    }

    public static int loadShader(int type, String shaderCode) {
        // select one type on vertex shader type, fragment shader type.
        int shader = GLES20.glCreateShader(type);

        // Load source code in shader element.
        GLES20.glShaderSource(shader, shaderCode);

        // Compile shader element.
        GLES20.glCompileShader(shader);

        return shader;
    }
}
