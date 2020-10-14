package dictionary;

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
            Scanner scf = new Scanner(new File("src\\dictionaryFiles\\dictionaries.txt"));
            while (scf.hasNextLine()){
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
                String temp = w.getWord_explain();
                int x = temp.indexOf("(");
                int y = temp.lastIndexOf("/");
                if (y > 0) lookup = temp.substring(0, y + 1) + "\n" + temp.substring(y + 2);
                else if (x == 0 && y < 0) {
                    int z = temp.lastIndexOf(")");
                    lookup = temp.substring(0, z + 1) + "\n" + temp.substring(z + 2);
                } else lookup = temp;
                break;
            }
        }
        return lookup;
    }

    public String addWord(String s1, String s2) {
        if (dictionaryLookup(s1).equals("Not find")) {
                Word w = new Word(s1, s2);
                words.add(w);
        } else return "The word has already been" + "\n" + "in dictionary!";
        return "The word has been added!";
    }

    public String deleteWord(String s) {
        boolean check = true;
        for (Word w : words) {
            if (w.getWord_target().equalsIgnoreCase(s)) {
                words.remove(w);
                check = false;
                break;
            }
        }
        if (check) return s + " is not" + "\n" + "in dictionary!";
        else return s + "\n" + " has been removed!";
    }

    public String dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("src\\dictionaryFiles\\newDic.txt");
            for (Word word : words) {
                fw.write(word.getWord_target() + " " + word.getWord_explain() + "\n");
            }
            fw.close();
            return "";
        } catch (Exception e) {
            return "Error in export to file";
        }
    }
}
