
package gkinexml;

import javax.swing.JFrame;

/**
 *
 * @author AntonioSousa
 */
public class GKineXML {
    
    public static final String pathApp = System.getProperty("user.dir")+"/";
    //public static final String pathRes   = pathApp + "/res/";
    public static final String pathImg   = pathApp;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame0 = new PrincipalForm();
        frame0.pack();
        frame0.setLocationByPlatform(true);
        frame0.setVisible(true);
    }
}
