import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dic = new Dictionary();
    String en, vi;

    public Word insertFromCommandline(Scanner sc) {
        Word w = new Word();
        en = sc.nextLine();
        w.setWord_target(en);
        vi = sc.nextLine();
        w.setWord_explain(vi);
        return w;
    }

    public void insertFromFile() {

        try {
            Scanner scf = new Scanner(new File("dictionaries.txt"));
            while (scf.hasNextLine()){
                Word w = new Word();
                String s = scf.nextLine();
                int x = s.lastIndexOf("\t");
                en = s.substring(0, x);
                w.setWord_target(en);
                vi = s.substring(x + 1);
                w.setWord_explain(vi);
                dic.words.add(w);
            }
            scf.close();
        } catch (Exception e) {
            System.out.println("File error!");
        }
    }

    public void dictionaryLookup (String s) {
        String lookup = "Not find";
        /*int l = 0, r = dic.words.size() - 1;
        while (l <= r) {
            int mid = l + (r - l)/2;
            String temp = dic.words.get(mid).getWord_target().contains(s);
            if (s.compareTo(dic.words.get(mid).getWord_target()) < 0) l = mid - 1;
            else if (s.compareTo(dic.words.get(mid).getWord_target()) > 0) r = mid + 1;
                else {
                    lookup = dic.words.get(mid).getWord_target();
                    break;
                }
            }*/

        for (int i = 0; i < dic.words.size(); i++) {
            int x = dic.words.get(i).getWord_target().indexOf(' ');
            //System.out.println(x);
            String temp = dic.words.get(i).getWord_target().substring(0, x);
            //System.out.println(temp);
            if (temp.equals(s)) {
                lookup = dic.words.get(i).getWord_target() + " | " + dic.words.get(i).getWord_explain();
                //break;
            }
        }
        System.out.println(lookup);
    }

    /*public ArrayList<Word> dictionarySearcher(String s) {
        ArrayList <Word> lookup = new ArrayList<>();
        int s_length = s.length();
        for (int i = 0; i < dic.words.size(); i++) {
            if (s_length > dic.words.get(i).getWord_target().length()) continue;
            String temp = dic.words.get(i).getWord_target().substring(0, s_length);
            if (temp.equals(s)) {
                Word w = new Word(dic.words.get(i).getWord_target(), dic.words.get(i).getWord_explain());
                lookup.add(w);
            }
        }
        return lookup;
    }*/

}
