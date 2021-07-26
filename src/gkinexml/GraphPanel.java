package gkinexml;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author John B. Matthews; distribution per GPL.
 */
public class GraphPanel extends JComponent {

//    private static final int WIDE = 640;
//    private static final int HIGH = 480;
    private final int WIDE = 800;
    private final int HIGH = 600;
    private static final int RADIUS = 25;
    private static final Random rnd = new Random();
    private ControlPanel control = new ControlPanel();
    private int radius = RADIUS;
    private Kind kind = Kind.Circular;
    private HashMap<String, Point> pontos = new HashMap<String, Point>();
    private List<Node> nodes = new ArrayList<Node>();
    private List<Node> selected = new ArrayList<Node>();
    private List<Edge> edges = new ArrayList<Edge>();
    private Point mousePt = new Point(WIDE / 2, HIGH / 2);
    private Rectangle mouseRect = new Rectangle();
    private boolean selecting = false;
   
    public GraphPanel() {        
        this.setOpaque(true);
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseMotionHandler());        
        inicializaPontos(new Point(300, 80));
                
    }

    public final void inicializaPontos(Point pontoCabeca) {
        int TAM_FRAME = 800;        
        int ALTURA = 0;
        int DESLOC = 0;
        
        final int TAM_PESCOCO = (int) (TAM_FRAME/7);
        final int TAM_OMBRO   = (int) (TAM_FRAME/8);
        final int TAM_BRACO   = (int) (TAM_FRAME/6);
        final int TAM_ANTEBRACO = (int) (TAM_FRAME/4);        
        final int TAM_DORSO     = (int) (TAM_FRAME/3);
        final int TAM_QUADRIL   = (int) (TAM_FRAME/10);
        final int TAM_COXA      = (int) (TAM_FRAME/3);
        final int TAM_CANELA    = (int) (TAM_FRAME/4);
        
        // Define um mapa dos pontos a serem utilizados para desenhar o skeleton
        pontos.put("cabeca",      pontoCabeca);    
               
        ALTURA = pontoCabeca.y + TAM_PESCOCO;
        DESLOC += TAM_OMBRO;
        pontos.put("base-cabeca", new Point(pontoCabeca.x, ALTURA));        
        pontos.put("ombro-dir",   new Point(pontoCabeca.x - DESLOC, ALTURA+15));
        pontos.put("ombro-esq",   new Point(pontoCabeca.x + DESLOC, ALTURA+15));
        
        ALTURA += TAM_BRACO;
        DESLOC += 30;
        pontos.put("cotov-dir",   new Point(pontoCabeca.x - DESLOC, ALTURA));
        pontos.put("cotov-esq",   new Point(pontoCabeca.x + DESLOC, ALTURA));

        ALTURA += TAM_ANTEBRACO;
        DESLOC += 50;
        pontos.put("pulso-dir",   new Point(pontoCabeca.x - DESLOC, ALTURA));
        pontos.put("pulso-esq",   new Point(pontoCabeca.x + DESLOC, ALTURA));
        
        ALTURA = pontoCabeca.y + TAM_PESCOCO + TAM_DORSO;
        DESLOC = TAM_QUADRIL;
        pontos.put("quadril-centro", new Point(pontoCabeca.x, ALTURA));
        pontos.put("quadril-dir",    new Point(pontoCabeca.x - DESLOC, ALTURA+15));      
        pontos.put("quadril-esq",    new Point(pontoCabeca.x + DESLOC, ALTURA+15));
               
        ALTURA += TAM_COXA;
        DESLOC += 30;
        pontos.put("joelho-dir",  new Point(pontoCabeca.x - DESLOC, ALTURA));  
        pontos.put("joelho-esq",  new Point(pontoCabeca.x + DESLOC, ALTURA));  
        
        ALTURA += TAM_CANELA;        
        pontos.put("tornoz-dir",  new Point(pontoCabeca.x - DESLOC, ALTURA));        
        pontos.put("tornoz-esq",  new Point(pontoCabeca.x + DESLOC, ALTURA));       
        
        
        // Insere os nos
        for (Map.Entry<String, Point> ponto : pontos.entrySet()) {
            insereNo(ponto.getKey(), ponto.getValue());
        }
        
        // Insere as conexoes entre os nos
        HashMap<Integer, ParNos> conexoes = new HashMap<Integer, ParNos>();        
        conexoes.put(1, new ParNos("cabeca", "base-cabeca"));
        conexoes.put(2, new ParNos("base-cabeca", "quadril-centro"));
        conexoes.put(3, new ParNos("base-cabeca", "ombro-esq"));
        conexoes.put(4, new ParNos("ombro-esq", "cotov-esq"));
        conexoes.put(5, new ParNos("cotov-esq", "pulso-esq"));
        conexoes.put(6, new ParNos("base-cabeca", "ombro-dir"));
        conexoes.put(7, new ParNos("ombro-dir", "cotov-dir"));
        conexoes.put(8, new ParNos("cotov-dir", "pulso-dir"));
        conexoes.put(9, new ParNos("quadril-centro", "quadril-esq"));
        conexoes.put(10, new ParNos("quadril-esq", "joelho-esq"));
        conexoes.put(11, new ParNos("joelho-esq", "tornoz-esq"));
        conexoes.put(12, new ParNos("quadril-centro", "quadril-dir"));
        conexoes.put(13, new ParNos("quadril-dir", "joelho-dir"));
        conexoes.put(14, new ParNos("joelho-dir", "tornoz-dir"));
                
        
        Node.selectNone(nodes);
        
        for (ParNos c : conexoes.values()) {                
            // Conecta os nos da linha central            
            Node.selectToggle(nodes, pontos.get(c.no1));
            Node.selectToggle(nodes, pontos.get(c.no2));
            conectaSelecionados();
        }        
    }
       
    public void insereNo(Point p) {
            Node.selectNone(nodes);            
            Color color = control.hueIcon.getColor();
            Node n = new Node(p, radius, color, kind);
            n.setSelected(true);
            nodes.add(n);
            //repaint();
    }
    
    public void insereNo(String id, Point p) {
            Node.selectNone(nodes);            
            Color color = control.hueIcon.getColor();
            Node n = new Node(id, p, radius, color, kind);
            n.setSelected(true);
            nodes.add(n);
            //repaint();
    }
    
    public void conectaSelecionados() {
        Node.getSelected(nodes, selected);
        if (selected.size() > 1) {
            for (int i = 0; i < selected.size() - 1; ++i) {
                Node n1 = selected.get(i);
                Node n2 = selected.get(i + 1);
                edges.add(new Edge(n1, n2));
            }
        }        
        // Desmarca todos os selecionados
        Node.selectNone(nodes);        
        repaint();
    }
   
  
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(0x00f0f0f0));
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Edge e : edges) {
            e.draw(g);
        }
        for (Node n : nodes) {
            n.draw(g);
        }
        if (selecting) {
            g.setColor(Color.darkGray);
            g.drawRect(mouseRect.x, mouseRect.y,
                mouseRect.width, mouseRect.height);
        }
    }

    private class ParNos {
        String no1;
        String no2;
        
        public ParNos(String s1, String s2) {
            this.no1 = s1;
            this.no2 = s2;
        }   
    }
    
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            selecting = false;
            mouseRect.setBounds(0, 0, 0, 0);
            if (e.isPopupTrigger()) {
                showPopup(e);
            }
            e.getComponent().repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mousePt = e.getPoint();
            if (e.isShiftDown()) {
                Node.selectToggle(nodes, mousePt);
            } else if (e.isPopupTrigger()) {
                Node.selectOne(nodes, mousePt);
                showPopup(e);
            } else if (Node.selectOne(nodes, mousePt)) {
                selecting = false;
            } else {
                Node.selectNone(nodes);
                selecting = true;
            }
            e.getComponent().repaint();
        }

        private void showPopup(MouseEvent e) {
            control.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private class MouseMotionHandler extends MouseMotionAdapter {

        Point delta = new Point();

        @Override
        public void mouseDragged(MouseEvent e) {
            if (selecting) {
                mouseRect.setBounds(
                    Math.min(mousePt.x, e.getX()),
                    Math.min(mousePt.y, e.getY()),
                    Math.abs(mousePt.x - e.getX()),
                    Math.abs(mousePt.y - e.getY()));
                Node.selectRect(nodes, mouseRect);
            } else {
                delta.setLocation(
                    e.getX() - mousePt.x,
                    e.getY() - mousePt.y);
                Node.updatePosition(nodes, delta);
                mousePt = e.getPoint();
            }
            e.getComponent().repaint();
        }
    }

    public JToolBar getControlPanel() {
        return control;
    }

    private class ControlPanel extends JToolBar {

        private Action newNode = new NewNodeAction("New");
        private Action clearAll = new ClearAction("Clear");
        private Action kind = new KindComboAction("Kind");
        private Action color = new ColorAction("Color");
        private Action connect = new ConnectAction("Connect");
        private Action delete = new DeleteAction("Delete");
        private Action random = new RandomAction("Random");
        private JButton defaultButton = new JButton(newNode);
        private JComboBox kindCombo = new JComboBox();
        private ColorIcon hueIcon = new ColorIcon(Color.blue);
        private JPopupMenu popup = new JPopupMenu();

        ControlPanel() {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.setBackground(Color.lightGray);

            this.add(defaultButton);
            this.add(new JButton(clearAll));
            this.add(kindCombo);
            this.add(new JButton(color));
            this.add(new JLabel(hueIcon));
            JSpinner js = new JSpinner();
            js.setModel(new SpinnerNumberModel(RADIUS, 5, 100, 5));
            js.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    JSpinner s = (JSpinner) e.getSource();
                    radius = (Integer) s.getValue();
                    Node.updateRadius(nodes, radius);
                    GraphPanel.this.repaint();
                }
            });
            this.add(new JLabel("Size:"));
            this.add(js);
            this.add(new JButton(random));

            popup.add(new JMenuItem(newNode));
            popup.add(new JMenuItem(color));
            popup.add(new JMenuItem(connect));
            popup.add(new JMenuItem(delete));
            JMenu subMenu = new JMenu("Kind");
            for (Kind k : Kind.values()) {
                kindCombo.addItem(k.toString());
                subMenu.add(new JMenuItem(new KindItemAction(k)));
            }
            popup.add(subMenu);
            kindCombo.addActionListener(kind);
        }

        class KindItemAction extends AbstractAction {

            private Kind k;

            public KindItemAction(Kind k) {
                super(k.toString());
                this.k = k;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                kindCombo.setSelectedItem(k);
            }
        }
    }

    private class ClearAction extends AbstractAction {

        public ClearAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            nodes.clear();
            edges.clear();
            repaint();
        }
    }

    private class ColorAction extends AbstractAction {

        public ColorAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            Color color = control.hueIcon.getColor();
            color = JColorChooser.showDialog(
                GraphPanel.this, "Choose a color", color);
            if (color != null) {
                Node.updateColor(nodes, color);
                control.hueIcon.setColor(color);
                control.repaint();
                repaint();
            }
        }
    }

    private class ConnectAction extends AbstractAction {

        public ConnectAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            Node.getSelected(nodes, selected);
            if (selected.size() > 1) {
                for (int i = 0; i < selected.size() - 1; ++i) {
                    Node n1 = selected.get(i);
                    Node n2 = selected.get(i + 1);
                    edges.add(new Edge(n1, n2));
                }
            }
            repaint();
        }
    }

    private class DeleteAction extends AbstractAction {

        public DeleteAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            ListIterator<Node> iter = nodes.listIterator();
            while (iter.hasNext()) {
                Node n = iter.next();
                if (n.isSelected()) {
                    deleteEdges(n);
                    iter.remove();
                }
            }
            repaint();
        }

        private void deleteEdges(Node n) {
            ListIterator<Edge> iter = edges.listIterator();
            while (iter.hasNext()) {
                Edge e = iter.next();
                if (e.n1 == n || e.n2 == n) {
                    iter.remove();
                }
            }
        }
    }

    private class KindComboAction extends AbstractAction {

        public KindComboAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            JComboBox combo = (JComboBox) e.getSource();
            kind = (Kind) combo.getSelectedItem();
            Node.updateKind(nodes, kind);
            repaint();
        }
    }

    private class NewNodeAction extends AbstractAction {

        public NewNodeAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            Node.selectNone(nodes);
            Point p = mousePt.getLocation();
            Color color = control.hueIcon.getColor();
            Node n = new Node(p, radius, color, kind);
            n.setSelected(true);
            nodes.add(n);
            repaint();
        }
    }

    private class RandomAction extends AbstractAction {

        public RandomAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 16; i++) {
                Point p = new Point(rnd.nextInt(getWidth()), rnd.nextInt(getHeight()));
                nodes.add(new Node(p, radius, new Color(rnd.nextInt()), kind));
            }
            repaint();
        }
    }

    /**
     * The kinds of node in a graph.
     */
    private enum Kind {

        Circular, Rounded, Square;
    }

    /**
     * An Edge is a pair of Nodes.
     */
    private static class Edge {

        private Node n1;
        private Node n2;

        public Edge(Node n1, Node n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        public void draw(Graphics g) {
            Point p1 = n1.getLocation();
            Point p2 = n2.getLocation();
            g.setColor(Color.darkGray);            
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    /**
     * A Node represents a node in a graph.
     */
    private static class Node {

        private Point p;
        private int r;
        private Color color;
        private Kind kind;
        private boolean selected = false;
        private Rectangle b = new Rectangle();
        private String id;
        private int num;
        private static int lastNum=0;
        

        /**
         * Construct a new node.
         */
        public Node(Point p, int r, Color color, Kind kind) {
            this.num = this.getId();
            this.p = p;
            this.r = r;
            this.color = color;
            this.kind = kind;
            setBoundary(b);            
        }
        
        public Node(String id, Point p, int r, Color color, Kind kind) {
            this.id = id;
            this.num = this.getId();
            this.p = p;
            this.r = r;
            this.color = color;
            this.kind = kind;
            setBoundary(b);
        }
        
        private int getId() {
            return (Node.lastNum++);
        }
        /**
         * Calculate this node's rectangular boundary.
         */
        private void setBoundary(Rectangle b) {
            b.setBounds(p.x - r, p.y - r, 2 * r, 2 * r);
        }

        /**
         * Draw this node.
         */
        public void draw(Graphics g) {
            g.setColor(this.color);
            int fontSize = 20;
            g.setFont(new Font("Arial", Font.PLAIN, fontSize));                 
            
            if (this.kind == Kind.Circular) {
                g.fillOval(b.x, b.y, b.width, b.height);
                g.setColor(Color.red);
                g.drawString(String.valueOf(this.num), b.x, b.y);
            } else if (this.kind == Kind.Rounded) {
                g.fillRoundRect(b.x, b.y, b.width, b.height, r, r);
            } else if (this.kind == Kind.Square) {
                g.fillRect(b.x, b.y, b.width, b.height);
            }
            if (selected) {
                g.setColor(Color.darkGray);
                g.drawRect(b.x, b.y, b.width, b.height);
            }
        }

        /**
         * Return this node's location.
         */
        public Point getLocation() {
            return p;
        }

        /**
         * Return true if this node contains p.
         */
        public boolean contains(Point p) {
            return b.contains(p);
        }

        /**
         * Return true if this node is selected.
         */
        public boolean isSelected() {
            return selected;
        }

        /**
         * Mark this node as selected.
         */
        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public static boolean selectById(List<Node> list, String id) {
            for (Node n : list) {
                if (n.id.equals(id)) {
                    if (!n.isSelected()) {
                        Node.selectNone(list);
                        n.setSelected(true);
                    }
                    return true;
                }
            }
            return false;
        }
        
        /**
         * Collected all the selected nodes in list.
         */
        public static void getSelected(List<Node> list, List<Node> selected) {
            selected.clear();
            for (Node n : list) {
                if (n.isSelected()) {
                    selected.add(n);
                }
            }
        }

        /**
         * Select no nodes.
         */
        public static void selectNone(List<Node> list) {
            for (Node n : list) {
                n.setSelected(false);
            }
        }

        /**
         * Select a single node; return true if not already selected.
         */
        public static boolean selectOne(List<Node> list, Point p) {
            for (Node n : list) {
                if (n.contains(p)) {
                    if (!n.isSelected()) {
                        Node.selectNone(list);
                        n.setSelected(true);
                    }
                    return true;
                }
            }
            return false;
        }

        /**
         * Select each node in r.
         */
        public static void selectRect(List<Node> list, Rectangle r) {
            for (Node n : list) {
                n.setSelected(r.contains(n.p));
            }
        }

        /**
         * Toggle selected state of each node containing p.
         */
        public static void selectToggle(List<Node> list, Point p) {
            for (Node n : list) {
                if (n.contains(p)) {
                    n.setSelected(!n.isSelected());
                }
            }
        }

        /**
         * Update each node's position by d (delta).
         */
        public static void updatePosition(List<Node> list, Point d) {
            for (Node n : list) {
                if (n.isSelected()) {
                    n.p.x += d.x;
                    n.p.y += d.y;
                    n.setBoundary(n.b);
                }
            }
        }

        /**
         * Update each node's radius r.
         */
        public static void updateRadius(List<Node> list, int r) {
            for (Node n : list) {
                if (n.isSelected()) {
                    n.r = r;
                    n.setBoundary(n.b);
                }
            }
        }

        /**
         * Update each node's color.
         */
        public static void updateColor(List<Node> list, Color color) {
            for (Node n : list) {
                if (n.isSelected()) {
                    n.color = color;
                }
            }
        }

        /**
         * Update each node's kind.
         */
        public static void updateKind(List<Node> list, Kind kind) {
            for (Node n : list) {
                if (n.isSelected()) {
                    n.kind = kind;
                }
            }
        }
    }

    private static class ColorIcon implements Icon {

        private static final int WIDE = 20;
        private static final int HIGH = 20;
        private Color color;

        public ColorIcon(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, WIDE, HIGH);
        }

        public int getIconWidth() {
            return WIDE;
        }

        public int getIconHeight() {
            return HIGH;
        }
    }
}
