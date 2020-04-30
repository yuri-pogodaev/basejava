package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainLambda {
    public static void main(String[] args) {
        int[] ints = {3, 5, 8, 1, 2, 3, 8};
        System.out.println(minValue(ints));
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        oddOrEven(list).forEach(i -> System.out.println(i + " "));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)//создаем стрим из массива
                .distinct()//возвращаем стрим без дубликатов
                .sorted()//сортируем в в натуральном порядке
                .reduce((x, y) -> x * 10 + y).orElse(0);//склеиваем значения
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers
                .stream() //создаем стрим
                .collect(Collectors.partitioningBy(i -> i % 2 == 0 )); // разбиваем числа по условию четности и нечетности
        return map.get(map.get(false).size() % 2 != 0);
    }
}
