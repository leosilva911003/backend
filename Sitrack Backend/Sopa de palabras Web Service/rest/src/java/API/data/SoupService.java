/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.data;

import API.models.SoupModel;

/**
 *
 * @author Leo
 */
public class SoupService {

    private SoupDao sd;

    public SoupService() {
        sd = new SoupDao();
    }

    public SoupModel getSoupModelById(String id) throws Exception {
        return sd.getSoupModelById(id);
    }

    public void addSoup(SoupModel sm) throws Exception {
        sd.addSoup(sm);
    }

    public void updateSoup(SoupModel sm) throws Exception {
        sd.updateSoup(sm);
    }
}
