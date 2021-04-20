package Telas;

import ConexaoBanco.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    public TelaUsuario() {

        String informacao = "Antes de cadastrar,"
                + " deletar ou alterar, "
                + "faça uma busca usando o Id para evitar possíveis erros";
        initComponents();

        JOptionPane.showMessageDialog(this, informacao);

        conn = Conexao.abrirBanco();
    }

    private void Criar() {

        String criando = "insert into usuarios(id_user, usuario, email,login,senha,perfil)"
                + "values(?,?,?,?,?,?);";
        String comcluido = "Usuário cadastrado!";
        String resposta2 = "";
        String resposta3 = "Admin";

        try {

            st = conn.prepareStatement(criando);

            st.setString(1, TxtId.getText().trim());
            st.setString(2, TxtUsuario.getText().trim());
            st.setString(3, TxtEmail.getText().trim());
            st.setString(4, TxtLogin.getText().trim());
            st.setString(5, TxtSenha.getText().trim());
            st.setString(6, CbPerfil.getSelectedItem().toString());

            if ((TxtId.getText().isEmpty()) || (TxtUsuario.getText().isEmpty())
                    || (TxtLogin.getText().isEmpty()) || (TxtSenha.getText().isEmpty())) {

                String informacao2 = "Preencha os campos obrigatórios!!!!";
                JOptionPane.showMessageDialog(this, informacao2);

            } else {

                st.executeUpdate();

                JOptionPane.showMessageDialog(this, comcluido);
                TxtId.setText(resposta2);
                TxtUsuario.setText(resposta2);
                TxtEmail.setText(resposta2);
                TxtLogin.setText(resposta2);
                TxtSenha.setText(resposta2);
                CbPerfil.setSelectedItem(resposta3);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    private void Buscar() {

        String busca = "select * from usuarios where id_user =?;";

        try {

            st = conn.prepareStatement(busca);
            st.setString(1, TxtId.getText());
            rs = st.executeQuery();

            if (rs.next()) {
                TxtUsuario.setText(rs.getString(2));
                TxtEmail.setText(rs.getString(3));
                TxtLogin.setText(rs.getString(4));
                TxtSenha.setText(rs.getString(5));
                CbPerfil.setSelectedItem(rs.getString(6));
            } else if (TxtId.getText().isEmpty()) {

                String informacao3 = "O campo Id precisa ser informado,"
                        + " para executar a busca!";
                JOptionPane.showMessageDialog(this, informacao3);

            } else {
                String resposta = "O usuario que você pesquisou, não existe";
                String resposta2 = "";
                String resposta3 = "Admin";
                JOptionPane.showMessageDialog(this, resposta);
                TxtId.setText(resposta2);
                TxtUsuario.setText(resposta2);
                TxtEmail.setText(resposta2);
                TxtLogin.setText(resposta2);
                TxtSenha.setText(resposta2);
                CbPerfil.setSelectedItem(resposta3);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            ///System.out.println(e);

        }

    }

    private void Alterar() {

    }

    private void Deletar() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblId = new javax.swing.JLabel();
        LblUsuario = new javax.swing.JLabel();
        LblEmail = new javax.swing.JLabel();
        LblLogin = new javax.swing.JLabel();
        LblSenha = new javax.swing.JLabel();
        LblPerfil = new javax.swing.JLabel();
        TxtId = new javax.swing.JTextField();
        TxtUsuario = new javax.swing.JTextField();
        TxtEmail = new javax.swing.JTextField();
        TxtLogin = new javax.swing.JTextField();
        TxtSenha = new javax.swing.JTextField();
        CbPerfil = new javax.swing.JComboBox<>();
        BtnCriar = new javax.swing.JButton();
        BtnBuscar = new javax.swing.JButton();
        BtnAlterar = new javax.swing.JButton();
        BtnDeletar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tela Usuários");
        setMinimumSize(new java.awt.Dimension(499, 0));
        setPreferredSize(new java.awt.Dimension(499, 370));

        LblId.setText("*Id");

        LblUsuario.setText("*Usuário");

        LblEmail.setText("Email");

        LblLogin.setText("*Login");

        LblSenha.setText("*Senha");

        LblPerfil.setText("*Perfil");

        CbPerfil.setBackground(new java.awt.Color(0, 153, 255));
        CbPerfil.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CbPerfil.setForeground(new java.awt.Color(255, 255, 255));
        CbPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        BtnCriar.setBackground(new java.awt.Color(0, 204, 0));
        BtnCriar.setForeground(new java.awt.Color(255, 255, 255));
        BtnCriar.setText("Criar");
        BtnCriar.setToolTipText("Cria um novo usuário");
        BtnCriar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarActionPerformed(evt);
            }
        });

        BtnBuscar.setBackground(new java.awt.Color(0, 153, 255));
        BtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        BtnBuscar.setText("Buscar");
        BtnBuscar.setToolTipText("Busca o usuário no banco de dados");
        BtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        BtnAlterar.setBackground(new java.awt.Color(255, 153, 0));
        BtnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAlterar.setText("Alterar");
        BtnAlterar.setToolTipText("Altera o usuário no banco de dados");
        BtnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        BtnDeletar.setBackground(new java.awt.Color(255, 0, 0));
        BtnDeletar.setForeground(new java.awt.Color(255, 255, 255));
        BtnDeletar.setText("Deletar");
        BtnDeletar.setToolTipText("Cuidado!! Deleta o usuário no banco de dados");
        BtnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/PC.png"))); // NOI18N

        jLabel2.setText("*Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(LblUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addComponent(TxtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnDeletar)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(LblId)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblUsuario))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEmail))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblLogin))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblSenha))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblPerfil)
                            .addComponent(CbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCriar)
                    .addComponent(BtnBuscar)
                    .addComponent(BtnAlterar)
                    .addComponent(BtnDeletar))
                .addGap(35, 35, 35))
        );

        setBounds(0, 0, 499, 408);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        Buscar();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarActionPerformed
        Criar();
    }//GEN-LAST:event_BtnCriarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnCriar;
    private javax.swing.JButton BtnDeletar;
    private javax.swing.JComboBox<String> CbPerfil;
    private javax.swing.JLabel LblEmail;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblLogin;
    private javax.swing.JLabel LblPerfil;
    private javax.swing.JLabel LblSenha;
    private javax.swing.JLabel LblUsuario;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtLogin;
    private javax.swing.JTextField TxtSenha;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
