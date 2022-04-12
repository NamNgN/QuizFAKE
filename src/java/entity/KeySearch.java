/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class KeySearch {
    private int id;
    private String keyWord;

    public KeySearch() {
    }

    public KeySearch(int id, String keyWord) {
        this.id = id;
        this.keyWord = keyWord;
    }

    public int getId() {
        return id;
    }

    public String getKeyWord() {
        return keyWord;
    }
    
}
