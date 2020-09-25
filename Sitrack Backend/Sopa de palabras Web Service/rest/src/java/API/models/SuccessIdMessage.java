/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Leo
 */
public class SuccessIdMessage {

    @SerializedName("id")
    private String id = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
