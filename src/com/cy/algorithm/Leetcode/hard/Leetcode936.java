package com.cy.algorithm.Leetcode.hard;


import java.util.Stack;

/**
 * 题名 : 戳印序列
 * 题目 : 你想要用小写字母组成一个目标字符串 target。
 * 开始的时候，序列由 target.length 个 '?' 记号组成。而你有一个小写字母印章 stamp。
 * 在每个回合，你可以将印章放在序列上，并将序列中的每个字母替换为印章上的相应字母。
 * 你最多可以进行 10 * target.length  个回合。
 * 举个例子，如果初始序列为 "?????"，而你的印章 stamp 是 "abc"，那么在第一回合，你可以得到 "abc??"、"?abc?"、"??abc"。
 * （请注意，印章必须完全包含在序列的边界内才能盖下去。）
 * 如果可以印出序列，那么返回一个数组，该数组由每个回合中被印下的最左边字母的索引组成。如果不能印出序列，就返回一个空数组。
 * 例如，如果序列是 "ababc"，印章是 "abc"，那么我们就可以返回与操作 "?????" -> "abc??" -> "ababc" 相对应的答案 [0, 2]；
 * 另外，如果可以印出序列，那么需要保证可以在 10 * target.length 个回合内完成。任何超过此数字的答案将不被接受。
 *
 * 思路 : 倒着匹配,'?'通配
 * 执行时间 : 53ms
 *  @author clay
 * @date 18-11-13 20:14
 */
public class Leetcode936 {

    public static void main(String[] args) {
        Leetcode936 l = new Leetcode936();
        int []result = l.movesToStamp("e", "eeeeeeeeee");
        for(int i : result){
            System.out.println(i);
        }
    }

    public int maxMatch(char []stampArray, char []targetArray){
        int len = stampArray.length;
        int maxLen = 0;
        int index = -1;
        jmp:
        for(int i = targetArray.length - 1; i >= len-1; i--){
            if(targetArray[i] == stampArray[len-1] || targetArray[i] == '?'){
                int curLen = 0;
                for(int j = 0; j < len; j++){
                    if(targetArray[i-j] == stampArray[len-j-1]){
                        curLen++;
                    }else if(targetArray[i-j] == '?'){

                    }else {
                        continue jmp;
                    }
                }
                if(curLen > maxLen){
                    maxLen = curLen;
                    index = i-len+1;
                }
            }
        }
        return index;
    }

    public int[] movesToStamp(String stamp, String target) {
        Stack<Integer> result = new Stack<>();
        char []stampArray = stamp.toCharArray();
        char []targetArray = target.toCharArray();
        int times = 0;
        int index = maxMatch(stampArray,targetArray);
        while(index != -1){
            result.push(index);
            for(int i = 0; i < stamp.length(); i++){
                targetArray[i+index] = '?';
            }
            if(++times > 10*targetArray.length){
                return new int[]{};
            }
            index = maxMatch(stampArray,targetArray);
        }
        for(int i = 0; i < targetArray.length; i++){
            if(targetArray[i] != '?'){
                return new int[]{};
            }
        }
        int []a = new int[result.size()];
        for(int i = 0; i < a.length; i++){
            a[i] = result.pop();
        }
        return a;
    }

}
