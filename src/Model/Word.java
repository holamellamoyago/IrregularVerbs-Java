package Model;

import java.io.Serializable;

public class Word implements Comparable<Word>, Serializable {
    protected String gerundio, traduccion;

    public Word(String gerundio, String traduccion) {
        this.gerundio = gerundio;
        this.traduccion = traduccion;
    }

    @Override
    public int compareTo(Word o) {
        return gerundio.compareTo(o.gerundio);
    }

    
}
