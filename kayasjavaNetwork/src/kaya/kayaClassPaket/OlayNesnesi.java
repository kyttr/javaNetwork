/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kaya.kayaClassPaket;

import java.util.EventObject;

/**
 *
 * @author kaya
 * www.javaworld.com/javaworld/javaqa/2002-03/01-qa-0315-happyevent.html?page=2
 */
public class OlayNesnesi extends EventObject{
    
    private Object ilgiliNesne;

    public OlayNesnesi( Object source, Object ilgiliNesne ) {
        super( source );
        this.ilgiliNesne=ilgiliNesne;
    }

    
    /*
     * Olaya neden olan nesneyi getirir.
     */
    public Object kaynakNesne() {
        return ilgiliNesne;
    }
}
