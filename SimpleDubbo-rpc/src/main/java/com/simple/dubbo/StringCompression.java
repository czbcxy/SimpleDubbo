package com.simple.dubbo;

import java.util.Arrays;

/**
 * @author chengzhengzheng
 * @date 2019-05-13
 * <p>
 * <p>
 * leetcode 443
 */
public class StringCompression {
    public static void main(String[] args) {
        char[] values = {'a', 'a', 'a', 'a', 'b'};
        int compress = compress(values);
        System.out.println(compress);
        System.out.println(Arrays.toString(values));
    }

    public static int compress(char[] chars) {
        if (null == chars || chars.length == 1) {
            return 1;
        }

        int currentIndex = 0;
        int left = 0;
        for (int j = 0; j < chars.length; j++) {
            if (chars[j] != chars[left]) {
                int count = j - left;
                if (count != 1) {
                    chars[currentIndex++] = chars[left];
                    char[] tempArray = String.valueOf(count).toCharArray();
                    for (int i = 0; i < tempArray.length; i++) {
                        chars[currentIndex++] = tempArray[i];
                    }
                } else {
                    chars[currentIndex++] = chars[left];
                }
                left = j;
            }
            if (j == chars.length - 1 && chars[j] == chars[left]) {
                int count = j - left + 1;
                if (count != 1) {
                    chars[currentIndex++] = chars[left];
                    char[] tempArray = String.valueOf(count).toCharArray();
                    for (int i = 0; i < tempArray.length; i++) {
                        chars[currentIndex++] = tempArray[i];
                    }
                } else {
                    chars[currentIndex++] = chars[left];
                }
                left = j;

            }

        }
        return currentIndex;
    }
}
