package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

import Model.Word;

public class ConvertJson {
    public static void main(String[] args) {

        Map<Word, Integer> mapaWords = convertirAmap();

        guardarEnJson(mapaWords);

    }

    private static void guardarEnJson(Map<Word,Integer> mapa){
        Gson gson = new Gson();
        String json = gson.toJson(mapa);

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("src/Model/Words2.json")));
            out.write(json);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Word, Integer> convertirAmap() {
        Map<Word, Integer> mapaWords = new TreeMap<>();
        try (FileReader file = new FileReader(new File("src/Model/words.json"))) {
            BufferedReader in = new BufferedReader(file);
            String linea = in.readLine();
            while (linea != null) {
                linea = linea.replace("\"", "");
                linea = linea.replace(",", "");

                if (linea.contains("{") || linea.contains("}")) {
                    linea = in.readLine();
                    continue;
                }

                System.out.println(linea);
        
                String[] palabras = linea.split(":");

                if (palabras.length > 0) {
                    Word word = new Word(palabras[0], palabras[1]);
                    mapaWords.put(word, 0);
                }

                linea = in.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapaWords;
    }
}
