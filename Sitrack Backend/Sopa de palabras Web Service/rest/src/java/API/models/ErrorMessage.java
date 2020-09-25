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
public class ErrorMessage {

    @SerializedName("message")
    private String message = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
