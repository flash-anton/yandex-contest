package contest36783;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/105306143">OK  170ms  14.71Mb</a>
 *
 * S. По ip вычислю
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В этой задаче вам предстоит ответить на вопрос, является данная на вход строка корректным IPv4 или IPv6-адресом.
 * Решите задачу без использования регулярных выражений.
 *
 * Валидный IPv4-адрес имеет следующий формат: s1.s2.s3.s4, где si — целое число от 0 до 255.
 * Числа si не должны иметь лидирующих нулей.
 *
 * Валидный IPv6-адрес имеет следующий формат: s1:s2:s3:s4:s5:s6:s7:s8,
 * где si - представление числа в 16-ричной системе,
 * состоящее не более чем из четырёх символов.
 * В записи каждого числа могут присутствовать лидирующие нули.
 * Разрешено использовать символы 0-9, a-f, A-F.
 * si не может быть пустой последовательностью символов.
 *
 * Замечание: в реальности в IPv6-адресе допустима замена группы нулевых полей на ::,
 * однако для простоты в данной задаче такое правило учитывать не нужно.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * На вход подается строка, состоящая из латинских букв, цифр и символов «.» и «:».
 * Длина строки не превосходит 100 символов.
 *
 * Формат вывода
 * Выведите «IPv4», если строка является корректным IPv4-адресом.
 * Выведите «IPv6», если строка является корректным IPv6-адресом.
 * Выведите «Error», если строка не является корректной записью IP-адреса.
 * </pre>
 */
public class S {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        String address = new Reader(is).readLine().strip();

        IpHandler h = new IpHandler();
        for (char c : address.toCharArray()) {
            if (h.ipType == IpType.ERROR) {
                break;
            } else {
                h.addChar(c);
            }
        }
        h.buildAddress();

        String solution = "Error";
        if (h.ipType == IpType.IPv4) {
            solution = "IPv4";
        } else if (h.ipType == IpType.IPv6) {
            solution = "IPv6";
        }

        writer.write(solution);
        writer.flush();
    }

    enum IpType {UNKNOWN, IPv4, IPv6, ERROR}

    static class IpHandler {
        IpType ipType = IpType.UNKNOWN;
        List<Integer> address = new ArrayList<>();
        StringBuilder lastNumber = new StringBuilder();

        void addChar(char c) {
            if (c >= '0' && c <= '9') {
                addDec(c);
            } else if ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {
                addHex(c);
            } else if (c == '.') {
                addDot();
            } else if (c == ':') {
                addColon();
            } else {
                ipType = IpType.ERROR;
            }
        }

        void addDec(char c) {
            lastNumber.append(c);
        }

        void addHex(char c) {
            if (ipType == IpType.IPv4) {
                ipType = IpType.ERROR;
            } else {
                lastNumber.append(c);
            }
        }

        void addDot() {
            if (ipType == IpType.IPv6) {
                ipType = IpType.ERROR;
            } else {
                ipType = IpType.IPv4;
                buildLastNumberOfIPv4();
            }
        }

        void addColon() {
            if (ipType == IpType.IPv4) {
                ipType = IpType.ERROR;
            } else {
                ipType = IpType.IPv6;
                buildLastNumberOfIPv6();
            }
        }

        int parseInt(String num, int radix) {
            try {
                return Integer.parseInt(num, radix);
            } catch (NumberFormatException ex) {
                return -1;
            }
        }

        void buildLastNumberOfIPv4() {
            int num = parseInt(lastNumber.toString(), 10);
            boolean noLeadNull = lastNumber.length() <= 1 || lastNumber.charAt(0) != '0';
            if (num >= 0 && num <= 255 && noLeadNull) {
                address.add(num);
                lastNumber = new StringBuilder();
            } else {
                ipType = IpType.ERROR;
            }
        }

        void buildLastNumberOfIPv6() {
            int num = parseInt(lastNumber.toString(), 16);
            boolean size4orLess = lastNumber.length() <= 4;
            if (num >= 0 && num <= 0xFFFF && size4orLess) {
                address.add(num);
                lastNumber = new StringBuilder();
            } else {
                ipType = IpType.ERROR;
            }
        }

        void buildAddress() {
            if (ipType == IpType.IPv4) {
                buildLastNumberOfIPv4();
            } else if (ipType == IpType.IPv6) {
                buildLastNumberOfIPv6();
            }
            boolean isIPv4 = ipType == IpType.IPv4 && address.size() == 4;
            boolean isIPv6 = ipType == IpType.IPv6 && address.size() == 8;
            if (!isIPv4 && !isIPv6) {
                ipType = IpType.ERROR;
            }
        }
    }

    /**
     * "Быстрый" ридер потока.
     */
    public static class Reader {
        private final InputStream is;
        private int lastReadByte = '\n';

        public Reader(InputStream is) {
            this.is = is;
        }

        public long nextLong() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            int sign = 1;
            if (lastReadByte == '-') {
                sign = -1;
                lastReadByte = is.read();
            }

            long num = 0;
            while (lastReadByte >= '0' && lastReadByte <= '9') {
                num = (num * 10) + sign * (lastReadByte - '0');
                lastReadByte = is.read();
            }
            return num;
        }

        public long[] nextLongs(long[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        public int nextInt() throws IOException {
            return (int) nextLong();
        }

        public int[] nextInts(int[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String nextWord() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            StringBuilder sb = new StringBuilder();
            while (!(lastReadByte == -1 || lastReadByte == ' ' || lastReadByte == '\n')) {
                sb.append((char) lastReadByte);
                lastReadByte = is.read();
            }
            return sb.toString();
        }

        public String[] nextWords(String[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextWord();
            }
            return a;
        }

        public String readLine() throws IOException {
            if (lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            StringBuilder sb = new StringBuilder();
            while (!(lastReadByte == -1 || lastReadByte == '\n')) {
                sb.append((char) lastReadByte);
                lastReadByte = is.read();
            }
            return sb.toString();
        }
    }
}
