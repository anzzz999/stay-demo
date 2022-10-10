package test;


import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhanmingwei
 */
public class InputText2 {
    private static List<String> inputList = new LinkedList<>();

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            s = handle(s);
            if (StringUtils.isNotBlank(s)){
                System.out.println(s);
            }
        }
    }

    private static String handle(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        if (":list".equals(s)) {
            for (String value : inputList) {
                stringBuilder.append(value);
            }
        } else if (":undo".equals(s)) {
            int i = inputList.size() - 1;
            i = i >= 0 ? inputList.size() - 1 : 0;
            inputList.remove(i);
            for (String value : inputList) {
                stringBuilder.append(value);
            }
        } else {
            inputList.add(s);
        }
        return stringBuilder.toString();
    }
}
