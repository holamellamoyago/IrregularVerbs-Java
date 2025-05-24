package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Util {
    final static String PATH = "src/Model/";

    static public String leerArchivo(String nombre) {
        String json = "";
        try (// 1º Leemos el archivo
                BufferedReader in = new BufferedReader(new FileReader(Util.PATH + nombre))) {
            int ch = in.read();

            while (ch != -1) {
                json += (char) ch;
                ch = in.read();
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    static public List<IrregularVerb> readVerbs() {
        List<IrregularVerb> verbos = new ArrayList<>();
        Gson gson = new Gson();

        try (JsonReader reader = new JsonReader(new FileReader(PATH + "IrregularVerbs.json"))) {
            reader.beginArray();

            while (reader.hasNext()) {
                IrregularVerb verb = gson.fromJson(reader, IrregularVerb.class);
                verbos.add(verb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(verbos);
        return verbos;
    }

}
