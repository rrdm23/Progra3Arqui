package sample;

import java.awt.*;

/**
 * Created by Randall on 04/06/2017.
 */
public class Matriz {

    public int filas;
    public int columnas;
    public double valores[][];

    public Matriz(int _filas, int _columnas, double _valores[][]) {
        filas = _filas;
        columnas = _columnas;
        valores = new double[filas][columnas];
        valores = _valores;
    }

    public boolean esCuadrada(){
        if(filas == columnas) {
            System.out.println("Sí es cuadrada");
            return true;
        }
        System.out.println("No es cuadrada");
        return false;
    }

    public boolean esFila(){
        if (filas == 1) {
            System.out.println("Si es matriz fila");
            return true;
        }
        System.out.println("No es matriz fila");
        return false;
    }

    public boolean esColumna(){
        if (columnas == 1) {
            System.out.println("Sí es matriz columna");
            return true;
        }
        System.out.println("No es matriz columna");
        return false;
    }

    public boolean esRectangular(){
        if (!esCuadrada() && !esFila() && !esColumna()) {   //si no es cuadrada, ni fila, ni columna
            System.out.println("Sí es rectangular");
            return true;
        }
        System.out.println("No es rectangular");
        return false;
    }

    public boolean esNula(){
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                if(valores[i][j] != 0) {    //si encuentra un valor diferente a cero, retorna falso
                    System.out.println("No es nula");
                    return false;
                }
            }
        }
        System.out.println("Sí es nula");
        return true;
    }

    public boolean esTriangularSuperior(){
        int cerosXFila = 1;
        for(int i = 1; i < filas; i++){
            for(int j = 0; j < cerosXFila; j++){    //lleva el conteo de cuántos ceros deben haber por fila
                if (valores[i][j] != 0) {
                    System.out.println("No es triangular superior");
                    return false;
                }
            }
            cerosXFila++;   //por cada fila aumenta un cero
        }
        System.out.println("Sí es triangular superior");
        return true;
    }

    public boolean esTriangularInferior(){
        int cerosXFila = 1;
        for(int i = filas-2; i >= 0; i--){
            for(int j = columnas-1; j > columnas-1-cerosXFila; j--){    //empieza la verificación desde abajo
                if(valores[i][j] != 0){
                    System.out.println("No es triangular inferior");
                    return false;
                }
            }
            cerosXFila++;   //cuando sube una fila aumenta un cero
        }
        System.out.println("Sí es triangular inferior");
        return true;
    }

    public boolean esDiagonal(){
        if (esCuadrada()) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {    //asegurarse que toda la matriz menos la diagonal es cero
                    if ((valores[i][j] != 0 && i != j) || (valores[i][j] == 0 && i == j)) {
                        System.out.println("No es matriz diagonal");
                        return false;
                    }
                }
            }
            System.out.println("Sí es matriz diagonal");
            return true;
        }
        else{
            System.out.println("No es matriz diagonal");
            return false;
        }
    }

    public boolean esIdentidad(){
        if (esDiagonal()) {     //con que sea diagonal, se sabe que sólo esta tiene valores
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (valores[i][j] != 1 && i == j) {     //la diagonal debe ser 1 en todos sus valores
                        System.out.println("No es matriz identidad");
                        return false;
                    }
                }
            }
            System.out.println("Sí es matriz identidad");
            return true;
        }
        else{
            System.out.println("No es matriz identidad");
            return false;
        }
    }

    public boolean esEscalar(){
        if (esDiagonal()){
            double numDiagonal = valores[0][0];     //guarda el valor de la esquina superior izquierda
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (valores[i][j] != numDiagonal && i == j) {   //el valor debe ser igual en toda la diagonal
                        System.out.println("No es matriz escalar");
                        return false;
                    }
                }
            }
            System.out.println("Sí es matriz escalar");
            return true;
        }
        else{
            System.out.println("No es matriz diagonal");
            return false;
        }
    }

    public Matriz sumarMatrices(Matriz matriz){
        Matriz resultado;
        double[][] resSuma;     //guarda el valor de la suma
        double[][] mat = matriz.valores;
        if (filas == mat.length && columnas == mat[0].length){      //deben ser del mismo tamaño
            resSuma = new double[filas][columnas];
            for(int i = 0; i < filas; i++){
                for(int j = 0; j < columnas; j++){
                    resSuma[i][j] += valores[i][j] + mat[i][j];     //guarda en el resultado la suma de ambas matrices
                }
            }
            resultado = new Matriz(filas, columnas, resSuma);
            return resultado;
        }
        else{
            System.out.println("No se pueden sumar matrices de diferente tamaño");
            return null;
        }
    }

    //el de matriz por escalar se hace en NASM

    public Matriz productoMatrices(Matriz matriz){
        Matriz resultado;
        double[][] resProducto;
        double[][] mat = matriz.valores;
        if (columnas == mat.length){        //deben coincidir las filas de una con las columnas de otra
            resProducto = new double[filas][mat[0].length];
            for (int i = 0; i < filas; i++){
                for (int j = 0; j < mat[0].length; j++){
                    for (int k = 0; k < columnas; k++){     //sumatoria de valores multiplicados abajo
                        resProducto[i][j] += (valores[i][k] * mat[k][j]);   //multiplica posiciones
                    }
                }
            }
            resultado = new Matriz(filas, mat[0].length, resProducto);
            return resultado;
        }
        else{
            System.out.println("Estas matrices no se pueden multiplicar");
            return null;
        }
    }

    public Matriz transpuesta(){
        Matriz resultado;
        double[][] transp = new double[columnas][filas];
        for(int i = 0; i < columnas; i++){
            for(int j = 0; j < filas; j++){
                transp[i][j] = valores[j][i];       //las filas son columnas, y viceversa
            }
        }
        resultado = new Matriz(columnas, filas, transp);
        return resultado;
    }

    public double determinante(double[][] matriz) {
        double det = 0;
        if(matriz.length == 2) {
            det = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
            System.out.println("Determinante = " + det);
            return det;
        }
        double suma = 0;
        for(int i = 0; i < matriz.length; i++){
            double[][] matAux = new double[matriz.length-1][matriz.length-1];
            for(int j = 0; j < matriz.length; j++){
                if(j != i){
                    for(int k = 1; k < matriz.length; k++){
                        int pos = -1;
                        if(j < i)
                            pos = j;
                        else if(j > i)
                            pos = j-1;
                        matAux[pos][k-1] = matriz[j][k];
                    }
                }
            }
            if(i%2 == 0)
                suma+=matriz[i][0] * determinante(matAux);
            else
                suma-=matriz[i][0] * determinante(matAux);
        }
        System.out.println("Determinante = " + suma);
        return suma;
    }

    public int rango(){
        int resultado = 0;
        if (esCuadrada()){
            return resultado;
        }
        else{
            System.out.println("A esta matriz no se le puede calcular el rango");
            return resultado;
        }

    }

    public Matriz inversa(){
        if (esCuadrada()) {
            double valA[][] = valores;
            double valB[][] = new double[filas][columnas];
            double valC[][] = new double[filas][columnas];

            //inicializar matriz identidad en valB
            for (int i = 0; i < filas; i++)
                valB[i][i] = 1;

            //transforma matriz y sus términos
            for (int k = 0; k < filas-1; k++) {
                for (int i = k+1; i < filas; i++) {
                    for (int s = 0; s < filas; s++) {
                        valB[i][s] -= valA[i][k] * valB[k][s] / valA[k][k];
                    }
                    for (int j = k + 1; j < filas; j++){
                        valA[i][j] -= valA[i][k] * valA[k][j] / valA[k][k];
                    }
                }
            }

            //calcula las incógnitas
            for (int s = 0; s < filas; s++) {
                valC[filas-1][s] = valB[filas-1][s] / valA[filas-1][filas-1];
                for (int i = filas-2; i >= 0; i--) {
                    valC[i][s] = valB[i][s] / valA[i][i];
                    for (int k = filas-1; k > i; k--)
                        valC[i][s] -= valA[i][k] * valC[k][s] / valA[i][i];
                }
            }
            Matriz c = new Matriz(filas, columnas, valC);
            return c;
        }
        else{
            System.out.println("Esta matriz no posee inversa");
            return null;
        }
    }

    public String toString(){
        String msg = "";
        msg += "Filas: " + filas + "\n";
        msg += "Columnas: " + columnas + "\n";
        msg += "Valores de la matriz: \n(\n";
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                msg += valores[i][j] + " ";
            }
            msg += "\n";
        }
        msg += ")";
        System.out.println(msg);
        return msg;
    }

}
