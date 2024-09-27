import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ": " + score + "点";
    }
}

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

            // 6を入力したら即終了
            if (choice.equals("6")) {
                System.out.println("プログラムを終了します。");
                break;
            }

            switch (choice) {
                case "1":
                    String name = "";

                    while (true) {
                        System.out.print("学生の名前を入力してください: ");
                        name = scanner.next();

                        // 「6」を入力したら即終了
                        if (name.equals("6")) {
                            System.out.println("プログラムを終了します。");
                            running = false;
                            break;
                        }

                        // 名前がひらがな、漢字、カタカナのみかチェック
                        if (name.matches("[\\p{IsHiragana}\\p{IsHan}\\p{IsKatakana}]+")) {
                            break;
                        } else {
                            System.out.println("名前にはひらがな、漢字、カタカナのみを使用してください。");
                        }
                    }

                    if (!running) break;

                    int score = -1;
                    // 点数入力ループ
                    while (true) {
                        System.out.print(name + "の点数を入力してください: ");
                        try {
                            String scoreInput = scanner.next();

                            // 「6」を入力したら即終了
                            if (scoreInput.equals("6")) {
                                System.out.println("プログラムを終了します。");
                                running = false;
                                break;
                            }

                            score = Integer.parseInt(scoreInput);

                            if (score >= 0 && score <= 100) {
                                break;
                            } else {
                                System.out.println("点数は0から100の間で入力してください。");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("無効な点数です。整数を入力してください。");
                        }
                    }

                    if (!running) break;

                    students.add(new Student(name, score));
                    System.out.println(name + "を追加しました。");
                    break;

                case "2":
                    boolean studentFound = false;

                    while (!studentFound) {
                        System.out.print("削除する学生の名前をフルネームで入力してください: ");
                        String nameToRemove = scanner.next();

                        // 「6」を入力したら即終了
                        if (nameToRemove.equals("6")) {
                            System.out.println("プログラムを終了します。");
                            running = false;
                            break;
                        }

                        if (students.removeIf(student -> student.getName().equals(nameToRemove))) {
                            System.out.println(nameToRemove + "を削除しました。");
                            studentFound = true;
                        } else {
                            System.out.println("その名前の学生は存在しません。再入力してください。");
                        }
                    }

                    if (!running) break;
                    break;

                case "3":
                    // 学生の名前入力ループ
                    boolean found = false;
                    Student studentToUpdate = null;

                    while (!found) {
                        System.out.print("点数を更新する学生の名前を入力してください: ");
                        String nameToUpdate = scanner.next();

                        // 6を入力したら即終了
                        if (nameToUpdate.equals("6")) {
                            System.out.println("プログラムを終了します。");
                            running = false;
                            break;
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

                    if (!running) break;

                    // 点数更新ループ
                    while (true) {
                        System.out.print("新しい点数を入力してください: ");
                        try {
                            String scoreInput = scanner.next();

                            // 「6」を入力したら即終了
                            if (scoreInput.equals("6")) {
                                System.out.println("プログラムを終了します。");
                                running = false;
                                break;
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
                    if (!running) break;
                    break;

                case "4":
                    if (students.isEmpty()) {
                        System.out.println("学生が登録されていません。");
                    } else {
                        double total = 0;
                        for (Student student : students) {
                            total += student.getScore();
                        }
                        double average = total / students.size();
                        System.out.println("平均点: " + average + "点");
                    }
                    break;

                case "5":
                    if (students.isEmpty()) {
                        System.out.println("学生が登録されていません。");
                    } else {
                        System.out.println("学生一覧:");
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    }
                    break;

                default:
                    System.out.println("無効な選択です。もう一度選んでください。");
                    break;
            }
        }

        scanner.close();
    }
}
