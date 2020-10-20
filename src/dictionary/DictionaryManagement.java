package dictionary;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class DictionaryManagement {
    private Connection c = null;

    public void insertFromDatabase() {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:a.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String dictionaryLookup (String s) {
        try {
            String lookup = "";
            String sql = "SELECT * from dictionaries WHERE english LIKE '" + s + "'";
            ResultSet rs = c.createStatement().executeQuery(sql);
            String temp = rs.getString("vietnamese");
            int x = temp.indexOf("(");
            int y = temp.lastIndexOf("/");
            if (y > 0) lookup = temp.substring(0, y + 1) + "\n" + temp.substring(y + 2);
            else if (x == 0 && y < 0) {
                int z = temp.lastIndexOf(")");
                lookup = temp.substring(0, z + 1) + "\n" + temp.substring(z + 2);
            } else lookup = temp;
            rs.close();
            return lookup;
        } catch (Exception e) {
            return "Not find";
        }
    }

    public String addWord(String s1, String s2) {
        if (!dictionaryLookup(s1).equals("Not find"))
            return "\"" + s1 + "\"" + " has already" + "\n" + "been in dictionary!";
        String sql = "INSERT INTO dictionaries(english,vietnamese) VALUES(?,?)";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, s1);
            pstmt.setString(2, s2);
            pstmt.executeUpdate();
            return "\"" + s1 + "\"" + " has been added!";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String deleteWord(String s) {
        if (dictionaryLookup(s).equals("Not find")) return "\"" + s + "\"" + " is not" + "\n" + "in dictionary";
        String sql = "DELETE from dictionaries WHERE english LIKE '" + s + "'";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.executeUpdate();
            return "\"" + s + "\"" + " has" + "\n" + "been removed";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String editWord(String old, String new_en, String new_vi) {
        if (dictionaryLookup(old).equals("Not find")) return "\"" + old + "\"" + " is" + "\n" + "not in dictionary";
        try {
            if (new_en.equals("")) {
                String sql = "UPDATE dictionaries SET vietnamese = ? " + "WHERE english LIKE '" + old + "'";
                PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1, new_vi);
                pstmt.executeUpdate();
            }
            else if (new_vi.equals("")) {
                String sql = "UPDATE dictionaries SET english = ? " + "WHERE english LIKE '" + old + "'";
                PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1, new_en);
                pstmt.executeUpdate();
            }
            else {
                String sql = "UPDATE dictionaries SET english = ? , " + "vietnamese = ? " + "WHERE english LIKE '" + old + "'";
                PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(2, new_vi);
                pstmt.setString(1, new_en);
                pstmt.executeUpdate();
            }
            return "\"" + old + "\"" + " is edited";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public ArrayList<String> dictionarySearcher1(String s) {
        ArrayList<String> search = new ArrayList<>();
        try {
            String sql = "SELECT * from dictionaries WHERE english LIKE '" + s + "%' order by LOWER(english);";
            ResultSet rs = c.createStatement().executeQuery(sql);
            while (rs.next()) {
                search.add(rs.getString("english"));
            }
            rs.close();
            return search;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return search;
        }
    }

    public void TextToSpeech(String s) {
        if (!dictionaryLookup(s).equals("Not find"))
            try {
                System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
                Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
                Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

                synthesizer.allocate();
                synthesizer.resume();
                synthesizer.speakPlainText(s, null);
                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                //synthesizer.deallocate(); // Clean up
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }

    public String dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("src/dictionaryFiles/newDic.txt");

            try {
                String sql = "SELECT * from dictionaries";
                ResultSet rs = c.createStatement().executeQuery(sql);
                while (rs.next()) {
                    fw.write(rs.getString("english") + "\t" + rs.getString("vietnamese") + "\n");
                }
                rs.close();
            } catch (Exception e) {
                return "File: " + e.getMessage();
            }
            fw.close();
            return "Export to file successfully";
        } catch (Exception e) {
            return "Error in export to file";
        }
    }
}
