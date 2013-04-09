/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;

/**
 *
 * @author michalblazek
 */
public class DisplejString {

    public String vstup;
    private boolean citatel = false;
    private boolean jmenovatel = false;

    public DisplejString(String vstup) {
        this.vstup = vstup;
    }

//    public ArrayList<Object> parceToArrayList() {
//        ArrayList<Object> list = new ArrayList<Object>();
//        int k = 0;
//        for (int i = 0; i < vstup.length(); i++) {
//
//            if (vstup.length() != 0) {
//                if (citatel) {
//                    if (list.size() < k + 1) {
//                        list.add(k, vstup);
//                        list.add(k + 1, 1);
//                    }
//                    list.set(k, vstup);
//                    list.set(k + 1, 1);
//                    if (vstup.charAt(i) == '\t') {
//                        list.set(k, vstup.substring(0, i));
//                        list.set(k + 1, 1);
//                        citatel = false;
//                        jmenovatel = true;
//                        vstup = vstup.substring(i + 1, vstup.length());
//                        i = 0;
//                        k += 2;
//                    }
//                }
//            }
//            if (vstup.length() != 0) {
//                if (jmenovatel) {
//                    if (list.size() < k + 1) {
//                        list.add(k, vstup);
//                        list.add(k + 1, 2);
//                    }
//                    list.set(k, vstup);
//                    list.set(k + 1, 2);
//                    if (vstup.charAt(i) == '\t') {
//                        list.set(k, vstup.substring(0, i));
//                        list.set(k + 1, 2);
//                        jmenovatel = false;
//                        citatel = false;
//                        vstup = vstup.substring(i + 1, vstup.length());
//                        i = 0;
//                        k += 2;
//                    }
//
//                }
//            }
//            if (vstup.length() != 0) {
//                if (jmenovatel == false && citatel == false) {
//                    if (list.size() < k + 1) {
//                        list.add(k, vstup);
//                        list.add(k + 1, 0);
//                    }
//                    list.set(k, vstup);
//                    list.set(k + 1, 0);
//                    if ((vstup.charAt(i) == '/') && (vstup.charAt(i - 1) == '/')) {
//                        list.set(k, vstup.substring(0, i - 1));
//                        list.set(k + 1, 0);
//                        citatel = true;
//                        jmenovatel = false;
//                        vstup = vstup.substring(i + 1, vstup.length());
//                        i = -1;
//                        k += 2;
//                    }
//                }
//            }
//        }
//        return list;
//    }
}
