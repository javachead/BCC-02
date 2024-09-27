package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n1. 学生を追加");
            System.out.println("2. 学生を削除");
            System.out.println("3. 点数を更新");
            System.out.println("4. 平均点を計算");
            System.out.println("5. 全学生の情報を表示");
            System.out.println("6. 終了");

            System.out.print("\n選択してください: ");
            String choice = scanner.next();

            switch (choice) {
                case "1":
                    // 学生の追加
                    String name = getValidStudentName(scanner);
                    if (name != null) { // プログラムが終了していないか確認
                        int score = getValidScore(scanner, name);
                        if (score != -1) { // スコアが正常に入力されたか確認
                            students.add(new Student(name, score));
                            System.out.println(name + "を追加しました。");
                        }
                    }
                    break;

                case "2":
                    // 学生の削除
                    removeStudent(scanner, students);
                    break;

                case "3":
                    // 点数を更新
                    updateStudentScore(scanner, students);
                    break;

                case "4":
                    calculateAverageScore(students);
                    break;

                case "5":
                    displayAllStudents(students);
                    break;

                case "6":
                    System.out.println("プログラムを終了します。");
                    running = false; // ここでループを終了
                    break;

                default:
                    System.out.println("無効な選択です。もう一度選んでください。");
                    break;
            }
        }

        scanner.close();
    }

    private static String getValidStudentName(Scanner scanner) {
        String name = "";

        while (true) {
            System.out.print("学生の名前を入力してください: ");
            name = scanner.next();

            // 「6」を入力したら即終了
            if (name.equals("6")) {
                System.out.println("プログラムを終了します。");
                return null; // 終了を示す
            }

            // 名前がひらがな、漢字、カタカナのみかチェック
            if (name.matches("[\\p{IsHiragana}\\p{IsHan}\\p{IsKatakana}]+")) {
                return name; // 有効な名前を返す
            } else {
                System.out.println("名前にはひらがな、漢字、カタカナのみを使用してください。");
            }
        }
    }

    private static int getValidScore(Scanner scanner, String name) {
        int score = -1; // 点数がまだ設定されていないことを示すために-1で初期化

        while (true) {
            System.out.print(name + "の点数を入力してください: ");
            try {
                String scoreInput = scanner.next();

                // 「6」を入力したら即終了
                if (scoreInput.equals("6")) {
                    System.out.println("プログラムを終了します。");
                    return -1; // 終了を示す
                }

                score = Integer.parseInt(scoreInput);

                if (score >= 0 && score <= 100) {
                    return score; // 有効な点数を返す
                } else {
                    System.out.println("点数は0から100の間で入力してください。");
                }
            } catch (NumberFormatException e) {
                System.out.println("無効な点数です。整数を入力してください。");
            }
        }
    }

    private static void removeStudent(Scanner scanner, List<Student> students) {
        boolean studentFound = false;

        while (!studentFound) {
            System.out.print("削除する学生の名前をフルネームで入力してください: ");
            String nameToRemove = scanner.next();

            // 「6」を入力したら即終了
            if (nameToRemove.equals("6")) {
                System.out.println("プログラムを終了します。");
                return; // 終了を示す
            }

            if (students.removeIf(student -> student.getName().equals(nameToRemove))) {
                System.out.println(nameToRemove + "を削除しました。");
                studentFound = true;
            } else {
                System.out.println("その名前の学生は存在しません。再入力してください。");
            }
        }
    }

    private static void updateStudentScore(Scanner scanner, List<Student> students) {
        // 学生の名前入力ループ
        boolean found = false;
        Student studentToUpdate = null;

        while (!found) {
            System.out.print("点数を更新する学生の名前を入力してください: ");
            String nameToUpdate = scanner.next();

            // 「6」を入力したら即終了
            if (nameToUpdate.equals("6")) {
                System.out.println("プログラムを終了します。");
                return; // 終了を示す
            }

            for (Student student : students) {
                if (student.getName().equals(nameToUpdate)) {
                    studentToUpdate = student;
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("その名前の学生は存在しません。再入力してください。");
            }
        }

        // 点数更新ループ
        while (true) {
            System.out.print("新しい点数を入力してください: ");
            try {
                String scoreInput = scanner.next();

                // 「6」を入力したら即終了
                if (scoreInput.equals("6")) {
                    System.out.println("プログラムを終了します。");
                    return; // 終了を示す
                }

                int newScore = Integer.parseInt(scoreInput);

                if (newScore >= 0 && newScore <= 100) {
                    studentToUpdate.setScore(newScore);
                    System.out.println(studentToUpdate.getName() + "の点数を更新しました。");
                    break;
                } else {
                    System.out.println("点数は0から100の間で入力してください。");
                }
            } catch (NumberFormatException e) {
                System.out.println("無効な点数です。整数を入力してください。");
            }
        }
    }

    private static void calculateAverageScore(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("学生が登録されていません。");
            return;
        }

        int totalScore = 0;
        for (Student student : students) {
            totalScore += student.getScore();
        }

        double average = (double) totalScore / students.size();
        System.out.println("平均点: " + average);
    }

    private static void displayAllStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("学生が登録されていません。");
            return;
        }

        System.out.println("全学生の情報:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
