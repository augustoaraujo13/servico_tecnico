package Telas;

import ConexaoBanco.Conexao;
import java.sql.Connection;


public class TelaLogin extends javax.swing.JFrame {

    Connection conn = null;
    String conectado = "Conectado!";
    String desconectado = "Não conectou!";

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
        JbLogin = new javax.swing.JButton();
        LblStatus = new javax.swing.JLabel();
        LblStatus2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Login");
        setResizable(false);

        LblIconePc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/PC.png"))); // NOI18N

        LblUsuario.setText("Usuário");

        LblSenha.setText("Senha");

        JbLogin.setBackground(new java.awt.Color(0, 153, 255));
        JbLogin.setForeground(new java.awt.Color(255, 255, 255));
        JbLogin.setText("Login");

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
                        .addGap(67, 67, 67)
                        .addComponent(JbLogin))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TxtUsuario)
                        .addComponent(JpSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JbLogin)
                    .addComponent(LblStatus)
                    .addComponent(LblStatus2))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(426, 219));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JButton JbLogin;
    private javax.swing.JPasswordField JpSenha;
    private javax.swing.JLabel LblIconePc;
    private javax.swing.JLabel LblSenha;
    private javax.swing.JLabel LblStatus;
    private javax.swing.JLabel LblStatus2;
    private javax.swing.JLabel LblUsuario;
    private javax.swing.JTextField TxtUsuario;
    // End of variables declaration//GEN-END:variables
}
