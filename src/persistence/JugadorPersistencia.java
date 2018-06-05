package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import utils.Pair;

/**
 *
 * @author Pérez Ortiz, Alejandro
 */
public class JugadorPersistencia {

    
    private boolean escribeFichero(String n, String c, File info) {
        try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(info));
                bw.write(n+" "+c);
                bw.close();
                return true;
            }
            catch(IOException e) {
                return false;
            }
    }
    
    /**
     *
     * @param n nombre de usuario
     * @param c contraseña
     * @return un booleano con si se ha podido registrar y un string con un mensaje de error si es necesario
     */
    public Pair<Boolean, String> register(String n, String c) {
        File dir = new File("data/players/"+n);
        boolean b = dir.mkdirs();
        Pair<Boolean, String> p = new Pair();
        if(b) {      
            p.setRight("Te has registrado correctamente");
            File dir2 = new File("data/players/"+n+"/games");
            dir2.mkdir();
            File info = new File("data/players/"+n+"/info.txt");
            if(escribeFichero(n,c,info))
                p.setLeft(true);
            else {
                p.setRight("Error en el registro");
                p.setLeft(false);
                return p;
            }
        }
        else {
            p.setRight("El jugador ya existe");
            p.setLeft(false);
        }
        return p;
    }
    
    /**
     *
     * @param n nombre de usuario
     * @param c contraseña
     * @return un booleano con si se ha podido hacer login y un string con un mensaje de error si es necesario
     */
    public Pair<Boolean, String> login(String n, String c) {
        Pair<Boolean, String> p = new Pair();
        try{
            String linea;
            FileReader f = new FileReader("data/players/"+n+"/info.txt");
            BufferedReader b = new BufferedReader(f);
            linea = b.readLine();
            b.close();
            String palabra[] = linea.split(" ");
            if(!palabra[1].equals(c)) {
                p.setRight("La contraseña introducida es incorrecta");
                p.setLeft(false);
                return p;
            }
            File dir2 = new File("data/players/"+n+"/games");
            if (!dir2.exists()) dir2.mkdir();
            p.setRight("Has iniciado sesión correctamente");
            p.setLeft(true);
        }
        catch(IOException ex) {
            p.setRight("El usuario introducido es incorrecto");
            p.setLeft(false);
            return p;
        }
        return p;
     }
    
    /**
     *
     * @param n1 nombre actual del usuario
     * @param n2 nombre que se quiere poner el usuario
     * @param c contraseña del usuario
     * @return un booleano con si se ha podido asignar el nombre y un string con un mensaje de error si es necesario
     */
    public Pair<Boolean, String> setName(String n1,String n2, String c) {
        File dir = new File("data/players/"+n1);
        File dir2 = new File("data/players/"+n2);
        boolean success = dir.renameTo(dir2);
        Pair<Boolean, String> p = new Pair<>();
        if(!success) {
            p.setRight("El nombre de usuario ya está en uso.");
            p.setLeft(false);
        }
        else {
            File info = new File("data/players/"+n2+"/info.txt");
            if(escribeFichero(n2,c,info)) {
                p.setRight("Has cambiado tu nombre de usuario correctamente.");
                p.setLeft(true);
            }
            else {
                p.setRight("Error al cambiar de nombre");
                p.setLeft(false);
            }
        }
        return p;
    }
    
    /**
     *
     * @param n el nombre del usuario
     * @param c la contraseña
     * @return un booleano con si se ha podido asignar la contraseña y un string con un mensaje de error si es necesario
     */
    public Pair<Boolean, String> setPassword(String n, String c) {
        File info = new File("data/players/"+n+"/info.txt");
        Pair<Boolean, String> p = new Pair<>();
        if(escribeFichero(n,c,info)) {
            p.setRight("Has cambiado tu contraseña correctamente.");
            p.setLeft(true);
        }
        else {
            p.setRight("Error al cambiar de contraseña");
            p.setLeft(false);
        }
        return p;
    }
    
    private void borrarDirectorio(File f) {
        File[] ficheros = f.listFiles();
        for(int i = 0; i < ficheros.length; i++) {
            if(ficheros[i].isDirectory())
                borrarDirectorio(ficheros[i]);
            ficheros[i].delete();
        }
    }
    
    /**
     *
     * @param n nombre del usuario
     * @return un booleano con si se ha podido eliminar y un string con un mensaje de error si es necesario
     */
    public Pair<Boolean,String> elimina(String n) {
        File f = new File("data/players/"+n);
        borrarDirectorio(f);
        Pair<Boolean,String> p = new Pair<>();
        if(f.delete()) {
            p.setLeft(true);
            p.setRight("El usuario se ha eliminado correctamente");
            return p;
        }
        else {
            p.setLeft(false);
            p.setRight("No se ha podido eliminar el usuario");
            return p;
        }
    }
    
    /**
     *
     * @param n el nombre del usuario que quiere ver sus partidas guardadas
     * @return una lista de las partidas guardadas por el usuario
     */
    public String[] obtenerPartidas(String n) {
        String[] res1 = {"No hay partidas"};
        File dir = new File("data/players/"+n+"/games");
        if(dir.exists()) {
            File[] ficheros = dir.listFiles();
            String[] res2 = new String[ficheros.length];
            for(int i = 0; i < ficheros.length; i++) {
                res2[i] = ficheros[i].getName();
            }
            if (res2.length > 0) return res2;
        }
        return res1;
    }
}
