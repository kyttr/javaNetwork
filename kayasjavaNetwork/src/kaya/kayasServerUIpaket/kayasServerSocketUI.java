/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * kayasServerSocketUI.java
 *
 * Created on Feb 27, 2012, 11:18:01 PM
 */
package kaya.kayasServerUIpaket;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import kaya.kayaClassPaket.*;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author kaya
 */
public class kayasServerSocketUI extends javax.swing.JFrame implements OlayDinleyenlerInterface {

    private static String jLabel_dosyaReadDurumSTR1 = "'OlayOlmus()' thrown, gnt.getGelenDMTN() != null, response arrived";
    private static String jLabel_dosyaReadDurumSTR2 = "'write2Soket()'  executed, waiting ...";
    private static String jLabel_dosyaReadDurumSTR3 = "Dosya geldi : ";
    // global değişkenler
    ServerSocket mySunucuSoket;
    LinkedList<LinkedList<String>> mySunucuSoketInfos;
    LinkedList<LinkedList<String>> dosyaBilgilerInfos;
    //   LinkedList<Integer> paramsforServerSocket;
    kayasMultiThrdServer MTSunucu;
    Thread clientDinleThread;
    GelenNesneTipleri gnt;
    DefaultMutableTreeNode tmpDMTN;

    public void OlayOlmus(OlayNesnesi olay) {
        /*
         * http://www.javaworld.com/javaworld/javaqa/2002-03/01-qa-0315-happyevent.html?page=2
         */
        Object nesne = olay.kaynakNesne();
        try {
            //gnt=new GelenNesneTipleri();
            //gnt = new GelenNesneTipleri(nesne);
            gnt.addGelenObje(nesne);
        } catch (IOException ex) {
            Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
        }


        /*
         * if (nesne instanceof BufferedImage) { //
         * gnt.gelenResim=(BufferedImage) nesne;
         * kayaNetworkAbstractClass1.bufferedImage2JLabel(gnt.gelenResim,
         * jLabel_screenShot); } else if (nesne instanceof String) { //
         * gnt.gelenStr=(String) nesne;
         * jTextArea_readFromSocket.append(gnt.gelenStr + "\n"); } else if
         * (nesne instanceof File) { // gnt.gelenDosya=(File) nesne;
         * jTextArea_readFromSocket.append("Dosya geldi : " +
         * gnt.gelenDosya.getAbsolutePath() + "\n"); } else if (nesne instanceof
         * DefaultMutableTreeNode) { //
         * gnt.setGelenDMTN((DefaultMutableTreeNode) nesne);
         * gnt.gelenDMTN2DosyaBilgilerTreeNode();
         *
         * //uiSwingMetotlar.dosyaBilgilerTree2List(gnt.gelenDMTN,
         * jList_dizinler, jList_dosyalar);
         * uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getGelenDMTN(),
         * jList_dizinler, jList_dosyalar); }
         */

        if (!gnt.gelenStr.isEmpty()) {
            jTextArea_readFromSocket.append(gnt.gelenStr + "\n");
            //For "jTextArea_readFromSocket", I want to see the most recent message coming from the other socket, so I want the bottom view
            uiSwingMetotlar.setViewOfJTextArea2Bottom(jTextArea_readFromSocket);
        } else if (gnt.gelenDosya != null) {
            jTextArea_readFromSocket.append("Dosya geldi : " + gnt.gelenDosya.getAbsolutePath() + "\n");
            jLabel_dosyaReadDurum.setText(jLabel_dosyaReadDurumSTR3 + gnt.gelenDosya.getAbsolutePath());
        } else if (gnt.gelenResim != null) {
            uiSwingMetotlar.bufferedImage2JLabel(gnt.gelenResim, jLabel_screenShot);
        } else if (gnt.getGelenDMTN() != null) {
            //uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getGelenDMTN(), jList_dizinler, jList_dosyalar);
            //   uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getGelenDMTN(), jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory);
            //      uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory);

            //dmtnVariable = (DefaultMutableTreeNode) GenelMetotlar.derinCopy(gnt.getGelenDMTN());
            //dmtnVariable = gnt.getCurrentDMTN();
            jLabel_dosyaReadDurum.setText(jLabel_dosyaReadDurumSTR1);
            uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar, jTextField_currentRootDirectory, jComboBox_currentRootDirectory);
            uiSwingMetotlar.LinkedList2ComboBox(gnt.gelenDMTNLL, jComboBox_gelenDMTNLL);
        }
    }

    /**
     * Creates new form kayasServerSocketUI
     */
    public kayasServerSocketUI() {
        initComponents();
        //Q - Her buton için : "enter" ile buton aktifleşir.
        //Component[] bilesenDizi=this.getComponents(); // işe yaramıyor.

        SwingAbsractClass.enterPressesWhenFocused2(jButton_accept);
        SwingAbsractClass.enterPressesWhenFocused2(jButton_kapat);
        SwingAbsractClass.enterPressesWhenFocused2(jButton_initializeAndSetServerSocket);
        SwingAbsractClass.enterPressesWhenFocused2(jButton_readFromSocket);
        SwingAbsractClass.enterPressesWhenFocused2(jButton_setVisible);
        SwingAbsractClass.enterPressesWhenFocused2(jButton_write2Socket);
        SwingAbsractClass.enterPressesWhenFocused2(jButton_screenShotGetir);

        // intialize "gnt"
        gnt = new GelenNesneTipleri(null);

        //Q         //
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame_dosyaGezgini = new javax.swing.JFrame();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jComboBox_fileTreeToWalk = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList_dizinler = new javax.swing.JList();
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
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable_dosyaRead = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jButton_requestFileFromSoket = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane_descriptionClientSocket = new javax.swing.JTabbedPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea_descriptionServerSocket = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jEditorPane_descriptionServerSocket = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea_descriptionServerSocket = new java.awt.TextArea();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea_valueClientSocket = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable_serverSocket = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea_readFromSocket = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea_written2Socket = new javax.swing.JTextArea();
        jTextField_write2Socket = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton_write2Socket = new javax.swing.JButton();
        jButton_readFromSocket = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton_screenShotGetir = new javax.swing.JButton();
        jSpinner_timerDelay = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jButton_periodicScreenShotGetir = new javax.swing.JButton();
        jButton_ScreenShotAl = new javax.swing.JButton();
        jComboBox_formatName = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jButton_timerScreenShotDurdur = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLabel_screenShot = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        jButton_dosyaGezginiFrameBaslat = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jSpinner_timeOut = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        jSpinner_port = new javax.swing.JSpinner();
        jSpinner_connectionTime = new javax.swing.JSpinner();
        jComboBox_on = new javax.swing.JComboBox();
        jSpinner_latency = new javax.swing.JSpinner();
        jSpinner_backlog = new javax.swing.JSpinner();
        jSpinner_size = new javax.swing.JSpinner();
        jSpinner_bandwidth = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton_accept = new javax.swing.JButton();
        jButton_setVisible = new javax.swing.JButton();
        jButton_initializeAndSetServerSocket = new javax.swing.JButton();
        jButton_kapat = new javax.swing.JButton();

        jFrame_dosyaGezgini.setTitle("dosyaGezgini");

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox_fileTreeToWalk.setEditable(true);
        jComboBox_fileTreeToWalk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        jPanel2.add(jComboBox_fileTreeToWalk, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 40, 434, -1));

        jList_dizinler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList_dizinlerMouseClicked(evt);
            }
        });
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

        jPanel2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 237, 270, 250));

        jLabel11.setText("Selection : ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 528, -1, 20));
        jPanel2.add(jLabel_dosyaReadSelection, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 480, 20));

        jList_dosyalar.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_dosyalarValueChanged(evt);
            }
        });
        jScrollPane15.setViewportView(jList_dosyalar);

        jPanel2.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 237, 280, 250));
        jPanel2.add(jLabel_dosyaReadDurum, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 480, 20));

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

        jLabel12.setText("Durum :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        jScrollPane17.setViewportView(jPanel2);

        jPanel10.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 600, 558));

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
    jScrollPane18.setViewportView(jTable_dosyaRead);

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

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                .addComponent(jScrollPane18))
            .addContainerGap())
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(30, Short.MAX_VALUE))
    );

    jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 12, -1, 558));

    javax.swing.GroupLayout jFrame_dosyaGezginiLayout = new javax.swing.GroupLayout(jFrame_dosyaGezgini.getContentPane());
    jFrame_dosyaGezgini.getContentPane().setLayout(jFrame_dosyaGezginiLayout);
    jFrame_dosyaGezginiLayout.setHorizontalGroup(
        jFrame_dosyaGezginiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 1221, Short.MAX_VALUE)
        .addGroup(jFrame_dosyaGezginiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame_dosyaGezginiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );
    jFrame_dosyaGezginiLayout.setVerticalGroup(
        jFrame_dosyaGezginiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 582, Short.MAX_VALUE)
        .addGroup(jFrame_dosyaGezginiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame_dosyaGezginiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );

    setTitle("Server (sunucu) Soket");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

    jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    jTabbedPane1.setPreferredSize(new java.awt.Dimension(700, 631));

    jTextArea_descriptionServerSocket.setColumns(20);
    jTextArea_descriptionServerSocket.setLineWrap(true);
    jTextArea_descriptionServerSocket.setRows(5);
    jScrollPane7.setViewportView(jTextArea_descriptionServerSocket);

    jTabbedPane_descriptionClientSocket.addTab("String (Verbose - Swing)", jScrollPane7);

    jEditorPane_descriptionServerSocket.setEditable(false);
    jScrollPane8.setViewportView(jEditorPane_descriptionServerSocket);

    jTabbedPane_descriptionClientSocket.addTab("http://docs.oracle.com/", jScrollPane8);

    jScrollPane2.setViewportView(textArea_descriptionServerSocket);

    jTabbedPane_descriptionClientSocket.addTab("Verbose - AWT", jScrollPane2);

    jLabel17.setText("Değer (Value)");

    jTextArea_valueClientSocket.setColumns(20);
    jTextArea_valueClientSocket.setLineWrap(true);
    jTextArea_valueClientSocket.setRows(5);
    jScrollPane9.setViewportView(jTextArea_valueClientSocket);

    jLabel18.setText("Açıklama (Description)");

    jTable_serverSocket.setModel(new javax.swing.table.DefaultTableModel(
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

    ListSelectionModel rowSM = jTable_serverSocket.getSelectionModel();
    rowSM.addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel rowSM = (ListSelectionModel)e.getSource();
            //int selectedIndex = rowSM.getMinSelectionIndex();
            // do something with selected index
            uiSwingMetotlar.tableInfo2TextArea(mySunucuSoketInfos, jTable_serverSocket, jTextArea_descriptionServerSocket);
            uiSwingMetotlar.tableInfo2TextArea(mySunucuSoketInfos, jTable_serverSocket,jTextArea_valueClientSocket, 1);
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
        }
    });

    ///////////
    jScrollPane10.setViewportView(jTable_serverSocket);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jTabbedPane_descriptionClientSocket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(350, 350, 350))
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel17)
            .addGap(7, 7, 7)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel18)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTabbedPane_descriptionClientSocket, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jTabbedPane1.addTab("Property-Value-Description", jPanel3);

    jLabel21.setText("readFromSocket");

    jTextArea_readFromSocket.setColumns(20);
    jTextArea_readFromSocket.setLineWrap(true);
    jTextArea_readFromSocket.setRows(5);
    jScrollPane11.setViewportView(jTextArea_readFromSocket);

    jLabel22.setText("written2Socket");

    jTextArea_written2Socket.setColumns(20);
    jTextArea_written2Socket.setLineWrap(true);
    jTextArea_written2Socket.setRows(5);
    jScrollPane12.setViewportView(jTextArea_written2Socket);

    jLabel23.setText("To be Written to Socket");

    jButton_write2Socket.setText("PrintWriter.println(jTextField_write2Socket.getText())");
    jButton_write2Socket.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_write2SocketActionPerformed(evt);
        }
    });

    jButton_readFromSocket.setText("BufferedReader.readLine()");
    jButton_readFromSocket.setEnabled(false);

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addComponent(jTextField_write2Socket, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addComponent(jButton_write2Socket, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addComponent(jButton_readFromSocket, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
            .addContainerGap())
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel21)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton_readFromSocket)
            .addGap(18, 18, 18)
            .addComponent(jLabel23)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jTextField_write2Socket, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton_write2Socket)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel22)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
            .addContainerGap())
    );

    jTabbedPane1.addTab("Reading from and Writing to a Socket", jPanel6);

    jButton_screenShotGetir.setText("screenShotGetir() + bufferefImage2JLabel()");
    jButton_screenShotGetir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_screenShotGetirActionPerformed(evt);
        }
    });

    jSpinner_timerDelay.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jLabel10.setText("Timer.setDelay(delay);");

    jButton_periodicScreenShotGetir.setText("periodicScreenShotGetir()");
    jButton_periodicScreenShotGetir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_periodicScreenShotGetirActionPerformed(evt);
        }
    });

    jButton_ScreenShotAl.setText("ScreenShotAl()");
    jButton_ScreenShotAl.setEnabled(false);
    jButton_ScreenShotAl.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_ScreenShotAlActionPerformed(evt);
        }
    });

    jComboBox_formatName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "png", "jpg", "gif", "bmp" }));

    jLabel24.setText("formatName");

    jButton_timerScreenShotDurdur.setText("timerScreenShotDurdur()");
    jButton_timerScreenShotDurdur.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_timerScreenShotDurdurActionPerformed(evt);
        }
    });

    jLabel_screenShot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel_screenShot.setText("Buraya ScreenShot gelecek.");
    jScrollPane3.setViewportView(jLabel_screenShot);

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton_screenShotGetir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jSpinner_timerDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton_periodicScreenShotGetir, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jComboBox_formatName, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_timerScreenShotDurdur, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                .addComponent(jButton_ScreenShotAl, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))))
                .addComponent(jScrollPane3))
            .addContainerGap())
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton_screenShotGetir)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(jSpinner_timerDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_periodicScreenShotGetir))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton_ScreenShotAl)
                .addComponent(jLabel24)
                .addComponent(jComboBox_formatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton_timerScreenShotDurdur)
            .addGap(17, 17, 17))
    );

    jTabbedPane1.addTab("ScreenShot", jPanel8);

    jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFileChooser1ActionPerformed(evt);
        }
    });
    jScrollPane4.setViewportView(jFileChooser1);

    jTabbedPane1.addTab("Dosya Write", jScrollPane4);

    jButton_dosyaGezginiFrameBaslat.setText("jFrame_dosyaGezgini.setVisible(true)");
    jButton_dosyaGezginiFrameBaslat.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_dosyaGezginiFrameBaslatActionPerformed(evt);
        }
    });
    jScrollPane5.setViewportView(jButton_dosyaGezginiFrameBaslat);

    jTabbedPane1.addTab("Dosya Read", jScrollPane5);
    jTabbedPane1.addTab("tab6", jScrollPane16);

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("ServerSocket Kur"));

    jSpinner_timeOut.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(-1), Integer.valueOf(-1), null, Integer.valueOf(1)));

    jSpinner_port.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(-1), Integer.valueOf(-1), null, Integer.valueOf(1)));

    jSpinner_connectionTime.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(-1), Integer.valueOf(-1), null, Integer.valueOf(1)));

    jComboBox_on.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "False", "True" }));

    jSpinner_latency.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(-1), Integer.valueOf(-1), null, Integer.valueOf(1)));

    jSpinner_backlog.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(-1), Integer.valueOf(-1), null, Integer.valueOf(1)));

    jSpinner_size.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(-1), Integer.valueOf(-1), null, Integer.valueOf(1)));

    jSpinner_bandwidth.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(-1), Integer.valueOf(-1), null, Integer.valueOf(1)));

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING))
                    .addContainerGap(47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSpinner_timeOut, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner_size, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner_bandwidth, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner_latency, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner_connectionTime, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner_backlog, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner_port, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBox_on, javax.swing.GroupLayout.Alignment.LEADING, 0, 75, Short.MAX_VALUE))
                    .addContainerGap())))
    );

    jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jSeparator1, jSeparator2, jSeparator3});

    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addComponent(jSpinner_port, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSpinner_backlog, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
            .addGap(32, 32, 32)
            .addComponent(jSpinner_connectionTime, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSpinner_latency, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSpinner_bandwidth, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSpinner_size, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jComboBox_on, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSpinner_timeOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jSpinner_bandwidth, jSpinner_latency, jSpinner_size, jSpinner_timeOut});

    jLabel1.setText("ServerSocket(int port) ");

    jLabel2.setText("ServerSocket(int port, int backlog)");

    jLabel3.setText("<html>ServerSocket(int port,<br> int backlog, InetAddress bindAddr)</html>");

    jLabel4.setText("bind(SocketAddress endpoint)");

    jLabel5.setText("<html>bind(SocketAddress endpoint,<br> int backlog)</html>");

    jLabel6.setText("<html>setPerformancePreferences<br>(int connectionTime, int latency, int bandwidth)</html>");

    jLabel7.setText("setReceiveBufferSize(int size)");

    jLabel8.setText("setReuseAddress(boolean on)");

    jLabel9.setText("setSoTimeout(int timeout)");

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(33, 33, 33)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jButton_accept.setText("ServerSocket.accept()");
    jButton_accept.setEnabled(false);
    jButton_accept.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_acceptActionPerformed(evt);
        }
    });

    jButton_setVisible.setText("kayasClientSocket.setVisible(true)");
    jButton_setVisible.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_setVisibleActionPerformed(evt);
        }
    });

    jButton_initializeAndSetServerSocket.setText("initializeAndSet ServerSocket");
    jButton_initializeAndSetServerSocket.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_initializeAndSetServerSocketActionPerformed(evt);
        }
    });

    jButton_kapat.setText("kayasMultiThrdServer.kapat()");
    jButton_kapat.setEnabled(false);
    jButton_kapat.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_kapatActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton_setVisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButton_initializeAndSetServerSocket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_accept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_kapat, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))))
            .addContainerGap(28, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton_initializeAndSetServerSocket)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton_accept)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton_kapat)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton_setVisible)
            .addContainerGap(27, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(28, 28, 28)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
            .addGap(13, 13, 13))
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
    );

    jScrollPane1.setViewportView(jPanel7);

    getContentPane().add(jScrollPane1);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_acceptActionPerformed
        /*
         * String gelenMesaj; try { // TODO add your handling code here:
         * gelenMesaj=
         * kayaNetworkAbstractClass1.acceptSoketAndSayWelcome(mySunucuSoket);
         * if(gelenMesaj.isEmpty()) { jTextArea_readFromSocket.append("YOK
         * ETWAS"); } else { jTextArea_readFromSocket.append(gelenMesaj); } }
         * catch (Exception ex) {
         * Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE,
         * null, ex); textArea_descriptionServerSocket.setText(ex.toString()); }
         *
         */
        /*
         * kayasMultiThrdServer MTSunucu=new
         * kayasMultiThrdServer(mySunucuSoket,jTextArea_readFromSocket);
         * MTSunucu.clientDinle(); // MTSunucu.finalize();
         */

        // kayasMultiThrdServer.finalize() butonu etkinleşsin.
        jButton_kapat.setEnabled(true);
        jButton_initializeAndSetServerSocket.setEnabled(false);

        //Thread serverDinleThread = new Thread() {
        clientDinleThread = new Thread() {
            @Override
            public void run() {
                try {
                    // A0-A1 arasındaki kod bir "Thread" içinde çalışmalı.
                    // Eğer Thread içinde çalışmazsa "jButton_acceptActionPerformed" metodu sonsuz döngüye girer ve program GUI donar.
                    //A0
                    //MTSunucu = new kayasMultiThrdServer(mySunucuSoket, jTextArea_readFromSocket, jLabel_screenShot);

                    LinkedList<Object> listeDinleyenler = new LinkedList<Object>();
                    listeDinleyenler.add(kayasServerSocketUI.this);
                    MTSunucu = new kayasMultiThrdServer(mySunucuSoket, listeDinleyenler);

                    MTSunucu.clientDinle();
                    //A1

                } catch (Exception e) {
                    jTextArea_descriptionServerSocket.append("\n" + e.toString());
                }
            }
        };

        clientDinleThread.start();  // thread başlasın.
    }//GEN-LAST:event_jButton_acceptActionPerformed

    private void jButton_setVisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_setVisibleActionPerformed
        // TODO add your handling code here:

        kayasClientSocket kayasIstemciSoket = new kayasClientSocket();
        kayasIstemciSoket.setVisible(true);
    }//GEN-LAST:event_jButton_setVisibleActionPerformed

    private void jButton_initializeAndSetServerSocketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_initializeAndSetServerSocketActionPerformed
        // TODO add your handling code here:

        textArea_descriptionServerSocket.setText("");

        LinkedList<Integer> paramsforServerSocket = new LinkedList<Integer>();
        paramsforServerSocket.add((Integer) jSpinner_port.getValue());
        paramsforServerSocket.add((Integer) jSpinner_backlog.getValue());
        paramsforServerSocket.add((Integer) jSpinner_connectionTime.getValue());
        paramsforServerSocket.add((Integer) jSpinner_latency.getValue());
        paramsforServerSocket.add((Integer) jSpinner_bandwidth.getValue());
        paramsforServerSocket.add((Integer) jSpinner_size.getValue());
        paramsforServerSocket.add((Integer) jComboBox_on.getSelectedIndex());   // false indeks=0, true indeks=1
        paramsforServerSocket.add((Integer) jSpinner_timeOut.getValue());

        try {
            if (mySunucuSoket != null)// var olan Soket açık ise onu kapat.
            {
                mySunucuSoket.close();
            }
            mySunucuSoket = kayaNetworkAbstractClass1.initializeAndSetServerSocket(paramsforServerSocket);      // java.net.BindException: Address already in use hatası alınabiliyor.
            mySunucuSoketInfos = kayaNetworkAbstractClass1.serverSocketBilgileri(mySunucuSoket);
            uiSwingMetotlar.bilgiler2Table(mySunucuSoketInfos, jTable_serverSocket);
            // ServerSocket.accept() butonu etkinleşsin.
            // Buton aktifleşmeleri
            jButton_accept.setEnabled(true);
            jButton_kapat.setEnabled(true);
        } catch (Exception ex) {//(IOException ex) {
            Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
            textArea_descriptionServerSocket.append(ex.toString());
        }

    }//GEN-LAST:event_jButton_initializeAndSetServerSocketActionPerformed

    private void jButton_kapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_kapatActionPerformed
        // TODO add your handling code here:
        /*
         * if(MTSunucu!=null) // kayasMultiThrdServer kapatılsın. {
         * MTSunucu.finalize(); } MTSunucu.finalize();
         * //serverDinleThread.interrupt(); //
         *
         * Bunlar işe yaramıyor. Kendi metodumu yazdım.
         */
        //Yukarıdakiler işe yaramıyor. Kendi metodumu yazdım.
        if (MTSunucu != null) {
            MTSunucu.kapat();
        }


        // ServerSocket.accept() butonu etksizi olsun.
        //buton aktifleşmeleri
        jButton_accept.setEnabled(false);
        jButton_initializeAndSetServerSocket.setEnabled(true);
        jButton_kapat.setEnabled(false);
    }//GEN-LAST:event_jButton_kapatActionPerformed

    private void jButton_write2SocketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_write2SocketActionPerformed
        // TODO add your handling code here:
        String gidecekMesaj = jTextField_write2Socket.getText().trim();
        try {
            kayaNetworkAbstractClass1.write2Soket(MTSunucu.clientSoket, gidecekMesaj, false);
            jTextArea_written2Socket.append(gidecekMesaj + "\n");
        } catch (IOException ex) {
            Logger.getLogger(kayasClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            textArea_descriptionServerSocket.append(ex.toString() + "\n");
            jTextArea_written2Socket.append(ex.toString() + "\n");
        }
    }//GEN-LAST:event_jButton_write2SocketActionPerformed

    private void jButton_screenShotGetirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_screenShotGetirActionPerformed
        try {
            uiSwingMetotlar.periodicScreenShotGetir(jLabel_screenShot);
        } catch (Exception ex) {
            Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_screenShotGetirActionPerformed

    private void jButton_periodicScreenShotGetirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_periodicScreenShotGetirActionPerformed
        int peryotOfScreenShot = (Integer) jSpinner_timerDelay.getValue();
        String formatTip = jComboBox_formatName.getSelectedItem().toString();
        //String gidecekMesaj = "screenShotGetir" + peryotOfScreenShot + "," + formatTip;
        try {
            kayaNetworkAbstractClass1.requestPeriodicScreenShot(peryotOfScreenShot, formatTip, MTSunucu.clientSoket);
            // kayaNetworkAbstractClass1.write2Soket(myClientSoket, gidecekMesaj, false);
            //  jTextArea_written2Socket.append(gidecekMesaj + "\n");
        } catch (IOException ex) {
            Logger.getLogger(kayasClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            textArea_descriptionServerSocket.append(ex.toString() + "\n");
            jTextArea_written2Socket.append(ex.toString() + "\n");
        }
    }//GEN-LAST:event_jButton_periodicScreenShotGetirActionPerformed

    private void jButton_ScreenShotAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ScreenShotAlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ScreenShotAlActionPerformed

    private void jButton_timerScreenShotDurdurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_timerScreenShotDurdurActionPerformed
        // TODO add your handling code here:
        //String gidecekMesaj = "timerScreenShotDurdur";
        try {
            //  kayaNetworkAbstractClass1.write2Soket(MTSunucu.clientSoket, gidecekMesaj, false);
            //jTextArea_written2Socket.append(gidecekMesaj + "\n");
            kayaNetworkAbstractClass1.requestTimerScreenShotDurdur(MTSunucu.clientSoket);
        } catch (IOException ex) {
            Logger.getLogger(kayasClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            textArea_descriptionServerSocket.append(ex.toString() + "\n");
            jTextArea_written2Socket.append(ex.toString() + "\n");
        }
    }//GEN-LAST:event_jButton_timerScreenShotDurdurActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
        /*
         * http://www.exampledepot.com/egs/javax.swing.filechooser/DoneEvent.html
         */
        Object actionKomut = evt.getActionCommand();

        if (JFileChooser.APPROVE_SELECTION.equals(actionKomut)) {
            File gidenDosya = jFileChooser1.getSelectedFile();
            try {
                kayaNetworkAbstractClass1.writeFile2SoketHighLevel(MTSunucu.clientSoket, gidenDosya);
            } catch (IOException ex) {
                Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
                textArea_descriptionServerSocket.append(ex.toString() + "\n");
                jTextArea_written2Socket.append(ex.toString() + "\n");
            }
        } else if (JFileChooser.CANCEL_SELECTION.equals(actionKomut)) {
        }
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jButton_dosyaGezginiFrameBaslatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_dosyaGezginiFrameBaslatActionPerformed
        // TODO add your handling code here:
        jFrame_dosyaGezgini.setVisible(true);
        //jFrame1.setExtendedState(JFrame.);
        jFrame_dosyaGezgini.pack();
    }//GEN-LAST:event_jButton_dosyaGezginiFrameBaslatActionPerformed

    private void jList_dizinlerValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_dizinlerValueChanged
        // TODO add your handling code here:
        try {
            String selectedChildName = (String) jList_dizinler.getSelectedValue();
            dosyaBilgilerInfos = gnt.bilgilerDon4DosyaBilgilerTreeNode(gnt.getGelenDMTN(), selectedChildName);
            uiSwingMetotlar.bilgiler2Table(dosyaBilgilerInfos, jTable_dosyaRead);
            jLabel_dosyaReadSelection.setText(selectedChildName);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jList_dizinlerValueChanged

    private void jList_dizinlerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList_dizinlerKeyPressed
        // TODO add your handling code here:

        // if the pressed key is not "ENTER", then leave method.
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            return;
        }

        int selectedDMNTIndex = jList_dizinler.getSelectedIndex() - 1;    // selectedDMNTIndex=-1 için bir dizin yukarı çık.
        gnt.subDosyaBilgilerTreeNode(gnt.getCurrentDMTN(), selectedDMNTIndex);
        uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar, jTextField_currentRootDirectory, jComboBox_currentRootDirectory);
        //gnt.setCurrentDMTN(uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory));

        //uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, gnt.getGelenDMTN(), jList_dizinler, jList_dosyalar);
        //uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, gnt.getGelenDMTN(), jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory);
        //uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory);
        //uiSwingMetotlar.subDosyaBilgilerTree2List(selectedDMNTIndex, dmtnVariable, jList_dizinler, jList_dosyalar,jTextField_currentRootDirectory,jComboBox_currentRootDirectory);
   }//GEN-LAST:event_jList_dizinlerKeyPressed

    private void jList_dosyalarValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_dosyalarValueChanged
        // TODO add your handling code here:
        try {
            String selectedChildName = (String) jList_dosyalar.getSelectedValue();
            dosyaBilgilerInfos = gnt.bilgilerDon4DosyaBilgilerTreeNode(gnt.getGelenDMTN(), selectedChildName);
            uiSwingMetotlar.bilgiler2Table(dosyaBilgilerInfos, jTable_dosyaRead);
            jLabel_dosyaReadSelection.setText(selectedChildName);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jList_dosyalarValueChanged

    private void jButton_walkFileTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_walkFileTreeActionPerformed
        // TODO add your handling code here:
        String kokDosyaPath = jComboBox_fileTreeToWalk.getSelectedItem().toString().trim();
        int treeDepth = (Integer) jSpinner_walkFileTreeDepth.getValue();

        String gidecekMesaj = kayaNetworkAbstractClass1.defaultMutableTreeNodeGetirStr + treeDepth + "," + kokDosyaPath;
        try {
            kayaNetworkAbstractClass1.write2Soket(MTSunucu.clientSoket, gidecekMesaj, false);
        } catch (IOException ex) {
            Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        // durum değişikliği belirt.
        jLabel_dosyaReadDurum.setText(jLabel_dosyaReadDurumSTR2);
    }//GEN-LAST:event_jButton_walkFileTreeActionPerformed

    private void jComboBox_gelenDMTNLLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_gelenDMTNLLActionPerformed
        // TODO add your handling code here:
        int indeks = jComboBox_gelenDMTNLL.getSelectedIndex();
        gnt.setGelenDMTNfromGelenDMTNLL(indeks);
        uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar, jTextField_currentRootDirectory, jComboBox_currentRootDirectory);
    }//GEN-LAST:event_jComboBox_gelenDMTNLLActionPerformed

    private void jButton_deleteDMTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteDMTNActionPerformed
        // TODO add your handling code here:
        int indeks = jComboBox_gelenDMTNLL.getSelectedIndex();
        gnt.removeGelenDMTNfromGelenDMTNLL(indeks);
        uiSwingMetotlar.LinkedList2ComboBox(gnt.gelenDMTNLL, jComboBox_gelenDMTNLL);
        uiSwingMetotlar.dosyaBilgilerTree2List(gnt.getCurrentDMTN(), jList_dizinler, jList_dosyalar, jTextField_currentRootDirectory, jComboBox_currentRootDirectory);
    }//GEN-LAST:event_jButton_deleteDMTNActionPerformed

    private void jButton_deleteDMTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteDMTN1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_deleteDMTN1ActionPerformed

    /*
     * request the selected files in "jList_dosyalar" from the other side
     */
    private void jButton_requestFileFromSoketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_requestFileFromSoketActionPerformed
        // TODO add your handling code here:
        List<String> requestedFiles = jList_dosyalar.getSelectedValuesList();
        Iterator iterator = requestedFiles.iterator();
        String requestedFileAbsolutPath = "";

        // do not request files one by one, first gather the absolute names of the files, then request those files iteratively.
        LinkedList<String> requestedFileAbsolutPathLL = new LinkedList<String>();

        String requestedFileStr = "";
        while (iterator.hasNext()) {
            requestedFileStr = (String) iterator.next();
            tmpDMTN = gnt.findChildWithName(gnt.getGelenDMTN(), requestedFileStr);
            requestedFileAbsolutPath = ((GelenNesneTipleri.DosyaBilgilerTreeNode) tmpDMTN.getUserObject()).toAbsolutePathString();

            requestedFileAbsolutPathLL.add(requestedFileAbsolutPath);
        }
        try {
            //        iterator = requestedFileAbsolutPathLL.iterator();
            //        while (iterator.hasNext()) {
            //            requestedFileAbsolutPath = (String) iterator.next();
            //            try {
            //                kayaNetworkAbstractClass1.requestFileFromSoket(requestedFileAbsolutPath, MTSunucu.clientSoket);
            //            } catch (InterruptedException ex) {
            //                Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
            //            } catch (IOException ex) {
            //                Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
            //                textArea_descriptionServerSocket.append(ex.toString() + "\n");
            //            }
            //        }
            kayaNetworkAbstractClass1.requestFilesFromSoket(requestedFileAbsolutPathLL, MTSunucu.clientSoket);
        } catch (IOException ex) {
            Logger.getLogger(kayasServerSocketUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_requestFileFromSoketActionPerformed

    /*
     * do something if it is a double click.
     *
     * http://www.rgagnon.com/javadetails/java-0219.html
     */
    private void jList_dizinlerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList_dizinlerMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) // if it is a double click.
        {
            //  pretend as if "Enter" is pressed.
            jList_dizinlerKeyPressed(new KeyEvent(this, 0, 0, 0, KeyEvent.VK_ENTER, '0'));
        }
    }//GEN-LAST:event_jList_dizinlerMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kayasServerSocketUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_ScreenShotAl;
    private javax.swing.JButton jButton_accept;
    private javax.swing.JButton jButton_deleteDMTN;
    private javax.swing.JButton jButton_deleteDMTN1;
    private javax.swing.JButton jButton_dosyaGezginiFrameBaslat;
    private javax.swing.JButton jButton_initializeAndSetServerSocket;
    private javax.swing.JButton jButton_kapat;
    private javax.swing.JButton jButton_periodicScreenShotGetir;
    private javax.swing.JButton jButton_readFromSocket;
    private javax.swing.JButton jButton_requestFileFromSoket;
    private javax.swing.JButton jButton_screenShotGetir;
    private javax.swing.JButton jButton_setVisible;
    private javax.swing.JButton jButton_timerScreenShotDurdur;
    private javax.swing.JButton jButton_walkFileTree;
    private javax.swing.JButton jButton_write2Socket;
    private javax.swing.JComboBox jComboBox_currentRootDirectory;
    private javax.swing.JComboBox jComboBox_fileTreeToWalk;
    private javax.swing.JComboBox jComboBox_formatName;
    private javax.swing.JComboBox jComboBox_gelenDMTNLL;
    private javax.swing.JComboBox jComboBox_on;
    private javax.swing.JEditorPane jEditorPane_descriptionServerSocket;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame_dosyaGezgini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_dosyaReadDurum;
    private javax.swing.JLabel jLabel_dosyaReadSelection;
    private javax.swing.JLabel jLabel_screenShot;
    private javax.swing.JList jList_dizinler;
    private javax.swing.JList jList_dosyalar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSpinner jSpinner_backlog;
    private javax.swing.JSpinner jSpinner_bandwidth;
    private javax.swing.JSpinner jSpinner_connectionTime;
    private javax.swing.JSpinner jSpinner_latency;
    private javax.swing.JSpinner jSpinner_port;
    private javax.swing.JSpinner jSpinner_size;
    private javax.swing.JSpinner jSpinner_timeOut;
    private javax.swing.JSpinner jSpinner_timerDelay;
    private javax.swing.JSpinner jSpinner_walkFileTreeDepth;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane_descriptionClientSocket;
    private javax.swing.JTable jTable_dosyaRead;
    private javax.swing.JTable jTable_serverSocket;
    private javax.swing.JTextArea jTextArea_descriptionServerSocket;
    private javax.swing.JTextArea jTextArea_readFromSocket;
    private javax.swing.JTextArea jTextArea_valueClientSocket;
    private javax.swing.JTextArea jTextArea_written2Socket;
    private javax.swing.JTextField jTextField_currentRootDirectory;
    private javax.swing.JTextField jTextField_write2Socket;
    private java.awt.TextArea textArea_descriptionServerSocket;
    // End of variables declaration//GEN-END:variables
}
