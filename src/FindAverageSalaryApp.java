import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;

public class FindAverageSalaryApp {
    /*
    Grouping list of employees by officeLocation and then by designation.
    Then finding average salary using averagingDouble stream method.
    Using GroupingByConcurrent method of stream to process parallel.
     */
    public void findAverageSalary(List<Employee> employees) {

        Map<String, ConcurrentMap<String, Double>> emplAvgSalaryByOfficeLocAndDesgn =
                employees.stream().collect(Collectors.groupingBy(
                        Employee::getOfficeLocation, Collectors.groupingByConcurrent(Employee::getDesignation, averagingDouble(Employee::getSalary))));

        for (Map.Entry<String, ConcurrentMap<String, Double>> entry : emplAvgSalaryByOfficeLocAndDesgn.entrySet()) {
            for (ConcurrentMap.Entry<String, Double> innerEntry : entry.getValue().entrySet()) {
                System.out.println(entry.getKey() + " --> " + innerEntry.getKey() + " --> " + innerEntry.getValue());
            }
        }
    }
}


