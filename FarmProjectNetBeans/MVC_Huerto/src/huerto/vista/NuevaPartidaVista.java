/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.vista;

/**
 *
 * @author tarde
 */
public class NuevaPartidaVista extends javax.swing.JFrame {

    /**
     * Creates new form NuevaPartidaVista
     */
    public NuevaPartidaVista() {
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

        bgProfesion = new javax.swing.ButtonGroup();
        Panel0 = new javax.swing.JPanel();
        Panel1Up = new javax.swing.JPanel();
        UpPanel2Left = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        UpPanel2Right = new javax.swing.JPanel();
        ayuda = new javax.swing.JButton();
        UpPanel2Center = new javax.swing.JPanel();
        imgLogo = new javax.swing.JLabel();
        Panel1Down = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        Panel1Center = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        CenterPanel21 = new javax.swing.JPanel();
        Panel21Left = new javax.swing.JPanel();
        LeftPanel211 = new javax.swing.JPanel();
        lblNombreUsu = new javax.swing.JLabel();
        LeftPanel212 = new javax.swing.JPanel();
        lblPassw = new javax.swing.JLabel();
        LeftPanel213 = new javax.swing.JPanel();
        lblNombrePer = new javax.swing.JLabel();
        Pane21Center = new javax.swing.JPanel();
        LeftPanel214 = new javax.swing.JPanel();
        txtNombreUsu = new javax.swing.JTextField();
        LeftPanel215 = new javax.swing.JPanel();
        txtPassw = new javax.swing.JPasswordField();
        LeftPanel216 = new javax.swing.JPanel();
        txtNombrePer = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));

        Panel0.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 0), 2, true));
        Panel0.setLayout(new java.awt.BorderLayout());

        Panel1Up.setBackground(new java.awt.Color(204, 255, 204));
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

        imgLogo.setText("HUERTO");
        UpPanel2Center.add(imgLogo);

        Panel1Up.add(UpPanel2Center, java.awt.BorderLayout.CENTER);

        Panel0.add(Panel1Up, java.awt.BorderLayout.PAGE_START);

        Panel1Down.setBackground(new java.awt.Color(204, 255, 204));
        Panel1Down.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnOk.setBackground(new java.awt.Color(204, 255, 204));
        btnOk.setText("Crear");
        btnOk.setActionCommand("accept");
        Panel1Down.add(btnOk);

        Panel0.add(Panel1Down, java.awt.BorderLayout.PAGE_END);

        Panel1Center.setBackground(new java.awt.Color(204, 255, 204));
        Panel1Center.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Panel1Center.setLayout(new javax.swing.BoxLayout(Panel1Center, javax.swing.BoxLayout.Y_AXIS));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Datos del personaje");
        jPanel1.add(jLabel4);

        Panel1Center.add(jPanel1);

        CenterPanel21.setLayout(new java.awt.BorderLayout());

        Panel21Left.setLayout(new javax.swing.BoxLayout(Panel21Left, javax.swing.BoxLayout.Y_AXIS));

        LeftPanel211.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lblNombreUsu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNombreUsu.setText("Nombre de usuario");
        LeftPanel211.add(lblNombreUsu);

        Panel21Left.add(LeftPanel211);

        LeftPanel212.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lblPassw.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPassw.setText("Contraseña");
        LeftPanel212.add(lblPassw);

        Panel21Left.add(LeftPanel212);

        LeftPanel213.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lblNombrePer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNombrePer.setText("Nombre del personaje");
        LeftPanel213.add(lblNombrePer);

        Panel21Left.add(LeftPanel213);

        CenterPanel21.add(Panel21Left, java.awt.BorderLayout.LINE_START);

        Pane21Center.setLayout(new javax.swing.BoxLayout(Pane21Center, javax.swing.BoxLayout.Y_AXIS));

        LeftPanel214.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtNombreUsu.setPreferredSize(new java.awt.Dimension(100, 20));
        LeftPanel214.add(txtNombreUsu);

        Pane21Center.add(LeftPanel214);

        LeftPanel215.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtPassw.setPreferredSize(new java.awt.Dimension(100, 20));
        LeftPanel215.add(txtPassw);

        Pane21Center.add(LeftPanel215);

        LeftPanel216.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtNombrePer.setPreferredSize(new java.awt.Dimension(150, 20));
        LeftPanel216.add(txtNombrePer);

        Pane21Center.add(LeftPanel216);

        CenterPanel21.add(Pane21Center, java.awt.BorderLayout.CENTER);

        Panel1Center.add(CenterPanel21);

        Panel0.add(Panel1Center, java.awt.BorderLayout.CENTER);

        getContentPane().add(Panel0, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CenterPanel21;
    private javax.swing.JPanel LeftPanel211;
    private javax.swing.JPanel LeftPanel212;
    private javax.swing.JPanel LeftPanel213;
    private javax.swing.JPanel LeftPanel214;
    private javax.swing.JPanel LeftPanel215;
    private javax.swing.JPanel LeftPanel216;
    private javax.swing.JPanel Pane21Center;
    private javax.swing.JPanel Panel0;
    private javax.swing.JPanel Panel1Center;
    private javax.swing.JPanel Panel1Down;
    private javax.swing.JPanel Panel1Up;
    private javax.swing.JPanel Panel21Left;
    private javax.swing.JPanel UpPanel2Center;
    private javax.swing.JPanel UpPanel2Left;
    private javax.swing.JPanel UpPanel2Right;
    public javax.swing.JButton ayuda;
    private javax.swing.ButtonGroup bgProfesion;
    public javax.swing.JButton btnBack;
    public javax.swing.JButton btnOk;
    private javax.swing.JLabel imgLogo;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNombrePer;
    private javax.swing.JLabel lblNombreUsu;
    private javax.swing.JLabel lblPassw;
    public javax.swing.JTextField txtNombrePer;
    public javax.swing.JTextField txtNombreUsu;
    public javax.swing.JPasswordField txtPassw;
    // End of variables declaration//GEN-END:variables
}