package com.test.jie.leetCode.tool;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ServiceLoader;


public class StringEdit {
    public static void main(String[] args) {
        StringEdit stringEdit = new StringEdit();
        stringEdit.toCamelCase("partition-array-for-maximum-sum", true, "mon4", "Y2023");
        stringEdit.convertToCurlyBraces("[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]");
    }

    public void toCamelCase(String str, boolean need, String mon, String year) {
        String[] ss = str.split("-");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            String s = ss[i];
            sb.append(s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1));
        }
        System.out.println(sb);

        if (!need) return;

//        String mon = "mon10";
        try {
            FileInputStream file = new FileInputStream("resources/1.txt");
            byte[] bytes = new byte[1024];
            int count = 0;
            StringBuilder sb2 = new StringBuilder();
            while ((count = file.read(bytes)) != -1) {
                sb2.append(new String(bytes, 0, count));
            }
            file.close();
            String result = sb2.toString().replaceAll("fileName", sb.toString()).replaceAll("mon", mon)
                    .replaceAll("year",year);

            Date date = new Date();
            int idx = result.indexOf("@d{");
            int lastIdx = idx;
            while (result.charAt(lastIdx) != '}') {
                lastIdx++;
            }
            String subDate = result.substring(idx + 3, lastIdx);
            SimpleDateFormat sdf = new SimpleDateFormat(subDate);

            result = result.replaceAll(result.substring(idx, lastIdx + 1).replace("{", "\\{").replace("}", "\\}"), sdf.format(date));
            File f = new File("src/com/test/jie/leetCode/Daily/" + year + "/" + mon);
            if (!f.exists()) {
                f.mkdir();
            }
            FileOutputStream fos = new FileOutputStream("src/com/test/jie/leetCode/Daily/" + year + "/" + mon + "/" + sb.toString() + ".java");
            fos.write(result.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void convertToCurlyBraces(String str) {
        String s = str.replaceAll("\\[", "{").replaceAll("]", "}");
        System.out.println(s);

    }
}
