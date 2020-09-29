import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    void showAllWords(ArrayList<Word> words) {
        for (int i = 0; i < words.size(); i++) {
            if (i == 0)
                System.out.println("No\t" + "| English\t" + "| Vietnamese");
            System.out.println((i+1) + "\t| " + words.get(i).getWord_target() + "\t| " + words.get(i).getWord_explain());
        }

    }

    public void dictionaryAdvanced(String s) {
        DictionaryManagement dicMa = new DictionaryManagement();
        dicMa.insertFromFile();
        dicMa.dictionaryLookup(s);
        /*ArrayList <Word> search = dicMa.dictionarySearcher(s);
        if (search.size() == 0) System.out.println("Not find");
        showAllWords(search);*/
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        DictionaryCommandline dicC = new DictionaryCommandline();
        dicC.dictionaryAdvanced(s);
        sc.close();
    }
}
