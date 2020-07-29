package io.yandex.reviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/*
Чип и Дейл устали от публичности. Чтобы как-то обезопасить свои персональные данные, они решили шифровать sms - сообщения.
Числа они решили разворачивать. Помогите им написать программу для кодирования чисел.

На вход подается целое число n, по модулю не превосходящее 10000.

На выходе должно быть число, развернутое в обратном порядке.
 */
public class EncodeSms {

    public static void main(final String[] args) throws IOException {
        writeOutput(reverse(readInput()));
    }

    private static List<String> readInput() throws IOException {
        return Files.readAllLines(Paths.get("./input.txt"));
    }

    private static void writeOutput(String output) throws IOException {
        Files.write(Paths.get("./output.txt"), output.getBytes());
    }

    private static String reverse(List<String> lines) {
        if (lines.size() <= 0) {
            return "";
        }
        String origin = lines.get(0);
        boolean negative = origin.startsWith("-");
        String unsigned = origin.substring(negative ? 1 : 0);
        String reverse = unsigned;
        if (unsigned.length() > 1) {
            reverse = new StringBuilder(unsigned).reverse().toString();
            while (reverse.startsWith("0")) {
                reverse = reverse.replaceFirst("0", "");
            }
        }

        return negative ? "-" + reverse : reverse;
    }
}
