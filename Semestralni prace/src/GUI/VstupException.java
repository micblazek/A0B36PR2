/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author michalblazek
 */
public class VstupException extends Exception{
    int code;

    public VstupException(int code) {
        this.code = code;
    }
    
}
