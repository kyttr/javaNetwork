/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaya-on-netbook
 */
public class DatagramSoketThread implements Runnable {

    private DatagramSocket datagramSoket = null;
    private DatagramPacket datagramPaket = null;
    private byte[] paketByteDizi = null;
    private int paketLength = 256;    // length of the byte array in the DatagramPacket paket, default length : 256
    private boolean isClosed = false;

    public DatagramSoketThread(DatagramSocket ds) {
        this.datagramSoket = ds;

        paketByteDizi = new byte[paketLength];
    }

    public DatagramSoketThread(DatagramSocket ds, int paketLength) {
        this.datagramSoket = ds;
        this.paketLength = paketLength;
    }

    public void run() {

        while (true) {
            if (isClosed) {
                return;
            }

            // gelecek paket için hazırlık başlat.
            paketByteDizi = new byte[paketLength];
            datagramPaket = new DatagramPacket(paketByteDizi, paketLength);
            try {
                // paketi  soketten al.
                datagramSoket.receive(datagramPaket);
                // gelen veri ile ilgili islem yap.
                workOnGelenBuffer(datagramPaket);

            } catch (IOException ex) {
                Logger.getLogger(DatagramSoketThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    private void workOnGelenBuffer(DatagramPacket gelenPaket) {

        // gelen veri
        byte[] gelenBuffer = gelenPaket.getData();

        // send the response to the client at "address" and "port"
        InetAddress address = gelenPaket.getAddress();
        int port = gelenPaket.getPort();

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
