/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayasUIpaket;

/**
 *
 * @author kaya
 */
public class dosyaGezginiPanelYedekServerUI extends javax.swing.JPanel {

    /**
     * Creates new form dosyaGezginiPanelYedekServerUI
     */
    public dosyaGezginiPanelYedekServerUI() {
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

        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable_dosyaRead = new javax.swing.JTable();
        jComboBox_fileTreeToWalk = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList_dizinler = new javax.swing.JList();
        jScrollPane14 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jButton_requestFileFromSoket = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel_dosyaReadSelection = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jList_dosyalar = new javax.swing.JList();
        jLabel_dosyaReadDurum = new javax.swing.JLabel();
        jButton_walkFileTree = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jSpinner_walkFileTreeDepth = new javax.swing.JSpinner();
        jComboBox_currentRootDirectory = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jTextField_currentRootDirectory = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jComboBox_gelenDMTNLL = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jButton_deleteDMTN = new javax.swing.JButton();
        jButton_deleteDMTN1 = new javax.swing.JButton();

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_dosyaRead.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        /////////// Bu kodu internetten buldum.
        // "jTable" ın "currentItemChanged" gibi bir olayı yokmuş.
        // Kendimizin yazması gerekiyor.

        //    ListSelectionModel rowSM2 = jTable_serverSocket.getSelectionModel();
        //rowSM2.addListSelectionListener(new ListSelectionListener() {
            //public void valueChanged(ListSelectionEvent e) {
                //ListSelectionModel rowSM2 = (ListSelectionModel)e.getSource();
                //int selectedIndex = rowSM.getMinSelectionIndex();
                // do something with selected index
                //kayaNetworkAbstractClass1.tableInfo2TextArea(mySunucuSoketInfos, jTable_serverSocket, jTextArea_descriptionServerSocket);
                //kayaNetworkAbstractClass1.tableInfo2TextArea(mySunucuSoketInfos, jTable_serverSocket,jTextArea_valueClientSocket, 1);
                /*try{
                    jEditorPane_descriptionServerSocket.setEditable(false);
                    String url4EditorPane="http://docs.oracle.com/javase/7/docs/api/java/net/ServerSocket.html";
                    kayaNetworkAbstractClass1.tableInfo2EditorPane(mySunucuSoketInfos, jTable_serverSocket, url4EditorPane, jEditorPane_descriptionServerSocket);
                    //jEditorPane_descriptionServerSocket.setPage(new URL("http://docs.oracle.com/"));
                }
                catch(Exception myexception)
                {
                    jEditorPane_descriptionServerSocket.setText(myexception.toString());
                }*/
                //}
            //});

    ///////////
    jScrollPane13.setViewportView(jTable_dosyaRead);

    jPanel2.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 564, 250));

    jComboBox_fileTreeToWalk.setEditable(true);
    jComboBox_fileTreeToWalk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
    jPanel2.add(jComboBox_fileTreeToWalk, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 40, 434, -1));

    jList_dizinler.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            jList_dizinlerValueChanged(evt);
        }
    });
    jList_dizinler.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jList_dizinlerKeyPressed(evt);
        }
    });
    jScrollPane6.setViewportView(jList_dizinler);

    jPanel2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 237, 262, 150));

    jScrollPane14.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Actionlar"));

    jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jButton_requestFileFromSoket.setText("requestFileFromSoket()");
    jButton_requestFileFromSoket.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_requestFileFromSoketActionPerformed(evt);
        }
    });
    jPanel9.add(jButton_requestFileFromSoket, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, -1));
    jPanel9.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));
    jPanel9.add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

    jLabel13.setText("Durum :");
    jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

    jButton3.setText("Visit Directory");
    jPanel9.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

    jScrollPane14.setViewportView(jPanel9);

    jPanel2.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 564, 140));

    jLabel11.setText("Selection : ");
    jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 449, -1, -1));
    jPanel2.add(jLabel_dosyaReadSelection, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 449, 483, -1));

    jList_dosyalar.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            jList_dosyalarValueChanged(evt);
        }
    });
    jScrollPane15.setViewportView(jList_dosyalar);

    jPanel2.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 237, 296, 150));
    jPanel2.add(jLabel_dosyaReadDurum, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 401, 503, -1));

    jButton_walkFileTree.setText("walkFileTree()");
    jButton_walkFileTree.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_walkFileTreeActionPerformed(evt);
        }
    });
    jPanel2.add(jButton_walkFileTree, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 78, -1, -1));

    jLabel15.setText("Files.walkFileTree(..., maxDepth,...)");
    jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 79, 252, 28));
    jPanel2.add(jSpinner_walkFileTreeDepth, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 77, 94, 32));
    jPanel2.add(jComboBox_currentRootDirectory, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 162, 331, -1));

    jLabel16.setText("root of currentDMTN :");
    jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 203, 163, 28));

    jTextField_currentRootDirectory.setEditable(false);
    jPanel2.add(jTextField_currentRootDirectory, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 203, 400, -1));

    jLabel19.setText("LL<LL<DMTN>> :");
    jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 162, 152, 28));

    jComboBox_gelenDMTNLL.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox_gelenDMTNLLActionPerformed(evt);
        }
    });
    jPanel2.add(jComboBox_gelenDMTNLL, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 126, 331, -1));

    jLabel20.setText("LL<DMTN> :");
    jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 126, 145, 28));

    jButton_deleteDMTN.setText("Del");
    jButton_deleteDMTN.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_deleteDMTNActionPerformed(evt);
        }
    });
    jPanel2.add(jButton_deleteDMTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(519, 125, 57, -1));

    jButton_deleteDMTN1.setText("Del");
    jButton_deleteDMTN1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_deleteDMTN1ActionPerformed(evt);
        }
    });
    jPanel2.add(jButton_deleteDMTN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(519, 161, 57, -1));

    jScrollPane5.setViewportView(jPanel2);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 615, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 574, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jList_dizinlerValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_dizinlerValueChanged
//        // TODO add your handling code here:
//        try {
//            String selectedChildName = (String) jList_dizinler.getSelectedValue();
//            dosyaBilgilerInfos = gnt.bilgilerDon4DosyaBilgilerTreeNode(gnt.getGelenDMTN(), selectedChildName);
//            uiSwingMetotlar.bilgiler2Table(dosyaBilgilerInfos, jTable_dosyaRead);
//            jLabel_dosyaReadSelection.setText(selectedChildName);
//        } catch (Exception ex) {
//        }
    }//GEN-LAST:event_jList_dizinlerValueChanged

    private void jList_dizinlerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList_dizinlerKeyPressed
//        // TODO add your handling code here:
//
//        // if the pressed key is not "ENTER", then leave method.
//        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
//            return;
//        }
//
//        int selectedDMNTIndex = jList_dizinler.getSelectedIndex() - 1;    // selectedDMNTIndex=-1 için bir dizin yukarı çık.
//        gnt.subDosyaBilgilerTreeNode(gnt.getCurrentDMTN(), selectedDMNTIndex);
//        uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar, jTextField_currentRootDirectory, jComboBox_currentRootDirectory);
//        //gnt.setCurrentDMTN(uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory));
//
//        //uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, gnt.getGelenDMTN(), jList_dizinler, jList_dosyalar);
//        //uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, gnt.getGelenDMTN(), jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory);
//        //uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory);
//        //uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, dmtnVariable, jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory);
    }//GEN-LAST:event_jList_dizinlerKeyPressed

    private void jButton_requestFileFromSoketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_requestFileFromSoketActionPerformed
//        // TODO add your handling code here:
//        List<String> requestedFiles = jList_dosyalar.getSelectedValuesList();
//        Iterator iterator = requestedFiles.iterator();
//        String requestedFileAbsolutPath = "";
//        String requestedFileStr = "";
//        while (iterator.hasNext()) {
//            requestedFileStr = (String) iterator.next();
//            tmpDMTN = gnt.findChildWithName(gnt.getGelenDMTN(), requestedFileStr);
//            requestedFileAbsolutPath = ((GelenNesneTipleri.DosyaBilgilerTreeNode) tmpDMTN.getUserObject()).toAbsolutePathString();
//            try {
//                kayaNetworkAbstractClass1.requestFileFromSoket(requestedFileAbsolutPath, MTSunucu.clientSoket);
//            } catch (IOException ex) {
//                Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
//                textArea_descriptionServerSocket.append(ex.toString() + "\n");
//            }
//        }
    }//GEN-LAST:event_jButton_requestFileFromSoketActionPerformed

    private void jList_dosyalarValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_dosyalarValueChanged
//        // TODO add your handling code here:
//        try {
//            String selectedChildName = (String) jList_dosyalar.getSelectedValue();
//            dosyaBilgilerInfos = gnt.bilgilerDon4DosyaBilgilerTreeNode(gnt.getGelenDMTN(), selectedChildName);
//            uiSwingMetotlar.bilgiler2Table(dosyaBilgilerInfos, jTable_dosyaRead);
//            jLabel_dosyaReadSelection.setText(selectedChildName);
//        } catch (Exception ex) {
//        }
    }//GEN-LAST:event_jList_dosyalarValueChanged

    private void jButton_walkFileTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_walkFileTreeActionPerformed
//        // TODO add your handling code here:
//        String kokDosyaPath = jComboBox_fileTreeToWalk.getSelectedItem().toString().trim();
//        int treeDepth = (Integer) jSpinner_walkFileTreeDepth.getValue();
//
//        String gidecekMesaj = kayaNetworkAbstractClass1.defaultMutableTreeNodeGetirStr + treeDepth + "," + kokDosyaPath;
//        try {
//            kayaNetworkAbstractClass1.write2Soket(MTSunucu.clientSoket, gidecekMesaj, false);
//        } catch (IOException ex) {
//            Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        // durum değişikliği belirt.
//        jLabel_dosyaReadDurum.setText(jLabel_dosyaReadDurumSTR2);
    }//GEN-LAST:event_jButton_walkFileTreeActionPerformed

    private void jComboBox_gelenDMTNLLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_gelenDMTNLLActionPerformed
//        // TODO add your handling code here:
//        int indeks = jComboBox_gelenDMTNLL.getSelectedIndex();
//        gnt.setGelenDMTNfromGelenDMTNLL(indeks);
//        uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar, jTextField_currentRootDirectory, jComboBox_currentRootDirectory);
    }//GEN-LAST:event_jComboBox_gelenDMTNLLActionPerformed

    private void jButton_deleteDMTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteDMTNActionPerformed
//        // TODO add your handling code here:
//        int indeks = jComboBox_gelenDMTNLL.getSelectedIndex();
//        gnt.removeGelenDMTNfromGelenDMTNLL(indeks);
//        uiSwingMetotlar.LinkedList2ComboBox(gnt.gelenDMTNLL, jComboBox_gelenDMTNLL);
//        uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar, jTextField_currentRootDirectory, jComboBox_currentRootDirectory);
    }//GEN-LAST:event_jButton_deleteDMTNActionPerformed

    private void jButton_deleteDMTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteDMTN1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_deleteDMTN1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_deleteDMTN;
    private javax.swing.JButton jButton_deleteDMTN1;
    private javax.swing.JButton jButton_requestFileFromSoket;
    private javax.swing.JButton jButton_walkFileTree;
    private javax.swing.JComboBox jComboBox_currentRootDirectory;
    private javax.swing.JComboBox jComboBox_fileTreeToWalk;
    private javax.swing.JComboBox jComboBox_gelenDMTNLL;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel_dosyaReadDurum;
    private javax.swing.JLabel jLabel_dosyaReadSelection;
    private javax.swing.JList jList_dizinler;
    private javax.swing.JList jList_dosyalar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jSpinner_walkFileTreeDepth;
    private javax.swing.JTable jTable_dosyaRead;
    private javax.swing.JTextField jTextField_currentRootDirectory;
    // End of variables declaration//GEN-END:variables
}