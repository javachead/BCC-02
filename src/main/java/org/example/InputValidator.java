package org.example;

import java.util.Scanner;

public class InputValidator {

    // 学生名を取得するメソッド
    public static String getValidStudentName(Scanner scanner) {
        System.out.print("学生の名前を入力してください: ");
        return scanner.next();
    }

    // 有効な点数を取得するメソッド
    public static int getValidScore(Scanner scanner, String name) {
        System.out.print(name + "の点数を入力してください: ");
        return scanner.nextInt();
    }
}
