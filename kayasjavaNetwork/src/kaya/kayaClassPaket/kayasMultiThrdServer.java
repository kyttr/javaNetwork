/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author kaya
 */
public class kayasMultiThrdServer {

    private ServerSocket serverSoket = null;
    private String hataStr = null;
    private boolean isClosed = false;

    // TO BE GONE
    private JTextArea textArea = null;
    private JLabel screenShotlabel = null;

    //
    public Socket clientSoket;
    //
    //private kayasSocketThread soketThrd;
    public kayasSocketThread soketThrd;
    private LinkedList<Object> listenerObjects = null;

    public kayasMultiThrdServer(ServerSocket sunucuSoket) {
        this.serverSoket = sunucuSoket;
    }

    // TO BE GONE
    public kayasMultiThrdServer(ServerSocket sunucuSoket, JTextArea jta) {
        this.serverSoket = sunucuSoket;
        this.textArea = jta;
    }

    // TO BE GONE
    public kayasMultiThrdServer(ServerSocket sunucuSoket, JTextArea jta, JLabel screenShotlabel) {
        this.serverSoket = sunucuSoket;
        this.textArea = jta;
        this.screenShotlabel = screenShotlabel;
    }

    public kayasMultiThrdServer(ServerSocket sunucuSoket, LinkedList<Object> dinleyenNesneler) {
        this.serverSoket = sunucuSoket;
        this.listenerObjects = dinleyenNesneler;
    }

    public void clientDinle() {
        while (true) {
            //kayasSocketThread soketThrd;

            try {
                //Socket clientSoket=serverSoket.accept();    // istemci soketi al.
                clientSoket = serverSoket.accept();    // istemci soketi al.
                // istemci soket için thread oluştur
                //
                if (textArea != null && screenShotlabel != null) {
                    soketThrd = new kayasSocketThread(clientSoket, textArea, screenShotlabel);
                } //
                else if (textArea != null) {
                    soketThrd = new kayasSocketThread(clientSoket, textArea);
                } else if (listenerObjects != null) {
                    soketThrd = new kayasSocketThread(clientSoket, listenerObjects);
                } else {
                    soketThrd = new kayasSocketThread(clientSoket);
                }
                soketThrd.welcomeSoket();
                soketThrd.throwSoketBaglandi();

                Thread t = new Thread(soketThrd);
                t.start();              // thread'i çalıştır.

                if (isClosed) {
                    return;  // Dinleme işlemini bitir.
                }
            } catch (IOException ex) {
                hataStr = ex.toString();
            }
        }
    }

    /**
    @Override
    public void finalize() {
        //Objects created in run method are finalized when
        //program terminates and thread exits
        try {
            serverSoket.close();
        } 
        catch (IOException ex) {
            hataMesaj = ex.toString();
        }
    }
     **/
    public void kapat() {
        try {
            //isClosed = true;
            if (soketThrd != null) {
                //clientSoket.close();      //pek bir şey değiştirmiyor.
                soketThrd.kapat();
            }
            serverSoket.close();
        } catch (Exception ex) {
            hataStr = ex.toString();
        }
    }
}
