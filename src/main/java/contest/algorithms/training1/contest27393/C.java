package contest.algorithms.training1.contest27393;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27393">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 1. Сложность, тестирование, особые случаи</a>
 *
 * C. Телефонные номера
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Телефонные номера в адресной книге мобильного телефона имеют один из следующих форматов: +7<код><номер>,
 * 8<код><номер>, <номер>, где <номер> — это семь цифр, а <код> — это три цифры или три цифры в круглых скобках. Если
 * код не указан, то считается, что он равен 495. Кроме того, в записи телефонного номера может стоять знак “-” между
 * любыми двумя цифрами (см. пример). На данный момент в адресной книге телефона Васи записано всего три телефонных номера,
 * и он хочет записать туда еще один. Но он не может понять, не записан ли уже такой номер в телефонной книге.
 * Помогите ему! Два телефонных номера совпадают, если у них равны коды и равны номера.
 * Например, +7(916)0123456 и 89160123456 — это один и тот же номер.
 *
 * Формат ввода
 * В первой строке входных данных записан номер телефона, который Вася хочет добавить в адресную книгу своего телефона.
 * В следующих трех строках записаны три номера телефонов, которые уже находятся в адресной книге телефона Васи.
 * Гарантируется, что каждая из записей соответствует одному из трех приведенных в условии форматов.
 *
 * Формат вывода
 * Для каждого телефонного номера в адресной книге выведите YES (заглавными буквами), если он совпадает с тем телефонным
 * номером, который Вася хочет добавить в адресную книгу или NO (заглавными буквами) в противном случае.
 *
 * OK  96ms  8.63Mb
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        String newPhoneNumber = parseNumber(reader.readLine());
        for (int i = 0; i < 3; i++) {
            String existingPhoneNumber = parseNumber(reader.readLine());
            writer.write(newPhoneNumber.equals(existingPhoneNumber) ? "YES\n" : "NO\n");
        }
        writer.flush();
    }

    private static String parseNumber(String phone) {
        // 79998887766, size = 11 -> 49579998887766, offset = 4, size = 10
        // 89998887766, size = 11 -> 49589998887766, offset = 4, size = 10
        //  9998887766, size = 10 -> 49599988877660, offset = 3, size = 10
        //     8887766, size =  7 -> 49588877660000, offset = 0, size = 10
        byte[] bytes = {'4', '9', '5', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int offset = 3;
        int length = 0;

        for (byte b : phone.getBytes()) {
            if (b >= '0' && b <= '9') {
                bytes[offset + length++] = b;
            }
        }

        offset = length == 11 ? 4
                : length == 10 ? 3
                : 0;
        length = 10;

        return new String(bytes, offset, length);
    }
}
