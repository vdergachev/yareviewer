package io.yandex.reviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

/*
В сервисе Киношечка зарегистрировано n пользователей.
Все пользователи, за исключением двух, в последние два месяца посещали сайт.
Нужно определить id пользователей, которые сайт не посещали.

Первая строка содержит число n — количество зарегистрированных пользователей. Это целое число в диапазоне от 2 до  10^6
Во второй строке через пробел заданы различные n - 2 целых числа. Каждое из них не превосходит n и больше нуля.

Нужно в одной строке вывести по возрастанию два пропущенных числа, разделённые пробелом.
*/
public class FilmFilmFilm {

    private static int MAX_VALUE = 1_000_000;

    public static void main(final String[] args) throws IOException {
        List<String> lines = readInput();
        List<String> ids = findAbsenceIds(lines);
        String output = String.join(" ", ids);
        writeOutput(output);
    }

    private static List<String> readInput() throws IOException {
        return Files.readAllLines(Paths.get("./input.txt"));
    }

    private static void writeOutput(String output) throws IOException {
        Files.write(Paths.get("./output.txt"), output.getBytes());
    }

    private static List<String> findAbsenceIds(List<String> lines) {

        int size = Integer.parseInt(lines.get(0));
        if (size <= 0) {
            return Collections.emptyList();
        }

        BitSet bits = new BitSet(MAX_VALUE);

        for (String numAsString : lines.get(1).split(" ")) {
            int num = Integer.parseInt(numAsString);
            bits.set(num - 1);
        }

        List<String> result = new ArrayList<>(2);
        int iter = 0;
        while (true) {
            iter = bits.nextClearBit(iter) + 1;
            if (iter < 0 || iter > size) {
                break;
            }
            result.add(String.valueOf(iter));
        }

        return result;
    }
}
