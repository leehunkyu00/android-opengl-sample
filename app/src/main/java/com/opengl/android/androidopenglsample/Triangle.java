package com.opengl.android.androidopenglsample;

/**
 * Created by hklee on 2017. 5. 5..
 */

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {

    private FloatBuffer vertexBuffer;
    private int m_program;

    // 0. Insert triangle vertex in float array
    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {
        0.0f, 0.622008459f, 0.0f,
        -0.5f, -0.311004243f, 0.0f,
        0.5f, -0.311004243f, 0.0f
    };

    // Insert red, green, blue, alpha value in color variable
    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "    gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "    gl_FragColor = vColor;" +
                    "}";

    private int m_positionHandle;
    private int m_colorHandle;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    public Triangle() {
        // 1. allocate ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(
                triangleCoords.length * 4);
        // 2. define endian with ByteBuffer
        // Using hardware's native byte order
        byteBuffer.order(ByteOrder.nativeOrder());

        // 3. Transfer to FloatBuffer from ByteBuffer
        vertexBuffer = byteBuffer.asFloatBuffer();

        // 4. Saving coordinate on float array to FloatBuffer
        vertexBuffer.put(triangleCoords);

        // 5. seek 0 position
        vertexBuffer.position(0);

        // Compile shader code for paint shape on screen.
        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        // create program element
        m_program = GLES20.glCreateProgram();
        GLES20.glAttachShader(m_program, vertexShader);
        GLES20.glAttachShader(m_program, fragmentShader);
        GLES20.glLinkProgram(m_program);
    }

    public void draw() {
        // Added program with a part of Rendering state.
        GLES20.glUseProgram(m_program);

        // Getting handle with vPosition of vertex shader from program
        m_positionHandle = GLES20.glGetAttribLocation(m_program, "vPosition");

        // Active triangle vertex attiribute
        GLES20.glEnableVertexAttribArray(m_positionHandle);

        // Define saved vertex coordinate in vertexBuffer with triangle vertex attribute.
        GLES20.glVertexAttribPointer(m_positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // Getting handle with vColor from program
        m_colorHandle = GLES20.glGetUniformLocation(m_program, "vColor");

        // Select color with defined color
        GLES20.glUniform4fv(m_colorHandle, 1, color, 0);

        // Rendering triangle on vertex count
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // deactivate vertex attribute
        GLES20.glDisableVertexAttribArray(m_positionHandle);
    }
}
