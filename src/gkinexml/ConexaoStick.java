
package gkinexml;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

/**
 *
 * @author anton_000
 */
public class ConexaoStick {
    private JuntaStick n1;
    private JuntaStick n2;
    private Color cor = Color.green;

        public ConexaoStick(JuntaStick n1, JuntaStick n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        public void desenha(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(10)); g2.setColor(cor); 
            Point p1 = n1.getCoord();
            Point p2 = n2.getCoord();
                      
            //g.drawLine(p1.x, p1.y, p2.x, p2.y);
            
            g2.draw(new Line2D.Float(p1.x, p1.y, p2.x, p2.y));
        }
        
}
