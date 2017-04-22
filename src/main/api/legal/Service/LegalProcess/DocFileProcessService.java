package legal.Service.LegalProcess;

import com.google.common.base.CharMatcher;
import com.sun.org.apache.xpath.internal.SourceTree;
import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.LegalInfo.LegalInfoModel;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Son on 4/13/2017.
 */
public class DocFileProcessService implements ILegalProcess{
    List<String> lines = new ArrayList<>();
    Boolean valid = false;
    LegalInfoModel legalInfo = new LegalInfoModel();

    public DocFileProcessService(String fileDir) {
        try
        {
            InputStream data = new FileInputStream(new File(fileDir));
            HWPFDocument document = new HWPFDocument(data);
            WordExtractor extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            this.lines = Arrays.asList(fileData);
            process();
        }
        catch (Exception exep)
        {
            exep.printStackTrace();
        }
    }

    public DocFileProcessService(InputStream data) throws IOException {
            HWPFDocument document = new HWPFDocument(data);
            WordExtractor extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            this.lines = Arrays.asList(fileData);
            process();
    }

    private void process(){
        int pointer = 0;
        boolean tmpBool = true;
        String tmp1 = "";
        for (;pointer<lines.size() && tmpBool;pointer++){
            String tmp = lines.get(pointer);
            for (int i = 0; i < tmp.length(); i++){
                if(tmp.charAt(i) == 7){
                    legalInfo.institution = standardlize(tmp1.trim());
                    tmpBool=false;
                    break;
                } else if (tmp.charAt(i) == 11 || tmp.charAt(i) == '\n' || tmp.charAt(i) == 160){
                    tmp1 += " ";
                } else {
                    tmp1 += tmp.charAt(i);
                }
            }
        }
        for(;pointer<lines.size();pointer++){
            if (lines.get(pointer).contains("Số:")){
//                System.out.println("@@@ " + lines.get(pointer) + "@@@");
                String[] arr = lines.get(pointer).split("Số:");
                char k = 7;
                String split = "" + k;
//                System.out.println(arr[arr.length-1]);
                String[] tmp =arr[arr.length-1].split(split);
//                System.out.println(tmp.length);
                legalInfo.number = tmp[0].trim();
                pointer++;
                break;
            }
        }
        for (;pointer<lines.size();pointer++){
            if (lines.get(pointer).contains("ngày")){
                String[] arr = lines.get(pointer).split("ngày");
                legalInfo.standing = standardlize(arr[0]);
                char k = 7;
                String split = "" + k;
                arr = arr[1].split(split);
                String[] date = arr[0].split("\\s+");
                String dateCreated = "";
                for (String x : date){
//                    x = x.trim();
//                    System.out.println(isNumber(x.trim()));
                    String tmp = "" + (char)160;
                    x = x.split(tmp)[0];
//                    System.out.println("##" + x + "##");
                    if(isNumber(x.trim())) dateCreated = x + "-" + dateCreated;
                }
//                System.out.println(dateCreated);
                legalInfo.dateCreated = Date.valueOf(dateCreated.substring(0,dateCreated.length()-1));
                pointer++;
                break;
            }
        }
        for (;pointer<lines.size();pointer++){
            if(isSentenceString(lines.get(pointer))){
                legalInfo.type = lines.get(pointer).trim();
                pointer++;
                break;
            }
        }

        for (;pointer<lines.size();pointer++){
            if(isSentenceString(lines.get(pointer))){
                legalInfo.title = lines.get(pointer).trim();
                pointer++;
                break;
            }
        }

        for (;pointer<lines.size();pointer++){
            if (isSentenceString(lines.get(pointer))) {
                String tmp = lines.get(pointer).trim();
                if (!CharMatcher.JAVA_LOWER_CASE.matchesNoneOf(tmp)) {
                    legalInfo.title += " " + tmp;
                } else {
                    pointer++;
                    break;
                }
            }
        }

        findConfirmation(lines.size()-1);
        System.out.println(legalInfo.toString());
    }

    private void findConfirmation(int downPointer){
        for (;downPointer >= 0;downPointer--){
            if(containsIgnoreCase(lines.get(downPointer),"Đã Ký")){
                final String down = lines.get(downPointer);
                int startPosition = down.toLowerCase().indexOf("đã ký");
                int endPosition = startPosition + 5;
                if(startPosition> 0 &&down.charAt(startPosition-1) == '(') startPosition--;
                if(startPosition> 0 && down.charAt(startPosition-1) == '(') startPosition--;
                if(endPosition+1 < down.length() && down.charAt(endPosition+1) == ')') endPosition++;
                if(endPosition+1 < down.length() && down.charAt(endPosition+1) == ')') endPosition++;
                LinkedList<String> stringList = new LinkedList<>();
                String temp = "";
                boolean startFlag = false;
                boolean endFlag = false;
                for (int i = 0; i < startPosition; i++){
                    if(down.charAt(i) == 7 ){
                        startFlag = true;
                        temp = "";
                        stringList = new LinkedList<>();
                    } else if(down.charAt(i) == 11 || down.charAt(i) == '\n'){
                        if(isSentenceString(temp.trim())){
                            if(temp.length() > 70){
                                findConfirmation(downPointer-1);
                                return;
                            }
                            stringList.addLast(temp);
                            temp = "";
                        }
                    } else {
                        temp  += down.charAt(i);
                    }
                }
                for (int i = endPosition + 1; i < down.length(); i++){
                    if(down.charAt(i) == 7 ){
                        endFlag = true;
                        if(isSentenceString(temp.trim())){
                            if(temp.length() > 70){
                                findConfirmation(downPointer-1);
                                return;
                            }
                            stringList.addLast(temp);
                        }
                        break;
                    } else if(down.charAt(i) == 11 || down.charAt(i) == '\n'){
                        if(isSentenceString(temp.trim())){
                            if(temp.length() > 60){
                                findConfirmation(downPointer-1);
                                return;
                            }
                            stringList.addLast(temp);
                            temp = "";
                        }
                    } else {
                        temp  += down.charAt(i);
                    }
                }
                if(startFlag == false){
                    LinkedList<String> tmpList = new LinkedList<>();
                    for (int j = downPointer-1; j >= 0 && !startFlag; j--){
                        LinkedList<String> tmpList1 = new LinkedList<>();
                        String tmp = lines.get(j);
                        for (int k = 0; k < tmp.length(); k++){
                            if(tmp.charAt(k) == 7 ){
                                startFlag = true;
                                temp = "";
                                tmpList1 = new LinkedList<>();
                            } else if(tmp.charAt(k) == 11 || tmp.charAt(k) == '\n'){
                                temp = temp.trim();
                                if(isSentenceString(temp)){
                                    tmpList1.addLast(temp);
                                    temp = "";
                                }
                            } else {
                                temp  += tmp.charAt(k);
                            }
                        }
                        if(!tmpList1.isEmpty()) {
                            tmpList1.addAll(tmpList);
                            tmpList = tmpList1;
                        }
                    }
                    if(!tmpList.isEmpty()) {
                        tmpList.addAll(stringList);
                        stringList = tmpList;
                    }
                }
                if(endFlag == false){
                    temp = "";
                    for (int j = downPointer+1; j < lines.size() && !endFlag; j++){
                        String tmp = lines.get(j);
                        for (int k = 0; k < tmp.length(); k++){
                            if(tmp.charAt(k) == 7 ){
                                endFlag = true;
                                temp = temp.trim();
                                if(isSentenceString(temp)){
                                    stringList.addLast(temp);
                                }
                            } else if(tmp.charAt(k) == 11 || tmp.charAt(k) == '\n'){
                                temp = temp.trim();
                                if(isSentenceString(temp)){
                                    stringList.addLast(temp);
                                    temp = "";
                                }
                            } else {
                                temp  += tmp.charAt(k);
                            }
                        }
                    }
                }
                int size = stringList.size();
                if(size > 1) legalInfo.position = stringList.get(size-2);
                legalInfo.confirmation = stringList.getLast();
//                for (String x : stringList){
//                    System.out.println(x);
//                }
            }
        }
    }

    private boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }

    private boolean isSentenceString(String input){
        for (int i = 0; i < input.length();i++){
            if(input.charAt(i) >= 'A' && input.charAt(i) <= 'z') {
                return true;
            }
        }
        return false;
    }

    private String standardlize(String input){
        String temp = input.trim();
        temp = temp.replaceAll("\\s+"," ");
        String result = "";
        for(int i = 0; i < temp.length(); i++){
            if(temp.charAt(i) >= 'A' || temp.charAt(i) == ' ') result += temp.charAt(i);
        }
        return result;
    }

    private boolean isNumber(String input){
        if(input == null || input.length() == 0) return false;
        boolean result = true;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) < '0' || input.charAt(i) > '9') {
                return false;
            }
        }
        return result;
    }

    public LegalInfoModel getRawInfo() {
        return legalInfo;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public LegalInfoModel getInfo() {
//        if("".equals(legalInfo.number) ||"".equals(legalInfo.title)||"".equals(legalInfo.standing)||"".equals(legalInfo.confirmation)||"".equals(legalInfo.institution)||"".equals(legalInfo.position)||"".equals(legalInfo.type)||Date.valueOf("2100-01-01").equals(legalInfo.dateCreated)) {
//            return null;
//        }
        if("".equals(legalInfo.number)){
            return  null;
        }
        return legalInfo;
    }

    @Override
    public List<String> getLines() {
        return lines;
    }

    @Override
    public String getData() {
        return null;
    }
}
