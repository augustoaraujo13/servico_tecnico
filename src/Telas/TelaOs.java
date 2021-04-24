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

    private void Pesquisar() {

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

    private void Mostrar() {

        int setar = TabPesquisaOs.getSelectedRow();

        TxtIdClienteOs.setText(TabPesquisaOs.getModel().getValueAt(setar, 0).toString().trim());

    }

    private void CriarOs() {

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
            st.setString(8, TxtIdClienteOs.getText().trim());

            if ((TxtEquipamento.getText().isEmpty()) || (TxtDefeito.getText().isEmpty())
                    || (TxtTecnico.getText().isEmpty())) {

                String informacao = "Preencha os campos obrigatórios!";
                JOptionPane.showMessageDialog(this, informacao);

            } else {

                String comcluido = "Ordem de serviço criada.";
                String resposta = "Na bancada";

                st.executeUpdate();

                JOptionPane.showMessageDialog(this, comcluido);

                TxtPesquisarOs.setText(null);
                CbSituação.setSelectedItem(resposta);
                TxtIdClienteOs.setText(null);
                TxtEquipamento.setText(null);
                TxtDefeito.setText(null);
                TxtServico.setText(null);
                TxtTecnico.setText(null);
                TxtValorTotal.setText(null);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    private void BuscarOs() {

        String mensagem = "Informe o numero da OS.";
        String numeroOs = JOptionPane.showInputDialog(mensagem);
        String buscaOs = "select * from os where os =" + numeroOs;

        try {

            st = conn.prepareStatement(buscaOs);
            rs = st.executeQuery();

            if (rs.next()) {
                TxtNumeroOs.setText(rs.getString(1));
                TxtDataHora.setText(rs.getString(2));
                String RadTipo = rs.getString(3);
                if (RadTipo.equals("OS")) {
                    RadOs.setSelected(true);
                    tipo = "OS";
                } else {
                    RadOrcamento.setSelected(true);
                    tipo = "Orçamento";
                }

                CbSituação.setSelectedItem(rs.getString(4));
                TxtEquipamento.setText(rs.getString(5));
                TxtDefeito.setText(rs.getString(6));
                TxtServico.setText(rs.getString(7));
                TxtTecnico.setText(rs.getString(8));
                TxtValorTotal.setText(rs.getString(9));
                TxtIdClienteOs.setText(rs.getString(10));

                BtnCriar.setEnabled(false);
                TxtPesquisarOs.setEnabled(false);
                TabPesquisaOs.setVisible(false);

            } else {
                String informacao = "OS não existe!";
                JOptionPane.showMessageDialog(this, informacao);

                TxtEquipamento.setText(null);
                TxtDefeito.setText(null);
                TxtServico.setText(null);
                TxtTecnico.setText(null);
                TxtValorTotal.setText(null);
                TxtIdClienteOs.setText(null);
                TxtDataHora.setText(null);
                TxtNumeroOs.setText(null);
                BtnCriar.setEnabled(true);
                TxtPesquisarOs.setEnabled(true);
                TabPesquisaOs.setVisible(true);

            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {

            JOptionPane.showMessageDialog(this, "OS Inválida!");
            // System.out.println(e);

            TxtEquipamento.setText(null);
            TxtDefeito.setText(null);
            TxtServico.setText(null);
            TxtTecnico.setText(null);
            TxtValorTotal.setText(null);
            TxtIdClienteOs.setText(null);
            TxtDataHora.setText(null);
            TxtNumeroOs.setText(null);
            BtnCriar.setEnabled(true);
            TxtPesquisarOs.setEnabled(true);
            TabPesquisaOs.setVisible(true);

        } catch (SQLException e2) {
            
            JOptionPane.showMessageDialog(this, e2);

            TxtEquipamento.setText(null);
            TxtDefeito.setText(null);
            TxtServico.setText(null);
            TxtTecnico.setText(null);
            TxtValorTotal.setText(null);
            TxtIdClienteOs.setText(null);
            TxtDataHora.setText(null);
            TxtNumeroOs.setText(null);
            BtnCriar.setEnabled(true);
            TxtPesquisarOs.setEnabled(true);
            TabPesquisaOs.setVisible(true);

        }

    }

    private void AlterarOs() {

        String alterandoOs = "update os set tipo =?, situação =?, "
                + "equipamento =?, defeito =?, servico =?,"
                + " tecnico =?, valor =? where os =?;";

        try {

            st = conn.prepareStatement(alterandoOs);

            st.setString(1, tipo);
            st.setString(2, CbSituação.getSelectedItem().toString());
            st.setString(3, TxtEquipamento.getText().trim());
            st.setString(4, TxtDefeito.getText().trim());
            st.setString(5, TxtServico.getText().trim());
            st.setString(6, TxtTecnico.getText().trim());
            st.setString(7, TxtValorTotal.getText().trim().replace(",", "."));
            st.setString(8, TxtNumeroOs.getText().trim());

            if ((TxtEquipamento.getText().isEmpty()) || (TxtDefeito.getText().isEmpty())
                    || (TxtTecnico.getText().isEmpty())) {

                String informacao = "Preencha os campos obrigatórios!";
                JOptionPane.showMessageDialog(this, informacao);

            } else {

                String comcluido = "Ordem de serviço alterada!";

                st.executeUpdate();

                JOptionPane.showMessageDialog(this, comcluido);

                TxtEquipamento.setText(null);
                TxtDefeito.setText(null);
                TxtServico.setText(null);
                TxtTecnico.setText(null);
                TxtValorTotal.setText(null);
                TxtIdClienteOs.setText(null);
                TxtDataHora.setText(null);
                TxtNumeroOs.setText(null);
                BtnCriar.setEnabled(true);
                TxtPesquisarOs.setEnabled(true);
                TabPesquisaOs.setVisible(true);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    private void DeletarOs() {

        String confirmando = "Deseja deletar esta OS?";
        String confirmando2 = "Atenção!";

        int confirmar = JOptionPane.showConfirmDialog(null, confirmando, confirmando, JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {

            String deletando = "delete from os where os=?;";

            try {
                st = conn.prepareStatement(deletando);
                st.setString(1, TxtNumeroOs.getText());

                if (TxtNumeroOs.getText().isEmpty()) {

                    String informacao = "Preencha o campo número OS, "
                            + "para excluir a ordem de serviço!";

                    JOptionPane.showMessageDialog(this, informacao);

                } else {

                    String comcluido = "OS excluído com sucesso!";

                    JOptionPane.showMessageDialog(this, comcluido);

                    st.executeUpdate();

                    TxtEquipamento.setText(null);
                    TxtDefeito.setText(null);
                    TxtServico.setText(null);
                    TxtTecnico.setText(null);
                    TxtValorTotal.setText(null);
                    TxtIdClienteOs.setText(null);
                    TxtDataHora.setText(null);
                    TxtNumeroOs.setText(null);
                    BtnCriar.setEnabled(true);
                    TxtPesquisarOs.setEnabled(true);
                    TabPesquisaOs.setVisible(true);

                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                //System.out.println(e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Houve um erro, tente novamente!");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        LblNumeroOs = new javax.swing.JLabel();
        TxtNumeroOs = new javax.swing.JTextField();
        RadOrcamento = new javax.swing.JRadioButton();
        RadOs = new javax.swing.JRadioButton();
        LblDataHora = new javax.swing.JLabel();
        TxtDataHora = new javax.swing.JTextField();
        LabSituação = new javax.swing.JLabel();
        CbSituação = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        LblPesquisarOs = new javax.swing.JLabel();
        TxtPesquisarOs = new javax.swing.JTextField();
        LblIdOs = new javax.swing.JLabel();
        TxtIdClienteOs = new javax.swing.JTextField();
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
        BtnBuscar = new javax.swing.JButton();

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

        LblNumeroOs.setText("Número OS:");

        TxtNumeroOs.setEnabled(false);

        buttonGroup1.add(RadOrcamento);
        RadOrcamento.setText("Orçamento");
        RadOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadOrcamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadOs);
        RadOs.setText("OS");
        RadOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadOsActionPerformed(evt);
            }
        });

        LblDataHora.setText("Data e hora:");

        TxtDataHora.setEnabled(false);
        TxtDataHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDataHoraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblNumeroOs)
                            .addComponent(LblDataHora))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNumeroOs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtDataHora)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(RadOs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(RadOrcamento, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtNumeroOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblNumeroOs))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblDataHora)
                    .addComponent(TxtDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RadOs)
                    .addComponent(RadOrcamento)))
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

        LblIdOs.setText("*Id Cliente:");

        TxtIdClienteOs.setEnabled(false);

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

        LblTecnico.setText("*Técnico:");

        TxtTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTecnicoActionPerformed(evt);
            }
        });

        LblValorTotal.setText("Valor total:");

        TxtValorTotal.setText("0");

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

        BtnBuscar.setBackground(new java.awt.Color(0, 153, 255));
        BtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        BtnBuscar.setText("Buscar");
        BtnBuscar.setToolTipText("Busca a ordem de serviço");
        BtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblPesquisarOs)
                            .addComponent(LblIdOs))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(TxtIdClienteOs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtPesquisarOs, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblEquipamento)
                            .addComponent(jLabel1)
                            .addComponent(LblValorTotal)
                            .addComponent(BtnCriar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BtnBuscar)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblPesquisarOs)
                            .addComponent(TxtPesquisarOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtIdClienteOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblIdOs))))
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
                    .addComponent(BtnImprimir)
                    .addComponent(BtnBuscar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(LabSituação)
                        .addGap(18, 18, 18)
                        .addComponent(CbSituação, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        CriarOs();
    }//GEN-LAST:event_BtnCriarActionPerformed

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        AlterarOs();
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void BtnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeletarActionPerformed
        DeletarOs();
    }//GEN-LAST:event_BtnDeletarActionPerformed

    private void BtnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnImprimirActionPerformed

    private void TxtPesquisarOsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPesquisarOsKeyReleased
        Pesquisar();
    }//GEN-LAST:event_TxtPesquisarOsKeyReleased

    private void TabPesquisaOsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPesquisaOsMouseClicked
        Mostrar();
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

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        BuscarOs();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void TxtDataHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDataHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDataHoraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnCriar;
    private javax.swing.JButton BtnDeletar;
    private javax.swing.JButton BtnImprimir;
    private javax.swing.JComboBox<String> CbSituação;
    private javax.swing.JLabel LabSituação;
    private javax.swing.JLabel LblDataHora;
    private javax.swing.JLabel LblDefeito;
    private javax.swing.JLabel LblEquipamento;
    private javax.swing.JLabel LblIdOs;
    private javax.swing.JLabel LblNumeroOs;
    private javax.swing.JLabel LblPesquisarOs;
    private javax.swing.JLabel LblTecnico;
    private javax.swing.JLabel LblValorTotal;
    private javax.swing.JRadioButton RadOrcamento;
    private javax.swing.JRadioButton RadOs;
    private javax.swing.JTable TabPesquisaOs;
    private javax.swing.JTextField TxtDataHora;
    private javax.swing.JTextField TxtDefeito;
    private javax.swing.JTextField TxtEquipamento;
    private javax.swing.JTextField TxtIdClienteOs;
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
