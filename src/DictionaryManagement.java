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
            while (scf.hasNextLine()) {
                Word w = new Word();
                String s = scf.nextLine();
                int x;
                if (s.contains("@")) x = s.indexOf("@");
                else if (s.contains("(")) x = s.indexOf("(");
                    else if (s.contains("/")) x = s.indexOf("/");
                        else x = s.length() / 2;
                en = s.substring(0, x-1);
                w.setWord_target(en);
                if (s.contains("@")) vi = s.substring(x + 2);
                else vi = s.substring(x);
                w.setWord_explain(vi);
                words.add(w);
            }
            scf.close();
        } catch (Exception e) {
            System.out.println("File error!");
        }
    }

    public String dictionaryLookup (String s) {
        String lookup = "Not find";
        for (Word w : words) {
            if (w.getWord_target().equalsIgnoreCase(s)) {
                lookup = w.getWord_explain();
                break;
            }
        }
        return lookup;
    }

    public void editDictionary(String s) {
        if (s.contains("add: ")) {
            int x = s.lastIndexOf("@");
            if (x != -1) {
                en = s.substring(5, x - 1);
                vi = s.substring(x + 2);
                Word w = new Word(en, vi);
                words.add(w);
            }
        }
        if (s.contains("delete: ")) {
            boolean check = true;
            String temp = s.substring(8);
            for (Word w : words) {
                if (w.getWord_target().equalsIgnoreCase(temp)) {
                    words.remove(w);
                    check = false;
                    break;
                }
            }
            if (check) System.out.println("the word is not in dictionary");
        }
        if (s.contains("export to txt")) dictionaryExportToFile();
    }

    public void dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("D:\\OOP\\Dictionary\\newDic.txt");
            for (Word word : words) {
                fw.write(word.getWord_target() + " " + word.getWord_explain() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error in export to file");
        }
    }
}
