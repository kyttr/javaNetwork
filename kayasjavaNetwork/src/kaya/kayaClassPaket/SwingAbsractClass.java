/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaya.kayaClassPaket;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kaya
 */
public abstract class SwingAbsractClass {

    /*
     *
     * http://www.devx.com/tips/Tip/14318 Firing Focused JButtons with the ENTER
     * Key Most users of GUI software know they can move the focus from one
     * control to another by pressing the tab key. There is usually some visual
     * cue to let them know which control has the current focus, for example,
     * when a JButton acquires the focus a small box will appear around its
     * label text. Most GUI users also expect that when a control, in particular
     * a button, has the focus, they can activate it by pressing the ENTER key.
     * With Swing applications, however, this may not always be the case; in
     * fact, pressing the ENTER key may sometimes activate a button that the
     * user emphatically does not want to be activated. This is due to a change
     * that was made to the Metal look-and-feel in Java 1.2. This is the default
     * look-and-feel for Swing apps, and until version 1.2 it behaved the way
     * most users expect it to, with the ENTER key activating a focused JButton.
     * Since version 1.2 though, pressing ENTER will not activate a focused
     * JButton in a Swing app using the Metal look-and-feel. Instead, the user
     * must press the spacebar to activate the focused JButton. Suppose a user
     * is presented with a Yes/No JOptionPane dialog that asks, "Do you want to
     * delete all of your files?" If the user tabs the focus over to the No
     * button and presses ENTER, the Yes button—the default button for the
     * JOptionPane—will be activated instead. Here is a simple static method
     * that accepts a JButton argument, that will alter the JButton so that
     * pressing the ENTER key will activate it when it is focused, provided that
     * is the action caused by pressing the spacebar:
     */
    public static void enterPressesWhenFocused(JButton button) {
//        button.registerKeyboardAction(
//                button.getActionForKeyStroke(
//                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
//                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
//                JComponent.WHEN_FOCUSED);
        button.registerKeyboardAction(
                button.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
    }

    public static void enterPressesWhenFocused2(JButton button) {
        /*
         * http://tips4java.wordpress.com/2008/10/10/key-bindings/
         * http://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html
         */
        KeyStroke existingKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true);
        KeyStroke addedKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true);
        InputMap im = button.getInputMap();
        im.put(addedKeyStroke, im.get(existingKeyStroke));
    }

    /*
     * perform action for "bilesen" when user presses "ENTER"
     */
    public static void enterPressesWhenFocusedJComponent(JComponent bilesen) {
    }
    
    
    /* 
     * Java has no implementation for the event that a Jlist item is double-clicked.
     * 
     * http://www.java2s.com/Tutorial/Java/0240__Swing/Selectioneventfordoubleclickinganiteminthelist.htm
     * http://stackoverflow.com/questions/4344682/double-click-event-on-jlist-element
     */
    MouseListener mouseListener = new MouseAdapter() {

        public void mouseClicked(MouseEvent mouseEvent) {
            JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2) {  // double click
                // bir şey yap
            }
        }
    };
    /*
     * jlist.addMouseListener(mouseListener);
     */
    
    /*
     *  Java has no implementation for the event that a JTable item is selected.
     */
    ListSelectionListener lsl = new ListSelectionListener() {

        public void valueChanged(ListSelectionEvent e) {
            // bir şey yap.
            //uiSwingMetotlar.tableInfo2TextArea(networkArayuzInfos, jTable_networkArayuz, jTextArea_descriptionNetworkArayuz);
        }
    };
    /*
     * ListSelectionModel rowSM = jTable_networkArayuz.getSelectionModel();
     * rowSM.addListSelectionListener(lsl);
     */
}
