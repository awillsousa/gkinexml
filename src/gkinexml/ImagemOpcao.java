
package gkinexml;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author anton_000
 */
public class ImagemOpcao extends JLabel {
       
    public ImagemOpcao(ImageIcon img) {
        this.setIcon(img);        
    }   
    
    public void destaca() {
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
    }
    
    public void limpaDestaque() {
        setBorder(null);
    }
   
}
