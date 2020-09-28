import java.util.Scanner;

public class DictionaryManagement {
    String en, vi;

    public Word insertFromCommandline(Scanner sc) {
        Word w = new Word();
        en = sc.nextLine();
        w.setWord_target(en);
        vi = sc.nextLine();
        w.setWord_explain(vi);
        return w;
    }

    /*public Word insertFromFile() {

    }

    public void dictionaryLookup() {

    }*/

}
