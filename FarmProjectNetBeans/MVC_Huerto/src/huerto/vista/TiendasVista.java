/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.vista;

/**
 *
 * @author Fran
 */
public class TiendasVista extends javax.swing.JFrame {

    /**
     * Creates new form TiendasVista
     */
    public TiendasVista() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel0 = new javax.swing.JPanel();
        Panel1Down = new javax.swing.JPanel();
        Objetos = new javax.swing.JPanel();
        itemUndo = new javax.swing.JButton();
        item1 = new javax.swing.JToggleButton();
        item2 = new javax.swing.JToggleButton();
        item3 = new javax.swing.JToggleButton();
        item4 = new javax.swing.JToggleButton();
        item5 = new javax.swing.JToggleButton();
        item6 = new javax.swing.JToggleButton();
        item7 = new javax.swing.JToggleButton();
        item8 = new javax.swing.JToggleButton();
        item9 = new javax.swing.JToggleButton();
        item10 = new javax.swing.JToggleButton();
        itemNext = new javax.swing.JButton();
        Panel1Up = new javax.swing.JPanel();
        UpPanel2Left = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        UpPanel2Right = new javax.swing.JPanel();
        ayuda = new javax.swing.JButton();
        UpPanel2Center = new javax.swing.JPanel();
        imgLogo = new javax.swing.JLabel();
        Panel1Center = new javax.swing.JPanel();
        Panel2Center = new javax.swing.JPanel();
        SeparaInfo = new javax.swing.JPanel();
        PBox2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        barEnergia = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        barAgua = new javax.swing.JProgressBar();
        PBox3 = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        lblNombreHuerto = new javax.swing.JLabel();
        lblDinero = new javax.swing.JLabel();
        PBox1 = new javax.swing.JPanel();
        PanelTiendas = new javax.swing.JPanel();
        btnTiendaGlobal = new javax.swing.JButton();
        PanelInfoProds = new javax.swing.JPanel();
        FotoProd = new javax.swing.JPanel();
        lblFotoProd = new javax.swing.JLabel();
        FotoProd1 = new javax.swing.JPanel();
        btnComprar = new javax.swing.JButton();
        PanelCentralProds = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel0.setLayout(new java.awt.BorderLayout());

        Panel1Down.setLayout(new javax.swing.BoxLayout(Panel1Down, javax.swing.BoxLayout.X_AXIS));

        Objetos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        itemUndo.setText("<<");
        itemUndo.setActionCommand("itemundo");
        itemUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUndoActionPerformed(evt);
            }
        });
        Objetos.add(itemUndo);

        item1.setActionCommand("item1");
        item1.setOpaque(true);
        Objetos.add(item1);

        item2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/girasol.png"))); // NOI18N
        item2.setActionCommand("item2");
        item2.setOpaque(true);
        Objetos.add(item2);

        item3.setActionCommand("item3");
        item3.setOpaque(true);
        Objetos.add(item3);

        item4.setActionCommand("item4");
        item4.setOpaque(true);
        Objetos.add(item4);

        item5.setActionCommand("item5");
        item5.setOpaque(true);
        Objetos.add(item5);

        item6.setActionCommand("item6");
        item6.setOpaque(true);
        Objetos.add(item6);

        item7.setActionCommand("item7");
        item7.setOpaque(true);
        Objetos.add(item7);

        item8.setActionCommand("item8");
        item8.setOpaque(true);
        Objetos.add(item8);

        item9.setActionCommand("item9");
        item9.setOpaque(true);
        Objetos.add(item9);

        item10.setActionCommand("item10");
        item10.setOpaque(true);
        Objetos.add(item10);

        itemNext.setText(">>");
        itemNext.setActionCommand("itemnext");
        itemNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNextActionPerformed(evt);
            }
        });
        Objetos.add(itemNext);

        Panel1Down.add(Objetos);

        jPanel0.add(Panel1Down, java.awt.BorderLayout.PAGE_END);

        Panel1Up.setLayout(new java.awt.BorderLayout());

        UpPanel2Left.setBackground(new java.awt.Color(153, 255, 153));

        btnBack.setBackground(new java.awt.Color(153, 153, 255));
        btnBack.setText("<");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        UpPanel2Left.add(btnBack);

        Panel1Up.add(UpPanel2Left, java.awt.BorderLayout.LINE_START);

        UpPanel2Right.setBackground(new java.awt.Color(153, 255, 153));

        ayuda.setText("Ayuda");
        ayuda.setActionCommand("help");
        UpPanel2Right.add(ayuda);

        Panel1Up.add(UpPanel2Right, java.awt.BorderLayout.LINE_END);

        UpPanel2Center.setBackground(new java.awt.Color(153, 255, 153));

        imgLogo.setText("Logo APP");
        UpPanel2Center.add(imgLogo);

        Panel1Up.add(UpPanel2Center, java.awt.BorderLayout.CENTER);

        jPanel0.add(Panel1Up, java.awt.BorderLayout.PAGE_START);

        Panel1Center.setLayout(new java.awt.BorderLayout());

        Panel2Center.setLayout(new javax.swing.BoxLayout(Panel2Center, javax.swing.BoxLayout.Y_AXIS));

        SeparaInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SeparaInfo.setLayout(new javax.swing.BoxLayout(SeparaInfo, javax.swing.BoxLayout.X_AXIS));

        PBox2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Energía");
        PBox2.add(jLabel1);

        barEnergia.setBackground(new java.awt.Color(0, 204, 0));
        barEnergia.setForeground(new java.awt.Color(0, 204, 0));
        PBox2.add(barEnergia);

        jLabel2.setText("Agua");
        PBox2.add(jLabel2);

        barAgua.setBackground(new java.awt.Color(0, 153, 153));
        barAgua.setForeground(new java.awt.Color(0, 153, 153));
        barAgua.setValue(100);
        PBox2.add(barAgua);

        SeparaInfo.add(PBox2);

        PBox3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFecha.setText("fecha");
        PBox3.add(lblFecha);

        lblNombreHuerto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNombreHuerto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreHuerto.setText("xxxxxxxxx");
        PBox3.add(lblNombreHuerto);

        lblDinero.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDinero.setForeground(new java.awt.Color(204, 204, 0));
        lblDinero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDinero.setText("1000 $");
        PBox3.add(lblDinero);

        SeparaInfo.add(PBox3);

        Panel2Center.add(SeparaInfo);

        PBox1.setLayout(new java.awt.BorderLayout());

        PanelTiendas.setLayout(new java.awt.GridLayout(1, 0));

        btnTiendaGlobal.setText("Comerciante global");
        btnTiendaGlobal.setActionCommand("global");
        PanelTiendas.add(btnTiendaGlobal);

        PBox1.add(PanelTiendas, java.awt.BorderLayout.LINE_START);

        PanelInfoProds.setLayout(new javax.swing.BoxLayout(PanelInfoProds, javax.swing.BoxLayout.Y_AXIS));

        FotoProd.add(lblFotoProd);

        PanelInfoProds.add(FotoProd);

        FotoProd1.setLayout(new java.awt.BorderLayout());

        btnComprar.setText("Comprar");
        btnComprar.setActionCommand("buy");
        btnComprar.setEnabled(false);
        FotoProd1.add(btnComprar, java.awt.BorderLayout.CENTER);

        PanelInfoProds.add(FotoProd1);

        PBox1.add(PanelInfoProds, java.awt.BorderLayout.LINE_END);

        PanelCentralProds.setLayout(new java.awt.BorderLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        PanelCentralProds.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        PBox1.add(PanelCentralProds, java.awt.BorderLayout.CENTER);

        Panel2Center.add(PBox1);

        Panel1Center.add(Panel2Center, java.awt.BorderLayout.CENTER);

        jPanel0.add(Panel1Center, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel0, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUndoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemUndoActionPerformed

    private void itemNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel FotoProd;
    private javax.swing.JPanel FotoProd1;
    private javax.swing.JPanel Objetos;
    private javax.swing.JPanel PBox1;
    private javax.swing.JPanel PBox2;
    private javax.swing.JPanel PBox3;
    private javax.swing.JPanel Panel1Center;
    private javax.swing.JPanel Panel1Down;
    private javax.swing.JPanel Panel1Up;
    private javax.swing.JPanel Panel2Center;
    private javax.swing.JPanel PanelCentralProds;
    private javax.swing.JPanel PanelInfoProds;
    public javax.swing.JPanel PanelTiendas;
    private javax.swing.JPanel SeparaInfo;
    private javax.swing.JPanel UpPanel2Center;
    private javax.swing.JPanel UpPanel2Left;
    private javax.swing.JPanel UpPanel2Right;
    public javax.swing.JButton ayuda;
    public javax.swing.JProgressBar barAgua;
    public javax.swing.JProgressBar barEnergia;
    public javax.swing.JButton btnBack;
    public javax.swing.JButton btnComprar;
    public javax.swing.JButton btnTiendaGlobal;
    private javax.swing.JLabel imgLogo;
    public javax.swing.JToggleButton item1;
    public javax.swing.JToggleButton item10;
    public javax.swing.JToggleButton item2;
    public javax.swing.JToggleButton item3;
    public javax.swing.JToggleButton item4;
    public javax.swing.JToggleButton item5;
    public javax.swing.JToggleButton item6;
    public javax.swing.JToggleButton item7;
    public javax.swing.JToggleButton item8;
    public javax.swing.JToggleButton item9;
    public javax.swing.JButton itemNext;
    public javax.swing.JButton itemUndo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel0;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblDinero;
    public javax.swing.JLabel lblFecha;
    public javax.swing.JLabel lblFotoProd;
    public javax.swing.JLabel lblNombreHuerto;
    public javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}