package dictionary;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {

    public ArrayList<String> dictionarySearcher(String s, ArrayList<Word> words) {
        ArrayList <String> search = new ArrayList<>();
        int s_length = s.length();
        for (Word w : words) {
            if (s_length > w.getWord_target().length()) continue;
            String temp = w.getWord_target().substring(0, s_length);
            if (temp.equalsIgnoreCase(s))
                search.add(w.getWord_target());
        }
        return search;
    }

    public void showAllWords(ArrayList<Word> words) {
        for (int i = 0; i < words.size(); i++) {
            if (i == 0)
                System.out.printf("%-6s%-20s%s\n", "No", "English", "Vietnamese");
            System.out.printf("%-6s%-20s%s\n", i + 1, words.get(i).getWord_target(), words.get(i).getWord_explain());
        }
    }

    public void printSearch(ArrayList<String> search) {
        for (String i : search) System.out.println(i);
    }

    public void dictionaryAdvanced(String s, DictionaryManagement dicMa) {

        String lookup = dicMa.dictionaryLookup(s);
        System.out.println(lookup);
    }
/*
    public static void main(String[] args) {
        System.out.println("Muon them du lieu vao tu dien, nhap theo cu phap: add: english\tvienamese");
        System.out.println("Muon xoa du lieu trong tu dien, nhap theo cu phap: delete: english");
        System.out.println("Muon xuat du lieu tu dien ra file txt, nhap theo cu phap: export to txt");

        Scanner sc = new Scanner(System.in);
        DictionaryCommandline dicC = new DictionaryCommandline();
        DictionaryManagement dicMa = new DictionaryManagement();
        dicMa.insertFromFile();

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            dicC.dictionaryAdvanced(s, dicMa);
        }

        sc.close();
    }

 */
}
