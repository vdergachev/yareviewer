package io.yandex.reviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Маруся очень любит слушать рок музыку. У нее есть два плейлиста: один - с песнями российских исполнителей, другой - зарубежных.
Помогите Марусе создать плейлист, в котором иностранные и российские композиции шли бы через одну.
При этом порядок их следования в оригинальных плейлистах должен быть сохранен. Первой должна звучать песня российского исполнителя.

В первой строке записано число n - длина обоих плейлистов. Оно не превосходит 1000. В следующей строке записаны id российских рок композиций.
Их n штук. В последней строке - id зарубежных композиций. Их также n штук. Каждое из чисел в двух последних строках не превосходит 1000.
*/
public class DecodeCypher {

    public static void main(final String[] args) throws IOException {
        List<String> lines = readInput();
        List<String> path = doItLikeUlitka(lines);
        String output = String.join("\n", path);
        writeOutput(output);
    }

    private static List<String> readInput() throws IOException {
        return Files.readAllLines(Paths.get("./input.txt"));
    }

    private static void writeOutput(String output) throws IOException {
        Files.write(Paths.get("./output.txt"), output.getBytes());
    }

    private static List<String> doItLikeUlitka(List<String> lines) {

        int dimension = Integer.parseInt(lines.get(0));
        if (dimension <= 0) {
            return Collections.emptyList();
        }

        // load matrix
        String[][] matrix = new String[dimension][];
        for (int i = 0; i < dimension; i++) {
            matrix[i] = lines.get(i + 1).split(" ");
        }

        List<String> result = new ArrayList<>(dimension * dimension);

        int n = dimension;
        int count = n;
        int value = -n;
        int sum = -1;

        do {
            value = -1 * value / n;
            for (int i = 0; i < count; i++) {
                sum += value;
                System.out.printf("%s", matrix[sum / n][sum % n]);
            }
            value *= n;
            count--;
            for (int i = 0; i < count; i++) {
                sum += value;
                System.out.printf("%s", matrix[sum / n][sum % n]);
            }
        } while (count > 0);

        return result;
    }
}
