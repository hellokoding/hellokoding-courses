package com.hellokoding.java.collections;

import java.time.LocalDate;
import java.util.*;

public class ArrayListSortExample {
    public static void main(String[] args) {
        List<Integer> arrayList1 = new ArrayList<>(List.of(3, 1, 2));

        // sort an ArrayList of numbers in ascending order with Collections.sort()
        Collections.sort(arrayList1);
        System.out.println(arrayList1); // [1, 2, 3]

        // sort an ArrayList of numbers in descending order with Collections.sort()
        Collections.sort(arrayList1, Comparator.reverseOrder());
        System.out.println(arrayList1); // [3, 2, 1]

        List<String> arrayList2 = new ArrayList<>(List.of("c", "a", "b"));

        // sort an ArrayList of Strings in alphabetical order with sort()
        arrayList2.sort(Comparator.naturalOrder());
        System.out.println(arrayList2); // [a, b, c]

        // sort an ArrayList of Strings in reverse alphabetical order with sort()
        arrayList2.sort(Comparator.reverseOrder());
        System.out.println(arrayList2); // [c, b, a]

        LocalDate ld1 = LocalDate.of(2019, 3, 1);
        LocalDate ld2 = LocalDate.of(2019, 1, 1);
        LocalDate ld3 = LocalDate.of(2019, 2, 1);
        List<LocalDate> arrayList3 = new ArrayList<>(List.of(ld1, ld2, ld3));

        // sort an ArrayList of Dates in chronological order
        arrayList3.sort(Comparator.naturalOrder());
        System.out.println(arrayList3); // [2019-01-01, 2019-02-01, 2019-03-01]

        // sort an ArrayList of Dates in reverse chronological order
        arrayList3.sort(Comparator.reverseOrder());
        System.out.println(arrayList3); // [2019-03-01, 2019-02-01, 2019-01-01]
    }

    static class Employee implements Comparable<Employee> {
        private final Integer id;
        private final String name;

        public Employee(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public int compareTo(Employee o) {
            return Comparator.comparing(Employee::getName)
                .compare(this, o);
        }
    }
}
