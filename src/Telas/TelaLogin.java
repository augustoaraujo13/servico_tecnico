package Telas;

import ConexaoBanco.Conexao;
//import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {

    Connection conn = null;
    String conectado = "Conectado!";
    String desconectado = "Não conectou!";
    PreparedStatement st = null;
    ResultSet rs = null;

    public void Logar() {

        String comando = "select * from usuarios where login = ? and senha = ?;";

        try {

            st = conn.prepareStatement(comando);
            st.setString(1, TxtUsuario.getText());
            st.setString(2, JpSenha.getText());
            rs = st.executeQuery();

            if (rs.next()) {

                String perfil = rs.getString(6);

                if (perfil.equals("Admin")) {

                    TelaPrincipal tp = new TelaPrincipal();
                    tp.setVisible(true);
                    TelaPrincipal.MenuRelatorio.setEnabled(true);
                    TelaPrincipal.MenuUsuario.setEnabled(true);
                    TelaPrincipal.LblUsuario2.setText(rs.getString(2));
                    //TelaPrincipal.LblUsuario2.setForeground(Color.BLUE);
                    this.dispose();
                    //conn.close();

                } else {

                    TelaPrincipal tp = new TelaPrincipal();
                    TelaPrincipal.LblUsuario2.setText(rs.getString(2));
                    tp.setVisible(true);
                    this.dispose();
                    //conn.close();

                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha Inválidos!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public TelaLogin() {
        initComponents();

        conn = Conexao.abrirBanco();
        if (conn != null) {
            LblStatus2.setText(conectado);
        } else {
            LblStatus2.setText(desconectado);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblIconePc = new javax.swing.JLabel();
        LblUsuario = new javax.swing.JLabel();
        LblSenha = new javax.swing.JLabel();
        TxtUsuario = new javax.swing.JTextField();
        JpSenha = new javax.swing.JPasswordField();
        BtnLogin = new javax.swing.JButton();
        LblStatus = new javax.swing.JLabel();
        LblStatus2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Login");
        setResizable(false);

        LblIconePc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/PC.png"))); // NOI18N

        LblUsuario.setText("Usuário");

        LblSenha.setText("Senha");

        BtnLogin.setBackground(new java.awt.Color(0, 153, 255));
        BtnLogin.setForeground(new java.awt.Color(255, 255, 255));
        BtnLogin.setText("Login");
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });

        LblStatus.setText("Status:");

        LblStatus2.setText("---------");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(LblIconePc)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblUsuario)
                    .addComponent(LblSenha)
                    .addComponent(LblStatus))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LblStatus2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnLogin))
                    .addComponent(TxtUsuario)
                    .addComponent(JpSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblUsuario)
                            .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblSenha)
                            .addComponent(JpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(LblIconePc))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblStatus2)
                    .addComponent(BtnLogin)
                    .addComponent(LblStatus))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(426, 219));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed
        Logar();
    }//GEN-LAST:event_BtnLoginActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLogin;
    private javax.swing.JPasswordField JpSenha;
    private javax.swing.JLabel LblIconePc;
    private javax.swing.JLabel LblSenha;
    private javax.swing.JLabel LblStatus;
    private javax.swing.JLabel LblStatus2;
    private javax.swing.JLabel LblUsuario;
    private javax.swing.JTextField TxtUsuario;
    // End of variables declaration//GEN-END:variables
}
