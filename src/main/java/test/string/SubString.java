package test.string;

import java.util.ArrayList;
import java.util.List;

/**
 * substring
 * ？
 * 
 * @date 2018-08-15
 * @author shenpeng
 */
public class SubString {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 1000; i++) {
            HugeStr h = new HugeStr();//可能会溢出

            //ImproveHugeStr h = new ImproveHugeStr();//不会溢出

            list.add(h.getString(1, 5));
        }
    }

    static class HugeStr {

        private String string = new String(new char[100000]);//一个很长的string

        public String getString(int begin, int end) {
            return string.substring(begin, end);
        }
    }

    static class ImproveHugeStr {

        private String string = new String(new char[100000]);

        public String getString(int begin, int end) {
            return new String(string.substring(begin, end));
        }
    }
}
