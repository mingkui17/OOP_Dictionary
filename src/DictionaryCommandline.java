import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    public ArrayList<Word> dictionarySearcher(String s, ArrayList<Word> words ) {
        ArrayList <Word> search = new ArrayList<>();
        int s_length = s.length();
        for (Word word : words) {
            if (s_length > word.getWord_target().length()) continue;
            String temp = word.getWord_target().substring(0, s_length);
            if (temp.equals(s)) {
                Word w = new Word(word.getWord_target(), word.getWord_explain());
                search.add(w);
            }
        }
        return search;
    }

    void showAllWords(ArrayList<Word> words) {
        for (int i = 0; i < words.size(); i++) {
            if (i == 0)
                System.out.println("No\t" + "| English\t" + "| Vietnamese");
            System.out.println((i+1) + "\t| " + words.get(i).getWord_target() + "\t| " + words.get(i).getWord_explain());
        }
    }

    public void dictionaryAdvanced(String s, DictionaryManagement dicMa) {
        dicMa.editDictionary(s);
        ArrayList <Word> search = dictionarySearcher(s, dicMa.words);
        if (search.size() == 0) System.out.println("Not find");
        else showAllWords(search);
    }

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
}
