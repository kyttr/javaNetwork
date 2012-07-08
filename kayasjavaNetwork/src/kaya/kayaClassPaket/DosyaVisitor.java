/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;     // JDK 1.7
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
//import sun.awt.DefaultMouseInfoPeer;

/**
 *
 * @author kaya
 */
public class DosyaVisitor extends SimpleFileVisitor<Path> {
    /*
     * http://docs.oracle.com/javase/tutorial/essential/io/walk.html
     * http://www.ibm.com/developerworks/java/library/j-nio2-2/
     * http://docs.oracle.com/javase/tutorial/uiswing/components/tree.html
     * file:///home/kaya/Documents/docs%20-%20jdk-7u2-apidocs/api/javax/swing/tree/DefaultMutableTreeNode.html
     */

    public DefaultMutableTreeNode dmtnDosyaTree = null;
    public DefaultMutableTreeNode dmtnDosyaBilgilerTree = null;


    public File kokFile = null;
    public DefaultMutableTreeNode dmtnSubDosyaTree = null;   //namely current parent
    public DefaultMutableTreeNode dmtnSubDosyaBilgilerTree = null;   //namely current parent
    public DefaultMutableTreeNode dmtnDosyaTmp = null;
    public DefaultMutableTreeNode dmtnDosyaBilgilerTmp = null;
    public int numChilds = 0;
    public int maxTreeDepth=Integer.MAX_VALUE;
    public LinkedList<LinkedList<String>> dosyaPropValueDescrip=null;
    public LinkedList<String> dosyaBilgilerValues=null;

    /*
     * if root file is not speficied, then build tree starting at home directory.
     */
    public DosyaVisitor(int maxDepth) throws IOException, ClassNotFoundException {
        
        kokFile = FileSystemView.getFileSystemView().getHomeDirectory();
        dosyaPropValueDescrip=kayaNetworkAbstractClass1.fileBilgileri(kokFile);
        dosyaBilgilerValues=kayaNetworkAbstractClass1.valsFromPropsValsDescriptions(dosyaPropValueDescrip);
        this.maxTreeDepth=maxDepth;

        dmtnDosyaTree = new DefaultMutableTreeNode(kokFile);
        dmtnDosyaBilgilerTree=new DefaultMutableTreeNode(dosyaBilgilerValues);

        dmtnSubDosyaTree = dmtnDosyaTree;
        dmtnSubDosyaBilgilerTree=dmtnDosyaBilgilerTree;

        EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        Files.walkFileTree(kokFile.toPath(),opts,maxTreeDepth, this);
    }

    /*
     * "rootDosya" is the root of the "dmtnDosyaTree"
     */
    public DosyaVisitor(File rootDosya,int maxDepth) throws IOException, ClassNotFoundException {
        
        kokFile = rootDosya;
        dosyaPropValueDescrip=kayaNetworkAbstractClass1.fileBilgileri(kokFile);
        dosyaBilgilerValues=kayaNetworkAbstractClass1.valsFromPropsValsDescriptions(dosyaPropValueDescrip);
        this.maxTreeDepth=Integer.MAX_VALUE;

        dmtnDosyaTree = new DefaultMutableTreeNode(rootDosya);
        dmtnDosyaBilgilerTree=new DefaultMutableTreeNode(dosyaBilgilerValues);    // each node is a "LinkedList<LinkedList<String>>"

        dmtnSubDosyaTree = dmtnDosyaTree;
        dmtnSubDosyaBilgilerTree=dmtnDosyaBilgilerTree;

        EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        Files.walkFileTree(kokFile.toPath(),opts,maxDepth, this);
    }

    /*
     * if root file is not speficied, then build tree starting at home directory.
     * if maxDepth is not specified, then take maximum possible depth.
     */
    public DosyaVisitor() throws IOException, ClassNotFoundException
    {
         new DosyaVisitor(Integer.MAX_VALUE);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dirPath,
            BasicFileAttributes attrs) throws IOException {

        File dir=dirPath.toFile();
        if (dir.equals(kokFile)) // if the incoming directory is "kokFile", do nothing.
        {
            return FileVisitResult.CONTINUE;
        }
        // first add incoming directory to the children of the current directory.
        dmtnDosyaTmp = new DefaultMutableTreeNode(dir);
        dmtnSubDosyaTree.add(dmtnDosyaTmp);

        // update the current directory, the new current directory is the last child of the previous current directory.
        numChilds = dmtnSubDosyaTree.getChildCount();
        dmtnSubDosyaTree = (DefaultMutableTreeNode) dmtnSubDosyaTree.getChildAt(numChilds - 1);

        // do same for "bilgiler" nodes
        dosyaPropValueDescrip=kayaNetworkAbstractClass1.fileBilgileri(dir);
        dosyaBilgilerValues=kayaNetworkAbstractClass1.valsFromPropsValsDescriptions(dosyaPropValueDescrip);

        dmtnDosyaBilgilerTmp=new DefaultMutableTreeNode(dosyaBilgilerValues);
        dmtnSubDosyaBilgilerTree.add(dmtnDosyaBilgilerTmp);

        dmtnSubDosyaBilgilerTree = (DefaultMutableTreeNode) dmtnSubDosyaBilgilerTree.getChildAt(numChilds - 1);

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dirPath,
            IOException exc) {

        // update current directory, the new current directory is the the parent of the previous current directory.
        dmtnSubDosyaTree = (DefaultMutableTreeNode) dmtnSubDosyaTree.getParent();

        dmtnSubDosyaBilgilerTree = (DefaultMutableTreeNode) dmtnSubDosyaBilgilerTree.getParent();

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path dirPath,
            BasicFileAttributes attr) throws IOException {

        File dosya=dirPath.toFile();
        // add the file to the children of the current directory.
        dmtnDosyaTmp = new DefaultMutableTreeNode(dosya);
        dmtnSubDosyaTree.add(dmtnDosyaTmp);

        // do same for "bilgiler" nodes
        dosyaPropValueDescrip=kayaNetworkAbstractClass1.fileBilgileri(dosya);
        dosyaBilgilerValues=kayaNetworkAbstractClass1.valsFromPropsValsDescriptions(dosyaPropValueDescrip);

        dmtnDosyaBilgilerTmp=new DefaultMutableTreeNode(dosyaBilgilerValues);
        dmtnSubDosyaBilgilerTree.add(dmtnDosyaBilgilerTmp);

        return FileVisitResult.CONTINUE;
    }

    // If there is some error accessing
    // the file, let the user know.
    // If you don't override this method
    // and an error occurs, an IOException
    // is thrown.
    @Override
    public FileVisitResult visitFileFailed(Path dirPath,
            IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    private void dmtnBilgilerDoldur(DefaultMutableTreeNode treeToBeFilled) throws IOException, ClassNotFoundException {
        treeToBeFilled = (DefaultMutableTreeNode) GenelMetotlar.derinCopy(dmtnDosyaTree);

        // "tree" yi dolaş.
        /*
         * http://stackoverflow.com/questions/1470857/traversing-tree-made-from-defaultmutabletreenode
         */

        Enumeration en = treeToBeFilled.depthFirstEnumeration();
        DefaultMutableTreeNode node, newNode;
        Path p;
        while (en.hasMoreElements()) {

            // Unfortunately the enumeration isn't genericised so we need to downcast
            // when calling nextElement():
            node = (DefaultMutableTreeNode) en.nextElement();
            p=(Path) node.getUserObject();
            node.setUserObject(p.toFile().getAbsolutePath());
        }

    }

   }









/*
 *  * file:///home/kaya/Documents/docs%20-%20jdk-7u2-apidocs/api/javax/swing/tree/DefaultMutableTreeNode.html
 * http://docs.oracle.com/javase/tutorial/uiswing/components/tree.html
 *
 * Java'da bir "tree" yapısı zaten varmış. Ben de yoktur diye düşünüp kendi "tree" yapımı yazmaya kalkıştım. Amma da saflık.
 *
 */
//class DosyaTree {
//
//    private List<DosyaTree> childrenOfDosyaTree;
//    private File rootOfDosyaTree;
//
//    public DosyaTree(File kokFile) {
//        this.rootOfDosyaTree = kokFile;
//    }
//
//    public DosyaTree(File kokFile, List<DosyaTree> childrenOfDosyaTree) {
//        this.rootOfDosyaTree = kokFile;
//        this.childrenOfDosyaTree = childrenOfDosyaTree;
//    }
//
//    // aşağıdakileri netbeans'in kendisi hazırladı.
//    /**
//     * @return the childrenOfDosyaTree
//     */
//    public List<DosyaTree> getChildrenOfDosyaTree() {
//        return childrenOfDosyaTree;
//    }
//
//    /**
//     * @param childrenOfDosyaTree the childrenOfDosyaTree to set
//     */
//    public void setChildrenOfDosyaTree(List<DosyaTree> childrenOfDosyaTree) {
//        this.childrenOfDosyaTree = childrenOfDosyaTree;
//    }
//
//    /**
//     * @return the rootOfDosyaTree
//     */
//    public File getRootOfDosyaTree() {
//        return rootOfDosyaTree;
//    }
//
//    /**
//     * @param rootOfDosyaTree the rootOfDosyaTree to set
//     */
//    public void setRootOfDosyaTree(File rootOfDosyaTree) {
//        this.rootOfDosyaTree = rootOfDosyaTree;
//    }
//}

