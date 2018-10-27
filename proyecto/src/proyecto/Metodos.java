/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import static proyecto.Proyecto.comm;

public class Metodos {
    //--------------------/             File checking method                /--------------------//

    public  boolean checkfile(String filename) throws IOException {
        try {
            FileReader fich = new FileReader("src/proyecto/" + filename.substring(1, filename.length() - 1) + ".txt");
            return true;
        }catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
            return false;
        }

    }
//--------------------/----------------------------------------/--------------------// 
   
//--------------------/             Assign method                /--------------------//

    public void assignx(String filename, String Var_identifier) {
        System.out.println("src/proyecto/" + filename.substring(1, filename.length() - 1) + ".txt");
        File tmp = new File("src/proyecto/" + filename.substring(1, filename.length() - 1) + ".txt");
        if (tmp.exists()) {
            comm.put(Var_identifier, tmp);
        }
        System.out.println(comm.keySet());
        System.out.println(comm.values());

    }
//--------------------/----------------------------------------/--------------------//
    
//--------------------/             Create method                /--------------------//

    public void createx(String filename, String Var_identifier) throws IOException {
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
//--------------------/----------------------------------------/--------------------//
    
//--------------------/             var_id1 = var_id2 method                /--------------------//

    public void eq (String v1,String v2) throws IOException{
           
            BufferedReader bfr = new BufferedReader(new FileReader(comm.get(v2)));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(comm.get(v1)));
            Stream<String> tmp = bfr.lines();
 
            tmp.forEach((x)->{
                try {
                    bfw.write(x);
                    bfw.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            bfw.close();
            bfr.close();
            
            
      
    }
//--------------------/----------------------------------------/--------------------//
   
//--------------------/             var_id1 = var_id2 + var_id3 method                 /--------------------//
    
    public void eqp(String v1, String v2,String v3) throws IOException{
            BufferedReader bfr = new BufferedReader(new FileReader(comm.get(v2)));
            BufferedReader bfr2 = new BufferedReader(new FileReader(comm.get(v3)));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(comm.get(v1)));
            
        Stream<String> tmp = bfr.lines();
        Stream<String> tmp2 = bfr2.lines();
        Stream<String> out = Stream.concat(tmp, tmp2);
        out.forEach((x)->{
                try {
                    bfw.write(x);
                    bfw.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        });
            bfr.close();
            bfr2.close();
            bfw.close();
    }
    
    
    
//--------------------/             Syntax checking method                /--------------------//

    public  int sintax_assign(String line) throws IOException {
        String[] tmp = line.split(" ");
        if (tmp.length == 4) {
//-----------------------------------------------------------------//
//>>    Checks command var_id1 = rem_doubles var_id2
//-----------------------------------------------------------------//
            
            if (tmp.length == 4 && tmp[1].equals("=")) {
                
                if (tmp[2].equals("rem_doubles")) {
                    if(comm.containsKey(tmp[0]) && comm.containsKey(tmp[3])){
                        return 1;
                    }
                    else return 0;
                } 
                else {
                    return 0;
                }
//-----------------------------------------------------------------//
//>>    Checks command assign and create
//-----------------------------------------------------------------//

            } else {
                switch (tmp[0]) {
                    case "assign":
                        if (tmp[1].startsWith(String.valueOf('"')) && tmp[1].endsWith(String.valueOf('"'))) {
                            if (checkfile(tmp[1]) == true) {
                                if( tmp[2].equals("to")){
                                   return 2; 
                                }
                                else return 0;
                            } else {
                                return 0;
                            }
                        } else {
                            return 0;
                        }
                    case "create":
                        System.out.println();
                        if (tmp[1].startsWith(String.valueOf('"')) && tmp[1].endsWith(String.valueOf('"'))) {

                            if(tmp[2].equals("as")){
                                return 3;
                            }
                            else return 0;

                        } else {
                            return 0;
                        }
                    default:
                        return 0;
                }
            }
        } else if (tmp.length == 3 || tmp.length == 5) {
//-----------------------------------------------------------------//
//>>    Checks command var_id1 = var_id2
//-----------------------------------------------------------------//

            if (tmp.length == 3 && tmp[1].equals("=")) {
                if( comm.containsKey(tmp[0]) && comm.containsKey(tmp[2])){
                    return 4;
                }
                else return 0;
//-----------------------------------------------------------------//
//>>    Checks command var_id1 = var_id2 + var_id3
//-----------------------------------------------------------------//

            } else if (tmp.length == 5 && tmp[1].equals("=") && tmp[3].equals("+")) {
                if( comm.containsKey(tmp[0]) && comm.containsKey(tmp[2]) && comm.containsKey(tmp[4])){
                    return 5;
                }
                else return 0;
//-----------------------------------------------------------------//
//>>    Checks command var_id1 = sort var_id2 asc/des
//-----------------------------------------------------------------//
            } else if (tmp.length == 5 && tmp[1].equals("=") && tmp[2].equals("sort")) {
                
                if (tmp[4].equalsIgnoreCase("asc") || tmp[4].equalsIgnoreCase("des")) {
                    if (comm.containsKey(tmp[0]) && comm.containsKey(tmp[3])){
                        return 6;
                    }else return 0;
                    
                } else {
                    return 0;
                }
            }
            else{
                return 0;
            }
        } else {
            return 0;
        }

    }
//--------------------/----------------------------------------/--------------------//
}
