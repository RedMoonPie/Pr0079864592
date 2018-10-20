/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.Hashtable;
import java.util.Iterator;

public class Proyecto {

    //static Hashtable<String,File> com = new Hashtable<String,File>();
    static HashMap<String, File> comm = new HashMap<String, File>();

    public static boolean checkfile(String filename) throws IOException {
        try {
            FileReader fich = new FileReader("src/proyecto/" + filename.substring(1, filename.length() - 1) + ".txt");
            return true;
        } catch (IOException a) {
            return false;
        }

    }

    public static void assignx(String filename, String Var_identifier) {
        System.out.println("src/proyecto/" + filename.substring(1, filename.length() - 1) + ".txt");
        File tmp = new File("src/proyecto/" + filename.substring(1, filename.length() - 1) + ".txt");
        if (tmp.exists()) {
            comm.put(Var_identifier, tmp);
        }
        System.out.println(comm.keySet());
        System.out.println(comm.values());

    }

    public static void createx(String filename, String Var_identifier) throws IOException {
        File tmp = new File("src/proyecto/" + filename.substring(1, filename.length() - 1) + ".txt");
        if (!tmp.exists()) {
            tmp.createNewFile();
            comm.put(Var_identifier, tmp);
        } else {
            System.out.println("El archivo Ya existe");
        }
        System.out.println(comm.keySet());
        System.out.println(comm.values());
    }

    public static boolean sintax_assign(String line) throws IOException {
        String[] tmp = line.split(" ");
        if (tmp.length == 4) {
            switch (tmp[0]) {
                case "assign":
                    if (tmp[1].startsWith(String.valueOf('"')) && tmp[1].endsWith(String.valueOf('"'))) {
                        if (checkfile(tmp[1]) == true) {
                            return tmp[2].equals("to");
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                case "create":
                    System.out.println();
                    if (tmp[1].startsWith(String.valueOf('"')) && tmp[1].endsWith(String.valueOf('"'))) {

                        return tmp[2].equals("as");

                    } else {
                        return false;
                    }
                default:
                    return false;
            }

        } else {
            return false;
        }

    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> sups = new ArrayList<String>();
        File cms = new File("src/proyecto/try.txt");
        BufferedReader bfr = new BufferedReader(new FileReader(cms));
        int ct = 0;

        String linea = bfr.readLine();
        while (linea != null) {
            sups.add(linea);
            linea = bfr.readLine();

        }
        for (int i = 0; i < sups.size(); i++) {
            ct += 1;

            if (sintax_assign(sups.get(i))) {
                if (sups.get(i).split(" ")[0].equals("assign")) {
                    assignx(sups.get(i).split(" ")[1], sups.get(i).split(" ")[3]);
                }
                if (sups.get(i).split(" ")[0].equals("create")) {
                    createx(sups.get(i).split(" ")[1], sups.get(i).split(" ")[3]);
                }
            } else {
                System.out.println("Error en la linea: " + ct);
            }
        }

//        createx("juan","aponte");
//        
////        
//        File a = comm.get("aponte");
//        
//         BufferedReader br = new BufferedReader(new FileReader(a));
//         BufferedWriter bw = new BufferedWriter(new FileWriter(a));
//         bw.write("Hola Mundo");
//         bw.flush();
//         System.out.println(br.readLine());
//         br.close();
    }

}
