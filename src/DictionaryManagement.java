import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    public ArrayList<Word> words = new ArrayList<>();
    private String en, vi;

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
                words.add(w);
            }
            scf.close();
        } catch (Exception e) {
            System.out.println("File error!");
        }
    }

    public int dictionaryLookup (String s) {
        //String lookup = "Not find";
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

        //ko dung voi truong hop vi du nhu "a bit" hay "a few"...
        for (int i = 0; i <words.size(); i++) {
            int x = words.get(i).getWord_target().indexOf(' ');
            String temp = words.get(i).getWord_target().substring(0, x);
            if (temp.equals(s)) {
                //lookup = words.get(i).getWord_target() + " | " + words.get(i).getWord_explain();
                //break;
                return i;
            }
        }
        return 0;
        //System.out.println(lookup);
    }

    public void editDictionary(String s) {
        if (s.contains("add: ")) {
            int x = s.lastIndexOf("\t");
            en = s.substring(5, x);
            vi = s.substring(x + 1);
            Word w = new Word(en, vi);
            words.add(w);
        }
        else if (s.contains("delete: ")) {
            String temp = s.substring(8);
            int x = dictionaryLookup(temp);
            if (x != 0) words.remove(x);
            else System.out.println("the word is not in dictionary");
        }
        if (s.contains("export to txt")) dictionaryExportToFile();
    }

    public void dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("D:\\OOP\\Dictionary\\newDic.txt");
            for (int i = 0; i < words.size(); i++) {
                fw.write(words.get(i).getWord_target() + "\t" + words.get(i).getWord_explain() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
