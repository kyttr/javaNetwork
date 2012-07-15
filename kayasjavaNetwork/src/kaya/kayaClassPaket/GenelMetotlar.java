/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author kaya
 */
public class GenelMetotlar {

    /**
     * creates ByteArray of length "len" out of "str". empty spaces is filled
     * with " "
     */
    public static byte[] string2ByteArray(String str, int len) {
        return stringFillWithSpace(str, len).getBytes();
    }

    /**
     * Append " " to the end of "str" until its length is "len" returns the
     * first ten characters of the resulting string
     */
    public static String stringFillWithSpace(String str, int len) {
        while (str.length() < len) {
            str += " ";
        }
        return str.substring(0, len);
    }

    /**
     * boolean toLeft : if true Append " " to the left of "str" until its length
     * is "len" returns the first ten characters of the resulting string if
     * false stringFillWithSpace(String str,int len)
     */
    public static String stringFillWithSpace(String str, int len, boolean toLeft) {
        if (toLeft) {
            while (str.length() < len) {
                str = " " + str;
            }
            return str.substring(0, len);
        } else {
            return stringFillWithSpace(str, len);
        }
    }

    /*
     * convert a byte array to long array
     */
    public static long[] byteArray2LongArray(byte[] b) {
        int len = b.length;
        long[] arr = new long[len];
        for (int i = 0; i < len; i++) {
            arr[i] = b[i];
        }
        return arr;
    }

    /*
     * convert a long array to a byte array
     */
    public static byte[] longArray2ByteArray(long[] l) {
        int len = l.length;
        byte[] arr = new byte[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (byte) l[i];
        }
        return arr;
    }

    /*
     * convert a String array with numeric values to a long array
     */
    public static long[] stringArray2LongArray(String[] s) {
        int len = s.length;
        long[] arr = new long[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Long.parseLong(s[i].trim());
        }
        return arr;
    }

    /*
     * "byte[]" nesnesinin alt dizisini bir "long" dizisine dönüştür.
     */
    public static long[] subByteArray2LongArray(byte[] b, int offset, int length) {
        byte[] bTmp = new byte[length];
        System.arraycopy(b, offset, bTmp, 0, length);
        return byteArray2LongArray(bTmp);
    }

    /*
     * "Object" nesnesini "byte[]" tipine dönüştür.
     *
     * http://www.javafaq.nu/java-article236.html
     * http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
     */
    public static byte[] object2ByteArray(Object obj) throws java.io.IOException {
        /*
         * http://www.javafaq.nu/java-article236.html
         * http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
         */

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        bos.close();
        byte[] data = bos.toByteArray();
        return data;
    }

    /*
     * "byte[]" nesnesini "Object" tipine dönüştür.
     */
    public static Object byteArray2Object(byte[] b) throws IOException, ClassNotFoundException {
        /*
         * http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
         */
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        ObjectInput oi = new ObjectInputStream(bis);
        Object nesne = oi.readObject();
        bis.close();
        oi.close();

        return nesne;
    }

    /*
     * "nesne"nin derin kopyasını oluştur. NOT : "nesne" "serializable" olmalı.
     *
     * http://stackoverflow.com/questions/64036/how-do-you-make-a-deep-copy-of-an-object-in-java
     * http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
     * http://stackoverflow.com/questions/6182565/java-deep-copy-shallow-copy-clone
     * http://en.wikipedia.org/wiki/Object_copy#Deep_copy
     */
    public static Object derinCopy(Object nesne) throws IOException, ClassNotFoundException {
        /*
         * http://stackoverflow.com/questions/64036/how-do-you-make-a-deep-copy-of-an-object-in-java
         * http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
         * http://stackoverflow.com/questions/6182565/java-deep-copy-shallow-copy-clone
         * http://en.wikipedia.org/wiki/Object_copy#Deep_copy
         */

        byte[] b = object2ByteArray(nesne);
        Object o = byteArray2Object(b);
        return o;
    }

    /*
     * BufferedImage'ı byte[]'a dönüştürür.
     */
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

    /*
     * "BufferedImage" nesnesini verilen isim ve tipteki dosyaya yazdırır.
     * varsayılan dosya tipi = "png"
     *
     * http://stackoverflow.com/questions/7738588/bufferedimage-turns-all-black-after-scaling-via-canvas
     */
    public static void bufferedImage2File(BufferedImage bi, String dosyaAd, String dosyaTip) throws IOException {
        /*
         * http://stackoverflow.com/questions/7738588/bufferedimage-turns-all-black-after-scaling-via-canvas
         */
        if (dosyaTip.isEmpty() || dosyaTip == null) {
            dosyaTip = "png";
        }
        File outputDosya = new File(dosyaAd + "." + dosyaTip);
        ImageIO.write(bi, dosyaTip, outputDosya);
    }

    /*
     * convert the input "byte[]" to a BufferedImage object
     * http://www.mkyong.com/java/how-to-convert-byte-to-bufferedimage-in-java/
     */
    public static BufferedImage byteArray2BufferedImage(byte[] b) throws IOException {
        /*
         * http://www.mkyong.com/java/how-to-convert-byte-to-bufferedimage-in-java/
         */
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        BufferedImage bi = ImageIO.read(bais);
        bais.close();
        return bi;
    }


    /*
     * executes the input "String" as a shell command and returns its output as
     * "String"
     */
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

    /*
     * "InputStream" nesnesinden "lenOfByteDizi" sayıda "byte" bir "byte[]"
     * dizisine oku.
     */
    public static byte[] readByteArrayFromInputStream(InputStream is, int lenOfByteDizi) throws IOException {
        byte[] byteDizi = new byte[lenOfByteDizi];

        int bytesRead = 0;    // "gelenVeriBytes" dan okunmuş toplam byte sayısı
        int bytesReadTmp;   // "gelenVeriBytes" dan bir döngüde okunmuş byte sayısı

        /*
         * !! Aşağıdaki döngü !!. tüm veri okunana kadar dön. !!
         * InputStream.read(byte[] b, int off, int len) --> fonksiyonu büyük
         * "len" değerleri için tüm veriyi okumayı sağlayamıyor. Bu nedenle tüm
         * veri okunana kadar "read(byte[] b, int off, int len)" fonksiyonu
         * çağrılmalı. Bu durumu bilmediğim için uzun byte dizisi gönderme
         * konusunda 3 gün boyunca sıkıntı çektim, debelenip durdum.
         */
        while (bytesRead < lenOfByteDizi) {
            bytesReadTmp = is.read(byteDizi, bytesRead, lenOfByteDizi - bytesRead);
            bytesRead += bytesReadTmp;
        }

        return byteDizi;
    }

    /*
     * Bir "InputStream" nesnesindeki veriyi bir "String"e aktar. Bunu yaparken
     * "BufferedReader" ve "InputStreamReader" nesneleri kullan. return
     * "InputStream" nesnesindeki veri
     *
     * http://stackoverflow.com/questions/9357822/how-to-show-regional-characters-in-android
     */
    public static String readStringfromInputStream(InputStream is) throws IOException {
        /*
         * http://stackoverflow.com/questions/9357822/how-to-show-regional-characters-in-android
         */
        //InputStreamReader isr = new InputStreamReader(is,"UTF-8");  //ç,ü,ğ,ş,ö
        // InputStreamReader isr = new InputStreamReader(is);    // bu da çalışıyor.   
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);
        String veriTemp;
        String veri = "";

        while ((veriTemp = bf.readLine()) != null) {
            veri += veriTemp + "\n";
        }

        // kaynakları kapatmayayım, şimdilik
        //isr.close();
        //bf.close();

        return veri;
    }

    /*
     * ekran görüntüsü al.
     * http://www.arulraj.net/2010/06/print-screen-using-java.html
     *
     */
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

    /*
     * çalışan bir "Timer" nesnesini durdur.
     *
     * http://www.java2s.com/Code/JavaAPI/javax.swing/Timerstop.htm
     */
    public static void timerDurdur(Timer myTimer) {
        /*
         * http://www.java2s.com/Code/JavaAPI/javax.swing/Timerstop.htm
         */
        if (myTimer != null && myTimer.isRunning()) {
            myTimer.stop();
            myTimer = null;
        }
    }
}
