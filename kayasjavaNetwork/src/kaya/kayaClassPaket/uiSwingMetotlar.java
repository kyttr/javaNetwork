/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import kaya.kayaClassPaket.GelenNesneTipleri.DosyaBilgilerTreeNode;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kaya
 */
public abstract class uiSwingMetotlar {

    private static String upperDizin = "../";

    /*
     * "dmtnDosyaBilgilerTree" bir "file system" ağacıdır.
     * "dmtnDosyaBilgilerTree" nin 1. seviyesindeki dosyalar ve dizinler ilgili listelere aktarılır.
     */
    public static void dosyaBilgilerTree2List(DefaultMutableTreeNode dmtnDosyaBilgilerTree, JList listDizin, JList listDosya) {
        //LinkedList<String> dosyaBilgiler=null;
        GelenNesneTipleri.DosyaBilgilerTreeNode dbtn = null;
        DefaultMutableTreeNode node = null;

//        DefaultListModel<GelenNesneTipleri.DosyaBilgilerTreeNode> dlmDizin=new DefaultListModel<GelenNesneTipleri.DosyaBilgilerTreeNode>();
//        DefaultListModel<GelenNesneTipleri.DosyaBilgilerTreeNode> dlmDosya=new DefaultListModel<GelenNesneTipleri.DosyaBilgilerTreeNode>();

        DefaultListModel<String> dlmDizin = new DefaultListModel<String>();
        DefaultListModel<String> dlmDosya = new DefaultListModel<String>();

        dlmDizin.addElement(upperDizin);   // symbol for traversing to the upper directory

        try{
        Enumeration enumerator = dmtnDosyaBilgilerTree.children();
        while (enumerator.hasMoreElements()) {
            node = (DefaultMutableTreeNode) enumerator.nextElement();
            dbtn = (GelenNesneTipleri.DosyaBilgilerTreeNode) node.getUserObject();

            // "dosya/klasör" kontrolü yap.
            // bkz. : kayaNetworkAbstractClass1.fileBilgileri(File dosya)
            if (dbtn.isDizin()) {
                dlmDizin.addElement(dbtn.toString());
            } else {
                dlmDosya.addElement(dbtn.toString());
            }
        }
        }
        catch(Exception ex)
        {     }

        listDizin.setModel(dlmDizin);
        listDosya.setModel(dlmDosya);
    }

    public static void dosyaBilgilerTree2List(DefaultMutableTreeNode dmtnDosyaBilgilerTree, JList listDizin, JList listDosya, JTextField tf_kokDizin, JComboBox cb_kokDizin) {
        dosyaBilgilerTree2List(dmtnDosyaBilgilerTree, listDizin, listDosya);

        // şimdi kok dizini yerleştir.
        GelenNesneTipleri.DosyaBilgilerTreeNode dbtn = (DosyaBilgilerTreeNode) dmtnDosyaBilgilerTree.getUserObject();
        tf_kokDizin.setText(dbtn.toAbsolutePathString());
        cb_kokDizin.addItem(dbtn.toAbsolutePathString());
    }

    /*
     *  "dmtnDosyaBilgilerTree" : a file system tree, nodes have objects of type "GelenNesneTipleri.DosyaBilgilerTreeNode"
     *  "indexOfChild"          : index of a directory among the directory-type children of "dmtnDosyaBilgilerTree".
     *  return "subDMNT
     */
    public static DefaultMutableTreeNode subDosyaBilgilerTree2List(int indexOfChild, DefaultMutableTreeNode dmtnDosyaBilgilerTree, JList listDizin, JList listDosya) {
        /*
         * "subDMNT"        : among children of "dmtnDosyaBilgilerTree" if we construct a list consisting of "directory" nodes, "subDMNT" is the element with index "indexOfChild"
         */
        DefaultMutableTreeNode subDMNT = null;

        if (indexOfChild > 0) // bir alt dizine inilecek.
        {
            //subDMNT = GelenNesneTipleri.subDosyaBilgilerTreeNode(dmtnDosyaBilgilerTree, indexOfChild);
            dosyaBilgilerTree2List(subDMNT, listDizin, listDosya);
        } else if (indexOfChild == -1) // bir üst dizine çıkılacak.
        {
            subDMNT = (DefaultMutableTreeNode) dmtnDosyaBilgilerTree.getParent();
        }

        // return new current directory
        return subDMNT;
    }

    /*
     *  "dmtnDosyaBilgilerTree" : a file system tree, nodes have objects of type "GelenNesneTipleri.DosyaBilgilerTreeNode"
     *  "indexOfChild"          : index of a directory among the directory-type children of "dmtnDosyaBilgilerTree".
     *  return "subDMNT
     */
    public static DefaultMutableTreeNode subDosyaBilgilerTree2List(int indexOfChild, DefaultMutableTreeNode dmtnDosyaBilgilerTree, JList listDizin, JList listDosya, JTextField tf_kokDizin, JComboBox cb_kokDizin) {
        /*
         * "subDMNT"        : among children of "dmtnDosyaBilgilerTree" if we construct a list consisting of "directory" nodes, "subDMNT" is the element with index "indexOfChild"
         */
        DefaultMutableTreeNode subDMNT = null;

        if (indexOfChild > 0) // bir alt dizine inilecek.
        {
//            subDMNT = GelenNesneTipleri.subDosyaBilgilerTreeNode(dmtnDosyaBilgilerTree, indexOfChild);
            dosyaBilgilerTree2List(subDMNT, listDizin, listDosya, tf_kokDizin, cb_kokDizin);
        } else if (indexOfChild == -1) // bir üst dizine çıkılacak.
        {
            subDMNT = (DefaultMutableTreeNode) dmtnDosyaBilgilerTree.getParent();
        }

        // return new current directory
        return subDMNT;
    }

      public static void bilgiler2Table(LinkedList<LinkedList<String>> propValueDescrip, JTable mytable) {
        int boyut = propValueDescrip.size();
        LinkedList<String> listeTemp = new LinkedList<String>();

        DefaultTableModel dtm = (DefaultTableModel) mytable.getModel();
        dtm.setRowCount(boyut);     // satır sayısı belirle.
        //x1 dtm.setColumnCount(3);      // sutun sayısı 3
        dtm.setColumnCount(2);      // sutun sayısı
        mytable.getColumnModel().getColumn(0).setHeaderValue("Özellik (Property)");
        mytable.getColumnModel().getColumn(1).setHeaderValue("Değerler (Values)");
        //x1 mytable.getColumnModel().getColumn(2).setHeaderValue("Açıklama (Description)");

        /*
         * Alt satırdaki yöntem işe yaramadı. //mytable.clearSelection();
         */


        for (int i = 0; i < boyut; i++) {
            //listeTemp.clear();   //"propValueDescrip" listesindekileri de siliyor.
            listeTemp = propValueDescrip.get(i);
            mytable.setValueAt(listeTemp.get(0), i, 0);
            mytable.setValueAt(listeTemp.get(1), i, 1);
            //x1  mytable.setValueAt(listeTemp.get(2), i, 2);
        }
    }

       public static void bufferedImage2JLabel(BufferedImage bi, JLabel myLabel) {
        // label'ın en-boy oranı, resmin en-boy oranı ile aynı olsun. Label'ın genişliği sabit olacak, yükseklik değişecek.
        //import java.lang.Math.*;
            /*
         * http://stackoverflow.com/questions/3144610/java-integer-division-how-do-you-produce-a-double
         */
        int h = bi.getHeight();
        int w = bi.getWidth();
        double oran = h / (double) w;
        double newh = myLabel.getWidth() * oran;
        int newh2 = (int) Math.round(newh);
        //myLabel.setSize(myLabel.getWidth(), newh2);      // bu satır, yüksekliği değer olarak güncelliyor, ama bu güncelleme GUI de gözükmüyor

        myLabel.setText(null);  // Label'da bir şey yazmasın.
            /*
         * http://stackoverflow.com/questions/299495/java-swing-how-to-add-an-image-to-a-jpanel
         * http://stackoverflow.com/questions/2244848/java-imageicon-size
         */
        // resmi Label'a sığacak şekilde boyutlandır.
        Image resizedImaj = (Image) bi.getScaledInstance(myLabel.getWidth(), myLabel.getHeight(), -1);

        ImageIcon ikon = new ImageIcon(resizedImaj);
        myLabel.setIcon(ikon);
    }

           public static void periodicScreenShotGetir(final JLabel mylabel) {
        /*
         * http://stackoverflow.com/questions/1006611/java-swing-timer
         * http://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
         * http://java.sun.com/products/jfc/tsc/articles/timer/
         */
        ActionListener actionDinler = new ActionListener() {

            BufferedImage bi;
            JLabel actionLabel = mylabel;

            public void actionPerformed(ActionEvent evt) {
                try {
                    bi = GenelMetotlar.screenShotGetir();
                    bufferedImage2JLabel(bi, actionLabel);
                } catch (AWTException ex) {
                    Logger.getLogger(kayaNetworkAbstractClass1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //import javax.swing.Timer;
        Timer timer = new Timer(1000, actionDinler);
        timer.start();
    }

    /*
     * send elements of a LL into a JList object, I assume elements of LL have a
     * proper toString() method.
     */
    public static void LinkedList2JList(LinkedList ll, JList listbox) {
        DefaultListModel dlm = new DefaultListModel();
        Iterator itrTmp = ll.iterator();
        while (itrTmp.hasNext()) {
            dlm.addElement(itrTmp.next());
        }
        listbox.setModel(dlm);
    }

    /*
     * send elements of a LL into a ComboBox, I assume elements of LL have a
     * proper toString() method.
     */
    public static void LinkedList2ComboBox(LinkedList ll,JComboBox cb)
    {
        Object[] nesneDizi=ll.toArray();
        DefaultComboBoxModel dcbm=new DefaultComboBoxModel(nesneDizi);
        cb.setModel(dcbm);

        // set the last item as selected
        cb.setSelectedIndex(nesneDizi.length-1);

     }
           
    
    //JTextArea için
    public static void tableInfo2TextArea(LinkedList<LinkedList<String>> propValueDescrip, JTable mytable, JTextArea mytextArea) {
        int indeks = mytable.getSelectedRow();
        if (indeks > 0) // bu olmazsa QT'deki gibi "out of range" hatası
        {
            mytextArea.setText(propValueDescrip.get(indeks).get(0) + " = " + propValueDescrip.get(indeks).get(2));
        }
    }

    //TextArea için
    public static void tableInfo2TextArea(LinkedList<LinkedList<String>> propValueDescrip, JTable mytable, TextArea mytextArea) {

        int indeks = mytable.getSelectedRow();
        if (indeks > 0) // bu olmazsa QT'deki gibi "out of range" hatası
        {
            mytextArea.setText(propValueDescrip.get(indeks).get(0) + " = " + propValueDescrip.get(indeks).get(2));
        }
    }

    // istenilen bilgiyi seçmek için
    public static void tableInfo2TextArea(LinkedList<LinkedList<String>> propValueDescrip, JTable mytable, JTextArea mytextArea, int bilgiIndeks) {
        int indeks = mytable.getSelectedRow();
        if (indeks > 0) // bu olmazsa QT'deki gibi "out of range" hatası
        {
            mytextArea.setText(propValueDescrip.get(indeks).get(bilgiIndeks));
        }
    }

    // "JTable" seçili alana ait olan özelliğin internetteki kaynağını "Editor Pane" e yansıtır.
    public static void tableInfo2EditorPane(LinkedList<LinkedList<String>> propValueDescrip, JTable mytable, String kaynakURL, JEditorPane myeditorpane) throws IOException {
        int indeks = mytable.getSelectedRow();
        if (indeks > 0) // bu olmazsa QT'deki gibi "out of range" hatası
        {
            String prop = propValueDescrip.get(indeks).get(0);
            if (!prop.endsWith("()")) {
                prop += "()";
            }
            String urlToGoStr = kaynakURL + "#" + prop;
            URL urlToGo = new URL(urlToGoStr);
            myeditorpane.setPage(urlToGo);
        }
    }


    
}
