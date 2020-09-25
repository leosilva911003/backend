/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.data;

import API.models.SoupModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo
 */
public class SoupDao {

    public SoupModel getSoupModelById(String id) throws Exception {
        SoupModel sm = null;
        Connection c = Dao.getConnection();
        String query = "select * from matrix where id ='" + id + "';";
        PreparedStatement ps = c.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            sm = new SoupModel(rs.getNString("id"), rs.getNString("matrix"), rs.getNString("words"));
        }
        c.close();
        return sm;
    }

    public void addSoup(SoupModel sm) throws Exception {
        Connection c = Dao.getConnection();
        String query = "insert into matrix(id,matrix,words) values('"
                + sm.getId() + "','"
                + sm.getMatrix() + "','"
                + sm.getWords() + "');";
        PreparedStatement ps = c.prepareStatement(query);
        ps.executeUpdate();
        c.close();
    }

    public void updateSoup(SoupModel newSoupModel) throws Exception {
        Connection c = Dao.getConnection();
        String query = "update matrix set matrix='"
                + newSoupModel.getMatrix() + "',words='"
                + newSoupModel.getWords() + "' where id='"
                + newSoupModel.getId() + "';";
        PreparedStatement ps = c.prepareStatement(query);
        ps.executeUpdate();
        c.close();
    }
}
