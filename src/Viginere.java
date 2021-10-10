import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*=============================================================================|
   Assignment:  pa01 - Encrypting a plaintext file using the Vigenere cipher
|
|     Author:  Bryson Paul
|     Language: Java
|
|   To Compile:  javac pa01.java
|   To Execute:  java -> java pa01 kX.txt pX.txt
|                      where kX.txt is the keytext file
|                      and pX.txt is plaintext file
|
|   Note:  All input files are simple 8 bit ASCII input|
|   Class:  CIS3360 - Security in Computing - Fall 2021
|   Instructor:  McAlpin
|   Due Date:  10/24/2021

|+=============================================================================*/
public class Viginere {
    public static void main(String[] args) throws FileNotFoundException {
        File ptFile = new File("src/input.txt");
        File keyFile = new File("src/KEY");
        char[] plainText = storePlainText(ptFile);//pt working properly
        char[] key = storeKey(keyFile);
        char[] cipher = viginere(plainText,key);
        printCipher(cipher);
    }
    public static void printCipher(char[] ct){
        for(int x=0;x<ct.length;x++){
            if(x % 80 == 0){
                System.out.print("\n");
            }
            System.out.print(ct[x]);
        }
    }
    public static char[] viginere(char[] plainText, char[] key){//needs work
        char[] cipherText = new char[plainText.length];
        for(int x=0;x<plainText.length;x++){
            int keyIndex = x % (key.length);
            int kChar = key[keyIndex] - 'a';
            int sum = (plainText[x]-'a') + kChar;
            if(sum > 25){
                sum = ( sum % 26 );
            }
            cipherText[x]= (char) ('a'+sum);
        }
        return cipherText;
    }
    public static char[] storePlainText(File f) throws FileNotFoundException {
        Scanner plainTextScanner = new Scanner(f);
        String pt = " ";
        while(plainTextScanner.hasNext()){
            pt+=plainTextScanner.next();
        }
        return parsePlainText(pt.toCharArray());
    }
    public static char[] parsePlainText(char[] pt){
        for(int x=0 ; x<pt.length; x++){
            if(Character.isAlphabetic(pt[x])){
                if(Character.isUpperCase(pt[x])){
                    Character.toLowerCase(pt[x]);
                }
                else continue;
            }
            if(!Character.isAlphabetic(pt[x])){//if not alphabetic, we disregard by deleting it
                pt[x]=' ';
            }
        }
        pt=removeSpaces(pt,512);
        padWithX(pt);
        return pt;
    }

    public static char[] storeKey(File f) throws FileNotFoundException {
        Scanner plainTextScanner = new Scanner(f);
        String key = " ";
        while(plainTextScanner.hasNext()){
            key+=plainTextScanner.next();
        }
        return parseKey(key.toCharArray());
    }

    public static char[] parseKey(char[] key){
        for(int x=0 ; x<key.length; x++){
            if(Character.isAlphabetic(key[x])){
                if(Character.isUpperCase(key[x])){
                    Character.toLowerCase(key[x]);
                }
                else continue;
            }
            if(!Character.isAlphabetic(key[x])){//if not alphabetic, we disregard by deleting it
                key[x]=' ';
            }
        }
            key = removeSpaces(key,key.length);
            return trimKey(key);
    }
    public static char[] trimKey(char[] c){
        String s ="";
        for(int x=0;x<c.length;x++){
            if(c[x]==Character.MIN_VALUE){
                return s.toCharArray();
            }
            else s+=c[x];
        }
        return s.toCharArray();
    }

    public static char[] removeSpaces(char[] c, int size){
        char[] noSpace= new char[size];
        int noSpaceIndex=0;
        for(int x=0;x<c.length;x++){
            if(c[x]!=' '){
                noSpace[noSpaceIndex]=c[x];
                noSpaceIndex++;
            }
        }
        return noSpace;
    }

    public static void padWithX(char[] c){
        for(int x=0;x<c.length;x++){
            if(c[x]==Character.MIN_VALUE){
                c[x]='x';
            }
        }
    }
    /*=============================================================================
    |     I Bryson Paul (br370258) affirm that this program is
    | entirely my own work and that I have neither developed my code together with
    | any another person, nor copied any code from any other person, nor permitted
    | my code to be copied  or otherwise used by any other person, nor have I
    | copied, modified, or otherwise used programs created by others. I acknowledge
    | that any violation of the above terms will be treated as academic dishonesty.
    +=============================================================================*/
}
