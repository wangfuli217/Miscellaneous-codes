/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Panel.java
 *
 * Created on 2009/11/18, 17:19:59
 */

package turtles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


/**
 *
 * @author LFabre
 */
public class Panel extends javax.swing.JPanel implements ActionListener {

    /** Creates new form Panel */
    public Panel() {
        initComponents();
        setSize(W,H);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(51, 51, 51));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    int W=250,H=500;
    double x1=0, y1=0,
           x2=0, y2=H,
           x3=W, y3=H,
           x4=W, y4=0;
    double x=0, y=0;

    private void CalcNextPos(){
        //turtle 1
        double dx1 = x2-x1;
        double dy1 = y2-y1;
        double dr1 = Math.sqrt(dx1*dx1+dy1*dy1);
        dx1 /= dr1; dy1 /= dr1;

        x1 += dx1;
        y1 += dy1;

        //turtle 2
        double dx2 = x3-x2;
        double dy2 = y3-y2;
        double dr2 = Math.sqrt(dx2*dx2+dy2*dy2);
        dx2 /= dr2; dy2 /= dr2;

        x2 += dx2;
        y2 += dy2;

        //turtle 3
        double dx3 = x4-x3;
        double dy3 = y4-y3;
        double dr3 = Math.sqrt(dx3*dx3+dy3*dy3);
        dx3 /= dr3; dy3 /= dr3;

        x3 += dx3;
        y3 += dy3;

        //turtle 4
        double dx4 = x1-x4;
        double dy4 = y1-y4;
        double dr4 = Math.sqrt(dx4*dx4+dy4*dy4);
        dx4 /= dr4; dy4 /= dr4;

        x4 += dx4;
        y4 += dy4;

        //turtle 0
        double dx = 0;
        double dy = 1;

        x += dx;
        y += dy;
    }

    int [][] i_s = new int [100][4];
    int [][] j_s = new int [100][4];
    int n_pol=0;

    private void AddPolygon(int [] i, int [] j){
        if (n_pol==99) return;
        for (int k=0;k<4;k++){
            i_s[n_pol][k]=i[k];
            j_s[n_pol][k]=j[k];
            //System.out.println(Integer.toString(i[k])+";"+Integer.toString(i[k]));
        }
        n_pol++;
        //System.out.println(n_pol);
        //System.out.println("");
    }


    int count=0;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
        );
        hints.put(RenderingHints.KEY_ANTIALIASING,
                  RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);
        


        int i0=(int)Math.round(x )-5, j0=(int)Math.round(y )-5;
        int i1=(int)Math.round(x1)-5, j1=(int)Math.round(y1)-5;
        int i2=(int)Math.round(x2)-5, j2=(int)Math.round(y2)-5;
        int i3=(int)Math.round(x3)-5, j3=(int)Math.round(y3)-5;
        int i4=(int)Math.round(x4)-5, j4=(int)Math.round(y4)-5;

        int [] i = {i1+5,i2+5,i3+5,i4+5};
        int [] j = {j1+5,j2+5,j3+5,j4+5};
        g2.setColor(Color.WHITE);
        g2.drawPolygon(i,j,4);

        int [] ii= new int[4];
        int [] jj=new int [4];
        count++;
        if ((count%20)==0) { count=1;AddPolygon(i,j); }
        for (int k=0;k<n_pol;k++){
            ii[0]=i_s[k][0];ii[1]=i_s[k][1];ii[2]=i_s[k][2];ii[3]=i_s[k][3];
            jj[0]=j_s[k][0];jj[1]=j_s[k][1];jj[2]=j_s[k][2];jj[3]=j_s[k][3];
            g2.setColor(Color.WHITE);
            g2.drawPolygon(ii,jj,4);
        }
        
        

        g2.setColor(Color.WHITE);
        g2.drawPolygon(i,j,4);

        g2.setColor(Color.RED);
        g2.drawOval(i0, j0, 11, 11);

        g2.setColor(Color.RED);
        g2.drawOval(i1, j1, 11, 11);

        g2.setColor(Color.GREEN);
        g2.drawOval(i2, j2, 11, 11);

        g2.setColor(Color.BLUE);
        g2.drawOval(i3, j3, 11, 11);

        g2.setColor(Color.YELLOW);
        g2.drawOval(i4, j4, 11, 11);


    }

    private void Update(){ update(getGraphics()); }

    @Override
    public void actionPerformed(ActionEvent e) {
        CalcNextPos();
        Update();
    }

    @Override
    public void addNotify()
    {
        super.addNotify();
        javax.swing.Timer timer = new Timer(10,this);
        timer.start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
