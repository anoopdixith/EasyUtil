package com.kennedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.EasyUtil.Ru.meanwhile;

/**
 * Created by adixith on 1/16/17.
 */
public class TestRu {

    // Works with classes without default constructors.
    public TestRu(String string) {}

    public static void main(String[] args) throws InterruptedException {
        meanwhile("com.kennedy.TestRu.myPrintln");

        List<Class> typeList1 = new ArrayList<>();
        typeList1.addAll(Arrays.asList(Boolean.class, Integer.class));
        List<Object> valueList1 = new ArrayList<>();
        valueList1.add(true);
        valueList1.add(8);
        Thread t1 = meanwhile("com.kennedy.TestRu.myPrintln", typeList1, valueList1);
        System.out.println("t1 name is " + t1.getName());

        List<Class> typeList2 = new ArrayList<>();
        typeList2.addAll(Arrays.asList(Integer.class, Integer.class));
        List<Object> valueList2 = new ArrayList<>();
        valueList2.add(8);
        valueList2.add(17);
        Thread t2 = meanwhile("com.kennedy.TestRu.justAdd", typeList2, valueList2);
        System.out.println("t2 name is " + t2.getName());

        List<Class> typeList4 = new ArrayList<>();
        typeList4.addAll(Arrays.asList(int.class, int.class));
        List<Object> valueList4 = new ArrayList<>();
        valueList4.add(81);
        valueList4.add(17);
        Thread t3 = meanwhile("com.kennedy.TestRu.justAdd", typeList4, valueList4);
        System.out.println("t3 name is " + t3.getName());

        List<Class> typeList3 = new ArrayList<>();
        typeList3.addAll(Arrays.asList(Integer.class, Integer.class));
        List<Object> valueList3 = new ArrayList<>();
        List<Class> constructorType = new ArrayList<>();
        constructorType.add(String.class);
        List<Object> constructorValue = new ArrayList<>();
        constructorValue.add("constructor input");
        valueList3.add(8);
        valueList3.add(17);
        Thread t4 = meanwhile("com.kennedy.TestRu.justAddNonStatic", typeList3, valueList3, constructorType, constructorValue);
        System.out.println("t4 name is " + t4.getName());
    }

    // No parameter static method.
    public static void myPrintln() {
        System.out.println("New Line no params");
        //for(int i=0; i < 100; i++)
            //System.out.println("Method A");
    }

    // Static method with heterogeneous parameters.
    public static void myPrintln(Boolean newLine, Integer num) {
        if(newLine)
            System.out.println("New line");
        else
            System.out.print("No New Line");
        System.out.println("Number is " + num);
        //for(int i=0; i < 100; i++)
            //System.out.println("Method B");
    }

    // Static method with parameters and return values.
    public static int justAdd(Integer a, Integer b) {
        System.out.println("In justAdd Classes a is " + a + " b is " + b);
        //for(int i=0; i < 100; i++)
            //System.out.println("Method C");
        return a+b;
    }

    // Static method with primitive parameters.
    public static int justAdd(int a, int b) {
        System.out.println("In justAdd primitives a is " + a + " b is " + b);
        //for(int i=0; i < 100; i++)
            //System.out.println("Method D");
        return a+b;
    }

    // Instance method with parameters and return values.
    public int justAddNonStatic(Integer a, Integer b) {
        System.out.println("In justAdd instance a is " + a + " b is " + b);
        //for(int i=0; i < 100; i++)
            //System.out.println("Method E");
        return a+b;
    }

    public void playMediaPlayer() {

    }

}
