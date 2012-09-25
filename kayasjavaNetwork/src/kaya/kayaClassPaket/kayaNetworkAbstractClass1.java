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
    private static String myEOF = ">myEOF<1>myEOF<2>myEOF<";    // EOF : end of file
    private static Timer timerScreenShot = null;
    private static String timerScreenShotDurdurStr = "timerScreenShotDurdur";
    private static String screenShotGetirStr = "screenShotGetir";
    private static String screenShotAlStr = "screenShotAl";
    private static String dosyaGetirStr = "dosyaGetir";
    private static String dosyalarGetirStr = "dosyalarGetir";
    private static String dosyaAlStr = "dosyaAl";
    private static String buBirByteDizidirStr = "buBirByteDizidir";
    private static String fileSystemViewAlStr = "fileSystemViewAl";
    //private static String defaultMutableTreeNodeAlStr="defaultMutableTreeNodeAl";
    public static String defaultMutableTreeNodeAlStr = "defaultMutableTreeNodeAl";
    public static String defaultMutableTreeNodeGetirStr = "defaultMutableTreeNodeGetir";
    private static String dosyaAbsolutePathSeparatorStr = "///";

    /*
     * organizes information corresponding to a object String strProp : names of
     * methods that get property, each element is separated with "," String
     * strDescrip : descriptions of the properties, each element is separated
     * with ":::" LinkedList<String> degerler : list of values of properties
     *
     * return LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = method that returns a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     */
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

    /*
     * veri alıp gönderirken, "props", "descrip" alanları her nesne için sabit,
     * onları da gönderip yer işgal etmeye gerek yok. Bilgiler listesinden
     * yalnızca "vals" kısmını dön.
     */
    public static LinkedList<String> valsFromPropsValsDescriptions(LinkedList<LinkedList<String>> propValueDescrip) {
        LinkedList<String> vals = new LinkedList<String>();
        int boyut = propValueDescrip.size();
        String tmp = null;

        for (int i = 0; i < boyut; i++) {
            tmp = propValueDescrip.get(i).get(1);   // get the second String in each element of propValueDescrip
            vals.add(tmp);
        }

        return vals;
    }

    /*
     * returns : information corresponding to a "File" object return
     * LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = method that returns a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     */
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
        try {
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
        } catch (Exception ex) {
        }

        String props = "canExecute,canRead,canWrite,exists,getAbsolutePath,getCanonicalPath,getFreeSpace,getName,getParent,getPath,getTotalSpace,getUsableSpace,hashCode,isAbsolute,isDirectory,isFile,isHidden,lastModified,length,toString,toURI,creationTime,fileKey,isDirectory,isOther,isRegularFile,isSymbolicLink,lastAccessTime,lastModifiedTime,size";

        String description;
        {
            description = "Tests whether the application can execute the file denoted by this abstract pathname.:::Tests whether the application can read the file denoted by this abstract pathname.:::Tests whether the application can modify the file denoted by this abstract pathname.:::Tests whether the file or directory denoted by this abstract pathname exists.:::Returns the absolute pathname string of this abstract pathname.:::Returns the canonical pathname string of this abstract pathname.:::Returns the number of unallocated bytes in the partition named by this abstract path name.:::Returns the name of the file or directory denoted by this abstract pathname.:::Returns the pathname string of this abstract pathname's parent, or null if this pathname does not name a parent directory.:::Converts this abstract pathname into a pathname string.:::Returns the size of the partition named by this abstract pathname.:::Returns the number of bytes available to this virtual machine on the partition named by this abstract pathname.:::Computes a hash code for this abstract pathname.:::Tests whether this abstract pathname is absolute.:::Tests whether the file denoted by this abstract pathname is a directory.:::Tests whether the file denoted by this abstract pathname is a normal file.:::Tests whether the file named by this abstract pathname is a hidden file.:::Returns the time that the file denoted by this abstract pathname was last modified.:::Returns the length of the file denoted by this abstract pathname.:::Returns the pathname string of this abstract pathname.:::Constructs a file: URI that represents this abstract pathname.:::Returns the creation time.:::Returns an object that uniquely identifies the given file, or null if a file key is not available.:::Tells whether the file is a directory.:::Tells whether the file is something other than a regular file, directory, or symbolic link.:::Tells whether the file is a regular file with opaque content.:::Tells whether the file is a symbolic link.:::Returns the time of last access.:::Returns the time of last modification.:::Returns the size of the file (in bytes).";
        }

        return bilgilerDon(props, description, listeVals);
    }

    /*
     * returns : information corresponding to a "URL" object return
     * LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = method that returns a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     */
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
            description = "the authority part of this URL.:::the contents of this URL.:::the default port number of the protocol associated with this URL.:::the file name of this URL.:::the host name of this URL, if applicable.:::the path part of this URL.:::the port number of this URL.:::the protocol name of this URL.:::the query part of this URL.:::the anchor (also known as the \"reference\") of this URL.:::the userInfo part of this URL.";
        }

        return bilgilerDon(props, description, listeVals);
    }

    /*
     * returns : information corresponding to a "URLConnection" object return
     * LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = method that returns a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     */
    public static LinkedList<LinkedList<String>> urlConnectionBilgileri(URLConnection urlBaglanti) throws IOException {
        LinkedList<String> listeVals = new LinkedList<String>();

        listeVals.add(String.valueOf(urlBaglanti.getAllowUserInteraction()));
        listeVals.add(String.valueOf(urlBaglanti.getConnectTimeout()));
        listeVals.add(String.valueOf(urlBaglanti.getContent()));
        listeVals.add(String.valueOf(urlBaglanti.getContentEncoding()));
        listeVals.add(String.valueOf(urlBaglanti.getContentLength()));
        listeVals.add(String.valueOf(urlBaglanti.getContentLengthLong()));
        listeVals.add(String.valueOf(urlBaglanti.getContentType()));
        listeVals.add(String.valueOf(urlBaglanti.getDate()));
        listeVals.add(String.valueOf(urlBaglanti.getDefaultAllowUserInteraction()));
        listeVals.add(String.valueOf(urlBaglanti.getDefaultUseCaches()));
        listeVals.add(String.valueOf(urlBaglanti.getDoInput()));
        listeVals.add(String.valueOf(urlBaglanti.getDoOutput()));
        listeVals.add(String.valueOf(urlBaglanti.getExpiration()));
        listeVals.add(String.valueOf(urlBaglanti.getFileNameMap()));
        listeVals.add(String.valueOf(urlBaglanti.getHeaderFields()));
        listeVals.add(String.valueOf(urlBaglanti.getIfModifiedSince()));
        listeVals.add(String.valueOf(urlBaglanti.getLastModified()));
        listeVals.add(String.valueOf(urlBaglanti.getPermission()));
        listeVals.add(String.valueOf(urlBaglanti.getReadTimeout()));
        try{
        listeVals.add(String.valueOf(urlBaglanti.getRequestProperties()));
        }
        catch(Exception ex)
                {
        listeVals.add(ex.toString());        
                }
        listeVals.add(String.valueOf(urlBaglanti.getURL()));
        listeVals.add(String.valueOf(urlBaglanti.getUseCaches()));
        listeVals.add(String.valueOf(urlBaglanti.toString()));

        String props = "getAllowUserInteraction,getConnectTimeout,getContent,getContentEncoding,getContentLength,getContentLengthLong,getContentType,getDate,getDefaultAllowUserInteraction,getDefaultUseCaches,getDoInput,getDoOutput,getExpiration,getFileNameMap,getHeaderFields,getIfModifiedSince,getLastModified,getPermission,getReadTimeout,getRequestProperties,getURL,getUseCaches,toString";

        String description;
        {
            description = "Returns the value of the allowUserInteraction field for this object.:::Returns setting for connect timeout.:::Retrieves the contents of this URL connection.:::Returns the value of the content-encoding header field.:::Returns the value of the content-length header field.:::Returns the value of the content-length header field as a long.:::Returns the value of the content-type header field.:::Returns the value of the date header field.:::Returns the default value of the allowUserInteraction field.:::Returns the default value of a URLConnection's useCaches flag.:::Returns the value of this URLConnection's doInput flag.:::Returns the value of this URLConnection's doOutput flag.:::Returns the value of the expires header field.:::Loads filename map (a mimetable) from a data file.:::Returns an unmodifiable Map of the header fields.:::Returns the value of this object's ifModifiedSince field.:::Returns the value of the last-modified header field.:::Returns a permission object representing the permission necessary to make the connection represented by this object.:::Returns setting for read timeout.:::Returns an unmodifiable Map of general request properties for this connection.:::Returns the value of this URLConnection's URL field.:::Returns the value of this URLConnection's useCaches field.:::Returns a String representation of this URL connection.";
        }

        return bilgilerDon(props, description, listeVals);
    }

    /*
     * returns : information corresponding to a "HttpURLConnection" object
     * return LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = method that returns a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     */
    public static LinkedList<LinkedList<String>> httpURLConnectionBilgileri(HttpURLConnection httpURLBaglanti) throws IOException {
        LinkedList<String> listeVals = new LinkedList<String>();

        listeVals.add(String.valueOf(httpURLBaglanti.getErrorStream()));
        listeVals.add(String.valueOf(httpURLBaglanti.getFollowRedirects()));
        listeVals.add(String.valueOf(httpURLBaglanti.getInstanceFollowRedirects()));
        listeVals.add(String.valueOf(httpURLBaglanti.getPermission()));
        listeVals.add(String.valueOf(httpURLBaglanti.getRequestMethod()));
        listeVals.add(String.valueOf(httpURLBaglanti.getResponseCode()));
        listeVals.add(String.valueOf(httpURLBaglanti.getResponseMessage()));
        listeVals.add(String.valueOf(httpURLBaglanti.usingProxy()));

        String props = "getErrorStream,getFollowRedirects,getInstanceFollowRedirects,getPermission,getRequestMethod,getResponseCode,getResponseMessage,usingProxy";

        String description;
        {
            description = "Returns the error stream if the connection failed but the server sent useful data nonetheless.:::Returns a boolean indicating whether or not HTTP redirects (3xx) should be automatically followed.:::Returns the value of this HttpURLConnection's instanceFollowRedirects field.:::Returns a SocketPermission object representing the permission necessary to connect to the destination host and port.:::Get the request method.:::Gets the status code from an HTTP response message.An int representing the three digit HTTP Status-Code.1xx: Informational2xx: Success3xx: Redirection4xx: Client Error5xx: Server ErrorGets the status code from an HTTP response message. For example, in the case of the following status lines:HTTP/1.0 200 OKHTTP/1.0 401 Unauthorized:::Gets the HTTP response message, if any, returned along with the response code from a server.Gets the HTTP response message, if any, returned along with the response code from a server. From responses like:HTTP/1.0 200 OKHTTP/1.0 404 Not FoundExtracts the Strings \"OK\" and \"Not Found\" respectively. Returns null if none could be discerned from the responses (the result was not valid HTTP).:::Indicates if the connection is going through a proxy.";
        }

        // Bir "HttpURLConnection" nesnesi aynı zamanda bir "URLConnection" nesnesidir. Bu nedenle "urlConnectionBilgileri()" metodundan gelen bilgiler de bir "HttpURLConnection" nesnesinin bilgilerinde gözükmeli. Bunun için superclass olan "URLConnection"daki bilgileri de aldım.
        LinkedList<LinkedList<String>> httpUrlConnectionInfos = bilgilerDon(props, description, listeVals);
        LinkedList<LinkedList<String>> UrlConnectionInfos = urlConnectionBilgileri(httpURLBaglanti);

        httpUrlConnectionInfos.addAll(UrlConnectionInfos);
        return httpUrlConnectionInfos;
    }

    /* Every Java application has a single instance of class Runtime that allows
     * the application to interface with the environment in which the
     * application is running. The current runtime can be obtained from the
     * getRuntime method.
     *
     * returns : information corresponding to a "Runtime" return
     * LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = name of a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     *
     * http://stackoverflow.com/questions/25552/using-java-to-get-os-level-system-information
     */
    public static LinkedList<LinkedList<String>> runtimeBilgileri() {
        Runtime r = Runtime.getRuntime();

        LinkedList<String> listeVals = new LinkedList<String>();

        listeVals.add(String.valueOf(r.availableProcessors()));
        listeVals.add(String.valueOf(r.freeMemory()));
        listeVals.add(String.valueOf(r.maxMemory()));
        listeVals.add(String.valueOf(r.totalMemory()));



        String props = "availableProcessors(),freeMemory(),maxMemory(),totalMemory()";

        String description;
        {
            description = " number of processors available to the Java virtual machine.::: amount of free memory in the Java Virtual Machine.::: maximum amount of memory that the Java virtual machine will attempt to use.::: total amount of memory in the Java virtual machine.";
        }

        return bilgilerDon(props, description, listeVals);
    }

    /*
     * The System class maintains a Properties object that describes the
     * configuration of the current working environment. System properties
     * include information about the current user, the current version of the
     * Java runtime, and the character used to separate components of a file
     * path name. returns : information corresponding to a "System Properties"
     * return LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = name of a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     */
    public static LinkedList<LinkedList<String>> systemPropertiesBilgileri() {
        LinkedList<String> listeVals = new LinkedList<String>();

        String props = "";
        String description = "";
        // temp vars.
        String tmpPropName;
        String tmpPropVal;

        //http://www.exampledepot.com/egs/java.lang/GetAllSysProps.html
        // Get all system properties
        Properties ozellikler = System.getProperties();
        // Enumerate all system properties
        Enumeration enumerasyon = ozellikler.propertyNames();
        while (enumerasyon.hasMoreElements()) {
            // Get property name
            tmpPropName = (String) enumerasyon.nextElement();
            props += "," + tmpPropName;
            description += ":::" + "yorum yok";

            // Get property value
            tmpPropVal = (String) ozellikler.get(tmpPropName);
            listeVals.add(tmpPropVal);
        }
        // get rid of excess delimiters
        props = props.replaceFirst(",", "");
        description = description.replaceFirst(":::", "");

        return bilgilerDon(props, description, listeVals);
    }

    /*
     * returns : information corresponding to a "ServerSocket" object return
     * LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = method that returns a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     */
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

    /*
     * returns : information corresponding to a "Socket" object return
     * LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = method that returns a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     */
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

    /*
     * get the HTML source of a URL return String : "HTML" source of the URL
     *
     * http://stackoverflow.com/questions/9357822/how-to-show-regional-characters-in-android
     */
    public static String readURLDirectly2HTMLString(URL url) throws IOException {
        // "InputStream" den "String" okumak için yeni bir metot yazdım, artık onu kullanıyorum.
        InputStream is = url.openStream();
        return GenelMetotlar.readStringfromInputStream(is);
    }

    /*
     * initialize and set a ServerSocket according to given parameters
     * LinkedList<Integer> parametreler : list of parameters describing the
     * Serverocket
     * parametreler={port,backlog,connectionTime,latency,bandwidth,size,on,timeout}
     *
     * return ServerSocket
     */
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

    /*
     * initialize and set a Socket according to given parameters
     * LinkedList<Integer> parametreler : list of parameters describing the
     * Serverocket
     * parametreler={port,keepAlive,ooBlnline,connectionTime,latency,bandwidth,receiveBufferSize,reuseAddress,
     * sendBufferSize,boolsoLinger,linger,timeout,tcpNoDelay,trafficClass}
     * String host : host of the "Socket"
     *
     * return Socket
     */
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
         * bazı parametreler boolean tipinde, onlar için uygulama şöyle: 0 =
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

    /*
     * write String message to Socket. PrintWriter of the Socket is closed after
     * the operation, so the Socket also will be closed. String mesaj : line of
     * message Socket mySoket :
     */
    public static void write2Soket(Socket mySoket, String mesaj) throws IOException {
        PrintWriter pw = new PrintWriter(mySoket.getOutputStream(), true);
        pw.println(mesaj);    // bunu kullanma
        //pw.println(mesaj + "\n");
        pw.println(myEOF + "\n");     // readFromSoket'te bu görülünce döngüden çıkılacak.
        pw.close();
    }

    /*
     * write String message to Socket. String mesaj : line of message Socket
     * mySoket : boolean closePrintWriterOnExit : if true, PrintWriter of the
     * Socket is closed after the operation, so the Socket also will be closed.
     */
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

    /*
     * write String message to Socket. initialize and set Socket, if it is
     * closed. String mesaj : line of message Socket mySoket :
     *
     */
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
     * Sokete "byteDizi" yaz. "byteDizi" uzunluğu bilinmiyor. Bu nedenle
     * "byteDizi"den hemen önce onun uzunluğunu gönder.
     *
     * http://stackoverflow.com/questions/1176135/java-socket-send-receive-byte-array
     * işe yaramadı.
     */
    public static void writeByteArrayAndLength2Soket(Socket mysoket, byte[] byteDizi) throws IOException {
        /*
         * http://stackoverflow.com/questions/1176135/java-socket-send-receive-byte-array
         * işe yaramadı.
         */
        OutputStream os = mysoket.getOutputStream();

        int len = byteDizi.length;
        String lenStr = String.valueOf(len);
        // lenBytes : "byteDizi" uzunluğunu tutan byte dizisi
        byte[] lenBytes = lenStr.getBytes();
        // lenOflenBytes : "lenBytes" ın uzunluğu
        // varsayım : 0<lenOflenBytes<256, bir anlamda "byteDizi" nin uzunluğu en fazla 255 basamaklı olabilir.
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
     * Sokete "dosya" yaz. "dosya" uzunluğu bilinmiyor. Bu nedenle "dosya"den
     * hemen önce onun uzunluğunu gönder.
     */
    public static void writeFileAndLength2Soket(Socket mysoket, File dosya) throws FileNotFoundException, IOException {
        byte[] byteDizi = new byte[(int) dosya.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(dosya));
        bis.read(byteDizi, 0, byteDizi.length);
        writeByteArrayAndLength2Soket(mysoket, byteDizi);
    }

    /*
     * Sokete "dosya" yaz. "dosya" uzunluğu biliniyor.
     */
    public static void writeFile2Soket(Socket mysoket, File dosya) throws IOException {
        byte[] byteDizi = new byte[(int) dosya.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(dosya));
        bis.read(byteDizi, 0, byteDizi.length);
        writeByteArray2Soket(mysoket, byteDizi);
    }

    /*
     * Sokete "dosya" yaz. öncelikle byte[] gönderdiğini anlat :
     * buBirByteDizidirStr "dosya" gönderdiğini anlat : dosyaAlStr "dosya"nın
     * boyutunu gönder. "dosya"nın ismini gönder. "dosya"nın kendisini gönder.
     * Dosya uzunluğu artık biliniyor.
     */
    public static void writeFile2SoketHighLevel(Socket mySoket, File dosya) throws IOException {
        // öncelikle byte dizi gönderdiğini anlat.
        byte[] buBirByteDizidirBytes = buBirByteDizidirStr.getBytes();
        writeByteArray2Soket(mySoket, buBirByteDizidirBytes);

        // dosya gönderdiğini anlat 
        String dosyaAlPrefix = dosyaAlStr + (int) dosya.length();
        byte[] dosyaAlPrefixBytes = dosyaAlPrefix.getBytes();
        writeByteArrayAndLength2Soket(mySoket, dosyaAlPrefixBytes); //Dosya uzunluğu artık biliniyor.

        // dosyaName to soket.
        byte[] dosyaNameBytes = dosya.getName().getBytes();
        writeByteArrayAndLength2Soket(mySoket, dosyaNameBytes);

        // dosya to soket. Dosya uzunluğu artık biliniyor.
        writeFile2Soket(mySoket, dosya);
    }

    /*
     * Sokete "ScreenShot" yaz. String formatName : "ScreenShot" formatı.
     */
    public static void writeScreenShot2Soket(Socket mySoket, String formatName) throws AWTException, IOException {
        BufferedImage bi = GenelMetotlar.screenShotGetir();
        byte[] b = GenelMetotlar.bufferedImage2ByteArray(bi, formatName);

        // byte[] gönderdiğini anlat  ve byte[] boyutunu yaz.
                /*
         * !! Bu mesaj BYTE ARRAY olarak gitmeli. String olarak giderse hemen
         * sonraki byte[] ile karışıyor.
         */
        byte[] buBirByteDizidirBytes = buBirByteDizidirStr.getBytes();
        writeByteArray2Soket(mySoket, buBirByteDizidirBytes);
        String screenShotAlPrefix = screenShotAlStr + b.length;
        byte[] screenShotAlPrefixBytes = screenShotAlPrefix.getBytes();
        writeByteArrayAndLength2Soket(mySoket, screenShotAlPrefixBytes);
        // karşı taraf "b" nin uzunluğunu artık biliyor.
        writeByteArray2Soket(mySoket, b);
    }

    /*
     * çalışmıyor, kullanılmıyor.
     */
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

    /*
     * Sokete "nesne" yaz. String prefixStr : karşı tarafa "nesne" nin tipini
     * anlatan prefix. Ör : "dosyaAl"
     */
    public static void writeObject2SoketHighLevel(Socket mySoket, Object nesne, String prefixStr) throws IOException {
        // öncelikle byte dizi gönderdiğini anlat.
        byte[] buBirByteDizidirBytes = buBirByteDizidirStr.getBytes();
        writeByteArray2Soket(mySoket, buBirByteDizidirBytes);

        // "nesne"yi byte dizisine çevir. 
        // "nesne" serileştirilebilir olmalı (implements Serializable).
        byte[] b = GenelMetotlar.object2ByteArray(nesne);

        // karşı tarafa "nesne" tipini ve "nesne" nin uzunluğunu gönder.
        String prefixStr2 = prefixStr + b.length;
        byte[] PrefixBytes = prefixStr2.getBytes();
        writeByteArrayAndLength2Soket(mySoket, PrefixBytes);

        // karşı taraf artık "nesne" nin uzunluğunu biliyor.
        writeByteArray2Soket(mySoket, b);
    }

    public kayaNetworkAbstractClass1() {
    }

    /*
     * "mySoket" ten gelen mesajı oku ve bir "Object" olarak döndür.
     *
     * karşı taraftaki soketten veri geldiğinde ilk çalışacak okuma metodu
     * budur. Bu metod gelen verinin String veya byte[] dizi olup olmadığına
     * karar verir. Gelen veri byte[] ise o veriye uygun metotları çağırır.
     * Gelen veri bir istek içeriyorsa, "mySoket" ona göre cevap verir.
     *
     * Gelen verinin sonuna gelindiği "myEOF" String'i ile anlaşılır. "myEOF"
     * görülünce okuma durdurulur.
     *
     */
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
           /*
             * A
             * while ((lineOfMesaj = br.readLine()) != null) { gelenMesaj +=
             * lineOfMesaj + "\n"; } br.close(); A
             */

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
                outputOfCommand = GenelMetotlar.executeString(gelenMesaj.substring(1).trim());
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
        } else if (gelenMesaj.startsWith(timerScreenShotDurdurStr)) // diğer taraf artık screenShot istemiyor.
        {
            GenelMetotlar.timerDurdur(timerScreenShot);
        } else if (gelenMesaj.startsWith(defaultMutableTreeNodeGetirStr)) // diğer taraf dosya sistemi istiyor.
        {
            gelenMesaj = gelenMesaj.replace(defaultMutableTreeNodeGetirStr, "");
            String[] paramsForDMTN = gelenMesaj.split(",", 2);     // oluşacak parametre dizisi en fazla 2 elemanlı olabilir.
            int depthOfTree = Integer.parseInt(paramsForDMTN[0]);
            String kokDosyaStr = paramsForDMTN[1];
            File kokDosya = new File(kokDosyaStr);

            DosyaVisitor dv = null;
            if (depthOfTree > 0) {
                if (kokDosya.exists()) {
                    dv = new DosyaVisitor(kokDosya, depthOfTree);
                } else {
                    dv = new DosyaVisitor(depthOfTree);
                }

            } else {
                dv = new DosyaVisitor();
            }

            writeObject2SoketHighLevel(mySoket, dv.dmtnDosyaBilgilerTree, kayaNetworkAbstractClass1.defaultMutableTreeNodeAlStr);
        } else if (gelenMesaj.startsWith(dosyaGetirStr)) //karşı taraf dosya istiyor.
        {
            gelenMesaj = gelenMesaj.replaceFirst(dosyaGetirStr, "");  // şu anda gelenMesaj=<dosyaAbsolutePath>
            File dosyaToBeSent = new File(gelenMesaj);
            writeFile2SoketHighLevel(mySoket, dosyaToBeSent);
        } else if (gelenMesaj.startsWith(dosyalarGetirStr)) {
            gelenMesaj = gelenMesaj.replaceFirst(dosyalarGetirStr, "");  // şu anda gelenMesaj=<dosyaAbsolutePathLL>
            String[] dosyalarDizi = gelenMesaj.split(dosyaAbsolutePathSeparatorStr);
            File dosyaToBeSent;
            for (int i = 0; i < dosyalarDizi.length; i++) {
                dosyaToBeSent = new File(dosyalarDizi[i]);
                writeFile2SoketHighLevel(mySoket, dosyaToBeSent);
            }
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
     * Socket üzerinden gönderilen veriyi oku. Gönderilen verinin uzunluğu
     * bilinmiyor. Verinin uzunluğunun verinin en başına koyulduğunu
     * varsayıyorum. Gelen verinin en başından gelen verinin uzunluğunu al.
     * Sonrasında veriyi alıp byte[] olarak döndür.
     *
     * http://stackoverflow.com/questions/6684665/java-byte-array-to-string-to-byte-array
     */
    public static byte[] readByteArrayFromSoket(Socket mySoket) throws IOException {
        /*
         * ESKİ KOD
         *
         * InputStream is = mySoket.getInputStream(); byte[] mesajByteDizi = new
         * byte[is.available()]; // Büyük değerler için is.available()'a
         * güvenme. is.read(mesajByteDizi); return mesajByteDizi;
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

        byte[] gelenVeriBytes = GenelMetotlar.readByteArrayFromInputStream(is, byteDiziUzunluk);

        return gelenVeriBytes;
    }

    /*
     * Socket üzerinden gönderilen veriyi oku. Verinin uzunluğu verinin önüne
     * yerleştirilmiş değil.
     *
     * int lenOfByteDizi : gönderilen verinin uzunluğu.
     */
    public static byte[] readByteArrayFromSoket(Socket mySoket, int lenOfByteDizi) throws IOException {
        InputStream is = mySoket.getInputStream();
        byte[] b = GenelMetotlar.readByteArrayFromInputStream(is, lenOfByteDizi);
        return b;
    }

    public static BufferedImage readBufferedImageFromSoket(Socket mySoket, int boyutOfByteDizi) throws IOException, InterruptedException {
        BufferedImage bi = GenelMetotlar.byteArray2BufferedImage(readByteArrayFromSoket(mySoket, boyutOfByteDizi));
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

    /*
     * diğer tarafta dosya alma isteği gönder. String dosyaAbsolutePath :
     * istenilen dosyanın mutlak ismi
     */
    public static void requestFileFromSoket(String dosyaAbsolutePath, Socket mySoket) throws IOException {
        String gidecekMesaj = dosyaGetirStr + dosyaAbsolutePath;
        write2Soket(mySoket, gidecekMesaj, false);
    }

    /*
     * diğer tarafa birden fazla dosya alma isteği gönder. LinkedList<String>
     * dosyaAbsolutePathLL : istenilen dosyaların mutlak isimlerini taşıyan
     * dizi.
     */
    public static void requestFilesFromSoket(LinkedList<String> dosyaAbsolutePathLL, Socket mySoket) throws IOException {
        String gidecekMesaj = dosyalarGetirStr;
        Iterator iter = dosyaAbsolutePathLL.iterator();
        while (iter.hasNext()) {
            gidecekMesaj += dosyaAbsolutePathSeparatorStr + iter.next();
        }
        gidecekMesaj = gidecekMesaj.replaceFirst(dosyaAbsolutePathSeparatorStr, "");
        write2Soket(mySoket, gidecekMesaj, false);
    }

    /*
     * diğer tarafa PeriodicScreenShot isteği gönder. int peryotOfScreenShot :
     * screenShot peryodu String formatTip : screenShot formatı
     */
    public static void requestPeriodicScreenShot(int peryotOfScreenShot, String formatTip, Socket mySoket) throws IOException {
        String gidecekMesaj = "screenShotGetir" + peryotOfScreenShot + "," + formatTip;
        kayaNetworkAbstractClass1.write2Soket(mySoket, gidecekMesaj, false);
    }

    public static void requestTimerScreenShotDurdur(Socket mySoket) throws IOException {
        write2Soket(mySoket, timerScreenShotDurdurStr, false);
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

    /*
     * Sokete belirli bir peryotta "screenShot" yaz.
     *
     * int delayOfTimer : "screenShot" gönderme peryodu .
     *
     * final String formatName : gönderilecek "screenShot" resminin formatı. Ö:
     * "png","jpg".
     *
     * http://stackoverflow.com/questions/1006611/java-swing-timer
     * http://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
     * http://java.sun.com/products/jfc/tsc/articles/timer/
     */
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

    /*
     * http://www.tutorialspoint.com/java/java_sending_email.htm
     * http://www.javabeat.net/2007/10/sending-mail-from-java/
     */
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
         * http://stackoverflow.com/questions/1990454/using-javamail-to-connect-to-gmail-smtp-server-ignores-specified-port-and-tries
         */

        Transport transport = postaSession.getTransport("smtp");
        transport.connect(props.getProperty("mail.smtp.host"), Integer.parseInt(props.getProperty("mail.smtp.port")), from, props.getProperty("mail.smtp.password"));
        //transport.connect();
        //transport.connect(from, props.getProperty("mail.smtp.password"));

        transport.sendMessage(myMesaj, myMesaj.getAllRecipients());
        transport.close();
    }

    /*
     * gönderilecek eposta ile ilgili detayları "Properties" nesnesine aktar.
     * LinkedList<String> epostaDetaylar :
     * {password,host,port,auth,starttls.enable}
     */
    public static Properties ePostaPropertiesVorBereiten(LinkedList<String> epostaDetaylar) {
        Properties epostaProps = new Properties();
        //Properties props = System.getProperties();
        //LinkedList<String> epostaDetaylar : {password,host,port,auth,starttls.enable}
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

    /*
     * returns : information corresponding to a "NetworkInterface" object return
     * LinkedList<LinkedList<String>> each LinkedList<String> has size 3.
     *
     * 1. element = method that returns a property
     *
     * 2. element = value of the property
     *
     * 3. element = description of the property
     */
    public static LinkedList<LinkedList<String>> networkInterfaceBilgileri(NetworkInterface ni) throws SocketException {
        LinkedList<String> listeVals = new LinkedList<String>();

        listeVals.add(String.valueOf(ni.getDisplayName()));
        listeVals.add(String.valueOf(ni.getHardwareAddress()));
        listeVals.add(String.valueOf(ni.getIndex()));
        listeVals.add(String.valueOf(ni.getInetAddresses().toString()));
        listeVals.add(String.valueOf(ni.getInterfaceAddresses().toString()));
        listeVals.add(String.valueOf(ni.getMTU()));
        listeVals.add(String.valueOf(ni.getName()));
        listeVals.add(String.valueOf(ni.getNetworkInterfaces()));
        if (ni.getParent() != null) {
            listeVals.add(String.valueOf(ni.getParent().getName()));
        } else {
            listeVals.add("no parent");
        }
        listeVals.add(String.valueOf(ni.getSubInterfaces().toString()));
        listeVals.add(String.valueOf(ni.isLoopback()));
        listeVals.add(String.valueOf(ni.isPointToPoint()));
        listeVals.add(String.valueOf(ni.isUp()));
        listeVals.add(String.valueOf(ni.isVirtual()));
        listeVals.add(String.valueOf(ni.supportsMulticast()));
        listeVals.add(String.valueOf(ni.toString()));


        String props = "getDisplayName,getHardwareAddress,getIndex,getInetAddresses,getInterfaceAddresses,getMTU,getName,getNetworkInterfaces,getParent,getSubInterfaces,isLoopback,isPointToPoint,isUp,isVirtual,supportsMulticast,toString";

        String description;
        {
            description = "Get the display name of this network interface.:::Returns the hardware address (usually MAC) of the interface if it has one and if it can be accessed given the current privileges.:::Returns the index of this network interface.:::Convenience method to return an Enumeration with all or a subset of the InetAddresses bound to this network interface.:::Get a List of all or a subset of the InterfaceAddresses of this network interface.:::Returns the Maximum Transmission Unit (MTU) of this interface.:::Get the name of this network interface.:::Returns all the interfaces on this machine.:::Returns the parent NetworkInterface of this interface if this is a subinterface, or null if it is a physical (non virtual) interface or has no parent.:::Get an Enumeration with all the subinterfaces (also known as virtual interfaces) attached to this network interface.:::Returns whether a network interface is a loopback interface.:::Returns whether a network interface is a point to point interface.:::Returns whether a network interface is up and running.:::Returns whether this interface is a virtual interface (also called subinterface).:::Returns whether a network interface supports multicasting or not.:::Returns a string representation of the object.";
        }

        return bilgilerDon(props, description, listeVals);
    }
}
