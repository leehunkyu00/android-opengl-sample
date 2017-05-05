package com.opengl.android.androidopenglsample;

/**
 * Created by hklee on 2017. 5. 4..
 */

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer m_render;

    public MyGLSurfaceView(Context context) {
        super(context);

        // create OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        m_render = new MyGLRenderer();

        // setting renderer for handling graphic on GLSurfaceView
        setRenderer(m_render);

        // Creating Surface and Calling requestRender, Repaint view.
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
