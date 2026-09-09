import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

class Result {
    public static void matrixRotation(List<List<Integer>> matrix, int r) {
        int m = matrix.size();
        int n = matrix.get(0).size();
        int layers = Math.min(m, n) / 2;
        for (int layer = 0; layer < layers; layer++) {
            List<Integer> elements = new ArrayList<>();
            int top = layer;
            int left = layer;
            int bottom = m - 1 - layer;
            int right = n - 1 - layer;
            for (int j = left; j <= right; j++) {
                elements.add(matrix.get(top).get(j));
            }
            for (int i = top + 1; i <= bottom; i++) {
                elements.add(matrix.get(i).get(right));
            }
            for (int j = right - 1; j >= left; j--) {
                elements.add(matrix.get(bottom).get(j));
            }
            for (int i = bottom - 1; i > top; i--) {
                elements.add(matrix.get(i).get(left));
            }
            int len = elements.size();
            int shift = r % len;
            Collections.rotate(elements, -shift);
            int index = 0;
            for (int j = left; j <= right; j++) {
                matrix.get(top).set(j, elements.get(index++));
            }
            for (int i = top + 1; i <= bottom; i++) {
                matrix.get(i).set(right, elements.get(index++));
            }
            for (int j = right - 1; j >= left; j--) {
                matrix.get(bottom).set(j, elements.get(index++));
            }
            for (int i = bottom - 1; i > top; i--) {
                matrix.get(i).set(left, elements.get(index++));
            }
        }
        for (List<Integer> row : matrix) {
            System.out.println(
                row.stream()
                   .map(String::valueOf)
                   .collect(Collectors.joining(" "))
            );
        }
    }
}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in));
        String[] firstMultipleInput =
            bufferedReader.readLine()
                .replaceAll("\\s+$", "")
                .split(" ");
        int m = Integer.parseInt(firstMultipleInput[0]);
        int n = Integer.parseInt(firstMultipleInput[1]);
        int r = Integer.parseInt(firstMultipleInput[2]);
        List<List<Integer>> matrix = new ArrayList<>();
        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(
                        bufferedReader.readLine()
                            .replaceAll("\\s+$", "")
                            .split(" ")
                    )
                    .map(Integer::parseInt)
                    .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
