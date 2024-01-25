package contest27883;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105810265">OK  0.607s  31.68Mb</a>
 *
 * D. Реклама
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Фирма NNN решила транслировать свой рекламный ролик в супермаркете XXX.
 * Однако денег, запланированных на рекламную кампанию, хватило лишь на две трансляции ролика в течение
 * одного рабочего дня.
 *
 * Фирма NNN собрала информацию о времени прихода и времени ухода каждого покупателя в некоторый день.
 * Менеджер по рекламе предположил, что и на следующий день покупатели будут приходить и уходить ровно в те же
 * моменты времени.
 *
 * Помогите ему определить моменты времени, когда нужно включить трансляцию рекламных роликов,
 * чтобы как можно большее количество покупателей прослушало ролик целиком от начала до конца хотя бы один раз.
 * Ролик длится ровно 5 единиц времен.
 * Трансляции роликов не должны пересекаться,
 * то есть начало второй трансляции должно быть хотя бы на 5 единиц времени позже, чем начало первой.
 *
 * Если трансляция ролика включается, например, в момент времени 10, то покупатели,
 * пришедшие в супермаркет в момент времени 10 (или раньше) и уходящие из супермаркета в момент 15 (или позднее)
 * успеют его прослушать целиком, а, например, покупатель,
 * пришедший в момент времени 11, равно как и покупатель, уходящий в момент 14 - не успеют.
 * Если покупатель успевает услышать только конец первой трансляции ролика (не сначала)
 * и начало второй трансляции (не до конца), то считается, что он не услышал объявления.
 * Если покупатель успевает услышать обе трансляции ролика, то при подсчете числа людей, прослушавших ролик,
 * он все равно учитывается всего один раз (фирме важно именно количество различных людей, услышавших ролик).
 *
 * Формат ввода
 * В первой строке входного файла вводится число N - количество покупателей (1 ≤ N ≤ 2000).
 * В следующих N строках записано по паре натуральных чисел - время прихода и время ухода каждого из них.
 * Все значения времени - натуральные числа, не превышающие 10^9.
 * Время ухода человека из супермаркета всегда строго больше времени его прихода в супермаркет.
 *
 * Формат вывода
 * Выведите через пробел три числа:
 * количество покупателей, которые прослушают ролик целиком от начала до конца хотя бы один раз,
 * и моменты времени, когда должна начинаться трансляция ролика.
 * Моменты времени должны быть выведены в возрастающем порядке и должны быть натуральными числами,
 * не превышающими 2*10^9.
 * Если вариантов ответа несколько, выведите любой из них.
 *
 * Примечания
 * 1. Трансляция роликов начинается в моменты времени 1 и 6.
 * Первое объявление успевают прослушать покупатели номер 1 и 4, второе - 1 и 3.
 * Когда бы ни начиналась трансляция объявления, 2-й покупатель не сможет его прослушать,
 * так как находится в супермаркете менее 5 минут.
 * Приведенный ответ является не единственным верным ответом на этот тест.
 * 2. Объявление, трансляция которого начинается в момент 3, единственный покупатель обязательно услышит.
 * Вторую трансляцию (раз она оплачена) мы можем сделать когда угодно, например, в 25 минут в пустом супермаркете
 * (впрочем, мы не можем начать трансляцию второго объявления, например, в момент 7 - т.к. к этому моменту
 * еще не закончится первая трансляция)
 * 3. Объявление услышат лишь 2 из 3-х покупателей.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int[] in = new int[n];
        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            in[i] = reader.nextInt();
            out[i] = reader.nextInt();
        }

        String solution = alg1(n, in, out);

        writer.write(solution);
        writer.flush();
    }

    // ---------------------------------------------------------------------------------------------
    static final int AD_DURATION = 5;

    static class Event implements Comparable<Event> {
        // порядок важен для сортировки событий одного момента
        enum Type {AD_OFF, AD_ON_FIRST, AD_ON_LAST, PERSON_OUT}

        static final Set<Type> AD1_START_EVENT_TYPES = Set.of(Type.AD_ON_FIRST, Type.AD_ON_LAST);
        static final Set<Type> AD2_START_EVENT_TYPES = Set.of(Type.AD_ON_FIRST, Type.AD_ON_LAST, Type.PERSON_OUT);

        final Type type;
        final int moment;
        final int personIndex;

        Event(Type type, int moment, int personIndex) {
            this.type = type;
            this.moment = moment;
            this.personIndex = personIndex;
        }

        @Override
        public int compareTo(Event e) {
            int c = Integer.compare(moment, e.moment);
            if (c == 0)
                c = Integer.compare(type.ordinal(), e.type.ordinal());
            return c;
        }
    }

    static class BestAd {
        int personCount;
        int moment;
    }

    // ---------------------------------------------------------------------------------------------

    public static String alg1(int n, int[] in, int[] out) {
        List<Event> events = events(n, in, out);

        int bestAdPersonCount = -1; // -1 на случай одного подходящего посетителя
        int bestAd1Moment = 1; // на случай отсутствия подходящих посетителей
        int bestAd2Moment = 6; // на случай отсутствия подходящих посетителей

        Set<Integer> ad1Persons = new HashSet<>();
        for (int ad1Index = 0; ad1Index < events.size(); ad1Index++) {
            Event ad1 = events.get(ad1Index);

            if (ad1.type == Event.Type.AD_ON_FIRST) {
                ad1Persons.add(ad1.personIndex);
            } else if (ad1.type == Event.Type.AD_OFF) {
                ad1Persons.remove(ad1.personIndex);
            }

            if (Event.AD1_START_EVENT_TYPES.contains(ad1.type)) {
                BestAd bestAd2 = bestAd2(events, ad1Index, ad1Persons);

                int adPersonCount = ad1Persons.size() + bestAd2.personCount;
                if (adPersonCount > bestAdPersonCount) {
                    bestAdPersonCount = adPersonCount;
                    bestAd1Moment = ad1.moment;
                    bestAd2Moment = bestAd2.moment;
                }
            }
        }

        if (bestAdPersonCount == -1) {
            bestAdPersonCount = 0;
        }

        return String.format("%d %d %d", bestAdPersonCount, bestAd1Moment, bestAd2Moment);
    }

    static List<Event> events(int n, int[] in, int[] out) {
        List<Event> events = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int stayDuration = out[i] - in[i];
            if (stayDuration >= AD_DURATION) {
                events.add(new Event(Event.Type.AD_ON_FIRST, in[i], i));
                events.add(new Event(Event.Type.AD_ON_LAST, out[i] - AD_DURATION, i));
                events.add(new Event(Event.Type.AD_OFF, out[i] + 1 - AD_DURATION, i));
                events.add(new Event(Event.Type.PERSON_OUT, out[i], i));
            }
        }
        Collections.sort(events);
        return events;
    }

    static BestAd bestAd2(List<Event> events, int ad1Index, Set<Integer> ad1Persons) {
        BestAd bestAd2 = new BestAd();
        bestAd2.personCount = -1; // -1 на случай одного подходящего события - ухода покупателя после окончания AD1
        bestAd2.moment = events.get(ad1Index).moment + AD_DURATION; // на случай отсутствия подходящих событий

        int ad2PersonCount = 0;
        for (int ad2Index = ad1Index + 1; ad2Index < events.size(); ad2Index++) {
            Event ad2 = events.get(ad2Index);

            if (ad2.type == Event.Type.AD_ON_FIRST) {
                ad2PersonCount++;
            } else if (ad2.type == Event.Type.AD_OFF && !ad1Persons.contains(ad2.personIndex)) {
                ad2PersonCount--;
            }

            boolean isAd2StartEvent = Event.AD2_START_EVENT_TYPES.contains(ad2.type);
            boolean isSuitableMoment = ad2.moment >= bestAd2.moment;
            boolean isBestPersonCount = ad2PersonCount > bestAd2.personCount;
            if (isAd2StartEvent && isSuitableMoment && isBestPersonCount) {
                bestAd2.personCount = ad2PersonCount;
                bestAd2.moment = ad2.moment;
            }
        }

        if (bestAd2.personCount == -1) {
            bestAd2.personCount = 0;
        }

        return bestAd2;
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
