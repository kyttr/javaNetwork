/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author kaya
 */
public class GenelMetotlar {

    /**
     *	creates ByteArray of length "len" out of "str".
     *  empty spaces is filled with " "
     */
    public static byte[] string2ByteArray(String str, int len) {
        return stringFillWithSpace(str, len).getBytes();
    }

    /**
     * Append " " to the end of "str" until its length is "len"
     * returns the first ten characters of the resulting string
     */
    public static String stringFillWithSpace(String str, int len) {
        while (str.length() < len) {
            str += " ";
        }
        return str.substring(0, len);
    }

    /**
     * boolean toLeft : if true Append " " to the left of "str" until its length is "len"
     * returns the first ten characters of the resulting string
     * 					if false stringFillWithSpace(String str,int len)
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
     *      convert a byte array to long array
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

    public static long[] subByteArray2LongArray(byte[] b, int offset, int length) {
        byte[] bTmp = new byte[length];
        System.arraycopy(b, offset, bTmp, 0, length);
        return byteArray2LongArray(bTmp);
    }

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

    public static Object derinCopy(Object nesne) throws IOException, ClassNotFoundException
    {
        /*
         * http://stackoverflow.com/questions/64036/how-do-you-make-a-deep-copy-of-an-object-in-java
         * http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
         * http://stackoverflow.com/questions/6182565/java-deep-copy-shallow-copy-clone
         * http://en.wikipedia.org/wiki/Object_copy#Deep_copy
         */

        byte[] b=object2ByteArray(nesne);
        Object o=byteArray2Object(b);
        return o;
    }
}
