package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import utils.Funciones;
import utils.Pair;

/**
 *
 * @author Pérez Ortiz, Alejandro
 */
public final class CodeBreaker extends Jugador implements Serializable{
    ArrayList<ArrayList<Integer> > compatibles;
    ArrayList<ArrayList<Integer> > noUsados;
    HashMap<ArrayList<Integer>, Integer> combinaciones;
         
    boolean primeraTirada = true;
    
    /**
     *
     * @param i posición de aux que se está modificando
     * @param aux lista que es un código que posiblemente adivine el patrón
     */
    protected void conjunt(int i, ArrayList<Integer> aux) {
            if(i == super.getNFichas()) {
                    ArrayList<Integer> añadir = (ArrayList<Integer>) aux.clone();
                    compatibles.add(añadir);
            }
            else {
                for(int j = 0; j < super.getNColores(); j++) {
                    aux.set(i,j+1);
                    conjunt(i+1, aux); 
                }
            }
    }
    
    /**
     *
     * @param i posición de aux que se está modificando
     * @param aux posible lista de pegs para dar la pista al CodeBreaker
     */
    protected void creaCombinaciones(int i, ArrayList<Integer> aux){
        if(i == super.getNFichas()){
            ArrayList<Integer> añadir = (ArrayList<Integer>) aux.clone();
            Funciones.ordenar(añadir);
            if(!combinaciones.containsKey(añadir)) combinaciones.put(añadir,0);
        }
        else{
            for(int j = 2; j >= 0; j--){
                aux.set(i,j);
                creaCombinaciones(i+1,aux);
            }
        }
    }
    
    /**
     *
     * @param IA si el CodeBreaker es IA o un jugador real
     * @param nfichas número de fichas de la partida actual
     * @param ncolores número de colores de la partida actual
     */
    public CodeBreaker(boolean IA, int nfichas, int ncolores) {
        super(nfichas,ncolores);
        this.compatibles = new ArrayList<>();
        this.noUsados = new ArrayList<>();
        this.combinaciones = new HashMap<>();
        
        if(IA)
            super.setIA();
        if(this.esIA()){
            ArrayList<Integer> aux = new ArrayList<>();
            ArrayList<Integer> aux2 = new ArrayList<>();
            for(int i = 0; i < super.getNFichas(); i++) {
                aux.add(1);
                aux2.add(1);
            }
            conjunt(0,aux);
            noUsados = (ArrayList<ArrayList<Integer>>) compatibles.clone();
            creaCombinaciones(0,aux2);
            ArrayList<Integer> imposible = new ArrayList<>();
            for(int i = 0; i < super.getNFichas(); i++){
                if(i == ((super.getNFichas())-1)) imposible.add(1);
                else imposible.add(2);
            }
            combinaciones.remove(imposible);
        }
    }
    
    /**
     *
     * @param a lista de int a convertir en lista de CodePeg
     * @return devuelve la lista de codepegs igual a la lista de enteros
     */
    protected ArrayList<CodePeg> convert(ArrayList<Integer> a) {
        ArrayList<CodePeg> cambioCodePeg = new ArrayList<>();
        for(int i = 0; i < a.size(); i++){
            cambioCodePeg.add(new CodePeg(a.get(i),i+1,getNFichas(),getNColores()));
        }
        return cambioCodePeg;
    }

    /**
     *
     * @param tirada una de las listas a comparar
     * @param solucio segunda de las listas a comparar
     * @param code código que tiene que dar como resultado de comparar tirada y solucio
     * @return devuelve si el codigo de pista obtenido al comparar tirada y solucio es igual a code
     */
    protected boolean compare(ArrayList<CodePeg> tirada, ArrayList<KeyPeg> solucio, ArrayList<Integer> code){
        int nblancas = 0;
        int nnegras = 0;
        ArrayList<CodePeg> cambioCode = convert(code);
        ArrayList<Integer> aux = super.donaSolucio(cambioCode, tirada);
        Funciones.ordenar(aux);
        for(int i = 0; i < aux.size(); i++) {
            if(solucio.get(i).getColour() != aux.get(i))
                return false;
        }
        return true;
    }
    
    /**
     *
     * @param candidat código candidato a ser el mejor intento
     * @param descartat código que posiblemente quede descartado si intentamos adivinar el patrón con candidat
     * @return el código de colores que daría CodeMaker si candidat fuera un intento y descartat el patrón
     */
    protected ArrayList<Integer> miraSolucio(ArrayList<Integer> candidat, ArrayList<Integer> descartat){
        ArrayList<CodePeg> cambioCodePeg = convert(candidat);
        ArrayList<CodePeg> cambioCodePeg2 = convert(descartat);
        return donaSolucio(cambioCodePeg, cambioCodePeg2);
    }
    
    /**
     *
     * @param hm contiene las posibles soluciones que podría dar el codemaker, con sus respectivas cantidades de cuantas veces se da
     * @return el máximo número de apariciones de una solución
     */
    public int compruebaMaximo(HashMap<ArrayList<Integer>, Integer> hm) {
        int count = -1;
        Iterator it = hm.keySet().iterator();
        while(it.hasNext()) {
            ArrayList<Integer> key = (ArrayList<Integer>) it.next();
            Integer val = hm.get(key);
            if(val > count)
                count = val;
        }
        return count;
    }
    
    /**
     *
     * @param count número máximo de apariciones de una solución
     * @param min mínimo número de apariciones de una solución
     * @param i posición del elemento actual que estamos tratando
     * @param indice posición del elemento que se considera la mejor solución
     * @param compatible dice si el elemento que se considera la mejor solución es compatible
     * @return se devuelve el minimo, el indice y el booleano compatible actualizados con el nuevo elemento, si es mejor solución que la considerada anteriormente
     */
    public Pair<Pair<Boolean,Integer>,Integer> esSolucion(int count, int min, int i, Integer indice, boolean compatible) {
        Pair<Boolean, Integer> p = new Pair<>();
        Pair<Pair<Boolean,Integer>,Integer> sol = new Pair<>();
        int m = -1;
        boolean comp = (compatibles.contains(noUsados.get(i)));
        if(count < min) {
            p.setRight(i);
            m = count;
            if(comp)
                p.setLeft(true);
            else
                p.setLeft(false);
        }
        if(count == min && !compatible && comp) {
            m = count;
            p.setRight(i);
            p.setLeft(comp);
        }
        sol.setLeft(p);
        sol.setRight(m);
        return sol;
    }
    
    /**
     *
     * @return el mejor intento para el actual turno
     */
    protected ArrayList<Integer> millorOpcio() {
        int min = Integer.MAX_VALUE;
        int indice = 0;
        boolean compatible = false;
        for(int i = 0; i < noUsados.size(); i++){
            //algoritmo de posibilidades
            HashMap<ArrayList<Integer>, Integer> combinacionesAux = (HashMap<ArrayList<Integer>, Integer>) combinaciones.clone();
            for(int j = 0; j < compatibles.size(); j++) {
                ArrayList<Integer> aux = miraSolucio(noUsados.get(i), compatibles.get(j));
                Integer value = combinacionesAux.get(aux);
                combinacionesAux.put(aux,value+1);
            }
            int count = compruebaMaximo(combinacionesAux);
            Pair<Pair<Boolean,Integer>,Integer> p = esSolucion(count, min, i, indice, compatible);      
            if(p.getRight() != -1) {
                min = p.getRight();
                compatible = p.getLeft().getLeft();
                indice = p.getLeft().getRight();
            }
        }
        return noUsados.get(indice);
    }
    
    /**
     *
     * @param tirada el intento de este turno mirando la pista del turno anterior
     * @param solucio pista a la tirada del turno anterior
     */
    public void eliminaCompatibles(ArrayList<CodePeg> tirada, ArrayList<KeyPeg> solucio) {
        for(int i = 0; i < compatibles.size(); i++){
                    if(!compare(tirada,solucio,compatibles.get(i))){
                        compatibles.remove(i);
                        i--;
                    }
                }     
    }
    
    /**
     *
     * @return devuelve un valor arbitrario compuesto por dos 1 y tantos 2 como espacios sobren
     */
    public ArrayList<Integer> primeraTirada() {
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add(1);
        for(int i = 1; i < super.getNFichas(); i++) {
            if(i == 1)
                aux.add(1);
            else
                aux.add(2);
        }
        return aux;
        
    }
   
    /**
     *
     * @param s si el CodeBreaker es IA o un jugador real
     * @param tirada el intento de este turno mirando la pista del turno anterior
     * @param solucio pista a la tirada del turno anterior
     * @return intento de adivinar el patrón para el turno actual
     */
    public ArrayList<Integer> jugar(String s, ArrayList<CodePeg> tirada, ArrayList<KeyPeg> solucio) {
        ArrayList<Integer> linea;
        linea = new ArrayList<>();
        if(s.equals("IA")) {
            if(!primeraTirada){
                eliminaCompatibles(tirada,solucio);
                linea = millorOpcio();
            }
            else{
                ArrayList<Integer> aux = primeraTirada();
                primeraTirada = false;
                compatibles.remove(aux);
                return aux;
            }
        }
        else {
            boolean jugadaHecha = false;
            boolean guardar = false;
            while (!jugadaHecha && !guardar){
                linea = new ArrayList<>();
                Scanner input = new Scanner(System.in);
                System.out.println("Introduce tu jugada poniendo "+super.getNFichas()+" fichas, poniendo cada ficha del 1 al "+super.getNColores()+" separada de un espacio."
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
                        if (num >= 1 && num <= super.getNColores()) linea.add(num);
                    }
                    if(linea.size() == super.getNFichas()) jugadaHecha = true;
                    if(fichasNoValid) 
                        System.out.println("El número de fichas introducido es incorrecto.");
                    if (!jugadaHecha) 
                        System.out.println("Has introducido un valor incorrecto.");
                }
            }
        }
        return linea;
    }
}
