package legal.Service.LegalProcess;

import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.LegalInfo.LegalInfoModel;
import org.apache.commons.lang.math.NumberUtils;

import java.sql.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by son on 2/20/2017.
 */
public class LinkProcess implements ILegalProcess {
    String inputLine;
    String link;
    Boolean valid = false;
    String data = null;
    LinkedList<String> lines = new LinkedList<>();
    LegalInfoModel legalInfo = new LegalInfoModel();

    public LinkProcess(String link) {
        this.link = link;
        process();
    }

    public void process() {
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
                lines.addLast(inputLine);
            }
            if (findFlag("vbInfo") != null) {
                LinkedList<String> copy = (LinkedList<String>) lines.clone();
                findFlag("class=\"red\"");
                String first = lines.pop();
                String temp = first;
                temp = temp.substring(0, temp.length() - 5);
                temp = temp.trim();
                temp = temp.replaceAll("\\s+", " ");
                legalInfo.status = temp;
                findFlag("li class=\"green\"");
                temp = lines.pop();
                temp = temp.substring(0, temp.length() - 5);
                temp = temp.trim();
                temp = temp.replaceAll("\\s+", " ");
                String[] ss = temp.split("/");
                temp = "";
                for (String t : ss) {
                    if (NumberUtils.isNumber(t)) {
                        temp = t + "-" + temp;
                    }
                }
                temp = temp.substring(0, temp.length() - 1);
                if (Date.valueOf(temp) != null) legalInfo.dateExecute = Date.valueOf(temp);
//                System.out.println(legalInfo.dateExecute);
                findFlag("<b >");
                temp = lines.pop();
                temp = temp.substring(0, temp.length() - 10);
                temp = temp.trim();
                temp = temp.replaceAll("\\s+", " ");
                legalInfo.institution = temp;

                findFlag("Số:");
                temp = lines.pop();
                temp = temp.substring(0, temp.length() - 6);
                temp = temp.trim();
                temp = temp.replaceAll("\\s+", " ");
                legalInfo.number = temp;

                findFlag("<i>");
                temp = lines.pop();
                temp = temp.substring(0, temp.length() - 1);
                temp = temp.trim();
                temp = temp.replaceAll("\\s+", " ");
                legalInfo.standing = temp;

                temp = lines.pop();
                temp += lines.pop();
                temp += lines.pop();
                temp += lines.pop();
                temp = temp.substring(0, temp.length() - 10);
                temp = temp.trim();
                temp = temp.replaceAll("\\s+", " ");
//            System.out.println(temp);
                ss = temp.split(" ");
                temp = "";
                for (String t : ss) {
                    if (NumberUtils.isNumber(t)) {
                        temp = t + "-" + temp;
                    }
                }
                temp = temp.substring(0, temp.length() - 1);
                if (Date.valueOf(temp) != null) legalInfo.dateCreated = Date.valueOf(temp);

                temp = findInLineByTag("<strong>");
//            System.out.println(temp);
                legalInfo.type = temp.replace("'","''");

                temp = findInLineByTag("<strong>");
//            System.out.println(temp);
                legalInfo.title = temp.replace("'","''");

                temp = findNguoiKi();
                temp = temp.trim();
                temp = temp.replaceAll("\\s+", " ");
//            System.out.println(temp);
                legalInfo.confirmation = temp.replace("'","''");
                valid = true;
                String last = lines.pop();
                lines.peekFirst();
                int a = copy.indexOf(first);
                int b = copy.indexOf(last);
                data = "";
                for (int t = a; t < b; t++) {
                    data += copy.get(t) + "\n";
                }
                data = data.replace("'","''");
            }
        } catch (Exception ex) {

        }
    }

    String findFlag(String key) {
        int nKey = key.length();
        boolean kt = true;
        while (kt && !lines.isEmpty()) {
            inputLine = lines.pop();
            for (int i = 0; i < inputLine.length(); i++) {
                if (inputLine.charAt(i) == key.charAt(0)) {
                    int check = 1;
                    for (int j = 1; j < nKey; j++) {
                        if (i + j >= inputLine.length()) {
                            check = 0;
                            break;
                        }
                        if (inputLine.charAt(i + j) != key.charAt(j)) {
                            check = 0;
                            break;
                        }
                    }
                    if (check == 1) {
                        return inputLine;
                    }
                }
            }
        }
        return null;
    }

    String findInLineByTag(String key) {
        String result = "";
        int nKey = key.length();
        boolean kt = true;
        while (kt && !lines.isEmpty()) {
            inputLine = lines.pop();
            for (int i = 0; i < inputLine.length(); i++) {
                if (inputLine.charAt(i) == key.charAt(0)) {
                    int check = 1;
                    for (int j = 1; j < nKey; j++) {
                        if (i + j >= inputLine.length()) {
                            check = 0;
                            break;
                        }
                        if (inputLine.charAt(i + j) != key.charAt(j)) {
                            check = 0;
                            break;
                        }
                    }
                    if (check == 1) {
                        for (int h = 0; h < inputLine.length(); h++) {
                            if (inputLine.charAt(h) == '<') {
                                h++;
                                while (inputLine.charAt(h) != '>' && h < inputLine.length()) h++;
                            } else {
                                result += inputLine.charAt(h);
                            }
                        }
                        result = result.trim();
                        kt = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    String findNguoiKi() {
        String inputLine = findFlag("class=\"table_cqbh_cd_nk\"");
        String key = "style=\"width:775px\"><p>";
        int nKey = key.length();
        String result = "";
        for (int i = 0; i < inputLine.length(); i++) {
            if (inputLine.charAt(i) == key.charAt(0)) {
                int check = 1;
                for (int j = 1; j < nKey; j++) {
                    if (i + j >= inputLine.length()) {
                        check = 0;
                        break;
                    }
                    if (inputLine.charAt(i + j) != key.charAt(j)) {
                        check = 0;
                        break;
                    }
                }
                if (check == 1) {
                    i += nKey;
                    while (inputLine.charAt(i) != '<') {
                        result += inputLine.charAt(i);
                        i++;
                    }
                    result += " ";
                }
            }
        }
        return result;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public LegalInfoModel getInfo() {
        return legalInfo;
    }

    @Override
    public List<String> getLines() {
        return lines;
    }

    @Override
    public String getData() {
        return data;
    }
}
