package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import utils.Funciones;

/**
 *
 * @author Pérez Ortiz, Alejandro
 */
public final class CodeMaker extends Jugador implements Serializable{
    
    /**
     *
     * @param IA si el CodeMaker es IA o es jugador real
     * @param nfichas número de fichas de la partida actual
     * @param ncolores número de colores de la partida actual
     */
    public CodeMaker(boolean IA, int nfichas, int ncolores) {
       super(nfichas, ncolores);
       if(IA)
           super.setIA();
    }
    
    /**
     *
     * @param s s es "IA" si el CodeMaker es IA
     * @return el patrón que se deberá adivinar
     */
    public ArrayList<Integer> dona_patro(String s) {
        ArrayList<Integer> linea;
        linea = new ArrayList<>();
        if(s.equals("IA")) {
            for(int i = 0; i < super.getNFichas(); i++) {
                int randomNum = ThreadLocalRandom.current().nextInt(1, super.getNColores() + 1);
                linea.add(randomNum);
            }
        }
        
        else {
            boolean jugadaHecha = false;
            while(!jugadaHecha) {
                linea = new ArrayList<Integer>();
                Scanner input = new Scanner(System.in);
                System.out.print("Introduce el patron a adivinar poniendo "+super.getNFichas()+" fichas, poniendo cada ficha del 1 al "+super.getNColores()+" separada de un espacio:" + "\n");
                String jugada = input.nextLine();
                String fichas[] = jugada.split(" ");
                boolean fichasNoValid = false;
                if(fichas.length != super.getNFichas())
                    fichasNoValid =  true;
                for(int i = 0; i < fichas.length && !fichasNoValid; i++) {
                    int num = Integer.parseInt(fichas[i]);
                    if(num >= 1 && num <= super.getNColores()) linea.add(num);
                }
                if(linea.size() == fichas.length) jugadaHecha = true;
                if(fichasNoValid) 
                    System.out.println("El número de fichas introducido es incorrecto.");
                else if (!jugadaHecha) 
                    System.out.println("Has introducido un valor incorrecto.");
            }
        }
        return linea;
    }
    
    /**
     *
     * @param s s es "IA" si el CodeMaker es IA
     * @param tirada intento de adivinar el patrón
     * @param solucio patrón de la partida
     * @return pegs que se devuelven para dar la pista al jugador CodeBreaker
     */
    public ArrayList<Integer> jugar(String s, ArrayList<CodePeg> tirada, ArrayList<CodePeg> solucio) {
        ArrayList<Integer> linea = null;
        linea = new ArrayList<>();
        if(s.equals("IA")) {
            linea = super.donaSolucio(tirada, solucio);
        }
        
        else {
            boolean jugadaHecha = false;
            boolean guardar = false;
            //esto se deberia hacer en game
            for(int i = 0; i < tirada.size(); i++) {
                System.out.print(tirada.get(i).getColour() + " ");
            }
            System.out.println();
            while (!jugadaHecha && !guardar){
                linea = new ArrayList<Integer>();
                Scanner input = new Scanner(System.in);
                System.out.print("Introduce tu pista poniendo "+super.getNFichas()+" fichas, cada ficha del 0 al 2 separada de un espacio. \n(2 si coincide posición y color, 1 si coincide color pero no posición y 0 si no coincide el color)"
                        + "\n(Introduce -1 para guardar partida, -2 para salir de la partida sin guardar):\n");
                String jugada = input.nextLine();
                String fichas[] = jugada.split(" ");
                if(fichas[0].equals("-1")) {
                    guardar = true;
                    linea.add(-1);
                }    
                else if(fichas[0].equals("-2")) {
                    guardar = true;
                    linea.add(-2);
                }
                if(!guardar) {
                    boolean fichasNoValid = false;
                    if(fichas.length != super.getNFichas())
                        fichasNoValid = true;
                    for(int i = 0; i < fichas.length && !fichasNoValid; i++) {
                        int num = Integer.parseInt(fichas[i]);
                        if (num >= 0 && num <= 2) linea.add(num);
                    }
                    boolean pistaCorrecta = true;
                    if(linea.size() == super.getNFichas()) {
                        ArrayList<Integer> pistaBuena = super.donaSolucio(tirada,solucio);
                        Funciones.ordenar(linea);
                        for(int i = 0; i < super.getNFichas(); i++) {
                            if(pistaBuena.get(i) != linea.get(i))
                                pistaCorrecta = false;
                        }
                        if(pistaCorrecta)
                            jugadaHecha = true;
                    }
                    if(fichasNoValid) 
                        System.out.println("El número de fichas introducido es incorrecto.");
                    if (!jugadaHecha && pistaCorrecta && !fichasNoValid) 
                        System.out.println("Has introducido un valor incorrecto.");
                    if(!pistaCorrecta)
                        System.out.println("La pista dada no es correcta.");
                }
            }
            
        }
        return linea;
    }
    
    /**
     *
     * @param tirada la tirada del codebreaker
     * @param solucio la solución de la partida
     * @param linea la pista
     * @return si la pista dada por el codemaker es la correcta
     */
    public boolean validarPista(ArrayList<CodePeg> tirada, ArrayList<CodePeg> solucio, ArrayList<Integer> linea){
        
        boolean pistaCorrecta = true;
        if(linea.size() == super.getNFichas()) {
            ArrayList<Integer> pistaBuena = super.donaSolucio(tirada,solucio);
            Funciones.ordenar(linea);
            for(int i = 0; i < super.getNFichas(); i++) {
                if(pistaBuena.get(i) != linea.get(i))
                    pistaCorrecta = false;
            }
        }
        return pistaCorrecta;        
    }
    
}
