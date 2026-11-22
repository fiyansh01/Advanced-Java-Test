package Test1_CoreJava;
// Q1

import java.util.*;

class Employee{
    int id ;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    String name;
    String department;
    double salary;

    public String toString(){
        return id + "|" + name + "|" + department + "|" + salary;
    }
}

public class Q1 {
    static void main() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(101, "Romil", "IT", 85000));
        list.add(new Employee(102, "Priya", "HR", 55000));
        list.add(new Employee(103, "Gaurav", "IT", 95000));
        list.add(new Employee(104, "Vansh", "Finance", 72000));
        list.add(new Employee(105, "Fiyansh", "Sales", 60000));
        list.add(new Employee(106, "Pahel", "IT", 85000));
        list.add(new Employee(103, "Kanak", "IT", 95000));      // Duplicate ID
        list.add(new Employee(107, "Dhruv", "Marketing", 60000));
        list.add(new Employee(108, "Shubh", "Finance", 72000));
        list.add(new Employee(109, "Rhythm", "IT", 110000));
        list.add(new Employee(107, "Kanishka", "Marketing", 60000)); // Duplicate ID
        list.add(new Employee(110, "Chitraksh", "HR", 55000));
        list.add(new Employee(111, "Shourya", "Sales", 68000));
        list.add(new Employee(112, "Divya", "Product", 95000));
        list.add(new Employee(113, "Harshini", "IT", 78000));

        // 1, 2 & 3: Find duplicates & remove duplicates (insertion order maintain)
        /*
        1.) to maintain insertion order and to tackle unique values we will use linkedHashMap.
        2.) every time when the loop runs, it will check if ID is already entered or not.
        If not then its unique and if yes then its dupliicate
         */
        Map<Integer, Employee> uniqueMap = new LinkedHashMap<>();
        List<Employee> duplicates = new ArrayList<>();

        for(Employee e : list){
            if(uniqueMap.containsKey(e.id)){
                duplicates.add(e);
            }else{
                uniqueMap.put(e.id, e);
            }
        }
        System.out.println("Duplicate Employees: ");
        for(Employee d : duplicates){
            System.out.println(d);
        }
        // Unique employees List
        List<Employee> uniqueList = new ArrayList<>(uniqueMap.values());
        System.out.println("Unique Employees with insertion order maintained: ");
        for(Employee u : uniqueList){
            System.out.println(u);
        }
        // Second Highest Distinct salary
        /*
        so for this we will create a Arraylist to check only unique salaries must be added
        then we will sort salary in descending order and assume index 0 as Highest salary
        and index 1 as second Highest distinct salary
        Also what if there is no second highest salary? we have to tackle that edge case also...

         */
        List<Double> salaries = new ArrayList<>();
        for(Employee u : uniqueList){
            if(!salaries.contains(u.salary)){
                salaries.add(u.salary);
            }
        }
        Collections.sort(salaries, Collections.reverseOrder());
        System.out.println("Second Highest Distinct Salary: ");
        if(salaries.size() >= 2){
            System.out.println("Second Highest Salary:" + salaries.get(1));
        }

    }
}
