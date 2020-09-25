/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import API.data.SoupService;
import API.models.Chords;
import API.models.Config;
import API.models.ErrorMessage;
import API.models.Helper;
import API.models.SoupGame;
import API.models.SoupModel;
import API.models.SuccessIdMessage;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.UUID;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Leo
 */
@Path("alphabetSoup")
public class Example {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Example
     */
    public Example() {

    }

    /**
     * Retrieves representation of an instance of API.Example
     *
     * @return an instance of java.lang.String
     */
    @Path("list/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getWords(@PathParam("id") String id) {
        try {
            String words = "";
            SoupService service = new SoupService();
            SoupModel sm = service.getSoupModelById(id);
            words = sm.getWords();
            return words;
        } catch (Exception e) {
            e.printStackTrace();
            ErrorMessage em = new ErrorMessage();
            em.setMessage(e.toString());
            return new Gson().toJson(em);
        }
    }

    @Path("view/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String viewMatrix(@PathParam("id") String id) {
        try {
            String matrix = "";
            SoupService service = new SoupService();
            SoupModel sm = service.getSoupModelById(id);
            matrix = sm.getMatrix();
            char[][] matrix_char = Helper.getMatrixFromJSON(matrix);
            matrix = Helper.mostrarSopa(matrix_char);
            return matrix;
        } catch (Exception e) {
            e.printStackTrace();
            ErrorMessage em = new ErrorMessage();
            em.setMessage(e.toString());
            return new Gson().toJson(em);
        }
    }

    /**
     * PUT method for updating or creating an instance of Example
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createMatrix(String content) {
        try {
            Config config = new Gson().fromJson(content, Config.class);
            SoupGame Game = new SoupGame(
                    config.getAlto(),
                    config.getAncho(),
                    config.getIz_der(),
                    config.getDer_iz(),
                    config.getAr_ab(),
                    config.getAb_ar(),
                    config.getDiag());
            String id = UUID.randomUUID().toString();
            String matrix = Helper.getJSONFromMatrix(Game.getMatrix());
            String words = new Gson().toJson(Game.in_matrix);
            SoupModel sm = new SoupModel(id, matrix, words);
            SoupService service = new SoupService();
            service.addSoup(sm);
            SuccessIdMessage sim = new SuccessIdMessage();
            sim.setId(id);
            return new Gson().toJson(sim);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorMessage em = new ErrorMessage();
            em.setMessage(e.toString());
            return new Gson().toJson(em);
        }
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String tryChords(String content, @PathParam("id") String id) {
        try {
            //taking chords
            Chords chords = new Gson().fromJson(content, Chords.class);
            //taking matrix
            SoupService service = new SoupService();
            SoupModel sm = service.getSoupModelById(id);
            char[][] matrix_char = Helper.getMatrixFromJSON(sm.getMatrix());
            ArrayList<String> words = new Gson().fromJson(sm.getWords(), ArrayList.class);
            //try to get word
            String result = Helper.getWord(
                    chords.getFi(),
                    chords.getCi(),
                    chords.getFf(),
                    chords.getCf(),
                    matrix_char,
                    words);
            //update matrix
            sm.setMatrix(Helper.getJSONFromMatrix(matrix_char));
            service.updateSoup(sm); 
            ErrorMessage em = new ErrorMessage();
            em.setMessage(result);
            return new Gson().toJson(em);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorMessage em = new ErrorMessage();
            em.setMessage(e.toString());
            return new Gson().toJson(em);
        }
    }
}
