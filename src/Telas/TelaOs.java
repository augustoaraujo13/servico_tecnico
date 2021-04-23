package Telas;

import ConexaoBanco.Conexao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaOs extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    private String tipo;
    private String situacao;

    public TelaOs() {
        initComponents();

        conn = Conexao.abrirBanco();

    }

    private void pesquisar() {

        String pesquisa = "select id_cliente as Id, nome as Nome, email as Email from clientes where nome like ?";
        String pesquisa2 = "%";

        try {

            st = conn.prepareStatement(pesquisa);

            st.setString(1, TxtPesquisarOs.getText().trim() + pesquisa2);
            rs = st.executeQuery();

            TabPesquisaOs.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }

    }

    private void mostrar() {

        int setar = TabPesquisaOs.getSelectedRow();

        TxtIdOs.setText(TabPesquisaOs.getModel().getValueAt(setar, 0).toString().trim());

    }

    private void criarOs() {

        String criando = "insert into os (situação, tipo, equipamento, defeito, "
                + "servico, tecnico, valor, id_cli) values(?,?,?,?,?,?,?,?);";

        try {

            st = conn.prepareStatement(criando);

            st.setString(1, tipo);
            st.setString(2, CbSituação.getSelectedItem().toString());
            st.setString(3, TxtEquipamento.getText().trim());
            st.setString(4, TxtDefeito.getText().trim());
            st.setString(5, TxtServico.getText().trim());
            st.setString(6, TxtTecnico.getText().trim());
            st.setString(7, TxtValorTotal.getText().trim().replace(",", "."));
            st.setString(8, TxtIdOs.getText().trim());

            if ((TxtEquipamento.getText().isEmpty()) || (TxtDefeito.getText().isEmpty())
                    || (TxtTecnico.getText().isEmpty())) {

                String informacao = "Preencha os campos obrigatórios!";
                JOptionPane.showMessageDialog(this, informacao);

            } else {

                String comcluido = "Ordem de serviço criada!";
                String resposta = "";
                String resposta2 = "Na bancada";

                st.executeUpdate();
                
                JOptionPane.showMessageDialog(this, comcluido);
                
                TxtPesquisarOs.setText(resposta);
                CbSituação.setSelectedItem(resposta2);
                TxtIdOs.setText(resposta);
                TxtEquipamento.setText(resposta);
                TxtDefeito.setText(resposta);
                TxtServico.setText(resposta);
                TxtTecnico.setText(resposta);
                TxtValorTotal.setText(resposta);
                

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        LblNumeroOs = new javax.swing.JLabel();
        TxtNumeroOs = new javax.swing.JTextField();
        LblIdCliente = new javax.swing.JLabel();
        TxtIdCliente = new javax.swing.JTextField();
        RadOrcamento = new javax.swing.JRadioButton();
        RadOs = new javax.swing.JRadioButton();
        LabSituação = new javax.swing.JLabel();
        CbSituação = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        LblPesquisarOs = new javax.swing.JLabel();
        TxtPesquisarOs = new javax.swing.JTextField();
        LblIdOs = new javax.swing.JLabel();
        TxtIdOs = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabPesquisaOs = new javax.swing.JTable();
        LblEquipamento = new javax.swing.JLabel();
        TxtEquipamento = new javax.swing.JTextField();
        LblDefeito = new javax.swing.JLabel();
        TxtDefeito = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TxtServico = new javax.swing.JTextField();
        LblTecnico = new javax.swing.JLabel();
        TxtTecnico = new javax.swing.JTextField();
        LblValorTotal = new javax.swing.JLabel();
        TxtValorTotal = new javax.swing.JTextField();
        BtnCriar = new javax.swing.JButton();
        BtnAlterar = new javax.swing.JButton();
        BtnDeletar = new javax.swing.JButton();
        BtnImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tela ordem de serviço");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LblNumeroOs.setText("Numero OS:");

        TxtNumeroOs.setEnabled(false);

        LblIdCliente.setText("Id cliente:");

        TxtIdCliente.setEnabled(false);

        buttonGroup1.add(RadOrcamento);
        RadOrcamento.setText("Orçamento");
        RadOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadOrcamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadOs);
        RadOs.setText("Ordem de serviço");
        RadOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadOsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblNumeroOs)
                    .addComponent(LblIdCliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtNumeroOs, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(TxtIdCliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RadOs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RadOrcamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtNumeroOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblNumeroOs))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RadOs)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(LblIdCliente))))
                    .addComponent(RadOrcamento))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        LabSituação.setText("Situação:");

        CbSituação.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na bancada", "Entrega ok", "Orçamento reprovado", "Aguardando aprovação", "Aguardando peças", "Abandonado pela cliente", "Retornou", " ", " " }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        jPanel2.setPreferredSize(new java.awt.Dimension(470, 450));

        LblPesquisarOs.setText("Pesquisar:");

        TxtPesquisarOs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtPesquisarOsKeyReleased(evt);
            }
        });

        LblIdOs.setText("*Id:");

        TxtIdOs.setEnabled(false);

        TabPesquisaOs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Email"
            }
        ));
        TabPesquisaOs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPesquisaOsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabPesquisaOs);

        LblEquipamento.setText("*Equipamento:");

        LblDefeito.setText("*Defeito:");

        jLabel1.setText("Serviço:");

        LblTecnico.setText("*Tecnico:");

        TxtTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTecnicoActionPerformed(evt);
            }
        });

        LblValorTotal.setText("Valor total:");

        BtnCriar.setBackground(new java.awt.Color(0, 204, 0));
        BtnCriar.setForeground(new java.awt.Color(255, 255, 255));
        BtnCriar.setText("Criar");
        BtnCriar.setToolTipText("Cria uma nova ordem de serviço");
        BtnCriar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarActionPerformed(evt);
            }
        });

        BtnAlterar.setBackground(new java.awt.Color(255, 153, 0));
        BtnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAlterar.setText("Alterar");
        BtnAlterar.setToolTipText("Altera a ordem de serviço");
        BtnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        BtnDeletar.setBackground(new java.awt.Color(255, 0, 0));
        BtnDeletar.setForeground(new java.awt.Color(255, 255, 255));
        BtnDeletar.setText("Deletar");
        BtnDeletar.setToolTipText("Cuidado!! Deleta a ordem de serviço");
        BtnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeletarActionPerformed(evt);
            }
        });

        BtnImprimir.setBackground(new java.awt.Color(255, 0, 255));
        BtnImprimir.setForeground(new java.awt.Color(255, 255, 255));
        BtnImprimir.setText("Imprimir");
        BtnImprimir.setToolTipText("Cuidado!! Deleta a ordem de serviço");
        BtnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(LblPesquisarOs))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(LblIdOs)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtIdOs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtPesquisarOs, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblEquipamento)
                            .addComponent(jLabel1)
                            .addComponent(LblValorTotal))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BtnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnAlterar)
                                .addGap(18, 18, 18)
                                .addComponent(BtnDeletar)
                                .addGap(18, 18, 18)
                                .addComponent(BtnImprimir))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtEquipamento)
                                    .addComponent(TxtServico)
                                    .addComponent(TxtValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LblDefeito)
                                    .addComponent(LblTecnico))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtDefeito)
                                    .addComponent(TxtTecnico, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblPesquisarOs)
                            .addComponent(TxtPesquisarOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblIdOs)
                            .addComponent(TxtIdOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LblEquipamento)
                        .addComponent(TxtEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblDefeito)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblTecnico)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblValorTotal))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCriar)
                    .addComponent(BtnAlterar)
                    .addComponent(BtnDeletar)
                    .addComponent(BtnImprimir))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(LabSituação)
                                .addGap(18, 18, 18)
                                .addComponent(CbSituação, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CbSituação, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabSituação))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 494, 499);
    }// </editor-fold>//GEN-END:initComponents

    private void TxtTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTecnicoActionPerformed

    private void BtnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarActionPerformed
         criarOs();
    }//GEN-LAST:event_BtnCriarActionPerformed

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        // alterar();
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void BtnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeletarActionPerformed
        // Deletar();
    }//GEN-LAST:event_BtnDeletarActionPerformed

    private void BtnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnImprimirActionPerformed

    private void TxtPesquisarOsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPesquisarOsKeyReleased
        pesquisar();
    }//GEN-LAST:event_TxtPesquisarOsKeyReleased

    private void TabPesquisaOsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPesquisaOsMouseClicked
        mostrar();
    }//GEN-LAST:event_TabPesquisaOsMouseClicked

    private void RadOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadOrcamentoActionPerformed
        tipo = "Orçamento";
    }//GEN-LAST:event_RadOrcamentoActionPerformed

    private void RadOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadOsActionPerformed
        tipo = "OS";
    }//GEN-LAST:event_RadOsActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        RadOrcamento.setSelected(true);
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnCriar;
    private javax.swing.JButton BtnDeletar;
    private javax.swing.JButton BtnImprimir;
    private javax.swing.JComboBox<String> CbSituação;
    private javax.swing.JLabel LabSituação;
    private javax.swing.JLabel LblDefeito;
    private javax.swing.JLabel LblEquipamento;
    private javax.swing.JLabel LblIdCliente;
    private javax.swing.JLabel LblIdOs;
    private javax.swing.JLabel LblNumeroOs;
    private javax.swing.JLabel LblPesquisarOs;
    private javax.swing.JLabel LblTecnico;
    private javax.swing.JLabel LblValorTotal;
    private javax.swing.JRadioButton RadOrcamento;
    private javax.swing.JRadioButton RadOs;
    private javax.swing.JTable TabPesquisaOs;
    private javax.swing.JTextField TxtDefeito;
    private javax.swing.JTextField TxtEquipamento;
    private javax.swing.JTextField TxtIdCliente;
    private javax.swing.JTextField TxtIdOs;
    private javax.swing.JTextField TxtNumeroOs;
    private javax.swing.JTextField TxtPesquisarOs;
    private javax.swing.JTextField TxtServico;
    private javax.swing.JTextField TxtTecnico;
    private javax.swing.JTextField TxtValorTotal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
