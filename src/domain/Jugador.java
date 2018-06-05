package domain;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author Pérez Ortiz, Alejandro
 */
public class Jugador implements Serializable{
    private String name;
    private String password;
    private boolean IA;
    private int nFichas;
    private int nColores;
    
    
    public Jugador() {
        
    }
    
    /**
     *
     * @param nfichas número de fichas de la partida actual
     * @param ncolores número de colores de la partida actual
     */
    public Jugador(int nfichas, int ncolores) {
        this.nFichas = nfichas;
        this.nColores = ncolores;
    }
    
    /**
     *
     * @param n nombre de usuario
     * @param c contraseña
     */
    public void register(String n, String c) {
        this.name = n;
        this.password = c;
        this.IA = false;
    }
    
    /**
     *
     * @param n nombre de usuario
     * @param c contraseña
     */
    public void login(String n, String c) {
        this.name = n;
        this.password = c;
        this.IA = false;
    }
    
    /**
     *
     * @return el nombre de usuario del jugador
     */
    public String getName() {
        return this.name;
    }
    
    /**
     *
     * @return la contraseña del jugador
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     *
     * @return el número de colores de la partida actual
     */
    public int getNColores() {
        return this.nColores;
    }
    
    /**
     *
     * @return el número de fichas de la partida actual
     */
    public int getNFichas() {
        return this.nFichas;
    }
    
    public void setIA() {
        this.IA = true;
    }
    
    /**
     *
     * @return si el jugador es la IA o es un jugador real
     */
    public boolean esIA() {
        return this.IA;
    }
    
    /**
     *
     * @param n el nombre de usuario
     */
    public void setName(String n) {
        this.name = n;
    }
    
    /**
     *
     * @param c la contraseña
     */
    public void setPassword(String c) {
        this.password = c;
    }
    
    /**
     *
     * @param tirada intento de adivinar el patrón
     * @param solucio patrón de la partida
     * @return pegs que indican qué colores están bien colocados y cuáles están pero mal colocados
     */
    public ArrayList<Integer> donaSolucio(ArrayList<CodePeg> tirada, ArrayList<CodePeg> solucio) {
        ArrayList<Integer> linea = new ArrayList<>();
        ArrayList<Boolean> visitats = new ArrayList<>();
        ArrayList<Boolean> visitats2 = new ArrayList<>();
        for(int i = 0; i < nFichas; i++) {
            visitats.add(false);
            visitats2.add(false);
        }
        
        for(int i = 0; i < tirada.size(); i++) {
            if(tirada.get(i).getColour() == solucio.get(i).getColour()) {
                linea.add(2);
                visitats.set(i,true);
                visitats2.set(i,true);
            }   
        }
        
        
        for(int i = 0; i < tirada.size(); i++) {
            for(int j = 0; j < solucio.size() && !visitats.get(i); j++) {
                if(tirada.get(i).getColour() == solucio.get(j).getColour() && !visitats2.get(j)) {
                    linea.add(1);
                    visitats2.set(j,true);
                    visitats.set(i,true);
                }
            }
        }
        while(linea.size() < nFichas) {
            linea.add(0);
        } 
        return linea;
    }
}
