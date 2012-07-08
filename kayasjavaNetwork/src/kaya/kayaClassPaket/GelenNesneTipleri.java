/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * possible types for an object received over a Socket
 *
 * @author kaya
 */
public class GelenNesneTipleri {

    // gelenler
    public Object gelenObje = null;
    public String gelenStr = "";
    public BufferedImage gelenResim = null;
    public File gelenDosya = null;
    private DefaultMutableTreeNode gelenDMTN = null;
    private DefaultMutableTreeNode currentDMTN = null;
    public LinkedList<DefaultMutableTreeNode> gelenDMTNLL=new LinkedList<DefaultMutableTreeNode>();     // LL : LinkedList
    public LinkedList<LinkedList<DefaultMutableTreeNode>> currentDMTNLL=new LinkedList<LinkedList<DefaultMutableTreeNode>>();

    public GelenNesneTipleri(Object gelenObje) {
        this.gelenObje = gelenObje;
    }

    public void addGelenObje(Object gelenObje) throws IOException, ClassNotFoundException
    {
        this.gelenObje = gelenObje;
        properlyAssignGelenObje();
    }

    /*
     * assign "gelenObje" to the proper field of this class.
     */
    public void properlyAssignGelenObje() throws IOException, ClassNotFoundException {

        gelenStr = "";
        gelenResim = null;
        gelenDosya = null;
        gelenDMTN = null;
        currentDMTN = null;

        if (gelenObje instanceof BufferedImage) {
            this.gelenResim = (BufferedImage) gelenObje;
        } else if (gelenObje instanceof String) {
            this.gelenStr = (String) gelenObje;
        } else if (gelenObje instanceof File) {
            this.gelenDosya = (File) gelenObje;
        } else if (gelenObje instanceof DefaultMutableTreeNode) {
//            this.setGelenDMTN2DosyaBilgilerTreeNode((DefaultMutableTreeNode) gelenObje);
//            this.setCurrentDMTN((DefaultMutableTreeNode) GenelMetotlar.derinCopy(gelenDMTN));

            this.gelenDMTN = (DefaultMutableTreeNode) gelenObje;
            this.currentDMTN = (DefaultMutableTreeNode) GenelMetotlar.derinCopy(gelenDMTN);

            gelenDMTN2DosyaBilgilerTreeNode(gelenDMTN);
            gelenDMTN2DosyaBilgilerTreeNode(currentDMTN);

            gelenDMTNLL.add(gelenDMTN);
            LinkedList<DefaultMutableTreeNode> tmpLL=new LinkedList<DefaultMutableTreeNode>();
            tmpLL.add(currentDMTN);
            currentDMTNLL.add(tmpLL);
        }



    }


    /*
     *"indexOfDirectory" : index of directory we are searching for. "indexOfDirectory" gives index of directory among other directories, files etc. not included.
     */
    public void subDosyaBilgilerTreeNode(DefaultMutableTreeNode dmtn, int indexOfDirectory) {
        DefaultMutableTreeNode subTreeNode = null;
        DosyaBilgilerTreeNode tmpDBTN = null;

        if (indexOfDirectory == -1) {
            upperDosyaBilgilerTreeNode(dmtn);
        }

        int i = 0;
        Enumeration enm = dmtn.children();
        while (enm.hasMoreElements()) {
            subTreeNode = (DefaultMutableTreeNode) enm.nextElement();
            tmpDBTN = (DosyaBilgilerTreeNode) subTreeNode.getUserObject();

            if (tmpDBTN.isDizin()) {
                if (i == indexOfDirectory) {
                    setCurrentDMTN(subTreeNode);
                    return;
                } else {
                    i++;
                }
            }
        }

    }

    public void upperDosyaBilgilerTreeNode(DefaultMutableTreeNode dmtn) {

        String absoluteStr = ((DosyaBilgilerTreeNode) dmtn.getUserObject()).toAbsolutePathString();
        DefaultMutableTreeNode upTreeNode = null;
        DosyaBilgilerTreeNode tmpDBTN = null;

        Enumeration enumerasyon = this.gelenDMTN.breadthFirstEnumeration();
        while (enumerasyon.hasMoreElements()) {
            upTreeNode = (DefaultMutableTreeNode) enumerasyon.nextElement();
            tmpDBTN = (DosyaBilgilerTreeNode) upTreeNode.getUserObject();

            if (tmpDBTN.toAbsolutePathString().equals(absoluteStr)) {
                setCurrentDMTN((DefaultMutableTreeNode) upTreeNode.getParent());
                return;
            }
        }
    }

    public LinkedList<LinkedList<String>> bilgilerDon4DosyaBilgilerTreeNode(DefaultMutableTreeNode dmtn, String nameOfChild)
    {
        DefaultMutableTreeNode tmpTreeNode =null;
        DosyaBilgilerTreeNode tmpDBTN=null;
        LinkedList<String> tmpDosyaBilgilerValues=new LinkedList<String>();


        tmpTreeNode=findChildWithName(dmtn, nameOfChild);
        tmpDBTN=(DosyaBilgilerTreeNode) tmpTreeNode.getUserObject();
        tmpDosyaBilgilerValues=tmpDBTN.getDosyaBilgilerValues();

        return kayaNetworkAbstractClass1.bilgilerDon(tmpDBTN.props, tmpDBTN.description, tmpDosyaBilgilerValues);
    }

    public DefaultMutableTreeNode findChildWithName(DefaultMutableTreeNode dmtnParent, String nameOfChild)
    {
        DefaultMutableTreeNode tmpTreeNode = dmtnParent;
        DosyaBilgilerTreeNode tmpDBTN=(DosyaBilgilerTreeNode) tmpTreeNode.getUserObject();

        if(nameOfChild.equals("../"))   // root of "dmtn" is the node that contains info about the desired file
        {
            return tmpTreeNode;
        }

        // else
        // desired file is one of the children of the root of "dmtn"

        // to be traversed level by level, so use DFS.
        Enumeration enumerasyon=dmtnParent.breadthFirstEnumeration();
        while(enumerasyon.hasMoreElements())
        {
            tmpTreeNode=(DefaultMutableTreeNode) enumerasyon.nextElement();
            tmpDBTN=(DosyaBilgilerTreeNode) tmpTreeNode.getUserObject();

            // isimleri kontrol et.
            if(tmpDBTN.toString().equals(nameOfChild))
            {
                // desired node is found.
                return tmpTreeNode;
            }
        }

        return null;    // node not found.
    }

    public void gelenDMTN2DosyaBilgilerTreeNode(DefaultMutableTreeNode dmtn) {
        DosyaBilgilerTreeNode tmpDBTN = null;
        DefaultMutableTreeNode tmpNode = null;
        LinkedList<String> tmpLLDosyaBilgiler = null;       // LL : LinkedList


        Enumeration enumerasyon = dmtn.depthFirstEnumeration();
        while (enumerasyon.hasMoreElements()) {
            tmpNode = (DefaultMutableTreeNode) enumerasyon.nextElement();
            tmpLLDosyaBilgiler = (LinkedList<String>) tmpNode.getUserObject();

            tmpDBTN = new DosyaBilgilerTreeNode(tmpLLDosyaBilgiler);
            tmpNode.setUserObject(tmpDBTN);
        }
    }

    public void setGelenDMTNfromGelenDMTNLL(int index)
    {
        gelenDMTN=gelenDMTNLL.get(index);
        currentDMTN=gelenDMTN;
    }

    public void removeGelenDMTNfromGelenDMTNLL(int index)
    {
        gelenDMTNLL.remove(index);
        setGelenDMTNfromGelenDMTNLL(gelenDMTNLL.size()-1);
    }

    /**
     * @return the gelenDMTN
     */
    public DefaultMutableTreeNode getGelenDMTN() {
        return gelenDMTN;
    }

    /**
     * @param gelenDMTN the gelenDMTN to set
     */
    public void setGelenDMTN(DefaultMutableTreeNode gelenDMTN) {
        this.gelenDMTN = gelenDMTN;
    }

    public void setGelenDMTN2DosyaBilgilerTreeNode(DefaultMutableTreeNode gelenDMTN) {
        this.gelenDMTN = gelenDMTN;
        gelenDMTN2DosyaBilgilerTreeNode(gelenDMTN);
    }

    /**
     * @return the currentDMTN
     */
    public DefaultMutableTreeNode getCurrentDMTN() {
        return currentDMTN;
    }

    /**
     * @param currentDMTN the currentDMTN to set
     */
    public void setCurrentDMTN(DefaultMutableTreeNode gelenDMTNyedek) {
        this.currentDMTN = gelenDMTNyedek;
    }

//    public void
    public class DosyaBilgilerTreeNode {

        private LinkedList<String> dosyaBilgilerValues = null;
        public final String props = "canExecute,canRead,canWrite,exists,getAbsolutePath,getCanonicalPath,getFreeSpace,getName,getParent,getPath,getTotalSpace,getUsableSpace,hashCode,isAbsolute,isDirectory,isFile,isHidden,lastModified,length,toString,toURI,creationTime,fileKey,isDirectory,isOther,isRegularFile,isSymbolicLink,lastAccessTime,lastModifiedTime,size";
        public final String description;
        {
            description = "Tests whether the application can execute the file denoted by this abstract pathname.:::Tests whether the application can read the file denoted by this abstract pathname.:::Tests whether the application can modify the file denoted by this abstract pathname.:::Tests whether the file or directory denoted by this abstract pathname exists.:::Returns the absolute pathname string of this abstract pathname.:::Returns the canonical pathname string of this abstract pathname.:::Returns the number of unallocated bytes in the partition named by this abstract path name.:::Returns the name of the file or directory denoted by this abstract pathname.:::Returns the pathname string of this abstract pathname's parent, or null if this pathname does not name a parent directory.:::Converts this abstract pathname into a pathname string.:::Returns the size of the partition named by this abstract pathname.:::Returns the number of bytes available to this virtual machine on the partition named by this abstract pathname.:::Computes a hash code for this abstract pathname.:::Tests whether this abstract pathname is absolute.:::Tests whether the file denoted by this abstract pathname is a directory.:::Tests whether the file denoted by this abstract pathname is a normal file.:::Tests whether the file named by this abstract pathname is a hidden file.:::Returns the time that the file denoted by this abstract pathname was last modified.:::Returns the length of the file denoted by this abstract pathname.:::Returns the pathname string of this abstract pathname.:::Constructs a file: URI that represents this abstract pathname.:::Returns the creation time.:::Returns an object that uniquely identifies the given file, or null if a file key is not available.:::Tells whether the file is a directory.:::Tells whether the file is something other than a regular file, directory, or symbolic link.:::Tells whether the file is a regular file with opaque content.:::Tells whether the file is a symbolic link.:::Returns the time of last access.:::Returns the time of last modification.:::Returns the size of the file (in bytes).";
        }

        public DosyaBilgilerTreeNode(LinkedList<String> dosyaBilgilerValues) {
            this.dosyaBilgilerValues = dosyaBilgilerValues;
        }

        @Override
        public String toString() {
            return dosyaBilgilerValues.get(7);
        }

        public String toAbsolutePathString() {
            return dosyaBilgilerValues.get(4);
        }


        /*
         * returns true if the "File" object corresponding to this node is a directory
         * returns false otherwise
         *  "dosya/klasör" kontrolü yap.
         *  bkz. : kayaNetworkAbstractClass1.fileBilgileri(File dosya)
         */
        public boolean isDizin() {
            if (dosyaBilgilerValues.get(15).equalsIgnoreCase("false")) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * @return the dosyaBilgilerValues
         */
        public LinkedList<String> getDosyaBilgilerValues() {
            return dosyaBilgilerValues;
        }

        /**
         * @param dosyaBilgilerValues the dosyaBilgilerValues to set
         */
        public void setDosyaBilgilerValues(LinkedList<String> dosyaBilgilerValues) {
            this.dosyaBilgilerValues = dosyaBilgilerValues;
        }
    }
}
