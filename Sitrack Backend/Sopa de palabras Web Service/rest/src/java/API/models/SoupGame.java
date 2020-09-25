/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.models;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Leo
 */
public class SoupGame {

    private char[][] matrix;
    private char[][] matrixAux;
    public String[] words = {
        "silla",
        "casa",
        "mesa",
        "palabra",
        "ambiente",
        "natural",
        "frecuencia",
        "planeta",
        "tierra",
        "artificial",
        "humanos",
        "bosque",
        "agua",
        "vida",
        "elementos",
        "puerta",
        "sombrilla",
        "llaves",
        "herramientas",
        "martillo",
        "historia"};
    private char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'w', 'y', 'z'};
    private Random r = new Random();
    private int row, col, size, cont, cont2;
    private int alto;
    private boolean id, di, ara, aba, d;

    public SoupGame(int size, int alto, boolean id, boolean di, boolean ara, boolean aba, boolean d) //creates a matrix of order size*size
    {
        this.id = id;
        this.di = di;
        this.ara = ara;
        this.aba = aba;
        this.d = d;
        this.size = size;
        this.alto = alto;
        this.matrix = new char[this.size][this.alto];
        this.matrixAux = new char[this.size][this.alto];
        this.cleanMatrix(this.matrix);
        this.setPosition();
        this.randomABC(this.matrix);
    }

    public String[] getWords() {
        return words;
    }

    public void cleanMatrix(char[][] matrixToClean) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < alto; j++) {
                matrixToClean[i][j] = '0';
            }
        }

    }

    public void cleanVector(char[] vector) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = '0';
        }
    }

    public char[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public char getABC(int i) {
//        return ' ';
        return this.abc[i];
    }

    public void randomABC(char[][] matrixR) //filling in matrix of random letters
    {
        int x;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.alto; j++) {
                if (matrixR[i][j] == '0') {
                    x = r.nextInt(26);
                    matrixR[i][j] = getABC(x);
                }
            }
        }
    }

    public int getOnMatrixAux(int r, int c) //filling in with '1' the position on the matrix that the word can't be on it
    {                                        //and returning the number of the positions that contains '1'
        int counting1 = 0;
        this.matrixAux[r][c] = '1';

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.alto; j++) {
                if (this.matrixAux[i][j] == '1') {
                    counting1++;
                }
            }
        }

        return counting1;
    }

    public void setPosition() //this function selects randomly the form for the word can be write on matrix
    {                           //if the word selected can't be wrote in any form inside matrix, the main matrix in cleaned
        int rand, x = 0, i = 0; //and start again until the whole words can be write inside matrix
        boolean flag = true;
        char[] vAux = new char[8];
        String word;
        while (i < words.length) {
            word = words[i];
            i++;
            do {
                rand = r.nextInt(8);
                if (vAux[rand] != '1') {
                    switch (rand) {
                        case 0:
                            flag = horizontal(word);
                            vAux[0] = '1';
                            break;    //this set of functions writes the words 
                        case 1:
                            flag = horizontalI(word);
                            vAux[1] = '1';
                            break;   //inside matrix as they name indicates
                        case 2:
                            flag = vertical(word);
                            vAux[2] = '1';
                            break;
                        case 3:
                            flag = verticalI(word);
                            vAux[3] = '1';
                            break;
                        case 4:
                            flag = diagonalL(word);
                            vAux[4] = '1';
                            break;
                        case 5:
                            flag = diagonalLI(word);
                            vAux[5] = '1';
                            break;
                        case 6:
                            flag = diagonalR(word);
                            vAux[6] = '1';
                            break;
                        case 7:
                            flag = diagonalRI(word);
                            vAux[7] = '1';
                            break;
                    }
                }
                for (int z = 0; z < vAux.length; z++) {
                    if (vAux[z] == '1') {
                        x++;
                    }
                }

                if (x == 8) {
                    cleanMatrix(this.matrix);
                    in_matrix = new ArrayList<>();
                    i = 0;
                    break;
                }
            } while (flag);
            this.cleanVector(vAux);
        }
    }

    public boolean horizontal(String word) {
        if (id) {
            cleanMatrix(this.matrixAux);
            int condicion = 0;
            while (condicion != this.size * this.alto) {
                this.row = r.nextInt(this.size);
                this.col = r.nextInt(this.alto);
                if ((this.col + word.length()) <= this.alto) {
                    addWord(word);
                    this.cont = 0;
                    this.cont2 = 0;
                    for (int i = col; i < (word.length() + col); i++) {
                        if (this.matrix[this.row][i] == '0' || matrix[this.row][i] == word.charAt(this.cont2)) {
                            this.cont++;
                        }

                        this.cont2++;
                    }
                    if (this.cont == word.length()) {

                        for (int i = 0; i < word.length(); i++) {
                            this.matrix[this.row][this.col] = word.charAt(i);
                            this.col++;
                        }
                        return false;
                    }
                }
                condicion = getOnMatrixAux(this.row, this.col);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean horizontalI(String word) {
        if (di) {
            cleanMatrix(this.matrixAux);
            int condicion = 0;
            while (condicion != this.size * this.alto) {
                this.row = r.nextInt(this.size);
                this.col = r.nextInt(this.alto);
                if ((this.col + 1) - word.length() >= 0) {
                    addWord(word);
                    this.cont = 0;
                    this.cont2 = 0;
                    int x = col;
                    while (x >= ((this.col + 1) - word.length())) {
                        if (this.matrix[this.row][x] == '0' || this.matrix[this.row][x] == word.charAt(this.cont2)) {
                            this.cont++;
                        }
                        this.cont2++;
                        x--;
                    }
                    if (this.cont == word.length()) {
                        for (int i = 0; i < word.length(); i++) {
                            this.matrix[this.row][this.col] = word.charAt(i);
                            this.col--;
                        }
                        return false;
                    }
                }
                condicion = getOnMatrixAux(this.row, this.col);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean vertical(String word) {
        if (ara) {
            cleanMatrix(this.matrixAux);
            int condicion = 0;
            while (condicion != this.size * this.alto) {
                this.row = r.nextInt(this.size);
                this.col = r.nextInt(this.alto);
                if (this.row + word.length() <= this.size) {
                    addWord(word);
                    this.cont = 0;
                    this.cont2 = 0;
                    for (int i = this.row; i < (word.length() + this.row); i++) {
                        if (this.matrix[i][this.col] == '0' || this.matrix[i][this.col] == word.charAt(this.cont2)) {
                            this.cont++;
                        }

                        this.cont2++;
                    }
                    if (this.cont == word.length()) {
                        for (int i = 0; i < word.length(); i++) {
                            this.matrix[this.row][this.col] = word.charAt(i);
                            this.row++;
                        }
                        return false;
                    }

                }
                condicion = getOnMatrixAux(this.row, this.col);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean verticalI(String word) {
        if (aba) {
            cleanMatrix(this.matrixAux);
            int condicion = 0;
            while (condicion != this.size * this.alto) {
                this.row = r.nextInt(this.size);
                this.col = r.nextInt(this.alto);
                if ((this.row + 1) - word.length() >= 0) {
                    addWord(word);
                    this.cont = 0;
                    this.cont2 = 0;
                    for (int i = this.row; i >= (this.row + 1) - word.length(); i--) {
                        if (this.matrix[i][this.col] == '0' || this.matrix[i][this.col] == word.charAt(this.cont2)) {
                            this.cont++;
                        }

                        this.cont2++;
                    }
                    if (cont == word.length()) {
                        for (int i = 0; i < word.length(); i++) {
                            this.matrix[this.row][this.col] = word.charAt(i);
                            this.row--;
                        }
                        return false;
                    }
                }
                condicion = getOnMatrixAux(this.row, this.col);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean diagonalL(String word) {
        if (d && (id && aba)) {
            cleanMatrix(this.matrixAux);
            int condicion = 0;
            while (condicion != this.size * this.alto) {
                this.row = r.nextInt(this.size);
                this.col = r.nextInt(this.alto);
                if ((this.row + 1) - word.length() >= 0 && this.col + word.length() <= this.alto) {
                    addWord(word);
                    this.cont = 0;
                    this.cont2 = 0;
                    int x = this.row, j = this.col;
                    while (x >= ((this.row + 1) - word.length()) && j <= (this.col + word.length())) {
                        if (this.matrix[x][j] == '0' || this.matrix[x][j] == word.charAt(cont2)) {
                            this.cont++;
                        }

                        this.cont2++;
                        x--;
                        j++;
                    }
                    if (this.cont == word.length()) {
                        for (int i = 0; i < word.length(); i++) {
                            this.matrix[this.row][this.col] = word.charAt(i);
                            this.row--;
                            this.col++;
                        }
                        return false;
                    }
                }
                condicion = getOnMatrixAux(this.row, this.col);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean diagonalLI(String word) {
        if (d && (di && ara)) {
            cleanMatrix(this.matrixAux);
            int condicion = 0;
            while (condicion != this.size * this.alto) {
                this.row = r.nextInt(this.size);
                this.col = r.nextInt(this.alto);
                if (this.row + word.length() <= this.size && (this.col + 1) - word.length() >= 0) {
                    addWord(word);
                    this.cont = 0;
                    this.cont2 = 0;
                    int x = this.row, j = this.col;
                    while (x <= (this.row + word.length()) && j >= ((this.col + 1) - word.length())) {
                        if (this.matrix[x][j] == '0' || this.matrix[x][j] == word.charAt(this.cont2)) {
                            this.cont++;
                        }

                        this.cont2++;
                        x++;
                        j--;
                    }
                    if (this.cont == word.length()) {
                        for (int i = 0; i < word.length(); i++) {
                            this.matrix[this.row][this.col] = word.charAt(i);
                            this.row++;
                            this.col--;
                        }
                        return false;
                    }
                }
                condicion = getOnMatrixAux(this.row, this.col);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean diagonalR(String word) {
        if (d && (di && aba)) {
            cleanMatrix(this.matrixAux);
            int condicion = 0;
            while (condicion != this.size * this.alto) {
                this.row = r.nextInt(this.size);
                this.col = r.nextInt(this.alto);
                if ((this.row + 1) - word.length() >= 0 && (this.col + 1) - word.length() >= 0) {
                    addWord(word);
                    cont = 0;
                    cont2 = 0;
                    int x = this.row, j = this.col;
                    while (x >= ((this.row + 1) - word.length()) && j >= ((this.col + 1) - word.length())) {
                        if (this.matrix[x][j] == '0' || this.matrix[x][j] == word.charAt(this.cont2)) {
                            this.cont++;
                        }

                        this.cont2++;
                        x--;
                        j--;
                    }
                    if (this.cont == word.length()) {
                        for (int i = 0; i < word.length(); i++) {
                            this.matrix[this.row][this.col] = word.charAt(i);
                            this.row--;
                            this.col--;
                        }
                        return false;
                    }
                }
                condicion = getOnMatrixAux(this.row, this.col);
            }
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<String> in_matrix = new ArrayList<String>();

    public void addWord(String word) {
        if (!existePalabra(word, in_matrix)) {
            in_matrix.add(word);
        }
    }

    public boolean existePalabra(String palabra, ArrayList<String> palabras) {
        for (int i = 0; i < palabras.size(); i++) {
            String palabra1 = palabras.get(i);
            if (palabra.toUpperCase().equals(palabra1.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getIn_matrix() {
        return in_matrix;
    }

    public boolean diagonalRI(String word) {
        if (d && (id && ara)) {
            cleanMatrix(this.matrixAux);
            int condicion = 0;
            while (condicion != this.size * this.alto) {
                this.row = r.nextInt(this.size);
                this.col = r.nextInt(this.alto);
                if ((this.row + word.length()) <= this.size && (this.col + word.length()) <= this.alto) {
                    addWord(word);
                    this.cont = 0;
                    this.cont2 = 0;
                    int x = this.row, j = this.col;
                    while (x < (this.row + word.length()) && j < (this.col + word.length())) {
                        if (this.matrix[x][j] == '0' || this.matrix[x][j] == word.charAt(this.cont2)) {
                            this.cont++;
                        }

                        this.cont2++;
                        x++;
                        j++;
                    }
                    if (this.cont == word.length()) {
                        for (int i = 0; i < word.length(); i++) {
                            this.matrix[this.row][this.col] = word.charAt(i);
                            this.row++;
                            this.col++;
                        }
                        return false;
                    }
                }
                condicion = getOnMatrixAux(this.row, this.col);
            }
            return true;
        } else {
            return false;
        }
    }
}
