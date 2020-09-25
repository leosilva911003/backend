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
public class Chords {

    @SerializedName("sr")
    private Integer fi = null;
    @SerializedName("sc")
    private Integer ci = null;
    @SerializedName("er")
    private Integer ff = null;
    @SerializedName("ec")
    private Integer cf = null;

    public Integer getFi() {
        return fi;
    }

    public void setFi(Integer fi) {
        this.fi = fi;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public Integer getFf() {
        return ff;
    }

    public void setFf(Integer ff) {
        this.ff = ff;
    }

    public Integer getCf() {
        return cf;
    }

    public void setCf(Integer cf) {
        this.cf = cf;
    }
    
}
