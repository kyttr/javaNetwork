/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

/**
 *
 * @author kaya
 */
import com.sun.imageio.plugins.bmp.BMPImageReader;
import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.lang.*;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.imageio.*;
import java.lang.Math.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import javax.imageio.stream.ImageInputStream;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.Session;
import javax.swing.Timer;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import kaya.kayasServerUIpaket.kayasServerSocketUI;

public abstract class kayaNetworkAbstractClass1 {

    //public static int boyutResimPaket = 1024 * 4;
    //public static String myEOF = ">myEOF<";
    private static String myEOF = ">myEOF<1>myEOF<2>myEOF<";
    private static Timer timerScreenShot = null;
    private static String timerScreenShotDurdurStr = "timerScreenShotDurdur";
    private static String screenShotGetirStr = "screenShotGetir";
    private static String screenShotAlStr = "screenShotAl";
    private static String dosyaAlStr = "dosyaAl";
    private static String buBirByteDizidirStr = "buBirByteDizidir";
    private static String fileSystemViewAlStr = "fileSystemViewAl";
    //private static String defaultMutableTreeNodeAlStr="defaultMutableTreeNodeAl";
    public static String defaultMutableTreeNodeAlStr = "defaultMutableTreeNodeAl";
    public static String defaultMutableTreeNodeGetirStr = "defaultMutableTreeNodeGetir";

    public static LinkedList<LinkedList<String>> bilgilerDon(String strProp, String strDescrip, LinkedList<String> degerler) {
        LinkedList<LinkedList<String>> listeBilgiler = new LinkedList<LinkedList<String>>();
        LinkedList<String> listeTemp = new LinkedList<String>();

        int boyut = degerler.size();

        String[] diziPropsTemp = strProp.split(",");
        String[] diziDescripTemp = strDescrip.split(":::");

        for (int i = 0; i < boyut; i++) {
            listeTemp.clear();
            listeTemp.add(diziPropsTemp[i]);
            listeTemp.add(degerler.get(i));
            listeTemp.add(diziDescripTemp[i]);

            //listeBilgiler.add(listeTemp);
            listeBilgiler.add(new LinkedList<String>(listeTemp));
        }

        return listeBilgiler;
    }

    // veri alıp gönderirken, "props", "descrip" alanları sabit, onları da gönderip yer işgal etmeye gerek yok.
    // Bilgiler listesinden yalnızca "vals" kısmını dön.
    public static  LinkedList<String> valsFromPropsValsDescriptions(LinkedList<LinkedList<String>> propValueDescrip)
    {
        LinkedList<String> vals = new LinkedList<String>();
        int boyut = propValueDescrip.size();
        String tmp=null;

        for(int i=0;i<boyut;i++)
        {
            tmp=propValueDescrip.get(i).get(1);
            vals.add(tmp);
        }

        return vals;
    }

  
    public static LinkedList<LinkedList<String>> fileBilgileri(File dosya) throws IOException {
        LinkedList<String> listeVals = new LinkedList<String>();

        listeVals.add(String.valueOf(dosya.canExecute()));
        listeVals.add(String.valueOf(dosya.canRead()));
        listeVals.add(String.valueOf(dosya.canWrite()));
        listeVals.add(String.valueOf(dosya.exists()));
        listeVals.add(String.valueOf(dosya.getAbsolutePath()));
        listeVals.add(String.valueOf(dosya.getCanonicalPath()));
        listeVals.add(String.valueOf(dosya.getFreeSpace()));
        listeVals.add(String.valueOf(dosya.getName()));
        listeVals.add(String.valueOf(dosya.getParent()));
        listeVals.add(String.valueOf(dosya.getPath()));
        listeVals.add(String.valueOf(dosya.getTotalSpace()));
        listeVals.add(String.valueOf(dosya.getUsableSpace()));
        listeVals.add(String.valueOf(dosya.hashCode()));
        listeVals.add(String.valueOf(dosya.isAbsolute()));
        listeVals.add(String.valueOf(dosya.isDirectory()));
        listeVals.add(String.valueOf(dosya.isFile()));
        listeVals.add(String.valueOf(dosya.isHidden()));
        listeVals.add(String.valueOf(dosya.lastModified()));
        listeVals.add(String.valueOf(dosya.length()));
        listeVals.add(String.valueOf(dosya.toString()));
        listeVals.add(String.valueOf(dosya.toURI().toString()));

        //"BasicFileAttributes" arayuzu ile diger özellikleri al.
        /*
         * http://docs.oracle.com/javase/7/docs/api/java/nio/file/attribute/BasicFileAttributes.html
         */
        try{
        BasicFileAttributes attrs = Files.readAttributes(dosya.toPath(), BasicFileAttributes.class);

        listeVals.add(String.valueOf(attrs.creationTime().toString()));
        listeVals.add(String.valueOf(attrs.fileKey().toString()));
        listeVals.add(String.valueOf(attrs.isDirectory()));
        listeVals.add(String.valueOf(attrs.isOther()));
        listeVals.add(String.valueOf(attrs.isRegularFile()));
        listeVals.add(String.valueOf(attrs.isSymbolicLink()));
        listeVals.add(String.valueOf(attrs.lastAccessTime().toString()));
        listeVals.add(String.valueOf(attrs.lastModifiedTime().toString()));
        listeVals.add(String.valueOf(attrs.size()));
        }
        catch(Exception ex)
        {

        }

        String props = "canExecute,canRead,canWrite,exists,getAbsolutePath,getCanonicalPath,getFreeSpace,getName,getParent,getPath,getTotalSpace,getUsableSpace,hashCode,isAbsolute,isDirectory,isFile,isHidden,lastModified,length,toString,toURI,creationTime,fileKey,isDirectory,isOther,isRegularFile,isSymbolicLink,lastAccessTime,lastModifiedTime,size";

        String description;
        {
            description = "Tests whether the application can execute the file denoted by this abstract pathname.:::Tests whether the application can read the file denoted by this abstract pathname.:::Tests whether the application can modify the file denoted by this abstract pathname.:::Tests whether the file or directory denoted by this abstract pathname exists.:::Returns the absolute pathname string of this abstract pathname.:::Returns the canonical pathname string of this abstract pathname.:::Returns the number of unallocated bytes in the partition named by this abstract path name.:::Returns the name of the file or directory denoted by this abstract pathname.:::Returns the pathname string of this abstract pathname's parent, or null if this pathname does not name a parent directory.:::Converts this abstract pathname into a pathname string.:::Returns the size of the partition named by this abstract pathname.:::Returns the number of bytes available to this virtual machine on the partition named by this abstract pathname.:::Computes a hash code for this abstract pathname.:::Tests whether this abstract pathname is absolute.:::Tests whether the file denoted by this abstract pathname is a directory.:::Tests whether the file denoted by this abstract pathname is a normal file.:::Tests whether the file named by this abstract pathname is a hidden file.:::Returns the time that the file denoted by this abstract pathname was last modified.:::Returns the length of the file denoted by this abstract pathname.:::Returns the pathname string of this abstract pathname.:::Constructs a file: URI that represents this abstract pathname.:::Returns the creation time.:::Returns an object that uniquely identifies the given file, or null if a file key is not available.:::Tells whether the file is a directory.:::Tells whether the file is something other than a regular file, directory, or symbolic link.:::Tells whether the file is a regular file with opaque content.:::Tells whether the file is a symbolic link.:::Returns the time of last access.:::Returns the time of last modification.:::Returns the size of the file (in bytes).";
        }

        return bilgilerDon(props, description, listeVals);
    }

    public static LinkedList<LinkedList<String>> urlBilgileri(URL url) throws IOException {
        LinkedList<String> listeVals = new LinkedList<String>();

        listeVals.add(String.valueOf(url.getAuthority()));
        listeVals.add(String.valueOf(url.getContent()));
        listeVals.add(String.valueOf(url.getDefaultPort()));
        listeVals.add(String.valueOf(url.getFile()));
        listeVals.add(String.valueOf(url.getHost()));
        listeVals.add(String.valueOf(url.getPath()));
        listeVals.add(String.valueOf(url.getPort()));
        listeVals.add(String.valueOf(url.getProtocol()));
        listeVals.add(String.valueOf(url.getQuery()));
        listeVals.add(String.valueOf(url.getRef()));
        listeVals.add(String.valueOf(url.getUserInfo()));

        String props = "getAuthority,getContent,getDefaultPort,getFile,getHost,getPath,getPort,getProtocol,getQuery,getRef,getUserInfo";

        String description;
        {
            description = "the authority part of this URL.:::the contents of this URL.:::the contents of this URL.:::the default port number of the protocol associated with this URL.:::the file name of this URL.:::the host name of this URL, if applicable.:::the path part of this URL.:::the port number of this URL.:::the protocol name of this URL.:::the query part of this URL.:::the anchor (also known as the \"reference\") of this URL.:::the userInfo part of this URL.";
        }

        return bilgilerDon(props, description, listeVals);
    }

    public static LinkedList<LinkedList<String>> serverSocketBilgileri(ServerSocket sunucuSoket) throws SocketException, IOException {
        LinkedList<String> listeVals = new LinkedList<String>();

        listeVals.add(String.valueOf(sunucuSoket.getChannel()));
        listeVals.add(String.valueOf(sunucuSoket.getInetAddress()));
        listeVals.add(String.valueOf(sunucuSoket.getLocalPort()));
        listeVals.add(String.valueOf(sunucuSoket.getLocalSocketAddress()));
        listeVals.add(String.valueOf(sunucuSoket.getReceiveBufferSize()));
        listeVals.add(String.valueOf(sunucuSoket.getReuseAddress()));
        listeVals.add(String.valueOf(sunucuSoket.getSoTimeout()));
        listeVals.add(String.valueOf(sunucuSoket.isBound()));
        listeVals.add(String.valueOf(sunucuSoket.isClosed()));
        listeVals.add(String.valueOf(sunucuSoket.toString()));


        String props = "getChannel(),getInetAddress(),getLocalPort(),getLocalSocketAddress(),getReceiveBufferSize(),getReuseAddress(),getSoTimeout(),isBound(),isClosed(),toString()";

        String description;
        {
            description = "the unique ServerSocketChannel object associated with this socket, if any.::: the local address of this server socket.::: the port number on which this socket is listening.::: the address of the endpoint this socket is bound to, or null if it is not bound yet.::: Gets the value of the SO_RCVBUF option for this ServerSocket, that is the proposed buffer size that will be used for Sockets accepted from this ServerSocket.::: Tests if SO_REUSEADDR is enabled.::: Retrieve setting for SO_TIMEOUT.::: the binding state of the ServerSocket.::: the closed state of the ServerSocket.::: the implementation address and implementation port of this socket as a String.";
        }

        return bilgilerDon(props, description, listeVals);
    }

    public static LinkedList<LinkedList<String>> socketBilgileri(Socket clientSoket) throws SocketException {
        LinkedList<String> listeVals = new LinkedList<String>();

        listeVals.add(String.valueOf(clientSoket.getChannel()));
        listeVals.add(String.valueOf(clientSoket.getInetAddress()));
        //listeVals.add(String.valueOf(clientSoket.getInputStream().toString()));
        listeVals.add(String.valueOf(clientSoket.getKeepAlive()));
        listeVals.add(String.valueOf(clientSoket.getLocalAddress()));
        listeVals.add(String.valueOf(clientSoket.getLocalPort()));
        listeVals.add(String.valueOf(clientSoket.getLocalSocketAddress()));
        listeVals.add(String.valueOf(clientSoket.getOOBInline()));
        //listeVals.add(String.valueOf(clientSoket.getOutputStream().toString()));
        listeVals.add(String.valueOf(clientSoket.getPort()));
        listeVals.add(String.valueOf(clientSoket.getReceiveBufferSize()));
        listeVals.add(String.valueOf(clientSoket.getRemoteSocketAddress()));
        listeVals.add(String.valueOf(clientSoket.getReuseAddress()));
        listeVals.add(String.valueOf(clientSoket.getSendBufferSize()));
        listeVals.add(String.valueOf(clientSoket.getSoLinger()));
        listeVals.add(String.valueOf(clientSoket.getSoTimeout()));
        listeVals.add(String.valueOf(clientSoket.getTcpNoDelay()));
        listeVals.add(String.valueOf(clientSoket.getTrafficClass()));
        listeVals.add(String.valueOf(clientSoket.isBound()));
        listeVals.add(String.valueOf(clientSoket.isClosed()));
        listeVals.add(String.valueOf(clientSoket.isConnected()));
        listeVals.add(String.valueOf(clientSoket.isInputShutdown()));
        listeVals.add(String.valueOf(clientSoket.isOutputShutdown()));


        String props = "getChannel(),getInetAddress(),getKeepAlive(),getLocalAddress(),getLocalPort(),getLocalSocketAddress(),getOOBInline(),getPort(),getReceiveBufferSize(),getRemoteSocketAddress(),getReuseAddress(),getSendBufferSize(),getSoLinger(),getSoTimeout(),getTcpNoDelay(),getTrafficClass(),isBound(),isClosed(),isConnected(),isInputShutdown(),isOutputShutdown()";

        String description;
        {
            description = "the unique SocketChannel object associated with this socket, if any.::: the address to which the socket is connected.::: Tests if SO_KEEPALIVE is enabled.::: Gets the local address to which the socket is bound.::: the local port number to which this socket is bound.::: the address of the endpoint this socket is bound to, or null if it is not bound yet.::: Tests if OOBINLINE is enabled.::: the remote port number to which this socket is connected.::: Gets the value of the SO_RCVBUF option for this Socket, that is the buffer size used by the platform for input on this Socket.::: the address of the endpoint this socket is connected to, or null if it is unconnected.::: Tests if SO_REUSEADDR is enabled.::: Get value of the SO_SNDBUF option for this Socket, that is the buffer size used by the platform for output on this Socket.::: setting for SO_LINGER.::: setting for SO_TIMEOUT.::: Tests if TCP_NODELAY is enabled.::: Gets traffic class or type-of-service in the IP header for packets sent from this Socket::: the binding state of the socket.::: the closed state of the socket.::: the connection state of the socket.::: whether the read-half of the socket connection is closed.::: whether the write-half of the socket connection is closed.";
        }

        return bilgilerDon(props, description, listeVals);
    }

    public static String readURLDirectly2HTMLString(URL url) throws IOException {
        /*
         * http://stackoverflow.com/questions/9357822/how-to-show-regional-characters-in-android
         */
        //InputStreamReader isr = new InputStreamReader(url.openStream(),"UTF-8");  //ç,ü,ğ,ş,ö
        InputStreamReader isr = new InputStreamReader(url.openStream());    // bu da çalışıyor.
        BufferedReader bf = new BufferedReader(isr);


        String htmlKaynaktemp;
        String htmlKaynak = "";

        while ((htmlKaynaktemp = bf.readLine()) != null) {
            htmlKaynak += htmlKaynaktemp + "\n";
        }

        isr.close();
        bf.close();
        return htmlKaynak;
    }

    public static ServerSocket initializeAndSetServerSocket(LinkedList<Integer> parametreler) throws IOException {
        ServerSocket sunucuSoket;

        /*
         * parametreler={port,backlog,connectionTime,latency,bandwidth,size,on,timeout}
         */
        if (parametreler.get(0) > 0 && parametreler.get(1) > 0) {
            sunucuSoket = new ServerSocket(parametreler.get(0), parametreler.get(1));
        } else if (parametreler.get(0) > 0) {
            sunucuSoket = new ServerSocket(parametreler.get(0));
        } else {
            sunucuSoket = new ServerSocket();
        }

        if (parametreler.get(2) > 0 && parametreler.get(3) > 0 && parametreler.get(4) > 0) {
            sunucuSoket.setPerformancePreferences(parametreler.get(2), parametreler.get(3), parametreler.get(4));
        }

        if (parametreler.get(5) > 0) {
            sunucuSoket.setReceiveBufferSize(parametreler.get(5));
        }
        if (parametreler.get(6) == 0) {
            sunucuSoket.setReuseAddress(false);
        }
        if (parametreler.get(7) > 0) {
            sunucuSoket.setSoTimeout(parametreler.get(7));
        }

        return sunucuSoket;
    }

    public static Socket initializeAndSetSocket(LinkedList<Integer> parametreler, String host) throws IOException {
        Socket clientSoket;

        /*
         * parametreler={port,keepAlive,ooBlnline,connectionTime,latency,bandwidth,receiveBufferSize,reuseAddress,
         * sendBufferSize,boolsoLinger,linger,timeout,tcpNoDelay,trafficClass}
         */
        if (!host.isEmpty() && parametreler.get(0) > 0) {
            clientSoket = new Socket(host, parametreler.get(0));
        } else {
            clientSoket = new Socket();
        }

        /*
         * bazı parametreler boolean tipind, onlar için uygulama şöyle: 0 =
         * hiçbir şey yapma 1 = false 2 = true
         */
        if (parametreler.get(1) > 0) {
            clientSoket.setKeepAlive((parametreler.get(1) > 1));
        }
        if (parametreler.get(2) > 0) {
            clientSoket.setOOBInline((parametreler.get(2) > 1));
        }
        if (parametreler.get(3) > 0 && parametreler.get(4) > 0 && parametreler.get(5) > 0) {
            clientSoket.setPerformancePreferences(parametreler.get(3), parametreler.get(4), parametreler.get(5));
        }
        if (parametreler.get(6) > 0) {
            clientSoket.setReceiveBufferSize(parametreler.get(6));
        }
        if (parametreler.get(7) > 0) {
            clientSoket.setReuseAddress((parametreler.get(7) > 1));
        }
        if (parametreler.get(8) > 0) {
            clientSoket.setSendBufferSize(parametreler.get(8));
        }
        if (parametreler.get(9) > 0 && parametreler.get(10) > 0) {
            clientSoket.setSoLinger(parametreler.get(9) > 1, parametreler.get(10));
        }
        if (parametreler.get(11) > 0) {
            clientSoket.setSoTimeout(parametreler.get(11));
        }
        if (parametreler.get(12) > 0) {
            clientSoket.setTcpNoDelay(parametreler.get(12) > 1);
        }
        if (parametreler.get(13) > 0) {
            clientSoket.setTrafficClass(parametreler.get(13));
        }

        return clientSoket;
    }

    public static void write2Soket(Socket mySoket, String mesaj) throws IOException {
        PrintWriter pw = new PrintWriter(mySoket.getOutputStream(), true);
        pw.println(mesaj);    // bunu kullanma
        //pw.println(mesaj + "\n");
        pw.println(myEOF + "\n");     // readFromSoket'te bu görülünce döngüden çıkılacak.
        pw.close();
    }

    public static void write2Soket(Socket mySoket, String mesaj, boolean closePrintWriterOnExit) throws IOException {
        PrintWriter pw = new PrintWriter(mySoket.getOutputStream(), true);
        pw.println(mesaj);    // bunu kullanma
        //pw.println(mesaj + "\n");
        pw.println(myEOF + "\n");     // readFromSoket'te bu görülünce döngüden çıkılacak.
        if (pw.checkError()) // yazma işlemi hata verdi.
        {
            throw new IOException("Sunucu Soket'le bağlantı yok. Bir önceki mesaj sunucuya gitmedi.");
        }
        /*
         * Bağlantıyı Client Tarafında kontrol etmek
         * http://stackoverflow.com/questions/151590/java-how-to-detect-a-remote-side-socket-close
         * clientSocket.isConnected() returns always true once the client
         * connects (and even after the disconnect) weird !!
         * getInputStream().read() makes the thread wait for input as long as
         * the client is connected and therefore makes your program not do
         * anything - except if you get some input returns -1 if the client
         * disconnected out.checkError() is true as soon as the client is
         * disconnected so I recommend this
         */

        if (closePrintWriterOnExit) // PrintWriter kapatmak, Soketi kapatır.    Closing the returned OutputStream will close the associated socket.
        {
            pw.close();
        }
    }

    public static void write2Soket(Socket mySoket, String mesaj, LinkedList<Integer> paramsforClientSocket, String host) throws IOException {
        //mySoket.close();
        if (mySoket.isClosed()) {
            try {    // Soket kapanmışsa, yeniden aç ve mesajı gönder.
                mySoket = kayaNetworkAbstractClass1.initializeAndSetSocket(paramsforClientSocket, host);
            } catch (Exception ex) {
                //Logger.getLogger(kayasClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        write2Soket(mySoket, mesaj, false);
        //write2Soket(mySoket, mesaj, true);
    }

    /*
     * "byteDizi" sokete yaz. "byteDizi" uzunluğu bilinmiyor. Bu nedenle "byteDizi"den hemen önce onun uzunluğunu gönder.
     */
    public static void writeByteArrayAndLength2Soket(Socket mysoket, byte[] byteDizi) throws IOException {
        /*
        http://stackoverflow.com/questions/1176135/java-socket-send-receive-byte-array
         * işe yaramadı.
         */
        OutputStream os = mysoket.getOutputStream();

        int len = byteDizi.length;
        String lenStr = String.valueOf(len);
        // lenBytes : "byteDizi" uzunluğunu tutan byte dizisi
        byte[] lenBytes = lenStr.getBytes();
        // lenOflenBytes : "lenBytes" ın uzunluğu
        // varsayım : 0<lenOflenBytes<256
        byte lenOflenBytes = (byte) lenBytes.length;

        os.write(lenOflenBytes);
        os.write(lenBytes, 0, lenOflenBytes);
        os.write(byteDizi, 0, len);
    }

    /*
     * "byteDizi" sokete yaz. "byteDizi" uzunluğu biliniyor.
     */
    public static void writeByteArray2Soket(Socket mysoket, byte[] byteDizi) throws IOException {
        OutputStream os = mysoket.getOutputStream();
        os.write(byteDizi, 0, byteDizi.length);
    }

    /*
     * "dosya" sokete yaz. "dosya" uzunluğu bilinmiyor. Bu nedenle "dosya"den hemen önce onun uzunluğunu gönder.
     */
    public static void writeFileAndLength2Soket(Socket mysoket, File dosya) throws FileNotFoundException, IOException {
        byte[] byteDizi = new byte[(int) dosya.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(dosya));
        bis.read(byteDizi, 0, byteDizi.length);
        writeByteArrayAndLength2Soket(mysoket, byteDizi);
    }

    /*
     * "dosya" sokete yaz. "dosya" uzunluğu biliniyor.
     */
    public static void writeFile2Soket(Socket mysoket, File dosya) throws IOException {
        byte[] byteDizi = new byte[(int) dosya.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(dosya));
        bis.read(byteDizi, 0, byteDizi.length);
        writeByteArray2Soket(mysoket, byteDizi);
    }

    public static void writeFile2SoketHighLevel(Socket mySoket, File dosya) throws IOException {
        // öncelikle byte dizi gönderdiğini anlat.
        byte[] buBirByteDizidirBytes = buBirByteDizidirStr.getBytes();
        writeByteArray2Soket(mySoket, buBirByteDizidirBytes);

        String dosyaAlPrefix = dosyaAlStr + (int) dosya.length();
        byte[] dosyaAlPrefixBytes = dosyaAlPrefix.getBytes();
        writeByteArrayAndLength2Soket(mySoket, dosyaAlPrefixBytes); //Dosya uzunluğu artık biliniyor.

        // dosyaName to soket.
        byte[] dosyaNameBytes = dosya.getName().getBytes();
        writeByteArrayAndLength2Soket(mySoket, dosyaNameBytes);

        // dosya to soket. Dosya uzunluğu artık biliniyor.
        writeFile2Soket(mySoket, dosya);
    }

    public static void writeScreenShot2Soket(Socket mySoket, String formatName) throws AWTException, IOException {
        BufferedImage bi = screenShotGetir();
        byte[] b = bufferedImage2ByteArray(bi, formatName);

        // byte[] gönderdiğini anlat  ve byte[] boyutunu yaz.
                /* !! Bu mesaj BYTE ARRAY olarak gitmeli.
         * String olarak giderse hemen sonraki byte[] ile karışıyor.
         */
        byte[] buBirByteDizidirBytes = buBirByteDizidirStr.getBytes();
        writeByteArray2Soket(mySoket, buBirByteDizidirBytes);
        String screenShotAlPrefix = screenShotAlStr + b.length;
        byte[] screenShotAlPrefixBytes = screenShotAlPrefix.getBytes();
        writeByteArrayAndLength2Soket(mySoket, screenShotAlPrefixBytes);
        // karşı taraf "b" nin uzunluğunu artık biliyor.
        writeByteArray2Soket(mySoket, b);
    }

    public static void writeFileSystemView2Soket(Socket mySoket) throws IOException {
        // öncelikle byte dizi gönderdiğini anlat.
        byte[] buBirByteDizidirBytes = buBirByteDizidirStr.getBytes();
        writeByteArray2Soket(mySoket, buBirByteDizidirBytes);

        //FileSystemView fsv = FileSystemView.getFileSystemView();
        File f = FileSystemView.getFileSystemView().getHomeDirectory();
        byte[] fsvBytes = GenelMetotlar.object2ByteArray(f);

        String fsvAlPrefix = fileSystemViewAlStr + fsvBytes.length;
        byte[] fsvAlPrefixBytes = fsvAlPrefix.getBytes();
        writeByteArrayAndLength2Soket(mySoket, fsvAlPrefixBytes);

        writeByteArray2Soket(mySoket, fsvBytes);
    }

    public static void writeObject2SoketHighLevel(Socket mySoket, Object nesne, String prefixStr) throws IOException {
        // öncelikle byte dizi gönderdiğini anlat.
        byte[] buBirByteDizidirBytes = buBirByteDizidirStr.getBytes();
        writeByteArray2Soket(mySoket, buBirByteDizidirBytes);

        byte[] b = GenelMetotlar.object2ByteArray(nesne);

        String prefixStr2 = prefixStr + b.length;
        byte[] fsvAlPrefixBytes = prefixStr2.getBytes();
        writeByteArrayAndLength2Soket(mySoket, fsvAlPrefixBytes);

        writeByteArray2Soket(mySoket, b);
    }

    public kayaNetworkAbstractClass1() {
    }

    public static Object readMesajFromSoket(Socket mySoket) throws IOException, InterruptedException, AWTException, ClassNotFoundException {
        String gelenMesaj = "";
        String lineOfgelenMesaj = "";
        BufferedReader br;

        ///////////////////////////////////
        // "gelenMesaj" ın byte[] veya String olup olmadığını kontrol et.
//        String firstscreenShotAlMesaj = "buBirByteDizidir";
//        byte[] firstscreenShotAlMesajBytes = firstscreenShotAlMesaj.object2ByteArray();
//        int lenfirstscreenShotAlMesajBytes = firstscreenShotAlMesajBytes.length;
        byte[] buBirByteDizidirBytes = buBirByteDizidirStr.getBytes();
        int lenbuBirByteDizidirBytes = buBirByteDizidirBytes.length;
        //InputStream isTmp=mySoket.getInputStream();
        //isTmp.mark(lenfirstscreenShotAlMesajBytes*77);
        byte[] gelenKontrolBytes = readByteArrayFromSoket(mySoket, lenbuBirByteDizidirBytes);
        //byte[] gelenKontrolBytes=readByteArrayFromInputStream(isTmp, lenfirstscreenShotAlMesajBytes);
        String gelenKontrol = new String(gelenKontrolBytes);
        gelenMesaj = gelenKontrol.replace("\n", "");
        if (gelenKontrol.startsWith(buBirByteDizidirStr)) {
            // gelen veri screenShot'tır. String okuma yapılmayacak.
            // edit : gelen veri byte[] .
//            byte[] screenShotAlBytes = readByteArrayFromSoket(mySoket);
//            gelenKontrol = new String(screenShotAlBytes);
//            gelenKontrol = gelenKontrol.replaceAll(screenShotAlStr, "");
//            int gelenByteDiziBoyut = Integer.parseInt(gelenKontrol);
//            BufferedImage bi = readBufferedImageFromSoket(mySoket, gelenByteDiziBoyut);
//            return bi;
            return readPrefixAndByteArrayFromSoket(mySoket);
        }
        // byte[] almayacağız, isTmp ilk pozisyonuna dönsün.
        ////////////////////////////////

        if (mySoket.getInputStream().available() > 0) {
            br = new BufferedReader(new InputStreamReader(mySoket.getInputStream()));
            //   "thread" kullanacaksam aşağıdaki gibi bir kod kullanmamalı. Çünkü, aşağıdaki kod "thread" de streamden sürekli input bekler.
           /*A
            while ((lineOfMesaj = br.readLine()) != null) {
            gelenMesaj += lineOfMesaj + "\n";
            }
            br.close();
            A*/

            while ((lineOfgelenMesaj = br.readLine()) != null) {
                if (lineOfgelenMesaj.equals(myEOF)) {
                    break;      //  okunan satır ">myEOF<" ise, döngüden çık.
                    // bu kontrol önemli.
                } else if (gelenMesaj.concat(lineOfgelenMesaj).endsWith(myEOF)) {
                    gelenMesaj += lineOfgelenMesaj;
                    break;
                }
                // 1606 gelenMesaj += lineOfMesaj + "\n";
                gelenMesaj += "\n" + lineOfgelenMesaj;
            }

            // 1606 gelenMesaj=gelenMesaj.substring(0, gelenMesaj.length()-3);  // gelenMesaj sonundaki "\n" ekini at.
//            gelenMesaj = gelenMesaj.replaceFirst("\n", "");  // gelenMesaj başındaki "\n" ekini at.
//            gelenMesaj = gelenMesaj.replaceAll(myEOF, "");    // ">myEOF<" yazan bölümleri at.
            //br.close(); // okuma işleminden sonra BufferedReader kapanırsa, soket kapanır.
        }
        gelenMesaj = gelenMesaj.replaceFirst("\n", "");  // gelenMesaj başındaki "\n" ekini at.
        gelenMesaj = gelenMesaj.replaceAll(myEOF, "");    // ">myEOF<" yazan bölümleri at.
        if (gelenMesaj.startsWith("$")) // "gelenMesaj" is a shell command to be executed
        {
            String outputOfCommand;
            try {
                outputOfCommand = executeString(gelenMesaj.substring(1).trim());
            } catch (Exception ex) {
                outputOfCommand = ex.toString();
            }

            if (outputOfCommand.length() > 0) {
                write2Soket(mySoket, outputOfCommand, false);    // write the output of the shell command to istemciSoket
            }
        } else if (gelenMesaj.startsWith(screenShotGetirStr)) // diğer taraf screenShot istiyor.
        {
            gelenMesaj = gelenMesaj.replace(screenShotGetirStr, "");
//            int timerDelay = Integer.parseInt(gelenMesaj);
            String[] paramsForScreenShot = gelenMesaj.split(",");
            int timerDelay = Integer.parseInt(paramsForScreenShot[0].trim());
            String format = paramsForScreenShot[1];
            if (timerDelay > 0) {
                periodicScreenShot2Soket(mySoket, timerDelay, format);
            } else {
                writeScreenShot2Soket(mySoket, format);
            }
        }
        else if (gelenMesaj.startsWith(timerScreenShotDurdurStr)) // diğer taraf artık screenShot istemiyor.
        {
            timerScreenShotDurdur();
        }
        else if (gelenMesaj.startsWith(defaultMutableTreeNodeGetirStr)) // diğer taraf dosya sistemi istiyor.
        {
            gelenMesaj = gelenMesaj.replace(defaultMutableTreeNodeGetirStr, "");
            String[] paramsForDMTN=gelenMesaj.split(",",2);     // oluşacak parametre dizisi en fazla 2 elemanlı olabilir.
            int depthOfTree=Integer.parseInt(paramsForDMTN[0]);
            String kokDosyaStr=paramsForDMTN[1];
            File kokDosya=new File(kokDosyaStr);

            DosyaVisitor dv=null;
            if(depthOfTree>0)
            {
                if(kokDosya.exists())
                {
                    dv=new DosyaVisitor(kokDosya, depthOfTree);
                }
 else{
    dv=new DosyaVisitor(depthOfTree);
 }

            }
            else
            {
dv=new DosyaVisitor();
            }

            writeObject2SoketHighLevel(mySoket, dv.dmtnDosyaBilgilerTree, kayaNetworkAbstractClass1.defaultMutableTreeNodeAlStr);
        }

        return gelenMesaj;
    }

    public static String readFromSoket(Socket mySoket) throws IOException, InterruptedException, AWTException, ClassNotFoundException {
        return readMesajFromSoket(mySoket).toString();
    }

    public static String readFromSoket(Socket mySoket, LinkedList<Integer> paramsforClientSocket, String host) throws IOException, InterruptedException, AWTException, ClassNotFoundException {
        if (mySoket.isClosed()) // kapanmışsa yeniden yarat.
        {
            try {
                mySoket = kayaNetworkAbstractClass1.initializeAndSetSocket(paramsforClientSocket, host);
            } catch (Exception ex) {
                //Logger.getLogger(kayasClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return readFromSoket(mySoket);
    }

    /*
     * Socket üzerinden gönderilen veriyi oku.
     * Gönderilen verinin uzunluğu bilinmiyor. Verinin uzunluğunun verinin en başına koyulduğunu varsayıyorum.
     * Gelen verinin en başından gelen verinin uzunluğunu al. Sonrasında veriyi alıp byte[] olarak döndür.
     */
    public static byte[] readByteArrayFromSoket(Socket mySoket) throws IOException {
        /* ESKİ KOD
        InputStream is = mySoket.getInputStream();
        byte[] mesajByteDizi = new byte[is.available()]; // Büyük değerler için is.available()'a güvenme.
        is.read(mesajByteDizi);
        return mesajByteDizi;
         */

        InputStream is = mySoket.getInputStream();

        //lenOfdosyaUzunlukBytes : "dosyaUzunlukBytes" adlı byte dizisinin uzunluğu
        // varsayım : 0<lenOfdosyaUzunlukBytes<256
        // dosyaUzunlukBytes : okunacak byte dizisinin uzunluğunu taşıyan byte dizisi
        int lenOfdosyaUzunlukBytes = is.read();
        byte[] byteDiziUzunlukBytes = new byte[lenOfdosyaUzunlukBytes];
        is.read(byteDiziUzunlukBytes, 0, lenOfdosyaUzunlukBytes);
        /*
         * http://stackoverflow.com/questions/6684665/java-byte-array-to-string-to-byte-array
         */
        String dosyaUzunlukStr = new String(byteDiziUzunlukBytes);
        int byteDiziUzunluk = Integer.parseInt(dosyaUzunlukStr);
        // "dosyaUzunlukBytes" yazan uzunluk bilgisi --> String --> Integer

        byte[] gelenVeriBytes = readByteArrayFromInputStream(is, byteDiziUzunluk);

        return gelenVeriBytes;
    }

    /*
     * Socket üzerinden gönderilen veriyi oku.
     * lenOfByteDizi : gönderilen verinin uzunluğu
     * Verinin uzunluğu verinin önüne yerleştirilmiş değil.
     */
    public static byte[] readByteArrayFromSoket(Socket mySoket, int lenOfByteDizi) throws IOException {
        InputStream is = mySoket.getInputStream();
        byte[] b = readByteArrayFromInputStream(is, lenOfByteDizi);
        return b;
    }

    public static byte[] readByteArrayFromInputStream(InputStream is, int lenOfByteDizi) throws IOException {
        byte[] byteDizi = new byte[lenOfByteDizi];

        int bytesRead = 0;    // "gelenVeriBytes" dan okunmuş toplam byte sayısı
        int bytesReadTmp;   // "gelenVeriBytes" dan bir döngüde okunmuş byte sayısı

        /* !! Aşağıdaki döngü !!.
         * tüm veri okunana kadar dön.
         * !! InputStream.read(byte[] b, int off, int len) --> fonksiyonu büyük  "len" değerleri için tüm veriyi okumayı sağlayamıyor.
         * Bu nedenle tüm veri okunana kadar "read(byte[] b, int off, int len)" fonksiyonu çağrılmalı.
         * Bu durumu bilmediğim için uzun byte dizisi gönderme konusunda 3 gün boyunca sıkıntı çektim, debelenip durdum.
         */
        while (bytesRead < lenOfByteDizi) {
            bytesReadTmp = is.read(byteDizi, bytesRead, lenOfByteDizi - bytesRead);
            bytesRead += bytesReadTmp;
        }

        return byteDizi;
    }

    public static BufferedImage readBufferedImageFromSoket(Socket mySoket, int boyutOfByteDizi) throws IOException, InterruptedException {
        BufferedImage bi = byteArray2BufferedImage(readByteArrayFromSoket(mySoket, boyutOfByteDizi));
        return bi;
    }

    public static File readFileFromSoket(String dosyaPathName, Socket mySoket) throws IOException {
        byte[] dosyaByteDizi = readByteArrayFromSoket(mySoket);
        File dosya = new File(dosyaPathName);
        FileOutputStream fos = new FileOutputStream(dosya);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(dosyaByteDizi, 0, dosyaByteDizi.length);
        bos.close();
        fos.close();

        return dosya;
    }

    public static File readFileFromSoket(String dosyaPathName, Socket mySoket, int lenOfByteDizi) throws IOException {
        byte[] dosyaByteDizi = readByteArrayFromSoket(mySoket, lenOfByteDizi);
        File dosya = new File(dosyaPathName);
        FileOutputStream fos = new FileOutputStream(dosya);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(dosyaByteDizi, 0, dosyaByteDizi.length);
        bos.close();
        fos.close();

        return dosya;
    }

    public static Object readObjectFromSoket(Socket mySoket, int lenOfByteDizi) throws IOException, ClassNotFoundException {
        byte[] nesneByteDizi = readByteArrayFromSoket(mySoket, lenOfByteDizi);
        Object nesne = GenelMetotlar.byteArray2Object(nesneByteDizi);
        return nesne;
    }

    public static Object readObjectAndLengthFromSoket(Socket mySoket) throws IOException, ClassNotFoundException {
        byte[] nesneByteDizi = readByteArrayFromSoket(mySoket);
        Object nesne = GenelMetotlar.byteArray2Object(nesneByteDizi);
        return nesne;
    }

    public static Object readPrefixAndByteArrayFromSoket(Socket mySoket) throws IOException, InterruptedException, ClassNotFoundException {
        Object nesne = null;
        byte[] prefixBytes = readByteArrayFromSoket(mySoket);
        String prefixStr = new String(prefixBytes);
        int gelenByteDiziBoyut = -1;
        if (prefixStr.startsWith(screenShotAlStr)) // gelen byte dizi bir "screenShot"tır.
        {
            prefixStr = prefixStr.replaceAll(screenShotAlStr, "");
            gelenByteDiziBoyut = Integer.parseInt(prefixStr);
            BufferedImage bi = readBufferedImageFromSoket(mySoket, gelenByteDiziBoyut);
            nesne = bi;
        } else if (prefixStr.startsWith(dosyaAlStr)) // gelen byte dizi bir "File" dır.
        {
            byte[] gelenDosyaNameBytes = readByteArrayFromSoket(mySoket);
            String gelenDosyaName = new String(gelenDosyaNameBytes);

            prefixStr = prefixStr.replaceAll(dosyaAlStr, "");
            gelenByteDiziBoyut = Integer.parseInt(prefixStr);

            File f = readFileFromSoket(gelenDosyaName, mySoket, gelenByteDiziBoyut);
            nesne = f;
        } else if (prefixStr.startsWith(fileSystemViewAlStr)) {
            prefixStr = prefixStr.replaceAll(fileSystemViewAlStr, "");
            gelenByteDiziBoyut = Integer.parseInt(prefixStr);

            //FileSystemView fsv=(FileSystemView) readObjectFromSoket(mySoket, gelenByteDiziBoyut);
            File fsv = (File) readObjectFromSoket(mySoket, gelenByteDiziBoyut);
            nesne = fsv;
        } else if (prefixStr.startsWith(defaultMutableTreeNodeAlStr)) {
            prefixStr = prefixStr.replaceAll(defaultMutableTreeNodeAlStr, "");
            gelenByteDiziBoyut = Integer.parseInt(prefixStr);

            //FileSystemView fsv=(FileSystemView) readObjectFromSoket(mySoket, gelenByteDiziBoyut);
            DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) readObjectFromSoket(mySoket, gelenByteDiziBoyut);
            nesne = dmtn;
        }
        return nesne;
    }

    public static String acceptSoketAndSayWelcome(ServerSocket sunucuSoket) throws IOException, InterruptedException, AWTException, ClassNotFoundException {
        Socket istemciSoket;
        istemciSoket = sunucuSoket.accept();
        // Karşılama mesajı gönder
        write2Soket(istemciSoket, "Welcome stream : I am " + sunucuSoket.toString(), false);

        // Gelen mesajı al
        String gelenMesaj = readFromSoket(istemciSoket);

        istemciSoket.close();

        return gelenMesaj;
    }

    // executes the input string as a shell command and returns its output as string
    public static String executeString(String komut) throws IOException, InterruptedException {
        /**
         * Runtime run = Runtime.getRuntime(); String verbose = ""; try{ Process
         * pr = run.exec(komut); pr.waitFor(); BufferedReader br = new
         * BufferedReader(new InputStreamReader(pr.getInputStream())); while
         * ((verbose+=br.readLine())!=null) { // Bu satırda program bekliyor.
         * br.readLine() --> program bekliyor. } } catch(Exception ex) {
         * verbose=ex.toString(); }
         */
        String verbose = "";
        try {
            Runtime run = Runtime.getRuntime();
            //Process myProcess = run.exec(komut);
            String[] komutDizi = komut.split(" ");
            /*
             * http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html?page=4
             */
            Process myProcess = run.exec(komutDizi);
            /*
             * http://stackoverflow.com/questions/1081084/is-java-runtime-execstring-platform-independent
             * I had some code that ran commands through
             * Runtime.getRuntime.exec(String), and it worked on Windows. When I
             * moved the code to Linux, it broke, and the only way of fixing it
             * was to switch to the exec(String[]) version. If I leave things
             * this way, will the code work the same on Windows and Linux, or
             * should I use the exec(String) on Windows and exec(String[]) on
             * Linux?
             */
            InputStream instream = myProcess.getInputStream(); // BufferedReader.readLine() --> program bekliyor. InputStream çalışıyor.
            int c;
            while ((c = instream.read()) != -1) {
                verbose += String.valueOf((char) c);
            }
            instream.close();
        } catch (Exception ex) {
            verbose = ex.toString();
        }

        return verbose;
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

    // ekran görüntüsü al.
    public static BufferedImage screenShotGetir() throws AWTException {
        /*
         * http://www.arulraj.net/2010/06/print-screen-using-java.html
         */

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        Rectangle rect = new Rectangle(dim);
        Robot myRobot = new Robot();

        BufferedImage myBufferedImage = myRobot.createScreenCapture(rect);
        return myBufferedImage;
    }

   

    // BufferedImage'i verilen isim ve tipteki dosyaya yazdırır.
    public static void bufferedImage2File(BufferedImage bi, String dosyaAd, String dosyaTip) throws IOException {
        /*
         * http://stackoverflow.com/questions/7738588/bufferedimage-turns-all-black-after-scaling-via-canvas
         */
        File outputDosya = new File(dosyaAd + "." + dosyaTip);
        ImageIO.write(bi, dosyaTip, outputDosya);
    }

    // BufferedImage'ı byte[]'a dönüştürür.
    public static byte[] bufferedImage2ByteArray(BufferedImage resim, String formatName) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //ImageIO.write(resim, "bmp", baos);
//        ImageIO.write(resim, "png", baos);  // "bmp" dosyalarının boyutu çok büyük oluyor. "png" formatı kullan.
        ImageIO.write(resim, formatName, baos);
        baos.flush();
        byte[] resimInByte = baos.toByteArray();
        baos.close();
        return resimInByte;
    }

    public static BufferedImage byteArray2BufferedImage(byte[] b) throws IOException {
        /*
        http://www.mkyong.com/java/how-to-convert-byte-to-bufferedimage-in-java/
         */
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        BufferedImage bi = ImageIO.read(bais);
        bais.close();
        return bi;
    }



    public static void periodicScreenShot2Soket(final Socket mySoket, int delayOfTimer, final String formatName) {

        /*
         * http://stackoverflow.com/questions/1006611/java-swing-timer
         * http://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
         * http://java.sun.com/products/jfc/tsc/articles/timer/
         */
        // "timerScreenShot" çalışıyorsa onu durdur.
        if (timerScreenShot != null && timerScreenShot.isRunning()) {
            timerScreenShot.stop();
            timerScreenShot = null;
        }

        ActionListener actionDinler = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    writeScreenShot2Soket(mySoket, formatName);
                } catch (IOException ex) {
                    Logger.getLogger(kayaNetworkAbstractClass1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (AWTException ex) {
                    Logger.getLogger(kayaNetworkAbstractClass1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //import javax.swing.Timer;
        timerScreenShot = new Timer(delayOfTimer, actionDinler);
        timerScreenShot.start();
    }

    public static void timerScreenShotDurdur() {
        /*
         * http://www.java2s.com/Code/JavaAPI/javax.swing/Timerstop.htm
         */
        if (timerScreenShot != null && timerScreenShot.isRunning()) {
            timerScreenShot.stop();
            timerScreenShot = null;
        }
    }

    public static void epostaGonder(String from, String to, String konu, String mesaj, LinkedList<String> detaylar) throws AddressException, MessagingException {
        /*
         * http://www.tutorialspoint.com/java/java_sending_email.htm
         * http://www.javabeat.net/2007/10/sending-mail-from-java/
         */
        Properties props = ePostaPropertiesVorBereiten(detaylar);

        Session postaSession = Session.getDefaultInstance(props);
        Message myMesaj = new MimeMessage(postaSession);

        InternetAddress fromAddress = new InternetAddress(from);
        InternetAddress toAddress = new InternetAddress(to);

        myMesaj.setFrom(fromAddress);
        myMesaj.setRecipient(Message.RecipientType.TO, toAddress);
        myMesaj.setSubject(konu);
        myMesaj.setText(mesaj);

        //Transport.send(myMesaj);

        /*
        http://stackoverflow.com/questions/1990454/using-javamail-to-connect-to-gmail-smtp-server-ignores-specified-port-and-tries
         */

        Transport transport = postaSession.getTransport("smtp");
        transport.connect(props.getProperty("mail.smtp.host"), Integer.parseInt(props.getProperty("mail.smtp.port")), from, props.getProperty("mail.smtp.password"));
        //transport.connect();
        //transport.connect(from, props.getProperty("mail.smtp.password"));

        transport.sendMessage(myMesaj, myMesaj.getAllRecipients());
        transport.close();
    }

    public static Properties ePostaPropertiesVorBereiten(LinkedList<String> epostaDetaylar) {
        Properties epostaProps = new Properties();
        //Properties props = System.getProperties();
        if (!epostaDetaylar.get(0).equals("")) // parola boş değilse
        {
            epostaProps.put("mail.smtp.password", epostaDetaylar.get(0));
        }
        if (!epostaDetaylar.get(1).equals("null")) // host boş değilse
        {
            epostaProps.put("mail.smtp.host", epostaDetaylar.get(1));
        }
        if (!epostaDetaylar.get(2).equals("-1")) // port geçerli ise
        {
            epostaProps.put("mail.smtp.port", epostaDetaylar.get(2));
        }
        if (!epostaDetaylar.get(3).equals("null")) {
            epostaProps.put("mail.smtp.auth", epostaDetaylar.get(3));
        }
        if (!epostaDetaylar.get(4).equals("null")) {
            epostaProps.put("mail.smtp.starttls.enable", epostaDetaylar.get(4));
        }

        return epostaProps;
    }
}
