package export;

import com.opencsv.CSVReader;
import constructor.User;
import org.testng.Assert;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSV_Helper {

    public static List<User> LISTA_USUARIO = new ArrayList<User>();
	private static CSVReader reader;
	
    public static void loadpeopleFromCSV() throws IOException{
        CSVReader reader = new CSVReader(new FileReader("dataloader/usuarios.csv"));
        //meto un contador para saltear la cabecera del archivo

        String[] fila;
        int cabecera = 0;
        while((fila=reader.readNext()) != null){
            if (cabecera != 0){
                //System.out.println("--> " + fila[0]);
                for(int i = 0; i < fila.length; i++){
                    String infoDePersona = fila[i];
                    //System.out.println(infoDePersona);
                    procesarDatos(infoDePersona);

                }

            }
            cabecera++;

        }

    }
    public static List<String> getDataFromCSV(String filename) throws IOException{
        Assert.assertTrue(filename.endsWith(".csv"));
        reader = new CSVReader(new FileReader(filename));
        //meto un contador para saltear la cabecera del archivo
        int cabecera = 0;
        String[] fila;
        List<String> csvData = new ArrayList<String>();

        while((fila=reader.readNext()) != null){
            if (cabecera != 0){
                for(int i = 0; i < fila.length; i++){
                    String datosDeLaFila = fila[i];
                    csvData.add(datosDeLaFila);

                }

            }
            cabecera++;

        }
        return csvData;

    }
    public static HashMap<String,String> getDataFromCSVToHashMap(String filename) throws IOException{
        Assert.assertTrue(filename.endsWith(".csv"));
        reader = new CSVReader(new FileReader(filename));
        //meto un contador para saltear la cabecera del archivo
        int cabecera = 0;
        String[] fila;
        HashMap<String,String> csvData = new HashMap<String,String>();

        while((fila=reader.readNext()) != null){
            if (cabecera != 0){
                for(int i = 0; i < fila.length; i++){
                    String datosDeLaFila = fila[i];
                    String[] dfSplitted = datosDeLaFila.split(";");
                    csvData.put(dfSplitted[0],dfSplitted[1]);

                }

            }
            cabecera++;

        }
        return csvData;

    }
    public static void procesarDatos(String datosUsuario){
        String[] datos = datosUsuario.split(";");
        String nombreUsuario = datos[0];
        String password = datos[1];
        
        User usuario = new User(nombreUsuario, password);
        LISTA_USUARIO.add(usuario);

        System.out.println(">>> " + usuario);

    }
    
}