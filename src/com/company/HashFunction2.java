package com.company;

import java.util.Arrays;

public class HashFunction2 {

    String[] theArray;
    int arraySize;
    int itemsInArray = 0;

    public static void main(String[] args) {

        HashFunction2 theFunc = new HashFunction2(30);
        //Mod хеш функциясы
        //Бұл соқтығысуды көрсететін дәл 30 элементтен тұрады

        String[] elementsToAdd2 = { "100", "510", "170", "214", "268", "398",
                "235", "802", "900", "723", "699", "1", "16", "999", "890",
                "725", "998", "978", "988", "990", "989", "984", "320", "321",
                "400", "415", "450", "50", "660", "624" };

        theFunc.hashFunction2(elementsToAdd2, theFunc.theArray);
        theFunc.findKey("660"); //Хэш кестесінде 660 мәнін табу
        theFunc.displayTheStack();
    }


    //Енді біз мәндерді 0-ден 999-ға дейін сақтауымыз керек делік
    //бірақ біз 15 мәннен артық болуды жоспарламаймыз. Біз не істей аламыз?
    //Бұл сандарды 30 элемент массивіне қоюдың бір жолы - mod функциясын қолдану
    public void hashFunction2(String[] stringsForArray, String[] theArray) {

        for (int n = 0; n < stringsForArray.length; n++) {
            String newElementVal = stringsForArray[n];
            //Модульді алу арқылы мәнді сақтау индексін жасау
            int arrayIndex = Integer.parseInt(newElementVal) % 29;
            System.out.println( "Modulus Index= " + arrayIndex + " for value "
                    + newElementVal);
            // бос орын тапқанша массив арқылы циклдік түрде өту
            while (theArray[arrayIndex] != "-1") {
                ++arrayIndex;
                System.out.println("Collision try " + arrayIndex + " instead ");
                //Егер массивтің соңына жетсек, 0 индексіне ораламыз
                arrayIndex %= arraySize;
            }
            theArray[arrayIndex] = newElementVal;
        }
    }
    // Хэш кестесінде сақталған мәнді қайтарады

    public String findKey(String key) {
        //хеш кілт табу
        int arrayIndexHash = Integer.parseInt(key) % 29;
        while (theArray[arrayIndexHash] != "-1") {
            if (theArray[arrayIndexHash] == key) {
                System.out.println(key + " was found in index "
                        + arrayIndexHash);
                return theArray[arrayIndexHash];
            }
            //келесі индексті қарау
            ++arrayIndexHash;
            //Егермассивтің соңына жетсек, 0 индексіне оралу
            arrayIndexHash %= arraySize;
        }
        //Кілтті табу мүмкін емес
        return null;
    }

    HashFunction2(int size) {
        arraySize = size;
        theArray = new String[size];
        Arrays.fill(theArray, "-1");
    }

    public void displayTheStack() {
        int increment = 0;
        for (int m = 0; m < 3; m++) {
            increment += 10;

            for (int n = 0; n < 71; n++)
                System.out.print("-");
            System.out.println();

            for (int n = increment - 10; n < increment; n++) {
                System.out.format("| %3s " + " ", n);
            }
            System.out.println("|");

            for (int n = 0; n < 71; n++)
                System.out.print("-");
            System.out.println();

            for (int n = increment - 10; n < increment; n++) {
                if (theArray[n].equals("-1"))
                    System.out.print("|      ");
                else
                    System.out
                            .print(String.format("| %3s " + " ", theArray[n]));
            }
            System.out.println("|");

            for (int n = 0; n < 71; n++)
                System.out.print("-");
            System.out.println();
        }
    }
}