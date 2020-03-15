package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * 
 *               给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *               示例 1:
 *
 *               输入: "abcabcbb"
 *               输出: 3
 *               解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *               示例 2:
 *
 *               输入: "bbbbb"
 *               输出: 1
 *               解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *               示例 3:
 *
 *               输入: "pwwkew"
 *               输出: 3
 *               解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *                 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *               来源：力扣（LeetCode）
 *               链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 *               著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: shenpeng
 * @Date: 2019-12-28
 */
public class LongestNoRepeatStr {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int curLength;
        int sLength = s.length();
        String[] strArray = new String[sLength];
        for (int i = 0; i < sLength; i++) {
            strArray[i] = s.substring(i, i + 1);
        }

        Set<String> elements;
        for (int i = 0; i < sLength; i++) {
            curLength = 0;
            elements = new HashSet<>();
            for (int j = 0; j < sLength - i; j++) {
                if (!elements.contains(strArray[i + j])) {
                    curLength++;
                    elements.add(strArray[i + j]);
                } else {
                    break;
                }
            }
            maxLength = Math.max(curLength, maxLength);
        }
        return maxLength;
    }
}
