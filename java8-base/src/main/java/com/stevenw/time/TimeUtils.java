package com.stevenw.time;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stevenw
 * @date 2019/7/26
 */
public class TimeUtils {

    //利用递归的思想，进行十进制转二十六进制
    public static void SysConvert(int n,List<Integer> ysList){
        int shang = n / 26;
        int yushu = n % 26;
        ysList.add(yushu);
        System.err.println(yushu);
        if(shang > 0){
            n = shang;
            SysConvert(n,ysList);
        }
    }
    //根据二十六进制计算所在列
    public static void low(int n){
        StringBuffer sb = new StringBuffer();
        List<Integer> ysList = new ArrayList<>();
        String[] word = { "Z","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
        String[] word1 = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y","Z"};
        SysConvert(n,ysList);
        boolean b = true;
        //Z - 25
        //ZA - 1,0,0 ~ ZZ - 1,0,25
        //ZZA - 1,0,0,0 ~ ZZZ - 1,0,0,25

        //701  25
        //26   0
        //     1
        if(ysList.size() > 2){
            for(int i = 1; i < ysList.size()-1; i++){
                System.err.println("re" + ysList.get(i));
                if(ysList.get(i) != 0){
                    b = false;
                }
            }
        }
        else{
            b = false;
        }
        System.out.println(b);
        if(b && ysList.get(ysList.size()-1) == 1){
            ysList.remove(ysList.size()-1);
        }
        for(int i = ysList.size()-1; i >= 0; i--){

            if(i == 0){
                sb.append(word1[ysList.get(i)]);
            }
            else{
                //A - 0
                //AA - 1,0
                //AAA - 1,1,0
                //AAAA - 1,0,1,0
                if(i > 1 && ysList.get(i) == 0 && ysList.get(1) == 1){
                    sb.append(word1[ysList.get(i)]);
                }
                else{
                    sb.append(word[ysList.get(i)]);
                }
            }
        }
        System.out.println("索引:"+n+"   所得列："+sb.toString());
    }


    public static String GetExcelColumnName(int columnNumber)
    {
        int dividend = columnNumber;
        String columnName = "";
        int modulo;

        while (dividend > 0)
        {
            modulo = (dividend - 1) % 26;
            System.out.println("modulo："+modulo);
          columnName = (char)(97 + modulo) + columnName;
            dividend = (int)((dividend - modulo) / 26);
            System.out.println("dividend："+dividend);
        }

        return columnName;
    }

    public static void main(String[] args) {
//         low(1351);
        System.err.println(GetExcelColumnName(702));

        //25,1,0,0,1   0 0 1
        //1,0,0,1,25
//        17575   675
    }
}
