
package gkinexml;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 *
 * @author anton_000
 */
class CorIcone implements Icon {

        private static final int WIDE = 20;
        private static final int HIGH = 20;
        private Color color;

        public CorIcone(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, WIDE, HIGH);
        }

        @Override
        public int getIconWidth() {
            return WIDE;
        }

        @Override
        public int getIconHeight() {
            return HIGH;
        }
    }
