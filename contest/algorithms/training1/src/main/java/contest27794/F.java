package contest27794;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27794">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 5. Префиксные суммы и два указателя</a>
 * <a href="https://contest.yandex.ru/contest/27794/run-report/94202149">OK  0.667s  34.82Mb</a>
 *
 * F. Кондиционеры
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * При реализации проекта «Умная школа» было решено в каждый учебный класс выбранной для этого школы установить по
 * кондиционеру нового поколения для автоматического охлаждения и вентиляции воздуха. По проекту в каждом классе должен
 * быть установлен только один кондиционер и мощность кондиционера должна быть достаточной для размеров класса.
 * Чем больше класс, тем мощнее должен быть кондиционер.
 *
 * Все классы школы пронумерованы последовательно от 1 до n. Известно, что для каждого класса с номером i,
 * требуется ровно один кондиционер, мощность которого больше или равна ai ватт.
 *
 * Администрации школы предоставили список из m различных моделей кондиционеров, которые можно закупить.
 * Для каждой модели кондиционера известна его мощность и стоимость. Требуется написать программу, которая определит,
 * за какую минимальную суммарную стоимость кондиционеров можно оснастить все классы школы.
 *
 * Формат ввода
 * Первая строка входного файла содержит одно целое число n (1 ≤ n ≤ 50 000) количество классов в школе.
 * Вторая строка содержит n целых чисел ai (1 ≤ ai ≤ 1000) — минимальная мощность кондиционера в ваттах, который можно установить в классе с номером i.
 * Третья строка содержит одно целое число m (1 ≤ m ≤ 50 000) — количество предложенных моделей кондиционеров.
 * Далее, в каждой из m строк содержится пара целых чисел bj и cj (1 ≤ bj ≤ 1000, 1 ≤ cj ≤ 1000) мощность в ваттах j-й модели кондиционера и его цена в рублях соответственно.
 *
 * Формат вывода
 * Выходной файл должен содержать одно число минимальную суммарную стоимость кондиционеров в рублях.
 * Гарантируется, что хотя бы один корректный выбор кондиционеров существует, и во всех классах можно установить подходящий кондиционер.
 *
 * Примечания
 * В первом примере нужно купить один единственно возможный кондиционер за 1000 рублей.
 * Во втором примере оптимально будет установить в первом и втором классах кондиционеры четвертого типа, а в третьем
 * классе – кондиционер третьего типа. Суммарная стоимость этих кондиционеров будет составлять 13 рублей (3 + 3 + 7).
 * </pre>
 */
public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int[] a = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(reader.readLine());
        int[][] bc = new int[m][];
        for (int i = 0; i < m; i++) {
            bc[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // O(N logN)
        Map<Integer, Integer> roomCounts = new HashMap<>();
        for (int power : a) {
            roomCounts.compute(power, (k, v) -> (v == null) ? 1 : ++v);
        }
        int[] roomPowers = roomCounts.keySet().stream().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();

        // O(M logM)
        HashMap<Integer, Integer> condPrices = new HashMap<>();
        for (int[] d : bc) {
            condPrices.compute(d[0], (k, v) -> (v == null) ? d[1] : Math.min(d[1], v));
        }
        int[] condPowers = condPrices.keySet().stream().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();

        // O(N + M)
        int price = 0;
        int L = 0;
        int bestCondPrice = condPrices.get(condPowers[L]);
        for (int roomPower : roomPowers) {
            while ((L < condPowers.length - 1) && (condPowers[L + 1] >= roomPower)) {
                L++;
                int condPrice = condPrices.get(condPowers[L]);
                bestCondPrice = Math.min(bestCondPrice, condPrice);
            }
            price += roomCounts.get(roomPower) * bestCondPrice;
        }

        writer.write("" + price);
        writer.flush();
    }
}
