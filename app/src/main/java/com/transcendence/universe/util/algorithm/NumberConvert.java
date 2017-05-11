package com.transcendence.universe.util.algorithm;

import java.math.BigInteger;
import java.util.Stack;

/**
 * Created by liuf on 16/5/13.
 */
public class NumberConvert {
    public static final char[] array = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z' };

    public static String _10_to_any(long number, int baseout) {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(array[new Long((rest - (rest / baseout) * baseout))
                    .intValue()]);
            rest = rest / baseout;
        }
        for (; !stack.isEmpty();) {
            result.append(stack.pop());
        }
        return result.toString();
    }
    //十进制转换为任意进制
    public static String toAnyConversion(BigInteger Bigtemp, BigInteger base)
    {
        String ans = "";
        while(Bigtemp.compareTo(BigInteger.ZERO) != 0)
        {
            BigInteger temp = Bigtemp.mod(base);
            Bigtemp = Bigtemp.divide(base);
            char ch = changToNum(temp);
            ans = ch + ans;
        }
        return ans;
    }
    //数字转换为字符
    static char changToNum(BigInteger temp)
    {
        int n = temp.intValue();

        if(n >= 10 && n <= 35)
            return (char) (n - 10 + 'A');

        else if(n >= 36 && n <= 61)
            return (char)(n - 36 + 'a');

        else
            return (char)(n + '0');
    }
    public static long _any_to_10(String sixty_str, int basein) {
        int multiple = 1;
        long result = 0;
        Character c;
        for (int i = 0; i < sixty_str.length(); i++) {
            c = sixty_str.charAt(sixty_str.length() - i - 1);
            result += _any_value(c) * multiple;
            multiple = multiple * basein;
        }
        return result;
    }

    private static int _any_value(Character c) {
        for (int i = 0; i < array.length; i++) {
            if (c == array[i]) {
                return i;
            }
        }
        return -1;
    }

}
