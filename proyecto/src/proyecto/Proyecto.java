/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Proyecto {

    //static Hashtable<String,File> com = new Hashtable<String,File>();
    static HashMap<String, File> comm = new HashMap<String, File>();
 
    public static void main(String[] args) throws IOException {
        Metodos m = new Metodos();
        ArrayList<String> sups = new ArrayList<String>();
        File cms = new File("src/proyecto/try.txt");
        BufferedReader bfr = new BufferedReader(new FileReader(cms));
        int ct = 0;
        String linea = bfr.readLine();
        while (linea != null) {
            sups.add(linea);
            linea = bfr.readLine();

        }
        bfr.close();
        
        for (int i = 0; i < sups.size(); i++) {
            ct += 1;
            int a = m.sintax_assign(sups.get(i));
            if (a !=0 ) {
//-----------------------------------------------------------------//
//>>    Assign command
//-----------------------------------------------------------------//

                if (a == 2) {
                    m.assignx(sups.get(i).split(" ")[1], sups.get(i).split(" ")[3]);
                }
//--------------------/----------------------------------------/--------------------//

//-----------------------------------------------------------------//
//>>    create command
//-----------------------------------------------------------------//

                if (a == 3) {
                    m.createx(sups.get(i).split(" ")[1], sups.get(i).split(" ")[3]);
                }
//--------------------/----------------------------------------/--------------------// 

//-----------------------------------------------------------------//
//>>    command var_id1 = var_id2
//-----------------------------------------------------------------//
            
                if(a == 4){
                    m.eq(sups.get(i).split(" ")[0], sups.get(i).split(" ")[2]);
                }
//-----------------------------------------------------------------//
//>>    comprueba comando var_id1 = var_id2 + var_id3
//-----------------------------------------------------------------//

                if(a == 5){
                    m.eqp(sups.get(i).split(" ")[0],sups.get(i).split(" ")[2],sups.get(i).split(" ")[4]);
                }


//--------------------/----------------------------------------/--------------------//

//--------------------/----------------------------------------/--------------------//
            } else {
                System.out.println("Error en la linea: " + ct);
            }
        }

    }

}
