package com.test.jie;

import java.math.BigDecimal;

public class HelloWorld {
    public static void main(String[] args) {
        HelloWorld world = new HelloWorld();
        System.out.println(world.sum(1,2,3,4,5));
        System.out.println(world.sum(5,null,-5));
        System.out.println(world.sum(1.0,false,1,true,1,'A',1,'B',1,'C',1,'D',1,'E',1,'F',1,'G',1));
        System.out.println(world.sum(0.1,0.2));
    }

    public String sum(Object ... objs) {
        double sum = 0.0;
        for (Object o:objs){

            if (o instanceof Double  ){
                sum += (double)o;
            }
            if (o instanceof Integer){
                sum+=(Integer)o;
            }
        }
        if (!String.valueOf(sum).contains(".")){
            return Integer.valueOf(String.valueOf(sum)).toString();
        }else {
            return new BigDecimal(sum).setScale(2,BigDecimal.ROUND_DOWN).toString();
        }
    }

}
