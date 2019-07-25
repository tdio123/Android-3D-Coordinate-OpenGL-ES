package ikm.wang.vogitninverse.View;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import ikm.wang.vogitninverse.Shape.Arrow;
import ikm.wang.vogitninverse.Shape.Arrow_X;
import ikm.wang.vogitninverse.Shape.Arrow_Y;
import ikm.wang.vogitninverse.Shape.Arrow_Z;
import ikm.wang.vogitninverse.Shape.Coordinate;

public class ArrowView extends GLSurfaceView {

    private ArrowRenderer arrowRenderer;
    private float shangX;
    private float shangY;
    private float ArrowRotation;

    public ArrowView(Context context) {
        super(context);

        arrowRenderer = new ArrowRenderer();
        setRenderer(arrowRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float SUO = 180.0f/320;
        float x = event.getX();
        float y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_MOVE){

            float dx = x - shangX;
            float dy = y - shangY;
//            arrowRenderer.coordinate.angleY += dx * SUO;
//            arrowRenderer.coordinate.angleZ += dy * SUO;
//            arrowRenderer.arrow_x.angleX_Co += dx * SUO;
//            arrowRenderer.arrow_x.angleY_Co += dy * SUO;
        }
        shangX = x;
        shangY = y;
        return true;
    }

    private class ArrowRenderer implements Renderer{
        Coordinate coordinate = new Coordinate();
        //Arrow arrow =new Arrow();
        Arrow_X arrow_x = new Arrow_X();
        Arrow_Y arrow_y = new Arrow_Y();
        Arrow_Z arrow_z = new Arrow_Z();

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            gl.glDisable(GL10.GL_DITHER);
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
            gl.glShadeModel(GL10.GL_SMOOTH);
            gl.glEnable(GL10.GL_DEPTH_TEST);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            float ratio = (float) width / height;
            gl.glFrustumf(-ratio, ratio, -1.0f, 1.0f, 1f, 100);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// 清楚颜色缓存
            gl.glMatrixMode(GL10.GL_MODELVIEW);//设置为矩阵模式
            gl.glLoadIdentity();
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.0f, -2.0f);
            gl.glRotatef(ArrowRotation, 1.0f, 1.0f, 1.0f);;

            arrow_x.draw(gl);
            gl.glRotatef(-90,0.0f,1.0f,0.0f);
            gl.glTranslatef(-0.62f,0.0f,0.0f);

            arrow_y.draw(gl);
            gl.glRotatef(90,1.0f,0.0f,0.0f);
            gl.glTranslatef(0.0f,-0.62f,0.0f);

            arrow_z.draw(gl);
            gl.glTranslatef(0.0f,0.0f,-0.5f);

            gl.glPopMatrix();
            ArrowRotation -= 1.0f;
        }
    }
}
