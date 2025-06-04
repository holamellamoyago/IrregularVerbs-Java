package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

public class IrregularVerb implements Comparable<IrregularVerb> {
    private String infinitive, simple, participle, translation;

    public IrregularVerb(String infinitive, String simple, String participle, String translation) {
        this.infinitive = infinitive;
        this.simple = simple;
        this.participle = participle;
        this.translation = translation;
    }

    @Override
    public String toString() {
        return infinitive;
    }

    public static IrregularVerb getVb(List<IrregularVerb> l) {

        int rdm = new Random().nextInt(l.size());
        return l.get(rdm);

    }

    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public String getParticiple() {
        return participle;
    }

    public void setParticiple(String participle) {
        this.participle = participle;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public int compareTo(IrregularVerb o) {
        return infinitive.compareTo(o.infinitive);
        
    }

}
