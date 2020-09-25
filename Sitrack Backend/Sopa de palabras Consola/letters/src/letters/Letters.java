/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letters;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Leo
 */
public class Letters {

    static int palabrasEncontradas = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Random randomGenerator = new Random();
        int ancho = ThreadLocalRandom.current().nextInt(15, 80);
        int alto = ThreadLocalRandom.current().nextInt(15, 80);
        boolean derecha_izquierda = randomGenerator.nextBoolean();
        boolean izquierda_derecha = randomGenerator.nextBoolean();
        if (!derecha_izquierda) {
            izquierda_derecha = true;
        }
        if (!izquierda_derecha) {
            derecha_izquierda = true;
        }
        boolean abajo_arriba = randomGenerator.nextBoolean();
        boolean arriba_abajo = randomGenerator.nextBoolean();

        if (!abajo_arriba) {
            arriba_abajo = true;
        }
        if (!arriba_abajo) {
            abajo_arriba = true;
        }
        boolean diagonal = randomGenerator.nextBoolean();
        System.out.println("Ancho: " + ancho + " Alto: " + alto + " (AUTO)");
        System.out.println("Derecha a izquierda: " + derecha_izquierda + " (AUTO)");
        System.out.println("Izquierda a derecha: " + izquierda_derecha + " (AUTO)");
        System.out.println("Abajo hacia arriba: " + abajo_arriba + " (AUTO)");
        System.out.println("Arriba hacia abajo: " + arriba_abajo + " (AUTO)");
        System.out.println("Diagonal: " + diagonal + " (AUTO)");
        LetterSoup Game = new LetterSoup(
                alto,
                ancho,
                izquierda_derecha,
                derecha_izquierda,
                arriba_abajo,
                abajo_arriba,
                diagonal);
        char[][] matrix = Game.getMatrix();
        mostrarSopa(matrix);
        boolean run = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (run) {
            System.out.println("Palabras:");
            for (int i = 0; i < Game.getIn_matrix().size(); i++) {
                System.out.println(i + 1 + ":" + Game.getIn_matrix().get(i));
            }
            System.out.println("Seleccione 1 para encontrar una palabra, 0 para finalizar el juego...");
            String option = br.readLine();
            if (!option.equals("1") && !option.equals("0")) {
                System.out.println("Opción no válida.");
            } else {
                if (option.equals("0")) {
                    System.out.println("Fin del juego, palabras encontradas: " + palabrasEncontradas);
                    run = false;
                } else {
                    System.out.println("Introduzca fila inicial(número)...");
                    String fi = br.readLine();
                    System.out.println("Introduzca columna inicial(número)...");
                    String ci = br.readLine();
                    System.out.println("Introduzca fila final(número)...");
                    String ff = br.readLine();
                    System.out.println("Introduzca columna final(número)...");
                    String cf = br.readLine();
                    getWord(fi, ci, ff, cf, matrix, Game);
                    mostrarSopa(Game.getMatrix());
                }
            }
        }
    }

    public static void util(char[][] matrix) {
        //convertir matrix a JSON
        String json_matrix = new Gson().toJson(matrix, char[][].class);
        System.out.println("JSON matrix: " + "\n");
        System.out.println("JSON matrix longitud: "+json_matrix.length() + "\n");
        System.out.println(json_matrix);

        //convertir JSON a matrix
        char[][] dataMap = new Gson().fromJson(json_matrix, char[][].class);
        System.out.println("Matrix from JSON: " + "\n");
        mostrarSopa(dataMap);
    }

    public static void getWord(String fi, String ci, String ff, String cf, char[][] matrix, LetterSoup sopa) {
        try {
            int fi_int = Integer.parseInt(fi) - 1;
            int ci_int = Integer.parseInt(ci) - 1;
            int ff_int = Integer.parseInt(ff) - 1;
            int cf_int = Integer.parseInt(cf) - 1;
            try {
                char initial_char = matrix[fi_int][ci_int];
            } catch (Exception e) {
                System.out.println("Path incorrecto.");
                return;
            }
            try {
                char final_char = matrix[ff_int][cf_int];
            } catch (Exception e) {
                System.out.println("Path incorrecto.");
                return;
            }
            if (fi_int == ff_int && ci_int == cf_int) {
                System.out.println("Path incorrecto.");
                return;
            }
            if (fi_int == ff_int) {
                String palabra = "";
                if (ci_int < cf_int) {
                    for (int i = ci_int; i <= cf_int; i++) {
                        palabra = palabra + String.valueOf(matrix[fi_int][i]);
                    }
                    System.out.println("Palabra: " + palabra);
                    if (existePalabra(palabra, sopa.getWords())) {
                        System.out.println("La palabra es correcta.");
                        palabrasEncontradas++;
                        for (int i = ci_int; i <= cf_int; i++) {
                            String char_old = String.valueOf(matrix[fi_int][i]);
                            char_old = char_old.toUpperCase();
                            char char_final = char_old.charAt(0);
                            matrix[fi_int][i] = char_final;
                            sopa.setMatrix(matrix);
                        }
                        return;
                    } else {
                        System.out.println("La palabra es incorrecta.");
                        return;
                    }
                } else {
                    for (int i = ci_int; i >= cf_int; i--) {
                        palabra = palabra + String.valueOf(matrix[fi_int][i]);
                    }
                    System.out.println("Palabra: " + palabra);
                    if (existePalabra(palabra, sopa.getWords())) {
                        System.out.println("La palabra es correcta.");
                        palabrasEncontradas++;
                        for (int i = ci_int; i >= cf_int; i--) {
                            String char_old = String.valueOf(matrix[fi_int][i]);
                            char_old = char_old.toUpperCase();
                            char char_final = char_old.charAt(0);
                            matrix[fi_int][i] = char_final;
                            sopa.setMatrix(matrix);
                        }
                        return;
                    } else {
                        System.out.println("La palabra es incorrecta.");
                        return;
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
                    if (existePalabra(palabra, sopa.getWords())) {
                        System.out.println("La palabra es correcta.");
                        palabrasEncontradas++;
                        for (int i = fi_int; i <= ff_int; i++) {
                            String char_old = String.valueOf(matrix[i][ci_int]);
                            char_old = char_old.toUpperCase();
                            char char_final = char_old.charAt(0);
                            matrix[i][ci_int] = char_final;
                            sopa.setMatrix(matrix);
                        }
                        return;
                    } else {
                        System.out.println("La palabra es incorrecta.");
                        return;
                    }
                } else {
                    for (int i = fi_int; i >= ff_int; i--) {
                        palabra = palabra + String.valueOf(matrix[i][ci_int]);
                    }
                    System.out.println("Palabra: " + palabra);
                    if (existePalabra(palabra, sopa.getWords())) {
                        System.out.println("La palabra es correcta.");
                        palabrasEncontradas++;
                        for (int i = fi_int; i >= ff_int; i--) {
                            String char_old = String.valueOf(matrix[i][ci_int]);
                            char_old = char_old.toUpperCase();
                            char char_final = char_old.charAt(0);
                            matrix[i][ci_int] = char_final;
                            sopa.setMatrix(matrix);
                        }
                        return;
                    } else {
                        System.out.println("La palabra es incorrecta.");
                        return;
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
                        if (existePalabra(palabra, sopa.getWords())) {
                            System.out.println("La palabra es correcta.");
                            palabrasEncontradas++;
                            int k = ci_int;
                            for (int i = fi_int; i <= ff_int; i++) {
                                String char_old = String.valueOf(matrix[i][k]);
                                char_old = char_old.toUpperCase();
                                char char_final = char_old.charAt(0);
                                matrix[i][k] = char_final;
                                sopa.setMatrix(matrix);
                                k++;
                            }
                            return;
                        } else {
                            System.out.println("La palabra es incorrecta.");
                            return;
                        }
                    } else {
                        String palabra = "";
                        int j = ci_int;
                        for (int i = fi_int; i <= ff_int; i++) {
                            palabra = palabra + String.valueOf(matrix[i][j]);
                            j--;
                        }
                        System.out.println("Palabra: " + palabra);
                        if (existePalabra(palabra, sopa.getWords())) {
                            System.out.println("La palabra es correcta.");
                            palabrasEncontradas++;
                            int k = ci_int;
                            for (int i = fi_int; i <= ff_int; i++) {
                                String char_old = String.valueOf(matrix[i][k]);
                                char_old = char_old.toUpperCase();
                                char char_final = char_old.charAt(0);
                                matrix[i][k] = char_final;
                                sopa.setMatrix(matrix);
                                k--;
                            }
                            return;
                        } else {
                            System.out.println("La palabra es incorrecta.");
                            return;
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
                        if (existePalabra(palabra, sopa.getWords())) {
                            System.out.println("La palabra es correcta.");
                            palabrasEncontradas++;
                            int k = ci_int;
                            for (int i = fi_int; i >= ff_int; i--) {
                                String char_old = String.valueOf(matrix[i][k]);
                                char_old = char_old.toUpperCase();
                                char char_final = char_old.charAt(0);
                                matrix[i][k] = char_final;
                                sopa.setMatrix(matrix);
                                k++;
                            }
                            return;
                        } else {
                            System.out.println("La palabra es incorrecta.");
                            return;
                        }
                    } else {
                        String palabra = "";
                        int j = ci_int;
                        for (int i = fi_int; i >= ff_int; i--) {
                            palabra = palabra + String.valueOf(matrix[i][j]);
                            j--;
                        }
                        System.out.println("Palabra: " + palabra);
                        if (existePalabra(palabra, sopa.getWords())) {
                            System.out.println("La palabra es correcta.");
                            palabrasEncontradas++;
                            int k = ci_int;
                            for (int i = fi_int; i >= ff_int; i--) {
                                String char_old = String.valueOf(matrix[i][k]);
                                char_old = char_old.toUpperCase();
                                char char_final = char_old.charAt(0);
                                matrix[i][k] = char_final;
                                sopa.setMatrix(matrix);
                                k--;
                            }
                            return;
                        } else {
                            System.out.println("La palabra es incorrecta.");
                            return;
                        }
                    }
                }
            } else {
                System.out.println("Path incorrecto.");
            }
        } catch (Exception e) {
            System.out.println("Error procesando sus datos: " + e.toString());
        }
    }

    public static void mostrarSopa(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            String fila = "|";
            for (int j = 0; j < matrix[i].length; j++) {
                fila = fila + matrix[i][j] + "|";
            }
            System.out.println(fila);
        }
    }

    public static boolean existePalabra(String palabra, String[] palabras) {
        for (int i = 0; i < palabras.length; i++) {
            String palabra1 = palabras[i];
            if (palabra.toUpperCase().equals(palabra1.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}
