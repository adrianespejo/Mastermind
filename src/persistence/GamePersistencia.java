/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import utils.Pair;

/**
 *
 * @author Martínez Martínez, Daniel
 */
public class GamePersistencia implements Serializable{
    
    /**
     *
     * @param dirName es el directorio donde buscar los archivos
     * @return el listado de archivos de ese directorio
     */
    public File[] finder( String dirName){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith("Game"); }
        } );

    }
    
    /**
     *
     * @param ident el identificador de la partida
     * @param userName el nombre de usuario del jugador
     * @return si el jugador puede crear una partida con ese identificador
     */
    public boolean CheckAvailability(String ident, String userName) {
        
        boolean available = true;
        
        File folder = new File("data/players/"+userName+"/games/");
        File[] listOfFiles = folder.listFiles();
        
        if (listOfFiles != null){
            for (int i = 0; i < listOfFiles.length && available; i++) {

                String fileName = listOfFiles[i].getName().replaceFirst("[.][^.]+$", "");
                if (fileName.equals(ident)) available = false;

            }
        }
        
        return available;
        
    }
    
    /**
     *
     * @param userName el nombre del usuario que quiere eliminar la partida
     * @param id el id de la partida que se quiere eliminar
     * @return un booleano con si se ha podido eliminar la partiday un string con un mensaje de error si es necesario
     */
    public Pair <Boolean, String> eliminarPartida(String userName, String id){
        return new Pair(false, "Aun no está implementado.");
    }
    
}
