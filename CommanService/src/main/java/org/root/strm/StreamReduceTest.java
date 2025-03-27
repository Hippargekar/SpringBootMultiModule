package org.root.strm;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamReduceTest {
    public static void main(String[] args) {
        String message = "programmingg";// message.chars().forEach(System.out::println);
        Stream<Character> characterStream = message.chars().mapToObj(d -> (char) d);

        //characterStream.forEach(System.out::println);
        Map<Character, Integer> result =  characterStream.reduce(new HashMap<Character, Integer>(), (map, element)-> {
            map.put(element, map.getOrDefault(element, 0)+1);
            return map;
        }, (map1, map2)->{
            map1.putAll(map2);
            return map1;
        });

        //result.forEach((k, v) -> System.out.println(k + " : " + v));
        //result.entrySet().stream().forEach(entry-> System.out.println(entry.getKey() +"-"+entry.getValue()));

        Comparator<Map.Entry<Character, Integer>> valueAsc = (value1, value2)-> value1.getValue().compareTo(value2.getValue());
        Comparator<Map.Entry<Character, Integer>>  keyAsc = (key1, key2)-> key1.getKey().compareTo(key2.getKey());
        result.entrySet().stream()
                .sorted(keyAsc)
                //.sorted(Map.Entry.comparingByKey())
                .forEach(entry-> System.out.println(entry.getKey() +"-"+entry.getValue()));


        List<String> strings = Arrays.asList("apple", "banana", "pear", "peach", "plum", "apple");
        Map<Integer, String> notEmptyMap = new LinkedHashMap<>();
        notEmptyMap.put(100, "data");

        Map<Integer, String> map = strings.stream()
                .collect(Collectors.toMap(
                        String::length,
                        Function.identity(),
                        (oldVal, newVal) -> oldVal +", "+newVal, // avoid duplicate
                        () -> notEmptyMap//If you want to return any child class of Map, such as a HashMap::new, LinkedHashMap::new, or TreeMap::new,
                ));

        System.out.println(map);



    }
}
