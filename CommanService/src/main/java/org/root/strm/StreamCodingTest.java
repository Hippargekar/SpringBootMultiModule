package org.root.strm;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class StreamCodingTest {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//    public static void main(String[] args) {
//
//    }

//    public static void main(String[] args) {
//        Map<Boolean, List<Employee>> collect = getData().stream().collect(Collectors.partitioningBy(d -> d.getAge() >= 55));
//        collect.entrySet().stream().forEach(System.out::println);
//    }

//    public static void main(String[] args) {
//        Optional<Employee> max = getData().stream().max(Comparator.comparing(Employee::getAge));
//        System.out.println(max);
//        Optional<Employee> min = getData().stream().min(Comparator.comparing(Employee::getAge));
//        System.out.println(min);
//    }

//    public static void main(String[] args) {
//        String input = "Java articles are Awesome";
//        Character c = input.chars()
//                .mapToObj(ch -> (char) ch)
//                .peek(n -> System.out.println("Processing: " + n))
//                .filter(da -> input.indexOf(da) == input.lastIndexOf(da))
//                .findFirst()
//                .orElse(null);
//        System.out.println(c);
//    }

    public static void main(String[] args) {
        String str = "Hello World!";
        str.chars().forEach(System.out::println);
        char[] chars = str.toCharArray();
        System.out.println("Character Array:");
        for (char c : chars) {
            System.out.print(c + " ");
        }System.out.println();
        System.out.println(Arrays.toString(chars));
        chars[0] = 'G';
        String modifiedStr = new String(chars);
        System.out.println("\nModified String: " + modifiedStr);
        char ch = (char) 98;
        System.out.println(ch);
        int asciiValue = (int) '!';
        System.out.println(asciiValue);
    }

//    public static void main(String[] args) {
//        Map<Integer, Long> collect = getData().stream().collect(Collectors.groupingBy(Employee::getDeptId, Collectors.counting()));
////        collect.entrySet().stream().forEach(entry-> System.out.println(entry.getKey() +"--"+entry.getValue()));
//        long count = getData().stream().map(Employee::getDeptId).distinct().count();
//        System.out.println(count);
//    }

//        public static void main(String[] args) {
//            //Average age of Male and Female Employees
//            Map<String, Double> collect = getData().stream()
//                    .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));
//            System.out.println(collect);
//        }

//    public static void main(String[] args) {
//        Comparator<Employee> sortName = Comparator.comparing(Employee::getName);
//        Comparator<Employee> sortAge = Comparator.comparingInt(Employee::getAge);
//        Comparator<Employee> sortSalary = Comparator.comparingDouble(Employee::getSalary).reversed();
//
//        Map<String, List<Employee>> sortedEmployeeDesc = getData().stream()
//                .collect(Collectors.groupingBy(employee -> String.valueOf(employee.getDeptId()),
//                        Collectors.collectingAndThen(
//                                Collectors.toList(),
//                                list -> list.stream().sorted(sortSalary).collect(Collectors.toList())
//                        )
//                ));
//
//        sortedEmployeeDesc.entrySet().stream().forEach(entry-> System.out.println(entry));
//    }

    public static void mainV4(String[] args) {//Print the list of employees with the second highest salary in each department
        //Nth Highest salary. based on department wise
        int n = 2;
        Comparator<Employee> sortSalary = Comparator.comparingDouble(Employee::getSalary).reversed();
        getData().stream().collect(Collectors.groupingBy(Employee::getDeptId,
                Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.stream().sorted(sortSalary)
                                .skip(n-1).findFirst())
                )
        );
    }

//    public static void main(String[] args) {
//        Comparator<Employee> sortSalary = Comparator.comparingDouble(Employee::getSalary);
//       getData().stream().collect(Collectors.groupingBy(Employee::getDeptId, Collectors.collectingAndThen(
//                Collectors.toList(), list -> list.stream().max(sortSalary)
//        )));
//    }

    public static void mainV1(String[] args) {
        Map<Integer, Double> collect = getData().stream()
                .collect(Collectors.groupingBy(Employee::getDeptId,
                        Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(collect);
    }

    public static void mainV2(String[] args) {
         Map<Integer, List<String>> dat = getData().stream()
                .collect(Collectors.groupingBy(Employee::getDeptId,
                        Collectors.mapping(Employee::getName, Collectors.toList())));
         dat.entrySet().forEach(entry-> System.out.println(entry.getKey() +"|"+entry.getValue()));
    }

    public static void mainV6(String[] args) {
        Map<Integer,List<Employee>> data = getData().stream().collect(Collectors.groupingBy(Employee::getDeptId,
                Collectors.filtering(emp-> emp.getGender().equalsIgnoreCase("Male"), Collectors.toList())));
        data.entrySet().stream().forEach(entry-> System.out.println(entry.getKey()+"|"+entry.getValue()));
    }

    public static void mainV3(String[] args) {
        double sum = getData().stream().filter(emp->emp.getDeptId() == 1)
                .mapToDouble(Employee::getSalary).sum();
        System.out.println(sum);
    }

    public static void mainV5(String[] args) {
        getData().stream()
                .filter(dat-> dat.getDeptId() == 1)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .ifPresent(System.out::println);
    }

    public static void mainV8(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<String, Long> result = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.filtering(n -> n % 2 == 0, Collectors.counting()), // Count evens
                        Collectors.filtering(n -> n % 2 != 0, Collectors.counting()), // Count odds
                        (evenCount, oddCount) -> Map.of("Even Count", evenCount, "Odd Count", oddCount)
                ));

        System.out.println(result);
    }

    public static void mainV9(String[] args) {
        String value = "aabbzbccdeffcfzzzz";
        int [] arr = new int[256];// default value is zero.
        for(Character ch: value.toCharArray()){
            arr[ch] = arr[ch] + 1;
        }
        int max = arr[0];
        int idx = 0;
        for (int index = 0; index< arr.length; index ++){
            if(arr[index] > max ){
                max = arr[index];
                idx = index;
            }
        }
        System.out.println(max +" "+(char)idx);
    }

    public static List<Employee> getData() {
        List<Employee> employees = new ArrayList<>();

        Department dept1 = new Department(1, "HR");
        Department dept2 = new Department(2, "IT");
        Department dept3 = new Department(3, "Finance");
        Department dept4 = new Department(4, "Marketing");
        Department dept5 = new Department(5, "Sales");
        try{
            employees.add(new Employee(101, "John Doe", 55000.00, dept1.getId(), "Male", dateFormat.parse("2020-01-15"),20));
            employees.add(new Employee(102, "Jane Smith", 62000.00, dept2.getId(), "Female", dateFormat.parse("2021-03-20"), 29));
            employees.add(new Employee(103, "Alice Johnson", 47000.00, dept3.getId(), "Female", dateFormat.parse("2019-07-30"), 30));
            employees.add(new Employee(104, "Bob Brown", 50000.00, dept2.getId(), "Male", dateFormat.parse("2022-05-05"), 32));
            employees.add(new Employee(105, "Mary Davis", 60000.00, dept1.getId(), "Female", dateFormat.parse("2020-09-10"), 26));
            employees.add(new Employee(106, "Michael Wilson", 67000.00, dept4.getId(), "Male", dateFormat.parse("2021-06-15"), 27));
            employees.add(new Employee(107, "Linda Thomas", 53000.00, dept5.getId(), "Female", dateFormat.parse("2020-02-10"), 28));
            employees.add(new Employee(108, "James Lee", 58000.00, dept3.getId(), "Male", dateFormat.parse("2019-11-22"), 30));
            employees.add(new Employee(109, "Paul Harris", 45000.00, dept1.getId(), "Male", dateFormat.parse("2018-04-30"), 38));
            employees.add(new Employee(110, "Emma Clark", 66000.00, dept4.getId(), "Female", dateFormat.parse("2021-08-25"), 46));
            employees.add(new Employee(111, "Daniel Allen", 62000.00, dept2.getId(), "Male", dateFormat.parse("2020-12-01"), 22));
            employees.add(new Employee(112, "Sophia Wright", 53000.00, dept5.getId(), "Female", dateFormat.parse("2022-01-10"), 55));
            employees.add(new Employee(113, "David Martinez", 59000.00, dept3.getId(), "Male", dateFormat.parse("2018-09-15"),77));
            employees.add(new Employee(114, "Olivia Young", 71000.00, dept1.getId(), "Female", dateFormat.parse("2021-11-13"),66));
            employees.add(new Employee(115, "Liam White", 50000.00, dept2.getId(), "Male", dateFormat.parse("2020-04-18"),36));
            employees.add(new Employee(116, "Charlotte King", 65000.00, dept4.getId(), "Female", dateFormat.parse("2019-12-09"), 25));
            employees.add(new Employee(117, "Benjamin Scott", 48000.00, dept5.getId(), "Male", dateFormat.parse("2020-06-21"), 52));
            employees.add(new Employee(118, "Amelia Adams", 54000.00, dept3.getId(), "Female", dateFormat.parse("2019-08-11"), 50));
            employees.add(new Employee(119, "Ethan Baker", 70000.00, dept1.getId(), "Male", dateFormat.parse("2022-03-17"), 42));
            employees.add(new Employee(120, "Mason Evans", 48000.00, dept2.getId(), "Male", dateFormat.parse("2021-02-25"), 21));
            employees.add(new Employee(121, "Isabella Nelson", 51000.00, dept4.getId(), "Female", dateFormat.parse("2020-07-14"), 55));
            employees.add(new Employee(122, "Lucas Carter", 60000.00, dept5.getId(), "Male", dateFormat.parse("2022-04-22"), 33));
            employees.add(new Employee(123, "Mia Mitchell", 54000.00, dept3.getId(), "Female", dateFormat.parse("2021-10-10"), 44));
            employees.add(new Employee(124, "Henry Perez", 72000.00, dept1.getId(), "Male", dateFormat.parse("2019-02-28"), 36));
            employees.add(new Employee(125, "Zoe Roberts", 66000.00, dept2.getId(), "Female", dateFormat.parse("2020-03-01"),40));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employees;
    }
}
