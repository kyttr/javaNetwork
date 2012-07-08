/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author kaya-on-netbook
 */
public class DatagramSoketThread implements Runnable{

    private DatagramSocket datagramSoket=null;
    private DatagramPacket datagramPaket=null;
    private byte[] paketByteDizi=null;
    private int paketLength=256;    // length of the byte array in the DatagramPacket paket, default length : 256
    
    public DatagramSoketThread(DatagramSocket ds)
    {
        this.datagramSoket=ds;
        
        paketByteDizi=new byte[paketLength];
    }
    
        public DatagramSoketThread(DatagramSocket ds, int paketLength)
    {
        this.datagramSoket=ds;
this.paketLength=paketLength;
    }

    
    public void run() {
        
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
