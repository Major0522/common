package com.seagen.ecc.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author 唐经纬
 *
 */
public class RandomUtils {

	
    /** 
     * 随机指定范围内N个不重复的数 
     * 在初始化的无重复待选数组中随机产生一个数放入结果中， 
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 
     * 然后从len-2里随机产生下一个随机数，如此类推 
     * @param max  指定范围最大值 
     * @param min  指定范围最小值 
     * @param n  随机数个数 
     * @return int[] 随机数结果集 
     */  
    public static Integer[] randomArray(int min,int max,int n){  
        int len = max-min+1;  
          
        if(max < min || n > len){  
            return null;  
        }  
          
        //初始化给定范围的待选数组  
        Integer[] source = new Integer[len];  
           for (int i = min; i < min+len; i++){  
            source[i-min] = i;  
           }  
             
           Integer[] result = new Integer[n];  
           Random rd = new Random();  
           int index = 0;  
           for (int i = 0; i < result.length; i++) {  
            //待选数组0到(len-2)随机一个下标  
               index = Math.abs(rd.nextInt() % len--);  
               //将随机到的数放入结果集  
               result[i] = source[index];  
               //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换  
               source[index] = source[len];  
           }  
           return result;  
    }  
    /** 
     * 随机指定范围内N个不重复的数 
     * 在初始化的无重复待选数组中随机产生一个数放入结果中， 
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 
     * 然后从len-2里随机产生下一个随机数，如此类推 
     * @param max  指定范围最大值 
     * @param min  指定范围最小值 
     * @param n  随机数个数 
     * @return int[] 随机数结果集 
     */  
    public static  List<Integer> randomList(int min,int max,int n){  
        int len = max-min+1;  
        List<Integer> result = new ArrayList<Integer>();  
        if(max < min || n > len){  
            return result;  
        }  
          
        //初始化给定范围的待选数组  
        Integer[] source = new Integer[len];  
           for (int i = min; i < min+len; i++){  
            source[i-min] = i;  
           }  
             
        
           Random rd = new Random();  
           int index = 0;  
           for (int i = 0; i < n; i++) {  
            //待选数组0到(len-2)随机一个下标  
               index = Math.abs(rd.nextInt() % len--);  
               //将随机到的数放入结果集  
               result.add(source[index]);  
               //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换  
               source[index] = source[len];  
           }  
           return result;  
    }  
    /**
     * 
    *
    * @Title: randomList 
    * @Description: 生成随机数，例如0-100生成22个不一样的数
    * @return List<Integer> 
    * @param total 范围0到total
    * @param cut 生成多少个数
    * @return
     */
    public static List<Integer> randomList(int total, int cut) {
    	 List<Integer>  intArrays =  randomList(0,total-1,cut);
		return intArrays;
	}
	public static Integer[] randomArray(int total, int cut) {
		Integer[] intArrays =  randomArray(0,total-1,cut);
		return intArrays;
	}
	public static void main(String[] args) {
//		int mustTime = 3;
//		int shakeTime = 3;
//		List<Integer> list = RandomUtils.randomList(mustTime, shakeTime);
//		//如果摇奖次数等于必须摇奖次数，则一定中奖
//		System.out.println(list.contains(shakeTime-1));
		for (int i = 0 ;i < 100;i++) {
			System.out.println(	Math.pow(27, 100));
		}
		
	
	}
}
