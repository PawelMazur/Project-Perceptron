/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;


/**
 *
 * @author Pawel
 */
public final class Formatka extends javax.swing.JFrame {

    /**
     * Creates new form Formatka
     */
    Perceptron perceptron;
    double [] iSetD = {0, 1, 1, 1};
    double [][] iSetX = 
            {{1, 0, 0},
             {1, 0, 1},
             {1, 1, 0},
             {1, 1, 1}};
    
    
    int krok = 0;
    int test;
    int ile = 0;
    
    XYSeries series = new XYSeries("funkcja y");
    int m = 4;                   
    double r = 0.5;                
    int t = 0;  
    int n = 2;
    
    ButtonGroup bg1 = new ButtonGroup();
    ButtonGroup bg2 = new ButtonGroup();
    Boolean czyUnipolarna = true;
    Boolean czyWylosowano = false;
    JFreeChart chart1;
    
    
    XYSeriesCollection dataSet;
    public Formatka() {
        initComponents();
            
        this.setTitle("Autorzy: Pawel Mazur, Piotr Mazur");
        double w[] = new double[]{-1, 0, 5};
        double w1[] = new double[]{-0.5, 0.5, 5};
        double w2[] = new double[]{0, 1, 5};
        double w3[] = new double[]{2, -1, -0.5};
        
        /*
        for (int i = 0; i < iSetX.length; i++){
            for (int j = 0; j < iSetX[i].length; j++){
                jTable1.getModel().setValueAt(iSetX[i][j], i, j + 1);
            }
        }
        */
         
        //przedzial losujacy a i b
        int a = 0;
        int b = 5;
        if (czyUnipolarna){
            perceptron = new Perceptron(n, 1, w, iSetX, iSetD, m, r);
        } else {
            perceptron = new Perceptron(n, -1, w, iSetX, iSetD, m, r);
        }
        
        /*
        for (int i = 0; i < perceptron.w.length ; i++){
            jTable1.getModel().setValueAt(perceptron.w[i], perceptron.t, 5 + i);
        }
        jTable1.getModel().setValueAt(perceptron.t, 0, 0);
        */
        

        bg1.add(ORRadioButton);
        bg1.add(ANDRadioButton);
        bg1.add(NANDRadioButton);
        bg1.add(NORRadioButton);
        
        bg2.add(UnipolarnaRadioButton);
        bg2.add(BipolarnaRadioButton);
        
        Krok.setEnabled(false);
        Reset.setEnabled(false);
        /*
        oblicz(perceptron.w,10);
        oblicz(perceptron.w,-10);
        */
        
       
        
        
        
        DefaultCategoryDataset dataBar = new DefaultCategoryDataset();
        dataBar.setValue(2.0, " ", "-5");
        dataBar.getRowIndex(3);
        
        //series.add(1, 1);
        //series.add(1.5, 1.5);
        //series.add(2, 5);
        //series.add(4, 3);
        //series.add(5, 4);
         

       
        dataSet = new XYSeriesCollection();
        dataSet.addSeries(series);
        
        chart1 = ChartFactory.createXYLineChart("Projekt Graficzny", "X", "Y", dataSet);

        XYPlot xyPlot = chart1.getXYPlot();
        ValueAxis domainAxis = xyPlot.getDomainAxis();
        
        ValueAxis rangeAxis = xyPlot.getRangeAxis();

        domainAxis.setRange(-2.0, 2.0);
        ((NumberAxis)domainAxis).setTickUnit(new NumberTickUnit(0.5));
        rangeAxis.setRange(-2.0, 2.0);
        ((NumberAxis)rangeAxis).setTickUnit(new NumberTickUnit(0.5));
        
        
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        
        
        //Shape cross = ShapeUtilities.createDiagonalCross(0.1f, 0.1f);
        //XYItemRenderer renderer = xyPlot.getRenderer();
        ///renderer.setSeriesShape(0, cross);
        //renderer.setSeriesPaint(0, Color.YELLOW);
        //XYItemRenderer renderer = xyPlot.getRenderer();
        //renderer.setSeriesPaint(0, Color.red);
        
        
        ChartPanel chartPanel = new ChartPanel( chart1);
        panelWykresu.setLayout(new BorderLayout());
        panelWykresu.removeAll();
        panelWykresu.add(chartPanel, BorderLayout.CENTER);
        panelWykresu.validate();
        
        
        /*
        for (int i = 0; i < perceptron.iSetX.length; ++i) {
            ((XYPlot)chart1.getPlot()).addAnnotation(new XYShapeAnnotation(new Ellipse2D.Double(perceptron.iSetX[i][1]-0.05, perceptron.iSetX[i][2]-0.05, 0.1, 0.1)));
        }*/
        //((XYPlot)chart1.getPlot()).addAnnotation(new XYShapeAnnotation(new Ellipse2D.Double(0.95, 0.95, 0.1, 0.1)));
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelWykresu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ANDRadioButton = new javax.swing.JRadioButton();
        NANDRadioButton = new javax.swing.JRadioButton();
        NORRadioButton = new javax.swing.JRadioButton();
        ORRadioButton = new javax.swing.JRadioButton();
        Reset = new javax.swing.JButton();
        Krok = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        odswiez = new javax.swing.JButton();
        UnipolarnaRadioButton = new javax.swing.JRadioButton();
        BipolarnaRadioButton = new javax.swing.JRadioButton();
        rozpocznij = new javax.swing.JButton();
        generuj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "t", "x0(t)", "x1(t)", "x2(t)", "d(t)", "w0(t)", "w1(t)", "w2(t)", "s(t)", "y(t)", "d(t)?y(t)"
            }
        ));
        jTable1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jTable1ComponentAdded(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        panelWykresu.setBackground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout panelWykresuLayout = new javax.swing.GroupLayout(panelWykresu);
        panelWykresu.setLayout(panelWykresuLayout);
        panelWykresuLayout.setHorizontalGroup(
            panelWykresuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 713, Short.MAX_VALUE)
        );
        panelWykresuLayout.setVerticalGroup(
            panelWykresuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        ANDRadioButton.setText("AND");
        ANDRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ANDRadioButtonActionPerformed(evt);
            }
        });

        NANDRadioButton.setText("NAND");
        NANDRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NANDRadioButtonActionPerformed(evt);
            }
        });

        NORRadioButton.setText("NOR");
        NORRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NORRadioButtonActionPerformed(evt);
            }
        });

        ORRadioButton.setText("OR");
        ORRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ORRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ANDRadioButton)
                    .addComponent(ORRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NORRadioButton)
                    .addComponent(NANDRadioButton))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ORRadioButton)
                    .addComponent(NORRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ANDRadioButton)
                    .addComponent(NANDRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        Krok.setText("Krok");
        Krok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KrokActionPerformed(evt);
            }
        });

        odswiez.setText("Odswież");
        odswiez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odswiezActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 27, Short.MAX_VALUE)
                .addComponent(odswiez))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(odswiez)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        UnipolarnaRadioButton.setText("Unipolarna");
        UnipolarnaRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnipolarnaRadioButtonActionPerformed(evt);
            }
        });

        BipolarnaRadioButton.setText("Bipolarna");
        BipolarnaRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BipolarnaRadioButtonActionPerformed(evt);
            }
        });

        rozpocznij.setText("Rozpocznij");
        rozpocznij.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rozpocznijActionPerformed(evt);
            }
        });

        generuj.setText("Generuj");
        generuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Krok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(generuj, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rozpocznij, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UnipolarnaRadioButton)
                            .addComponent(BipolarnaRadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelWykresu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(UnipolarnaRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BipolarnaRadioButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Krok)
                            .addComponent(rozpocznij))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Reset)
                            .addComponent(generuj))))
                .addGap(54, 54, 54))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelWykresu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KrokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KrokActionPerformed
        
        //refreshChart();
        ORRadioButton.setEnabled(false);
        ANDRadioButton.setEnabled(false);
        NANDRadioButton.setEnabled(false);
        NORRadioButton.setEnabled(false);
        
        BipolarnaRadioButton.setEnabled(false);
        UnipolarnaRadioButton.setEnabled(false);
                
        
        System.out.println("-------------------------------------------");
        //System.out.println("Krok : " + perceptron.t);
        //perceptron.wypisanieNowychWag();
        //System.out.println("D : " + perceptron.wypisanieD() );
        //System.out.println("S : " + perceptron.ComputeS());
        //System.out.println("Y : " + perceptron.Y());
        //System.out.println("Test : " + perceptron.Test());
        
        perceptron.ComputeS();
        perceptron.Y();
        
        
        test = perceptron.Test();
        //System.out.println("D || " + perceptron.wypisanieD());
        //System.out.print("Learning : ");
        System.out.printf(perceptron.toString());
       
        
        jTable1.getModel().setValueAt(perceptron.s, perceptron.t, 8);
        jTable1.getModel().setValueAt(perceptron.y, perceptron.t, 9);
        jTable1.getModel().setValueAt(test, perceptron.t, 10);
        perceptron.LearningStep();
        jTable1.getModel().setValueAt(perceptron.t, perceptron.t, 0);
        if (test == 1) {
            krok = 0;
            //System.out.println("Nowe Wagi");
            //perceptron.wypisanieNowychWag();
            for (int i = 0; i < perceptron.w.length ; i++){
                jTable1.getModel().setValueAt(perceptron.w[i], perceptron.t, 5 + i);
            }
            oblicz(perceptron.w,10);
            oblicz(perceptron.w,-10);
            
        } else {
            //System.out.println("Stare Wagi");
            //perceptron.wypisanieNowychWag();
            for (int i = 0; i < perceptron.w.length ; i++){
                jTable1.getModel().setValueAt(perceptron.w[i], perceptron.t, 5 + i);
            }
            oblicz(perceptron.w,10);
            oblicz(perceptron.w,-10);
            krok++;
        }

        System.out.println("k " + krok);
        if (krok >= 5) {
            Krok.setEnabled(false);
            System.out.println("Perceptron jest Nauczony");
        }
        
        
        if (perceptron.t%3 == 0){
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            
            for (int i = 0; i < iSetX.length; i++){
                model.addRow(new Object[]{" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "});
                jTable1.getModel().setValueAt(iSetD[i], perceptron.t + i+1 , 4);
                
                for (int j = 0; j < iSetX[i].length; j++){
                    jTable1.getModel().setValueAt(iSetX[i][j], perceptron.t + i+1, j + 1);
                }
            }
        }
        
        for (int i = 0; i < perceptron.iSetX.length; ++i) {
            ((XYPlot) chart1.getPlot()).addAnnotation(new XYShapeAnnotation(new Ellipse2D.Double(perceptron.iSetX[i][1] - 0.05, perceptron.iSetX[i][2] - 0.05, 0.1, 0.1)));
        }
        
        //oblicz(perceptron.w,10);
        //oblicz(perceptron.w,-10);
        refreshChart();
        
    }//GEN-LAST:event_KrokActionPerformed
    
    private void refreshChart() {
        
        series = new XYSeries("funkcja y");
        dataSet = new XYSeriesCollection();
        dataSet.addSeries(series);
        
        
        XYPlot xyPlot = chart1.getXYPlot();
        ValueAxis domainAxis = xyPlot.getDomainAxis();
        
        ValueAxis rangeAxis = xyPlot.getRangeAxis();

        domainAxis.setRange(-2.0, 2.0);
        ((NumberAxis)domainAxis).setTickUnit(new NumberTickUnit(0.5));
        rangeAxis.setRange(-2.0, 2.0);
        ((NumberAxis)rangeAxis).setTickUnit(new NumberTickUnit(0.5));
        
        
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        panelWykresu.removeAll();
        panelWykresu.revalidate(); // This removes the old chart 
        //chart1 = createChart();
        //chart1 = ChartFactory.createXYLineChart("Projekt Graficzny", "X", "Y", dataSet);
        chart1.removeLegend(); 
        ChartPanel chartPanel1 = new ChartPanel(chart1); 
        panelWykresu.setLayout(new BorderLayout()); 
        panelWykresu.add(chartPanel1); 
        panelWykresu.repaint();
        chart1 = ChartFactory.createXYLineChart("Projekt Graficzny", "X", "Y", dataSet);
    }
    
    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        //perceptron.ResetLearningProcess();
        //perceptron = new Perceptron(n, 1, perceptron.w, iSetX, iSet, m, r);
        
        krok = 0;
        perceptron.t = 0;
        Krok.setEnabled(false);
        generuj.setEnabled(true);
        for (int i = jTable1.getModel().getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) jTable1.getModel()).removeRow(i);
        }
            
        
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        for (int i = 0; i <= 3; i++) {
            model.addRow(new Object[]{" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "});
        }
        
        //perceptron.ResetLearningProcess();
        
        /*
        for (int i = 0; i < perceptron.w.length ; i++){
            jTable1.getModel().setValueAt(perceptron.w[i], perceptron.t, 5 + i);
        }
        jTable1.getModel().setValueAt(perceptron.t, 0, 0);
        
        for (int i = 0; i < iSetX.length; i++) {
            for (int j = 0; j < iSetX[i].length; j++) {
                jTable1.getModel().setValueAt(iSetX[i][j], i, j + 1);
            }
        }

        krok = 0;
        */
        perceptron.t = 0;
        ORRadioButton.setEnabled(true);
        ANDRadioButton.setEnabled(true);
        NANDRadioButton.setEnabled(true);
        NORRadioButton.setEnabled(true);
        BipolarnaRadioButton.setEnabled(true);
        UnipolarnaRadioButton.setEnabled(true);
        Krok.setEnabled(false);
        rozpocznij.setEnabled(true);
        Reset.setEnabled(false);
    }//GEN-LAST:event_ResetActionPerformed
    
    private void ustawD() {
        if (ORRadioButton.isSelected()) {
            if (UnipolarnaRadioButton.isSelected()) {
                iSetD = new double[] {0, 1, 1, 1};
            } else {
                iSetD = new double[] {-1, 1, 1, 1};
            }
        } else if (ANDRadioButton.isSelected()) {
            if (UnipolarnaRadioButton.isSelected()) {
                iSetD = new double[] {0, 0, 0, 1};
            } else {
                iSetD = new double[] {-1, -1, -1, 1};
            }
        } else if (NANDRadioButton.isSelected()) {
            if (UnipolarnaRadioButton.isSelected()) {
                iSetD = new double[] {1, 1, 1, 0};
            } else {
                iSetD = new double[] {1, 1, 1, -1};
            }
        } else if (NORRadioButton.isSelected()) {
            if (UnipolarnaRadioButton.isSelected()) {
                iSetD = new double[] {1, 0, 0, 0};
            } else {
                iSetD = new double[] {1, -1, -1, -1};
            }
        }
        
        perceptron.iSetD = iSetD;
        
        for(int i = 0; i < iSetD.length; i++){
            jTable1.getModel().setValueAt(iSetD[i],i , 4);
        }
    }
    
    private void ORRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ORRadioButtonActionPerformed
        //iSetD = new double[] {0, 1, 1, 1};
        //iSetD = new double[] {-1, 1, 1, 1};
        /*
        for(int i = 0; i < iSetD.length; i++){
            jTable1.getModel().setValueAt(iSetD[i],i , 4);
        }
        //Krok.setEnabled(true);
        */
    }//GEN-LAST:event_ORRadioButtonActionPerformed

    private void ANDRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ANDRadioButtonActionPerformed
        /*
        iSetD = new double[] {0, 0, 0, 1};
        //iSetD = new double[] {-1, -1, -1, 1};
        for(int i = 0; i < iSetD.length; i++){
            jTable1.getModel().setValueAt(iSetD[i],i , 4);
        }
        //Krok.setEnabled(true);
        */
    }//GEN-LAST:event_ANDRadioButtonActionPerformed

    private void NANDRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NANDRadioButtonActionPerformed
        /*
        iSetD = new double[] {1, 1, 1, 0};
        //iSetD = new double[] {1, 1, 1, -1};
        for(int i = 0; i < iSetD.length; i++){
            jTable1.getModel().setValueAt(iSetD[i],i , 4);
        }
        //Krok.setEnabled(true);
        */ 
    }//GEN-LAST:event_NANDRadioButtonActionPerformed

    private void NORRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NORRadioButtonActionPerformed
        /*
        iSetD = new double[] {1, 0, 0, 0};
        //iSetD = new double[] {1, -1, -1, -1};
        for(int i = 0; i < iSetD.length; i++){
            jTable1.getModel().setValueAt(iSetD[i],i , 4);
        }
        //Krok.setEnabled(true);
        */
    }//GEN-LAST:event_NORRadioButtonActionPerformed

    private void jTable1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTable1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1ComponentAdded

    private void UnipolarnaRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UnipolarnaRadioButtonActionPerformed
        /*
        iSetX = new double[][]
            {{1, 0, 0},
             {1, 0, 1},
             {1, 1, 0},
             {1, 1, 1}};
        for (int i = 0; i < iSetX.length; i++){
            for (int j = 0; j < iSetX[i].length; j++){
                jTable1.getModel().setValueAt(iSetX[i][j], i, j + 1);
            }
        }
        ORRadioButton.setEnabled(false);
        ANDRadioButton.setEnabled(false);
        NANDRadioButton.setEnabled(false);
        NORRadioButton.setEnabled(false);
        Krok.setEnabled(true);
       */
    }//GEN-LAST:event_UnipolarnaRadioButtonActionPerformed

    private void BipolarnaRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BipolarnaRadioButtonActionPerformed
        /*
        iSetX = new double[][]
            {{1, -1, -1},
             {1, -1, 1},
             {1, 1, -1},
             {1, 1, 1}};
        for (int i = 0; i < iSetX.length; i++){
            for (int j = 0; j < iSetX[i].length; j++){
                jTable1.getModel().setValueAt(iSetX[i][j], i, j + 1);
            }
        }
        ORRadioButton.setEnabled(false);
        ANDRadioButton.setEnabled(false);
        NANDRadioButton.setEnabled(false);
        NORRadioButton.setEnabled(false);
        //Krok.setEnabled(true);
        */
    }//GEN-LAST:event_BipolarnaRadioButtonActionPerformed

    private void rozpocznijActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rozpocznijActionPerformed
        if ((ORRadioButton.isSelected() || ANDRadioButton.isSelected() || NANDRadioButton.isSelected()
            || NORRadioButton.isSelected())
        && (BipolarnaRadioButton.isSelected() || UnipolarnaRadioButton.isSelected()))
        {
            ORRadioButton.setEnabled(false);
            ANDRadioButton.setEnabled(false);
            NANDRadioButton.setEnabled(false);
            NORRadioButton.setEnabled(false);
            BipolarnaRadioButton.setEnabled(false);
            UnipolarnaRadioButton.setEnabled(false);
            Krok.setEnabled(true);
            if (BipolarnaRadioButton.isSelected()) {
                perceptron.f = -1;
                iSetX = new double [][] 
                {{1, -1, -1},
                    {1, -1, 1},
                    {1, 1, -1},
                    {1, 1, 1}};
                for (int i = 0; i < iSetX.length; i++){
                    for (int j = 0; j < iSetX[i].length; j++){
                        jTable1.getModel().setValueAt(iSetX[i][j], i, j + 1);
                    }
                }
            } else {
                perceptron.f = 1;
                iSetX = new double [][] 
                {{1, 0, 0},
                    {1, 0, 1},
                    {1, 1, 0},
                    {1, 1, 1}};
                for (int i = 0; i < iSetX.length; i++){
                    for (int j = 0; j < iSetX[i].length; j++){
                        jTable1.getModel().setValueAt(iSetX[i][j], i, j + 1);
                    }
                }
            }
            perceptron.iSetX = iSetX;
            ustawD();
            rozpocznij.setEnabled(false);
            Reset.setEnabled(true);
            generuj.setEnabled(false);
            if (czyWylosowano == false) {
                generujActionPerformed(evt);
                //perceptron.w = new double[]{2, -1, -0.5};
                //perceptron.w = new double[]{-1, 2, 2};
                //perceptron.w = new double[]{0, 0.5, 4};
                //perceptron.w = new double[]{0, 2.0, 4};
            }
            czyWylosowano = false;
            //generujActionPerformed(evt);
            /*
            for (int i = 0; i < perceptron.iSetX.length; ++i) {
                ((XYPlot)chart1.getPlot()).addAnnotation(new XYShapeAnnotation(new Ellipse2D.Double(perceptron.iSetX[i][1]-0.05, perceptron.iSetX[i][2]-0.05, 0.1, 0.1)));
            }
            */
        }
    }//GEN-LAST:event_rozpocznijActionPerformed

    private void generujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generujActionPerformed
       czyWylosowano = true;
       perceptron.ResetLearningProcess();
       for (int i = 0; i < perceptron.w.length ; i++){
            jTable1.getModel().setValueAt(perceptron.w[i], perceptron.t, 5 + i);
       }
       jTable1.getModel().setValueAt(perceptron.t, 0, 0);
    }//GEN-LAST:event_generujActionPerformed

    private void odswiezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odswiezActionPerformed
        refreshChart();
    }//GEN-LAST:event_odswiezActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formatka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formatka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formatka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formatka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formatka().setVisible(true);
            }
        });
    }
    
    public void oblicz(double []w, double x1){
        double x2;
        if (w[1] == 0 && w[2] == 0){
            System.out.println("sprzeczność lub nieoznaczoność");
        } else {
            x2 = (-(w[0]/w[2])) - ((w[1]/w[2])* x1);
            series.add(x1, x2);
            System.out.println("X 1: " + x1 + " X2 : " + x2);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ANDRadioButton;
    private javax.swing.JRadioButton BipolarnaRadioButton;
    private javax.swing.JButton Krok;
    private javax.swing.JRadioButton NANDRadioButton;
    private javax.swing.JRadioButton NORRadioButton;
    private javax.swing.JRadioButton ORRadioButton;
    private javax.swing.JButton Reset;
    private javax.swing.JRadioButton UnipolarnaRadioButton;
    private javax.swing.JButton generuj;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton odswiez;
    private javax.swing.JPanel panelWykresu;
    private javax.swing.JButton rozpocznij;
    // End of variables declaration//GEN-END:variables
}
