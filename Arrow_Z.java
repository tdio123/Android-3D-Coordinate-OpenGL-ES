import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

public class Arrow_Z {
    public float angleX_Co,angleY_Co,angleZ_Co;//this parameters Co: Cone are for cone
    private FloatBuffer mVertexBuffer_Co;

    public float angleX_Cy,angleY_Cy,angleZ_Cy;//this parameters Cy: Cylinder are for Cylinder
    private FloatBuffer mVertexBuffer_Cy;

    private int n = 360;  //切割份数

    private float height_Co = 0.4f;  //圆锥高度
    private float radius_Co = 0.1f;  //圆锥底面半径
    private float height_Cy = -0.6f;  //圆柱高度
    private float radius_Cy = 0.05f;  //圆柱底面半径

    private int vSize_Co,vSize_Cy;

    public Arrow_Z(){
        // here is the code for cone
        ArrayList<Float> pos_Co=new ArrayList<>();
        pos_Co.add(0.0f);
        pos_Co.add(0.0f);
        pos_Co.add(height_Co);
        float angDegSpan=360f/n;
        for(float i=0;i<360+angDegSpan;i+=angDegSpan){
            pos_Co.add((float) (radius_Co*Math.sin(i*Math.PI/180f)));
            pos_Co.add((float) (radius_Co*Math.cos(i*Math.PI/180f)));
            pos_Co.add(0.0f);
        }
        float[] d_Co=new float[pos_Co.size()];
        for (int i=0;i<d_Co.length;i++){
            d_Co[i]=pos_Co.get(i);
        }
        vSize_Co=d_Co.length/3;
        ByteBuffer buffer_Co=ByteBuffer.allocateDirect(d_Co.length*4);
        buffer_Co.order(ByteOrder.nativeOrder());
        mVertexBuffer_Co=buffer_Co.asFloatBuffer();
        mVertexBuffer_Co.put(d_Co);
        mVertexBuffer_Co.position(0);

        // here is for cylinder
        ArrayList<Float> pos_Cy=new ArrayList<>();
        for(float i=0;i<360+angDegSpan;i+=angDegSpan){
            pos_Cy.add((float)(radius_Cy*Math.sin(i*Math.PI/180f)));
            pos_Cy.add((float)(radius_Cy*Math.cos(i*Math.PI/180f)));
            pos_Cy.add(height_Cy);
            pos_Cy.add((float)(radius_Cy*Math.sin(i*Math.PI/180f)));
            pos_Cy.add((float)(radius_Cy*Math.cos(i*Math.PI/180f)));
            pos_Cy.add(0.0f);
        }
        float[] d_Cy=new float[pos_Cy.size()];
        for (int i=0;i<d_Cy.length;i++){
            d_Cy[i]=pos_Cy.get(i);
        }
        vSize_Cy=d_Cy.length/3;
        ByteBuffer buffer=ByteBuffer.allocateDirect(d_Cy.length*4);
        buffer.order(ByteOrder.nativeOrder());
        mVertexBuffer_Cy=buffer.asFloatBuffer();
        mVertexBuffer_Cy.put(d_Cy);
        mVertexBuffer_Cy.position(0);
    }

    public void draw(GL10 gl) {

        gl.glTranslatef(0.0f,0.0f,0.5f);

        gl.glScalef(0.8F, 0.8F, 0.8F);

        gl.glFrontFace(GL10.GL_CW);

        // here is for Cone
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer_Co);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,vSize_Co);
        gl.glColor4f(0.50f, 0.8f, 0.98f, 0.5f);//change color

        // here is for Cylinder
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer_Cy);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,vSize_Cy);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glColor4f(1.0f, 0.0f, 0.0f, 0.5f);//change color
    }
}
