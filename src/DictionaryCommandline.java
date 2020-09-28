import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    Dictionary dic = new Dictionary();

    void showAllWords(int n, ArrayList<Word> words) {
        for (int i = 0; i < n; i++) {
            if (i == 0)
                System.out.println("No\t" + "| English\t" + "| Vietnamese");
            System.out.println((i+1) + "\t| " + words.get(i).getWord_target() + "\t| " + words.get(i).getWord_explain());
        }

    }

    public void dictionaryBasic(int n, Scanner sc) {
        DictionaryManagement dicMa = new DictionaryManagement();
        for (int i = 0; i < n; i++) {
            Word w = dicMa.insertFromCommandline(sc);
            dic.words.add(w);
        }
        showAllWords(n, dic.words);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        DictionaryCommandline dicC = new DictionaryCommandline();
        dicC.dictionaryBasic(n, sc);
    }
}
