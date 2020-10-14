package dictionary;

public class Word {
    private String word_target;
    private String word_explain;

    public String getWord_target() {
        return word_target;
    }
    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_target(String s) {
        word_target = s;
    }
    public void setWord_explain(String s) {
        word_explain = s;
    }

    public Word() {
        word_target = "hello";
        word_explain = "xin chao";
    }
    public Word(String en, String vi) {
        word_target = en;
        word_explain = vi;
    }
}
