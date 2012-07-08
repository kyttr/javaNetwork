/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import kaya.kayasServerUIpaket.kayasServerSocketUI;

/**
 *
 * @author kaya
 */
//extends Thread{
public class kayasSocketThread implements Runnable {

    private Socket soket = null;
    private BufferedReader soketsInputStream = null;
    private BufferedWriter soketsOutputStream = null;
    private boolean isClosed = false;
    private String hataStr = "";
    private String gidecekStr = "";
    private LinkedList<Object> listenerObjects = null;     // objects listening to events happened in this thread.
    // gelenler
    private Object gelenObje = null;
    private String gelenStr = "";
    private BufferedImage gelenResim = null;
    private File gelenDosya = null;
    public DefaultMutableTreeNode gelenDMTN = null;

    // UI elemanları : // TO BE GONE
    private JTextArea textArea = null;
    private JLabel screenShotLabel = null;

    public kayasSocketThread(Socket gelenSoket) throws IOException {
        this.soket = gelenSoket;
        this.soketsInputStream = new BufferedReader(new InputStreamReader(gelenSoket.getInputStream()));
        this.soketsOutputStream = new BufferedWriter(new OutputStreamWriter(gelenSoket.getOutputStream()));
    }

    // TO BE GONE
    public kayasSocketThread(Socket gelenSoket, JTextArea ja) throws IOException {
        this.soket = gelenSoket;
        this.soketsInputStream = new BufferedReader(new InputStreamReader(gelenSoket.getInputStream()));
        this.soketsOutputStream = new BufferedWriter(new OutputStreamWriter(gelenSoket.getOutputStream()));
        this.textArea = ja;
    }

    // TO BE GONE
    public kayasSocketThread(Socket gelenSoket, JTextArea ja, JLabel screenShotLabel) throws IOException {
        this.soket = gelenSoket;
        this.soketsInputStream = new BufferedReader(new InputStreamReader(gelenSoket.getInputStream()));
        this.soketsOutputStream = new BufferedWriter(new OutputStreamWriter(gelenSoket.getOutputStream()));
        this.textArea = ja;
        this.screenShotLabel = screenShotLabel;
    }

    public kayasSocketThread(Socket gelenSoket, LinkedList<Object> dinleyenNesneler) throws IOException {
        this.soket = gelenSoket;
        this.soketsInputStream = new BufferedReader(new InputStreamReader(gelenSoket.getInputStream()));
        this.soketsOutputStream = new BufferedWriter(new OutputStreamWriter(gelenSoket.getOutputStream()));
        this.listenerObjects = dinleyenNesneler;
    }

    @Override
    public void run() {
        while (true) {
            if (isClosed) {
                return;     // Thread'i bitir.
            }
            try {
                //gelenStr = kayaNetworkAbstractClass1.readFromSoket(soket);
                gelenObje = kayaNetworkAbstractClass1.readMesajFromSoket(soket);
//                if(gelenStr==null)
//                {
//                    break;
//                }
                //
                gelenResim = null;
                gelenStr = "";
                gelenDosya = null;
                gelenDMTN = null;
                if (gelenObje instanceof BufferedImage) {
                    gelenResim = (BufferedImage) gelenObje;
                } else if (gelenObje instanceof String) {
                    gelenStr = (String) gelenObje;
                } else if (gelenObje instanceof File) {
                    gelenDosya = (File) gelenObje;
                } else if (gelenObje instanceof DefaultMutableTreeNode) {
                    gelenDMTN = (DefaultMutableTreeNode) gelenObje;
                }
                //
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(kayasSocketThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(kayasSocketThread.class.getName()).log(Level.SEVERE, null, ex);
                hataStr = ex.toString();
            } catch (AWTException ex) {
                Logger.getLogger(kayasSocketThread.class.getName()).log(Level.SEVERE, null, ex);
                hataStr = ex.toString();
            } catch (IOException ex) {
                Logger.getLogger(kayasSocketThread.class.getName()).log(Level.SEVERE, null, ex);
                hataStr = ex.toString();
                break;
            }

            if (gelenObje != null) {
                throwOlay();
            }
            gelenObje = null;

            // gelenStr boş değilse ve gösterim için bir TextArea varsa, gelenStr bastır.
            if (!gelenStr.isEmpty() && textArea != null) {
                textArea.append(gelenStr + "\n");
            }

            //
            // gelenResim null değilse ve gösterim için bir JLabel varsa, gelenResmi bastır.
            if (gelenResim != null && screenShotLabel != null) {
                uiSwingMetotlar.bufferedImage2JLabel(gelenResim, screenShotLabel);
            }
            //
            if (gelenDosya != null) {
                textArea.append("Dosya geldi : " + gelenDosya.getAbsolutePath() + "\n");
            }

            // gönderilecek mesaj varsa gönder
            if (!gidecekStr.isEmpty()) {
                mesajGonder(gidecekStr);
            }
        }
    }

    public void mesajGonder(String gidenMesaj) {
        try {
            soketsOutputStream.write(gidenMesaj + "\n");
            soketsOutputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            hataStr = ex.toString();
        }
    }

    public void kapat() {
        try {
            isClosed = true;
            soket.close();
        } catch (Exception ex) {
            hataStr = ex.toString();
        }
    }

    /*
     * bir olay gerçekleşir. Bu "class" ı takip eden her nesneye bu olay bildirilir.
     */
    public void throwOlay()
    {
        /*
         * http://www.javaworld.com/javaworld/javaqa/2002-03/01-qa-0315-happyevent.html?page=2
         */
        OlayNesnesi olay = new OlayNesnesi( this, this.gelenObje);
        Iterator itr=listenerObjects.iterator();
        Object tmpNesne=null;
        while( itr.hasNext() ) {
            tmpNesne=itr.next();
            ((OlayDinleyenlerInterface)tmpNesne).OlayOlmus(olay);
        }
    }
}
