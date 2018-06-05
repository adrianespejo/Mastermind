package domain;

import java.io.Serializable;

/**
 *
 * @author Espejo Saldaña, Adrián
 */
public final class KeyPeg extends Casilla implements Serializable{
    private final int colourKey;
    private final int position;
    
    /**
     *
     * @param col color del KeyPeg
     * @param pos posición del KeyPeg
     * @param total total de fichas
     * @throws IllegalArgumentException si el color no está dentro del rango o la posición no es válida
     */
    public KeyPeg(int col, int pos,int total) throws IllegalArgumentException {
        if(!colourValid(col,2)){
            System.out.println("El color no está dentro del rango");
            throw new IllegalArgumentException("Invalid argument");
        }
        if(!posValid(pos,total)){
            System.out.println("La posición no está dentro del rango");
            throw new IllegalArgumentException("Invalid argument");
        }
        this.colourKey = col;
        this.position = pos;
    }

    /**
     *
     * @param col color del KeyPeg
     * @param rango rango de colores
     * @return si el color está en el rango de colores de las pistas
     */
    @Override
    public boolean colourValid(int col, int rango){    
        return (col >= 0 && col <= rango);
    }

    /**
     *
     * @param pos posición del KeyPeg
     * @param total total de fichas
     * @return si la posición es válida con el número de fichas
     */
    @Override
    public boolean posValid(int pos, int total){
        return (pos > 0 && pos <= total);
    }

    /**
     *
     * @return el color del KeyPeg
     */
    public int getColour(){
        return this.colourKey;
    }

    /**
     *
     * @return la posición del KeyPeg
     */
    public int getPosition(){
        return this.position;
    }
}
