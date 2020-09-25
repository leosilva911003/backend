/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.models;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author Leo
 */
public class Helper {

    public static String getJSONFromMatrix(char[][] matrix) throws Exception {
        return new Gson().toJson(matrix, char[][].class);
    }

    public static char[][] getMatrixFromJSON(String json_matrix) throws Exception {
        return new Gson().fromJson(json_matrix, char[][].class);
    }

    public static boolean existePalabra(String palabra, ArrayList<String> palabras) throws Exception {
        for (int i = 0; i < palabras.size(); i++) {
            String palabra1 = palabras.get(i);
            if (palabra.toUpperCase().equals(palabra1.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static String mostrarSopa(char[][] matrix) throws Exception {
        String sopa = "";
        for (int i = 0; i < matrix.length; i++) {
            String fila = "|";
            for (int j = 0; j < matrix[i].length; j++) {
                fila = fila + matrix[i][j] + "|";
            }
            sopa = sopa + fila + "\n";
        }
        return sopa;
    }

    public static String getWord(int fi, int ci, int ff, int cf, char[][] matrix, ArrayList<String> palabras) throws Exception {
        try {
            int fi_int = fi - 1;
            int ci_int = ci - 1;
            int ff_int = ff - 1;
            int cf_int = cf - 1;
            try {
                char initial_char = matrix[fi_int][ci_int];
            } catch (Exception e) {
                return "Incorrecto.";
            }
            try {
                char final_char = matrix[ff_int][cf_int];
            } catch (Exception e) {
                return "Incorrecto.";
            }
            if (fi_int == ff_int && ci_int == cf_int) {
                return "Incorrecto.";
            }
            if (fi_int == ff_int) {
                String palabra = "";
                if (ci_int < cf_int) {
                    for (int i = ci_int; i <= cf_int; i++) {
                        palabra = palabra + String.valueOf(matrix[fi_int][i]);
                    }
                    System.out.println("Palabra: " + palabra);
                    if (existePalabra(palabra, palabras)) {
                        System.out.println("La palabra es correcta.");
                        for (int i = ci_int; i <= cf_int; i++) {
                            String char_old = String.valueOf(matrix[fi_int][i]);
                            char_old = char_old.toUpperCase();
                            char char_final = char_old.charAt(0);
                            matrix[fi_int][i] = char_final;
                        }
                        return "Correcto.";
                    } else {
                        return "Incorrecto.";
                    }
                } else {
                    for (int i = ci_int; i >= cf_int; i--) {
                        palabra = palabra + String.valueOf(matrix[fi_int][i]);
                    }
                    System.out.println("Palabra: " + palabra);
                    if (existePalabra(palabra, palabras)) {
                        System.out.println("La palabra es correcta.");
                        for (int i = ci_int; i >= cf_int; i--) {
                            String char_old = String.valueOf(matrix[fi_int][i]);
                            char_old = char_old.toUpperCase();
                            char char_final = char_old.charAt(0);
                            matrix[fi_int][i] = char_final;
                        }
                        return "Correcto.";
                    } else {
                        return "Incorrecto.";
                    }
                }
            }
            if (ci_int == cf_int) {
                String palabra = "";
                if (fi_int < ff_int) {
                    for (int i = fi_int; i <= ff_int; i++) {
                        palabra = palabra + String.valueOf(matrix[i][ci_int]);
                    }
                    System.out.println("Palabra: " + palabra);
                    if (existePalabra(palabra, palabras)) {
                        System.out.println("La palabra es correcta.");
                        for (int i = fi_int; i <= ff_int; i++) {
                            String char_old = String.valueOf(matrix[i][ci_int]);
                            char_old = char_old.toUpperCase();
                            char char_final = char_old.charAt(0);
                            matrix[i][ci_int] = char_final;
                        }
                        return "Correcto.";
                    } else {
                        return "Incorrecto.";
                    }
                } else {
                    for (int i = fi_int; i >= ff_int; i--) {
                        palabra = palabra + String.valueOf(matrix[i][ci_int]);
                    }
                    System.out.println("Palabra: " + palabra);
                    if (existePalabra(palabra, palabras)) {
                        System.out.println("La palabra es correcta.");
                        for (int i = fi_int; i >= ff_int; i--) {
                            String char_old = String.valueOf(matrix[i][ci_int]);
                            char_old = char_old.toUpperCase();
                            char char_final = char_old.charAt(0);
                            matrix[i][ci_int] = char_final;
                        }
                        return "Correcto.";
                    } else {
                        return "Incorrecto.";
                    }
                }
            }
            if (Math.abs(fi_int - ff_int) == Math.abs(ci_int - cf_int)) {
                if (fi_int < ff_int) {
                    if (ci_int < cf_int) {
                        String palabra = "";
                        int j = ci_int;
                        for (int i = fi_int; i <= ff_int; i++) {
                            palabra = palabra + String.valueOf(matrix[i][j]);
                            j++;
                        }
                        System.out.println("Palabra: " + palabra);
                        if (existePalabra(palabra, palabras)) {
                            System.out.println("La palabra es correcta.");
                            int k = ci_int;
                            for (int i = fi_int; i <= ff_int; i++) {
                                String char_old = String.valueOf(matrix[i][k]);
                                char_old = char_old.toUpperCase();
                                char char_final = char_old.charAt(0);
                                matrix[i][k] = char_final;
                                k++;
                            }
                            return "Correcto.";
                        } else {
                            return "Incorrecto.";
                        }
                    } else {
                        String palabra = "";
                        int j = ci_int;
                        for (int i = fi_int; i <= ff_int; i++) {
                            palabra = palabra + String.valueOf(matrix[i][j]);
                            j--;
                        }
                        System.out.println("Palabra: " + palabra);
                        if (existePalabra(palabra, palabras)) {
                            System.out.println("La palabra es correcta.");
                            int k = ci_int;
                            for (int i = fi_int; i <= ff_int; i++) {
                                String char_old = String.valueOf(matrix[i][k]);
                                char_old = char_old.toUpperCase();
                                char char_final = char_old.charAt(0);
                                matrix[i][k] = char_final;
                                k--;
                            }
                            return "Correcto.";
                        } else {
                            return "Incorrecto.";
                        }
                    }
                } else {
                    if (ci_int < cf_int) {
                        String palabra = "";
                        int j = ci_int;
                        for (int i = fi_int; i >= ff_int; i--) {
                            palabra = palabra + String.valueOf(matrix[i][j]);
                            j++;
                        }
                        System.out.println("Palabra: " + palabra);
                        if (existePalabra(palabra, palabras)) {
                            System.out.println("La palabra es correcta.");
                            int k = ci_int;
                            for (int i = fi_int; i >= ff_int; i--) {
                                String char_old = String.valueOf(matrix[i][k]);
                                char_old = char_old.toUpperCase();
                                char char_final = char_old.charAt(0);
                                matrix[i][k] = char_final;
                                k++;
                            }
                            return "Correcto.";
                        } else {
                            return "Incorrecto.";
                        }
                    } else {
                        String palabra = "";
                        int j = ci_int;
                        for (int i = fi_int; i >= ff_int; i--) {
                            palabra = palabra + String.valueOf(matrix[i][j]);
                            j--;
                        }
                        System.out.println("Palabra: " + palabra);
                        if (existePalabra(palabra, palabras)) {
                            System.out.println("La palabra es correcta.");
                            int k = ci_int;
                            for (int i = fi_int; i >= ff_int; i--) {
                                String char_old = String.valueOf(matrix[i][k]);
                                char_old = char_old.toUpperCase();
                                char char_final = char_old.charAt(0);
                                matrix[i][k] = char_final;
                                k--;
                            }
                            return "Correcto.";
                        } else {
                            return "Incorrecto.";
                        }
                    }
                }
            } else {
                return "Incorrecto.";
            }
        } catch (Exception e) {
            return "Incorrecto.";
        }
    }
}
