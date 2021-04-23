package Telas;

import ConexaoBanco.Conexao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    public TelaCliente() {

        String informacao = "Use o campo pesquisar, para ver"
                + " se o usuário já existe!";

        initComponents();

        JOptionPane.showMessageDialog(this, informacao);

        conn = Conexao.abrirBanco();

    }

    private void pesquisar() {

        String pesquisa = "select * from clientes where nome like ?";
        String pesquisa2 = "%";

        try {

            st = conn.prepareStatement(pesquisa);

            st.setString(1, TxtPesquisa.getText().trim() + pesquisa2);
            rs = st.executeQuery();

            TabClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    private void mostrar() {

        int setar = TabClientes.getSelectedRow();

        TxtId.setText(TabClientes.getModel().getValueAt(setar, 0).toString().trim());
        TxtNome.setText(TabClientes.getModel().getValueAt(setar, 1).toString().trim());
        TxtEmail.setText(TabClientes.getModel().getValueAt(setar, 2).toString().trim());
        TxtTelefone.setText(TabClientes.getModel().getValueAt(setar, 3).toString().trim());
        TxtCpf.setText(TabClientes.getModel().getValueAt(setar, 4).toString().trim());

        BtnCriar.setEnabled(false);

    }

    private void Criar() {

        String criando = "insert into clientes (nome, email, telefone, CPF)"
                + " values (?,?,?,?);";

        try {

            st = conn.prepareStatement(criando);

            st.setString(1, TxtNome.getText().trim());
            st.setString(2, TxtEmail.getText().trim());
            st.setString(3, TxtTelefone.getText().trim());
            st.setString(4, TxtCpf.getText().trim());

            if ((TxtNome.getText().isEmpty())
                    || (TxtTelefone.getText().isEmpty()) || (TxtCpf.getText().isEmpty())) {

                String informacao = "Preencha os campos obrigatórios!";
                JOptionPane.showMessageDialog(this, informacao);

            } else {

                String comcluido = "Cliente cadastrado!";
                String resposta = "";

                st.executeUpdate();

                JOptionPane.showMessageDialog(this, comcluido);

                TxtNome.setText(resposta);
                TxtEmail.setText(resposta);
                TxtTelefone.setText(resposta);
                TxtCpf.setText(resposta);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }

    }

    private void alterar() {

        String alterando = "update clientes set nome =?, email =?, telefone =?, "
                + "CPF =? where id_cliente =?;";

        try {

            st = conn.prepareStatement(alterando);
            st.setString(1, TxtNome.getText().trim());
            st.setString(2, TxtEmail.getText().trim());
            st.setString(3, TxtTelefone.getText().trim());
            st.setString(4, TxtCpf.getText().trim());
            st.setString(5, TxtId.getText().trim());

            if ((TxtNome.getText().isEmpty())
                    || (TxtTelefone.getText().isEmpty()) || (TxtCpf.getText().isEmpty())) {

                String informacao = "Preencha os campos obrigatórios!";
                JOptionPane.showMessageDialog(this, informacao);

            } else {

                String comcluido = "Dados alterados com sucesso!";
                String resposta2 = "";

                st.executeUpdate();

                JOptionPane.showMessageDialog(this, comcluido);

                BtnCriar.setEnabled(true);

                TxtPesquisa.setText(resposta2);
                TxtNome.setText(resposta2);
                TxtEmail.setText(resposta2);
                TxtTelefone.setText(resposta2);
                TxtCpf.setText(resposta2);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            ///System.out.println(e);
        }

    }

    private void Deletar() {

        String confirmando = "Deseja deletar esse cliente?";
        String confirmando2 = "Atenção";

        int confirmar = JOptionPane.showConfirmDialog(null, confirmando, confirmando, JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {

            String deletando = "delete from clientes where id_cliente=?;";

            try {

                st = conn.prepareStatement(deletando);
                st.setString(1, TxtId.getText().trim());

                if (TxtId.getText().isEmpty()) {

                    String informacao = "Preencha o campo Id, para excluir usuario!";
                    JOptionPane.showMessageDialog(this, informacao);

                } else {

                    String comcluido = "Cliente excluído com sucesso!";
                    String resposta2 = "";

                    st.executeUpdate();

                    JOptionPane.showMessageDialog(this, comcluido);
                    BtnCriar.setEnabled(true);

                    TxtId.setText(resposta2);
                    TxtNome.setText(resposta2);
                    TxtEmail.setText(resposta2);
                    TxtTelefone.setText(resposta2);
                    TxtCpf.setText(resposta2);
                    TxtPesquisa.setText(resposta2);
                    

                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
        } else {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        LblNome = new javax.swing.JLabel();
        LblEmail = new javax.swing.JLabel();
        LblTelefone = new javax.swing.JLabel();
        LblCpf = new javax.swing.JLabel();
        TxtNome = new javax.swing.JTextField();
        TxtEmail = new javax.swing.JTextField();
        TxtTelefone = new javax.swing.JTextField();
        TxtCpf = new javax.swing.JTextField();
        BtnCriar = new javax.swing.JButton();
        BtnAlterar = new javax.swing.JButton();
        BtnDeletar = new javax.swing.JButton();
        TxtPesquisa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabClientes = new javax.swing.JTable();
        LblPesquisar = new javax.swing.JLabel();
        LblId = new javax.swing.JLabel();
        TxtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tela Clientes");

        LblNome.setText("*Nome:");

        LblEmail.setText("Email:");

        LblTelefone.setText("*Telefone:");

        LblCpf.setText("*CPF:");

        BtnCriar.setBackground(new java.awt.Color(0, 204, 0));
        BtnCriar.setForeground(new java.awt.Color(255, 255, 255));
        BtnCriar.setText("Criar");
        BtnCriar.setToolTipText("Cria um novo cliente");
        BtnCriar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarActionPerformed(evt);
            }
        });

        BtnAlterar.setBackground(new java.awt.Color(255, 153, 0));
        BtnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAlterar.setText("Alterar");
        BtnAlterar.setToolTipText("Altera o cliente no banco de dados");
        BtnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        BtnDeletar.setBackground(new java.awt.Color(255, 0, 0));
        BtnDeletar.setForeground(new java.awt.Color(255, 255, 255));
        BtnDeletar.setText("Deletar");
        BtnDeletar.setToolTipText("Cuidado!! Deleta o cliente no banco de dados");
        BtnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeletarActionPerformed(evt);
            }
        });

        TxtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPesquisaActionPerformed(evt);
            }
        });
        TxtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtPesquisaKeyReleased(evt);
            }
        });

        TabClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabClientes);

        LblPesquisar.setText("Pesquisar:");

        LblId.setText("*Id:");

        TxtId.setEnabled(false);

        jLabel1.setText("*Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LblPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(LblId))
                                    .addComponent(LblCpf))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1))
                                    .addComponent(TxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BtnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtnAlterar)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtnDeletar))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(LblEmail)
                                    .addGap(35, 35, 35)
                                    .addComponent(TxtEmail))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(LblNome)
                                    .addGap(27, 27, 27)
                                    .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(83, 83, 83))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LblTelefone)
                        .addGap(18, 18, 18)
                        .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblId)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblNome))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEmail))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblTelefone))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCpf))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAlterar)
                    .addComponent(BtnDeletar)
                    .addComponent(BtnCriar))
                .addGap(23, 23, 23))
        );

        getAccessibleContext().setAccessibleName("Tela Cliente");

        setBounds(0, 0, 494, 464);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarActionPerformed
        Criar();
    }//GEN-LAST:event_BtnCriarActionPerformed

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void BtnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeletarActionPerformed
        Deletar();
    }//GEN-LAST:event_BtnDeletarActionPerformed

    private void TxtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPesquisaActionPerformed

    private void TxtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPesquisaKeyReleased
        pesquisar();
    }//GEN-LAST:event_TxtPesquisaKeyReleased

    private void TabClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabClientesMouseClicked
        mostrar();
    }//GEN-LAST:event_TabClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnCriar;
    private javax.swing.JButton BtnDeletar;
    private javax.swing.JLabel LblCpf;
    private javax.swing.JLabel LblEmail;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblNome;
    private javax.swing.JLabel LblPesquisar;
    private javax.swing.JLabel LblTelefone;
    private javax.swing.JTable TabClientes;
    private javax.swing.JTextField TxtCpf;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JTextField TxtTelefone;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
