import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// first we create abstract class
abstract  class Employee{
    private String name;
    private int id;

    // her we create constructor
    public  Employee(String name, int id){
        this.name = name;
        this.id = id;
    }

    // we create name of get for
    public  String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public abstract double calculateSalary();
    @Override
    public  String toString(){
       // return "Employee[name ="+name+", id="+id+" ,salary="+calculateSalary()+"]";
        return String.format("👤 Employee [Name: %-10s | ID: %4d | 💰 Salary: ₹%.2f]",
                name, id, calculateSalary());
    }
}

// Full-Time Employee
 class FullTimeEmployee extends Employee{
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary){
        super(name,id); // used super of parent class which is present in constructor
        this.monthlySalary=monthlySalary;
        // name and id is already in constructor
    }

    @Override
    public double  calculateSalary(){
        return monthlySalary;
    }
}
// Part-Time Employee
class  PartTimeEmployee extends Employee{
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name , int id , int hoursWorked, double hourlyRate){
        super(name,id);
        this.hoursWorked=hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary(){
        return hoursWorked*hourlyRate;
    }
}

// Intern
class Intern extends Employee{
    private double stipend;

    public Intern(String name, int id, double stipend){
        super(name,id);
        this.stipend=stipend;
    }
    @Override
    public double calculateSalary(){
        return stipend;
    }
}

// payroll system
class PayrollSystem {
   // private ArrayList<Employee>employeeList;
    // used hashmap for faster lookup and where key is employeeId.
    private Map<Integer,Employee>employeeMap;
    public PayrollSystem(){
        employeeMap = new HashMap<>();

    }

    public void addEmployee(Employee employee){
        employeeMap.put(employee.getId(),employee);
        System.out.println("✅ Added:"+employee.getName());
    }
    public  void removeEmployee(int id) {
        if (employeeMap.containsKey(id)) {
            String name = employeeMap.get(id).getName();
            employeeMap.remove(id);
            System.out.println("❌ Removed: " + name + " (ID: " + id + ")");
        } else {
            System.out.println("⚠️ No employee found with ID: " + id);
        }
    }

    public void displayEmployees(){
        System.out.println("\n📋 Current Employee List:");
        if (employeeMap.isEmpty()) {
            System.out.println("🚫 No employees in the system.");
        }
        else {
            for (Employee emp:employeeMap.values()){
                System.out.println(emp);
            }
        }
    }
}

// Main Class
public class Main {
    public static void main(String[]arg){
   PayrollSystem payrollSystem = new PayrollSystem();

   // Adding employees
   FullTimeEmployee emp1= new FullTimeEmployee("Deepak",9713,73000);
   payrollSystem.addEmployee(new FullTimeEmployee("Adarsh",9734,56000));
   payrollSystem.addEmployee(new FullTimeEmployee("Rohan",9711,78000));


   PartTimeEmployee emp2 = new PartTimeEmployee("Ankit",9725,40,100);
   payrollSystem.addEmployee(new PartTimeEmployee("Vikash",9734,89,200));
   payrollSystem.addEmployee(new PartTimeEmployee("gulshan",9844,74,150));


   Intern emp3 = new Intern("Sudhansu",9735,8000);
   payrollSystem.addEmployee(new Intern("Abhishek",9740,12000));
   payrollSystem.addEmployee(new Intern("Aditya",9720,11000));

   payrollSystem.addEmployee(emp1);
   payrollSystem.addEmployee(emp2);
   payrollSystem.addEmployee(emp3);

   //Display initial list
 //  System.out.println("Initial Employee Details: ");
   payrollSystem.displayEmployees();

   // Removing employee
        System.out.println("\n🗑️ Removing Employee with ID: 9725");
        payrollSystem.removeEmployee(9725);

        // Display updated list
   payrollSystem.displayEmployees();


    }
}