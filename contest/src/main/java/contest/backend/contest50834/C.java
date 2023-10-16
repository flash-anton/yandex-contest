package contest.backend.contest50834;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/50834/enter">Intern week offer 2023 — бэкенд</a>
 *
 * C. Наследование репозиториев
 *
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В системе контроля версий SEHR GUT можно наследовать репозиторий и вносить изменения кода в какую либо версию кода.
 * При этом изменение, внесенное в версию кода, автоматически вносится во все репозитории, из которых был наследован код.
 * Исходно в системе есть только репозиторий 0. Если от него были пронаследованы репозитории 1 и 2, а от репозитория 1
 * был наследован репозиторий 3 и изменения были внесены в репозиторий 3, то они также внесутся в репозитории 1 и 0 (но
 * не в репозиторий 2).
 * Вася хочет внести изменение в один репозиторий таким образом, чтобы они оказались в как можно большем количестве
 * репозиториев.
 *
 * Формат ввода
 * Во входном файле записано число N — общее количество наследованных репозиториев (1 ≤ N ≤ 100000). Затем следует
 * описание наследования репозиториев: в i-ой строке записано число Ri — номер репозитория, наследником которого
 * является i-ый репозиторий (0 ≤ Ri < i). Начальный репозиторий имеет номер 0.
 *
 * Формат вывода
 * Выведите номер репозитория, в который нужно внести изменения Васе. Если правильных ответов несколько — выведите любой
 * из них.
 *
 * Примечания
 * Первый пример соответствует ситуации, описанной в условии. Репозитории номер 1 и 2 пронаследованы от репоизитория с
 * номером 0. Репозиторий номер 3 пронаследован от репозитория номер 1. Если внести изменение в репозиторий номер 3, то
 * это изменение окажется в репозиториях с номерами 3, 1 и 0.
 *
 * OK 175ms 14.74Mb
 * </pre>
 */
public class C {
    private final InputStream in;
    private final OutputStream out;

    public C(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void execute() throws IOException {
        byte[] buf = new byte[10];
        int N = readInt(buf);
        int[] R = new int[N + 1];
        for (int i = 1; i < N + 1; i++)
            R[i] = R[readInt(buf)] + 1;

        int max = 0;
        int iMax = 0;
        for (int i = 1; i < N + 1; i++)
            if (R[i] > max) {
                max = R[i];
                iMax = i;
            }

        buf = Integer.toString(iMax).getBytes();
        out.write(buf, 0, buf.length);
    }

    private int readInt(byte[] buf) throws IOException {
        int size = 0;
        byte b;
        while ((b = (byte) in.read()) != -1 && b != '\n')
            buf[size++] = b;
        return Integer.parseInt(new String(buf, 0, size));
    }

    public static void main(String[] args) throws IOException {
        try (InputStream in = new BufferedInputStream(System.in);
             OutputStream out = new BufferedOutputStream(System.out)) {
            new C(in, out).execute();
        }
    }
}
