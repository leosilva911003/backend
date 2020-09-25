/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.models;

/**
 *
 * @author Leo
 */
public class SoupModel {

    private String id;
    private String matrix;
    private String words;

    public SoupModel(String id, String matrix, String words) {
        this.id = id;
        this.matrix = matrix;
        this.words = words;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatrix() {
        return matrix;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
    
}
