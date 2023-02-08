package com.sh.game.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.sh.game.system.map.Point;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 周锟 on 2016/1/25 10:12.
 */
public class Utils {

    /**
     * 一亿
     */
    public static final long _100_MILLION = 100000000;
    /**
     * 一百万
     */
    public static final long MILLION = 1000000;
    /**
     * 一千
     */
    public static final long THOUSAND = 1000;

    /**
     * 返回0~max之间的一个随机数,不包括max
     *
     * @param max
     * @return
     */
    public static int nextInt(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max <= 0");
        }
        return ThreadLocalRandom.current().nextInt(max);
    }

    /**
     * 返回0~max之间的一个随机数,不包括max
     *
     * @param max
     * @return
     */
    public static long nextLong(long max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max <= 0");
        }
        return ThreadLocalRandom.current().nextLong(max);
    }

    /**
     * rate%的概率返回true
     *
     * @param rate
     * @return
     */
    public static boolean isLuck(int rate) {
        return isLuck(rate, 100);
    }

    /**
     * rate/base的概率返回true
     *
     * @param rate
     * @param base
     * @return
     */
    public static boolean isLuck(int rate, int base) {
        if (base <= 0) {
            throw new IllegalArgumentException("base < 0");
        }
        return nextInt(base) < rate;
    }

    /**
     * 返回from~to之间的随机数,包括form和to
     *
     * @param from
     * @param to
     * @return
     */
    public static int random(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("from > to");
        }
        int n = to - from + 1;
        return nextInt(n) + from;
    }

    /**
     * 等概率随机选一个
     *
     * @param col
     * @param <T>
     * @return
     */
    public static <T> T randomChoose(Collection<T> col) {
        if (col == null || col.isEmpty()) {
            return null;
        }
        int index = nextInt(col.size());
        return get(col, index);
    }

    /**
     * 等概率随机选一个
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> T randomChoose(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int index = nextInt(array.length);
        return array[index];
    }

    /**
     * 等概率随机选一个
     *
     * @param ints
     * @return
     */
    public static int randomChoose(int[] ints) {
        if (ints == null || ints.length == 0) {
            throw new IllegalArgumentException();
        }
        int index = nextInt(ints.length);
        return ints[index];
    }

    /**
     * 等概率随机选择一个点
     *
     * @param ints
     * @return
     */
    public static Point randomChoosePoint(int[] ints) {
        if (ints == null || ints.length < 2 || ints.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        int index = nextInt(ints.length / 2);
        return new Point(ints[index * 2], ints[index * 2 + 1]);
    }

    /**
     * 等概率随机选择n个
     *
     * @param list
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<T> randomChooseN(List<T> list, int n) {
        if (list == null || n <= 0) {
            return Collections.emptyList();
        }
        int size = list.size();
        List<T> result = new ArrayList<>(n);
        for (int i = 0; i < size && n > 0; i++) {
            if (nextInt(size - i) < n) {
                result.add(list.get(i));
                n--;
            }
        }
        return result;
    }

    /**
     * 等概率随机选择n个
     *
     * @param col
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<T> randomChooseN(Collection<T> col, int n) {
        if (col == null || n <= 0) {
            return Collections.emptyList();
        }
        int size = col.size();
        List<T> result = new ArrayList<>(n);
        Iterator<T> iterator = col.iterator();
        for (int i = 0; i < size && iterator.hasNext() && n > 0; i++) {
            T next = iterator.next();
            if (nextInt(size - i) < n) {
                result.add(next);
                n--;
            }
        }
        return result;
    }

    /**
     * 等概率随机选择n个
     *
     * @param array
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<T> randomChooseN(T[] array, int n) {
        int length;
        if (array == null || (length = array.length) == 0 || n <= 0) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(n);
        for (int i = 0; i < length && n > 0; i++) {
            if (nextInt(length - i) < n) {
                result.add(array[i]);
                n--;
            }
        }
        return result;
    }

    /**
     * 等概率随机选择n个
     *
     * @param ints
     * @param n
     * @return
     */
    public static List<Integer> randomChooseN(int[] ints, int n) {
        int length;
        if (ints == null || (length = ints.length) == 0 || n <= 0) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>(n);
        for (int i = 0; i < length && n > 0; i++) {
            if (nextInt(length - i) < n) {
                result.add(ints[i]);
                n--;
            }
        }
        return result;
    }

    /**
     * 等概率随机删除一个并返回
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T randomRemove(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int index = nextInt(list.size());
        return list.remove(index);
    }

    /**
     * 以对应索引上的概率选择物品
     *
     * @param items
     * @param rates
     * @param <T>
     * @return
     */
    public static <T> T randomChooseByRate(List<T> items, List<Integer> rates) {
        if (items == null || rates == null) {
            return null;
        }
        int index = randomIndex(rates);
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    /**
     * 根据权重选择N个
     * key: 需要返回的数据configId值
     * value: 权重
     * count: 需要随机的个数
     */
    public static List<Integer> randomChooseByRate(Map<Integer, Integer> map, int count) {
        if (map == null || map.size() <= 0 || count <= 0 || map.size() < count) {
            return Collections.emptyList();
        }
        Map<Integer, Integer> map2 = new HashMap<>();
        map2.putAll(map);
        int totalWeight = 0;
        for (Integer weight : map2.values()) {
            totalWeight += weight;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int number = random(1, totalWeight);
            int total = 0;
            Iterator<Map.Entry<Integer, Integer>> iterator = map2.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                total += entry.getValue();
                if (total >= number) {
                    list.add(entry.getKey());
                    totalWeight -= entry.getValue();
                    iterator.remove();
                    break;
                }
            }
        }
        return list;
    }

    /**
     *
     * 根据权重选择N个
     * key: 需要返回的数据Id值
     * value: 权重
     */
    public static int randomChooseByRate(Map<Integer, Integer> map) {
        int sumRate = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sumRate += entry.getValue();
        }
        if (sumRate <= 0) {
            return 0;
        }
        int num = random(1, sumRate);
        int rate = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            rate += entry.getValue();
            if (rate >= num) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public static long randomChooseLongIntegerMapByRate(Map<Long, Integer> map) {
        int sumRate = 0;
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            sumRate += entry.getValue();
        }
        if (sumRate <= 0) {
            return 0;
        }
        int num = random(1, sumRate);
        int rate = 0;
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            rate += entry.getValue();
            if (rate >= num) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public static <T> T randomChooseByRate(List<T> items, int[] rates) {
        if (items == null || rates == null) {
            return null;
        }
        int index = randomIndex(rates);
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public static <T> T randomChooseByRate(T[] items, List<Integer> rates) {
        if (items == null || rates == null) {
            return null;
        }
        int index = randomIndex(rates);
        if (index >= 0 && index < items.length) {
            return items[index];
        }
        return null;
    }

    public static <T> T randomChooseByRate(T[] items, int[] rates) {
        if (items == null || rates == null) {
            return null;
        }
        int index = randomIndex(rates);
        if (index >= 0 && index < items.length) {
            return items[index];
        }
        return null;
    }

    public static int randomChooseByRate(int[] ints, int[] rates) {
        if (ints == null || ints.length == 0 || rates == null || rates.length == 0 || ints.length < rates.length) {
            throw new IllegalArgumentException();
        }
        int index = randomIndex(rates);
        return ints[index];
    }

    /**
     * 按概率返回索引
     *
     * @param rates
     * @return
     */
    public static int randomIndex(List<Integer> rates) {
        if (rates == null) {
            return -1;
        }
        int total = 0;
        for (Integer rate : rates) {
            if (rate != null) {
                total += rate;
            }
        }
        if (total == 0) {
            return -1;
        }
        int random = nextInt(total);
        for (int i = 0; i < rates.size(); i++) {
            Integer rate = rates.get(i);
            if (rate != null) {
                random -= rate;
                if (random < 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 按概率返回索引
     *
     * @param rates
     * @return
     */
    public static int randomIndex(int[] rates) {
        if (rates == null) {
            return -1;
        }
        int total = 0;
        for (int i = 0; i < rates.length; i++) {
            total += rates[i];
        }
        if (total == 0) {
            return -1;
        }
        int random = nextInt(total);
        for (int i = 0; i < rates.length; i++) {
            random -= rates[i];
            if (random < 0) {
                return i;
            }
        }
        return -1;
    }

    //=============================================================================================

    /**
     * 返回长度为length的随机字符串,仅包括字母
     *
     * @param length
     * @return
     */
    public static String randomString(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        return randomString(length, false);
    }

    /**
     * 返回长度为length的随机字符串
     *
     * @param length
     * @param numbers 是否包含数字
     * @return
     */
    public static String randomString(int length, boolean numbers) {
        if (length < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        return RandomStringUtils.random(length, true, numbers);
    }

    //=============================================================================================

    /**
     * 将字符串数组转换为int数组
     *
     * @param strings
     * @return
     */
    public static int[] parseToInts(String[] strings) {
        if (strings == null) {
            return new int[0];
        }
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = parseToInt(strings[i]);
        }
        return ints;
    }

    public static int[] parseToInts(String string, String separator) {
        if (string == null || separator == null) {
            return new int[0];
        }
        return parseToInts(string.trim().split(separator));
    }

    private static Number toNumber(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Number) {
            return (Number) object;
        } else if (object instanceof Boolean) {
            Boolean aBoolean = (Boolean) object;
            return aBoolean ? 1 : 0;
        } else if (object instanceof BigInteger) {
            BigInteger bigInteger = (BigInteger) object;
            return bigInteger.longValue();
        } else if (object instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) object;
            return decimal.doubleValue();
        }
        return null;
    }

    public static int parseToInt(Object object) {
        return Ints.saturatedCast(parseToLong(object));
    }

    public static int parseToInt(String string) {
        return Ints.saturatedCast(parseToLong(string));
    }

    public static long parseToLong(Object object) {
        if (object == null) {
            return 0L;
        }
        Number number = toNumber(object);
        if (number != null) {
            return number.longValue();
        }
        return parseToLong(object.toString());
    }

    public static long parseToLong(String string) {
        if (string == null) {
            return 0L;
        }
        string = string.trim();
        int length = string.length();
        if (length == 0) {
            return 0L;
        }
        int radix = 10;
        if (string.charAt(0) == '0' && length > 1) {
            char c = string.charAt(1);
            switch (c) {
                case 'x':
                case 'X':
                    if (length > 2) {
                        string = string.substring(2);
                    } else {
                        return 0L;
                    }
                    radix = 16;
                    break;
                case 'b':
                case 'B':
                    if (length > 2) {
                        string = string.substring(2);
                    } else {
                        return 0L;
                    }
                    radix = 2;
                    break;
                default:
                    string = string.substring(1);
                    radix = 8;
                    break;
            }
            if (string.isEmpty()) {
                return 0L;
            }
        }
        Long aLong = Longs.tryParse(string, radix);
        return aLong == null ? 0L : aLong;
    }

    public static double parseToDouble(Object object) {
        if (object == null) {
            return 0.0;
        }
        Number number = toNumber(object);
        if (number != null) {
            return number.doubleValue();
        }
        return parseToDouble(object.toString());
    }

    public static double parseToDouble(String string) {
        if (string == null) {
            return 0.0;
        }
        string = string.trim();
        if (string.isEmpty()) {
            return 0.0;
        }
        Double aDouble = Doubles.tryParse(string);
        return aDouble == null ? 0.0 : aDouble;
    }

    public static boolean parseToBoolean(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Boolean) {
            return (Boolean) object;
        } else {
            Number number = toNumber(object);
            if (number != null) {
                return number.doubleValue() != 0;
            }
        }
        return parseToBoolean(object.toString());
    }

    /**
     * true, yes, on(无视大小写),非0数返回true,其他情况返回false
     *
     * @param string
     * @return
     */
    public static boolean parseToBoolean(String string) {
        if (string == null) {
            return false;
        }
        string = string.trim();
        if (string.isEmpty()) {
            return false;
        }
        if (string.equalsIgnoreCase("true") || string.equalsIgnoreCase("yes") ||
                string.equalsIgnoreCase("on")) {
            return true;
        }
        return parseToDouble(string) != 0;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    //=============================================================================================

    public static String getStackTrace() {
        return getStackTrace(1, 6);
    }

    public static String getStackTrace(int start, int stop) {
        if (start > stop) {
            throw new IllegalArgumentException("start > stop");
        }
        StringBuilder builder = new StringBuilder((stop - start + 1) * 50);
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        if (stackTrace.length < start + 2) {
            return builder.toString();
        }
        appendElement(builder, stackTrace[start + 1]);
        for (int i = start + 2; i < stop + 3 && i < stackTrace.length; i++) {
            builder.append("<=");
            appendElement(builder, stackTrace[i]);
        }
        return builder.toString();
    }

    private static void appendElement(StringBuilder builder, StackTraceElement element) {
        String className = element.getClassName();
        String methodName = element.getMethodName();
        int index = className.lastIndexOf('.');
        builder.append(className.substring(index + 1))
                .append('.')
                .append(methodName)
                .append(':')
                .append(element.getLineNumber());
    }

    //=============================================================================================

    /**
     * 返回list中第一个小于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexLessThan(List<Integer> list, Integer target) {
        return index(list, target, true, false);
    }

    /**
     * 返回list中第一个小于等于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexLessThanOrEqualTo(List<Integer> list, Integer target) {
        return index(list, target, true, true);
    }

    /**
     * 返回list中第一个大于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexLessThan(int[] list, int target) {
        return index(list, target, true, false);
    }

    /**
     * 返回list中第一个大于等于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexLessThanOrEqualTo(int[] list, int target) {
        return index(list, target, true, true);
    }

    /**
     * 返回list中最后一个小于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexLastLessThan(int[] list, int target) {
        return index2(list, target, true, false);
    }

    /**
     * 返回list中最后一个小于等于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexLastLessThanOrEqualTo(int[] list, int target) {
        return index2(list, target, true, true);
    }

    /**
     * 返回list中第一个大于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexMoreThan(List<Integer> list, Integer target) {
        return index(list, target, false, false);
    }

    /**
     * 返回list中第一个大于等于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexMoreThanOrEqualTo(List<Integer> list, Integer target) {
        return index(list, target, false, true);
    }

    /**
     * 返回list中第一个大于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexMoreThan(int[] list, int target) {
        return index(list, target, false, false);
    }

    /**
     * 返回list中第一个大于等于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexMoreThanOrEqualTo(int[] list, int target) {
        return index(list, target, false, true);
    }

    /**
     * 返回list中target的数大于等于第一位并且小于第二位的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexMoreThanOrEqualAndLessSecond(int[] list, int target) {
        return index3(list, target);
    }

    /**
     * 返回list中最后一个大于target的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexLastGreaterThan(int[] list, int target) {
        return index4(list, target, true, false);
    }

    /**
     * 返回list中最后一个target大于等于的数的索引
     *
     * @param list
     * @param target
     * @return
     */
    public static int indexLastGreaterThanOrEqual(int[] list, int target) {
        return index5(list, target, true, true);
    }

    private static int index(List<Integer> list, Integer target, boolean less, boolean equal) {
        if (list == null) {
            return -1;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Integer n = list.get(i);
            if (n == null) {
                if (equal && target == null) {
                    return i;
                }
            } else if ((less && target < n) || (!less && target > n) || (equal && n.equals(target))) {
                return i;
            }
        }
        return -1;
    }

    private static int index(int[] list, int target, boolean less, boolean equal) {
        if (list == null) {
            return -1;
        }
        int size = list.length;
        for (int i = 0; i < size; i++) {
            int n = list[i];
            if ((less && target < n) || (!less && target > n) || (equal && target == n)) {
                return i;
            }
        }
        return -1;
    }

    private static int index2(int[] list, int target, boolean less, boolean equal) {
        if (list == null) {
            return -1;
        }
        int size = list.length;
        int max = -1;
        for (int i = 0; i < size; i++) {
            int n = list[i];
            if ((less && n < target) || (equal && target == n)) {
                max = i;
            }
        }
        return max;
    }

    private static int index3(int[] list, int target) {
        if (list == null) {
            return -1;
        }
        int size = list.length;
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                return i;
            }
            int n = list[i];
            int n1 = list[i + 1];
            if (target >= n && target < n1) {
                return i;
            }
        }
        return -1;
    }

    private static int index4(int[] list, int target, boolean less, boolean equal) {
        if (list == null) {
            return -1;
        }
        int size = list.length;
        int max = -1;
        for (int i = 0; i < size; i++) {
            int n = list[i];
            if ((less && n > target) || (equal && target == n)) {
                max = i;
            }
        }
        return max;
    }

    private static int index5(int[] list, int target, boolean less, boolean equal) {
        if (list == null) {
            return -1;
        }
        int size = list.length;
        int max = -1;
        for (int i = 0; i < size; i++) {
            int n = list[i];
            if ((less && target > n) || (equal && target == n)) {
                max = i;
            }
        }
        return max;
    }

    //=============================================================================================

    public static String md5(String input) {
        if (input == null) {
            return "";
        }
        return Hashing.md5().hashString(input, Charsets.UTF_8).toString();
    }

    public static String sha1(String input) {
        if (input == null) {
            return "";
        }
        return Hashing.sha1().hashString(input, Charsets.UTF_8).toString();
    }

    public static String sha256(String input) {
        if (input == null) {
            return "";
        }
        return Hashing.sha256().hashString(input, Charsets.UTF_8).toString();
    }

    /**
     * list数据的去重（用于背包下标这种唯一）
     *
     * @param bagIndex
     * @return
     */
    public static List<Integer> deleteRepeats(List<Integer> bagIndex) {
        List<Integer> result = new ArrayList<>(bagIndex.size());
        for (Integer index : bagIndex) {
            if (!result.contains(index)) {
                result.add(index);
            }
        }
        return result;
    }

    /**
     * 从集合中随机取得一个元素
     *
     * @param
     * @return
     */
    public static <E> E getRandomElement(Collection<E> collection) {
        if (collection == null) throw new IllegalArgumentException("collection==null");
        int rn = nextInt(collection.size());
        int i = 0;
        for (E e : collection) {
            if (i == rn) {
                return e;
            }
            i++;
        }
        return null;
    }

    public static <E> E getRandomElement(Collection<E> collection, Collection<E> filterCollection, Comparator<E> comparator) {
        Collection<E> collect = filterCollection(collection, filterCollection, comparator);
        return getRandomElement(collect);
    }

    public static <E> Set<E> getRandomElement(Collection<E> collection, Collection<E> filterCollection, Comparator<E> comparator, int num) {
        Set<E> set = new HashSet<>();
        Collection<E> collect = filterCollection(collection, filterCollection, comparator);
        Set<E> filterSet = new HashSet<>(collect);
        while (filterSet.size() > 0 && set.size() < num) {
            E e = getRandomElement(filterSet);
            filterSet.remove(e);
            set.add(e);
        }
        return set;
    }

    public static <E> Collection<E> filterCollection(Collection<E> collection, Collection<E> filterCollection, Comparator<E> comparator) {
        if (collection == null || filterCollection == null)
            throw new IllegalArgumentException("set==null||filterSet==null");
        Set<E> set = new HashSet<>(collection);
        Iterator<E> it = set.iterator();
        while (it.hasNext()) {
            E next = it.next();
            for (E e : filterCollection) {
                if (comparator.compare(next, e) == 0) {
                    it.remove();
                }
            }
        }
        return set;
    }

    /**
     * 通过元素值 得到在数组中的下标index
     * （若数组中有多个相同元素，则得到第一个）
     * @param array
     * @param value
     * @return
     */
    public static int getIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;//当if条件不成立时，默认返回一个负数值-1
    }

    /**
     * 通过下标得到元素
     * @param array
     * @param index
     * @return
     */
    public static int get(int[] array, int index) {
        return get(array, index, 0);
    }

    public static int get(int[] array, int index, int defaultValue) {
        if (array == null) {
            return defaultValue;
        }
        if (index < 0) {
            index += array.length;
        }
        if (index < 0 || index >= array.length) {
            return defaultValue;
        }
        return array[index];
    }

    public static <T> T get(T[] array, int index) {
        return get(array, index, null);
    }

    public static <T> T get(T[] array, int index, T defaultValue) {
        if (array == null) {
            return defaultValue;
        }
        if (index < 0) {
            index += array.length;
        }
        if (index < 0 || index >= array.length) {
            return defaultValue;
        }
        return array[index];
    }

    public static <T> T get(Collection<T> collection, int index) {
        return get(collection, index, null);
    }

    public static <T> T get(Collection<T> collection, int index, T defaultValue) {
        if (collection == null) {
            return defaultValue;
        }
        if (index < 0) {
            index += collection.size();
        }
        if (index < 0 || index >= collection.size()) {
            return defaultValue;
        }
        T t = Iterables.get(collection, index);
        return t == null ? defaultValue : t;
    }

    /**
     * 返回列表中第k小的元素, 并将列表按第k小元素划分
     *
     * @param list
     * @param comparator
     * @param k
     * @param <T>
     * @return
     */
    public static <T> T selectKth(List<T> list, Comparator<T> comparator, int k) {
        int index = selectKthIndex(list, comparator, k);
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    /**
     * 返回列表中第k小的元素的下标, 并将列表按第k小元素划分
     *
     * @param list
     * @param comparator
     * @param k
     * @param <T>
     * @return
     */
    public static <T> int selectKthIndex(List<T> list, Comparator<T> comparator, int k) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        return selectIndex(list, comparator, 0, list.size() - 1, k);
    }


    /**
     * 返回数组中第k小的元素, 并将数组按第k小元素划分
     *
     * @param array
     * @param comparator
     * @param k
     * @param <T>
     * @return
     */
    public static <T> T selectKth(T[] array, Comparator<T> comparator, int k) {
        int index = selectKthIndex(array, comparator, k);
        if (index >= 0 && index < array.length) {
            return array[index];
        }
        return null;
    }

    /**
     * 返回数组中第k小的元素的下标, 并将数组按第k小元素划分
     *
     * @param array
     * @param comparator
     * @param k
     * @param <T>
     * @return
     */
    public static <T> int selectKthIndex(T[] array, Comparator<T> comparator, int k) {
        if (array == null || array.length == 0) {
            return -1;
        }
        return selectIndex(array, comparator, 0, array.length - 1, k);
    }

    /**
     * 返回列表中m到n下标之间第i小元素, 并将m到n间元素按第i小元素划分
     *
     * @param list
     * @param comparator
     * @param m
     * @param n
     * @param i
     * @param <T>
     * @return
     */
    public static <T> T select(List<T> list, Comparator<T> comparator, int m, int n, int i) {
        int index = selectIndex(list, comparator, m, n, i);
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    /**
     * 返回列表m到n下标之间第i小元素的下标, 并将m到n间元素按第i大元素划分
     *
     * @param list
     * @param comparator
     * @param m
     * @param n
     * @param i
     * @param <T>
     * @return
     */
    public static <T> int selectIndex(List<T> list, Comparator<T> comparator, int m, int n, int i) {
        if (list == null || list.isEmpty() || comparator == null || n < m || n < 0 || m < 0) {
            return -1;
        }
        if (m == n) {
            return m;
        }
        int len = n - m + 1;
        if (i < 0) {
            i += len;
        }
        if (i < 0) {
            i = 0;
        } else if (i > len) {
            i = len;
        }
        int index = partition(list, comparator, m, n);
        int k = index - m + 1;
        if (k == i) {
            return index;
        } else if (k > i) {
            return selectIndex(list, comparator, m, index - 1, i);
        } else {
            return selectIndex(list, comparator, index + 1, n, i - k);
        }
    }

    private static <T> int partition(List<T> list, Comparator<T> comparator, int m, int n) {
        int r = random(m, n);
        T key = list.get(r);
        swap(list, r, n);
        int index = m;
        for (int i = index + 1; i < n; i++) {
            if (comparator.compare(list.get(i), key) < 0) {
                swap(list, i, index++);
            }
        }
        swap(list, index, n);
        return index;
    }

    private static <T> void swap(List<T> list, int m, int n) {
        T tmp = list.get(m);
        list.set(m, list.get(n));
        list.set(n, tmp);
    }

    /**
     * 返回数组中m到n下标之间第i小元素, 并将m到n间元素按第i小元素划分
     *
     * @param array
     * @param comparator
     * @param m
     * @param n
     * @param i
     * @param <T>
     * @return
     */
    public static <T> T select(T[] array, Comparator<T> comparator, int m, int n, int i) {
        int index = selectIndex(array, comparator, m, n, i);
        if (index >= 0 && index < array.length) {
            return array[index];
        }
        return null;
    }

    /**
     * 返回数组中m到n下标之间第i小元素的下标, 并将m到n间元素按第i小元素划分
     *
     * @param array
     * @param comparator
     * @param m
     * @param n
     * @param i
     * @param <T>
     * @return
     */
    public static <T> int selectIndex(T[] array, Comparator<T> comparator, int m, int n, int i) {
        if (array == null || array.length <= 0 || comparator == null || n < m || n < 0 || m < 0) {
            return -1;
        }
        if (m == n) {
            return m;
        }
        int len = n - m + 1;
        if (i < 0) {
            i += len;
        }
        if (i < 0) {
            i = 0;
        } else if (i > len) {
            i = len;
        }
        int index = partition(array, comparator, m, n);
        int k = index - m + 1;
        if (k == i) {
            return index;
        } else if (k > i) {
            return selectIndex(array, comparator, m, index - 1, i);
        } else {
            return selectIndex(array, comparator, index + 1, n, i - k);
        }
    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int m, int n) {
        int r = random(m, n);
        T key = array[r];
        swap(array, r, n);
        int index = m;
        for (int i = index + 1; i < n; i++) {
            if (comparator.compare(array[i], key) < 0) {
                swap(array, i, index++);
            }
        }
        swap(array, index, n);
        return index;
    }

    private static <T> void swap(T[] array, int m, int n) {
        T tmp = array[m];
        array[m] = array[n];
        array[n] = tmp;
    }

    public static String listToString(List<String> strings, char separator) {
        return StringUtils.join(strings, separator);
    }

    public static List<String> stringToList(String str, String separator) {
        return Arrays.asList(str.split(separator));
    }

    /**
     * 按照概率进行选择KEY[手写,调用请测试]
     *
     * @param map ConcurrentHashMap不可以存放NUll
     * @param <T> 要返回的值 Integer:概率 概率 分子/总数
     * @return
     */
    public static <T> T chanceSelect(ConcurrentHashMap<T, Integer> map) {
        ConcurrentHashMap<T, Integer> copyMap = new ConcurrentHashMap<>(map);
        int maxNum = 0;
        for (Map.Entry<T, Integer> entry : copyMap.entrySet()) {
            Integer value = entry.getValue();
            if (value <= 0) {
                throw new IllegalArgumentException("概率<=0,请调整数据");
            }
            maxNum += value;
        }
        if (maxNum <= 0) {
            throw new IllegalArgumentException("max<=0");
        }
        //范围 0~maxNum-1
        int radom = nextInt(maxNum);
        for (Map.Entry<T, Integer> entry : copyMap.entrySet()) {
            Integer value = entry.getValue();
            radom -= value;
            if (radom < 0) {
                T key = entry.getKey();
                return key;
            }
        }
        throw new IllegalArgumentException("未找到对应的值,请检查程序");
    }

    /**
     * 概率选择几个KEY[手写,调用请测试]
     *
     * @param map
     * @param num
     * @param <T>
     * @return
     */
    public static <T> Set<T> ChanceSelect(ConcurrentHashMap<T, Integer> map, int num) {
        ConcurrentHashMap<T, Integer> copyMap = new ConcurrentHashMap<>(map);
        boolean loop = true;
        Set<T> set = new HashSet<>();
        if (copyMap.size() <= num) {
            set = copyMap.keySet();
            loop = false;
        }
        while (loop) {
            //排除num<=0的情况
            if (set.size() >= num) {
                break;
            }
            T t = chanceSelect(copyMap);
            set.add(t);
            copyMap.remove(t);
        }
        return set;
    }

    /**
     * 概率选择几个数据并且过滤一些数据[手写,调用请测试]
     *
     * @param map
     * @param filterSet
     * @param num
     * @param <T>
     * @return
     */
    public static <T> Set<T> ChanceSelect(ConcurrentHashMap<T, Integer> map, Set<T> filterSet, int num) {
        ConcurrentHashMap<T, Integer> copyMap = new ConcurrentHashMap<>(map);
        Iterator<T> iterator = copyMap.keySet().iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (filterSet.contains(next)) {
                iterator.remove();
            }
        }
        boolean loop = true;
        Set<T> set = new HashSet<>();
        if (copyMap.size() <= num) {
            set = copyMap.keySet();
            loop = false;
        }
        while (loop) {
            //排除num<=0的情况
            if (set.size() >= num) {
                break;
            }
            T t = chanceSelect(copyMap);
            set.add(t);
            copyMap.remove(t);
        }
        return set;
    }

    /**
     * 解析int[] 为Map[手写,调用请测试] key=array[偶数] value=array[奇数]
     *
     * @param array
     * @return
     */
    public static Map<Integer, Integer> parseArrayToMap(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        if (array.length % 2 != 0) {
            throw new IllegalArgumentException("参数错误，数组长度不为偶数");
        }
        for (int i = 0; i < array.length; i += 2) {
            map.put(array[i], array[i + 1]);
        }
        return map;
    }

    /**
     * 解析long[] 为Map[手写,调用请测试] key=array[偶数] value=array[奇数]
     *
     * @param array
     * @return
     */
    public static Map<Long, Long> parseArrayToMap(long[] array) {
        Map<Long, Long> map = new HashMap<>();
        if (array.length % 2 != 0) {
            throw new IllegalArgumentException("参数错误，数组长度不为偶数");
        }
        for (int i = 0; i < array.length; i += 2) {
            map.put(array[i], array[i + 1]);
        }
        return map;
    }

    /**
     * 解析int[] 为Map[手写,调用请测试] key=array[偶数] value=array[奇数]
     *
     * @param array
     * @return
     */
    public static Set<Integer> parseArrayToSet(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (Integer e : array) {
            set.add(e);
        }
        return set;
    }

    public static <E> Set<E> parseArrayToSet(E[] array) {
        Set<E> set = new HashSet<>();
        Collections.addAll(set, array);
        return set;
    }

    /**
     * 得到位置[用时测试]
     *
     * @param collection
     * @param num
     * @param sort
     * @return
     */
    public static Integer getPos(Collection<Integer> collection, int num, boolean sort) {
        List<Integer> list = new ArrayList<>(collection);
        if (sort) {
            Collections.sort(list);
        }
        Integer pos = null;
        for (Integer value : list) {
            if (num >= value) {
                pos = value;
            } else {
                break;
            }
        }
        return pos;
    }

    /**
     * 删除位置list有序[用时测试]
     *
     * @param value
     * @param list
     */
    public static void deletePos(int value, List<Integer> list) {
        if (list == null || list.isEmpty()) return;
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            Integer next = iterator.next();
            if (next != value) {
                iterator.remove();
            } else {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * 得到位置[用时测试]
     *
     * @param collection
     * @param e
     * @param sort
     * @param comparator
     * @param <E>
     * @return
     */
    public static <E> E getPos(Collection<E> collection, E e, boolean sort, Comparator<E> comparator) {
        if (collection == null || collection.isEmpty()) return null;
        List<E> list = new ArrayList<>(collection);
        if (sort) {
            Collections.sort(list, comparator);
        }
        E pos = null;
        for (E value : list) {
            int compare = comparator.compare(e, value);
            if (compare >= 0) {
                pos = value;
            }
        }
        return pos;
    }

    /**
     * 删除位置[用时测试]
     *
     * @param collection
     * @param e
     * @param sort
     * @param comparator
     * @param <E>
     * @return
     */
    public static <E> boolean deletePos(Collection<E> collection, E e, boolean sort, Comparator<E> comparator) {
        if (collection == null || collection.isEmpty()) return false;
        List<E> list = new ArrayList<>(collection);
        if (sort) {
            Collections.sort(list, comparator);
        }
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (next != e) {
                iterator.remove();
            } else {
                iterator.remove();
                break;
            }
        }
        return true;
    }

    /**
     * 只适合2对应的物品数组
     *
     * @param array
     * @return
     */
    public static Map<Integer, Integer> arrayToBeMap(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        if (array == null) {
            return map;
        }
        int size = array.length / 2;
        for (int i = 0, j = 0; j < size; i += 2, j++) {
            map.put(array[i], array[i + 1]);
        }
        return map;
    }

    /* 随机生成字母加数字的密码
     * @param lengths 密码的位数
     * @param strBig : 字母是否大写
     * @return
     */
    public static String getStringRandom(int lengths, boolean strBig) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        //参数lengths，表示生成几位随机数
        for (int i = 0; i < lengths; i++) {
            String strOrNum = random.nextInt(2) % 2 == 0 ? "str" : "num";
            //随机输出是字母还是数字
            if ("str".equalsIgnoreCase(strOrNum)) {
                //随机输出是大写字母还是小写字母
                //int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                int temp = strBig ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else if ("num".equalsIgnoreCase(strOrNum)) {
                val.append(String.valueOf(random.nextInt(10)));
            }
        }

        return val.toString();
    }

    /**
     * 字符串数组中是否包含
     *
     * @param array
     * @param str
     * @return
     */
    public static boolean stringArrayContain(String[] array, String str) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public static String mapToString(Map<Integer, Integer> map, String init) {
        if (map == null || map.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(init);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            stringBuilder.append("&").append(entry.getKey()).append("|").append(entry.getValue());
        }
        return stringBuilder.toString();
    }

    public static boolean isNum(String str) {
//        Pattern pattern = Pattern.compile("^-?[0-9]+");
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
