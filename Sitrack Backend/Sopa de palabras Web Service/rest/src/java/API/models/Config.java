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
public class Config {

    @SerializedName("w")
    private Integer ancho = 15;
    @SerializedName("h")
    private Integer alto = 15;
    @SerializedName("ltr")
    private Boolean iz_der = true;
    @SerializedName("rtl")
    private Boolean der_iz = false;
    @SerializedName("ttb")
    private Boolean ar_ab = true;
    @SerializedName("btt")
    private Boolean ab_ar = false;
    @SerializedName("d")
    private Boolean diag = false;

    public Config() {
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getAlto() {
        return alto;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public Boolean getIz_der() {
        return iz_der;
    }

    public void setIz_der(Boolean iz_der) {
        this.iz_der = iz_der;
    }

    public Boolean getDer_iz() {
        return der_iz;
    }

    public void setDer_iz(Boolean der_iz) {
        this.der_iz = der_iz;
    }

    public Boolean getAr_ab() {
        return ar_ab;
    }

    public void setAr_ab(Boolean ar_ab) {
        this.ar_ab = ar_ab;
    }

    public Boolean getAb_ar() {
        return ab_ar;
    }

    public void setAb_ar(Boolean ab_ar) {
        this.ab_ar = ab_ar;
    }

    public Boolean getDiag() {
        return diag;
    }

    public void setDiag(Boolean diag) {
        this.diag = diag;
    }

}
