package com.hellokoding.jpa;

import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;

    String name;

    @OneToMany(fetch = LAZY, mappedBy = "company", cascade = ALL)
    @Builder.Default Set<Employee> employees = new HashSet<>();

    @OneToMany(fetch = LAZY, mappedBy = "company", cascade = ALL)
    @Builder.Default Set<CompanyOwner> companyOwners = new HashSet<>();

    @OneToMany(fetch = LAZY, mappedBy = "company", cascade = ALL)
    @Builder.Default Set<Department> departments = new HashSet<>();

    @OneToOne(fetch = LAZY, cascade = PERSIST) Address address;
}

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;

    String address;

    @OneToOne(mappedBy = "address", fetch = EAGER) Company company;
}

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;

    String name;

    @ManyToOne Company company;
}

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class Owner {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;

    String name;

    @OneToMany(mappedBy = "owner", cascade = ALL)
    @Builder.Default Set<CompanyOwner> companyOwners = new HashSet<>();
}

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class CompanyOwner implements Serializable {
    @Id @ManyToOne @JoinColumn Company company;

    @Id @ManyToOne @JoinColumn Owner owner;

    int noOfStocks;
}

@Value
@Builder @AllArgsConstructor
class CompanyIdName {
    Integer id;
    String name;
    List<EmployeeIdName> employees;

    static CompanyIdName of(Company company) {
        return CompanyIdName.builder()
            .id(company.id)
            .name(company.name)
            .employees(company.employees.stream()
                .map(EmployeeIdName::of)
                .collect(Collectors.toList()))
            .build();
    }
}

@Builder @AllArgsConstructor
class EmployeeIdName {
    Integer id;
    String name;

    static EmployeeIdName of(Employee employee) {
        return EmployeeIdName.builder()
            .id(employee.id)
            .name(employee.name)
            .build();
    }
}

interface CompanyProjection {
    int getId();
    String getName();
    List<EmployeeProjection> getEmployees();
}

interface EmployeeProjection {
    int getId();
    String getName();
}

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;

    String name;

    @ManyToOne Company company;
}

interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findFirst10ByOrderByNameAsc();

    @EntityGraph(attributePaths = "employees")
    List<Company> findFirst10ByOrderByNameDesc();

    @Query(value="FROM Company c LEFT JOIN FETCH c.employees")
    List<Company> findWithEmployeesAndJoinFetch(Pageable pageable);

    @EntityGraph(attributePaths = "employees")
    List<CompanyProjection> findFirst10ByOrderByIdDesc();

    @EntityGraph(attributePaths = "employees")
    List<CompanyIdName> findFirst10ByOrderByIdAsc();
}

interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.company.id = ?1")
    void deleteInBulkByCompanyId(int companyId);

    @Transactional
    void deleteByCompanyId(int categoryId);
}

interface OwnerRepository extends JpaRepository<Owner, Integer> {
}

@Service
@RequiredArgsConstructor
class CompanyService {
    final CompanyRepository companyRepository;
    final OwnerRepository ownerRepository;

    public void create() {
        Stream<Company> companies  = IntStream.range(1, 11).mapToObj(i -> {
            Company c = Company.builder().name("Company " + i).build();

            IntStream.range(1, 3).forEach(j -> {
                Employee q = Employee.builder().name(String.format("Employee %d.%d", i, j))
                    .company(c).build();
                c.employees.add(q);
            });

            IntStream.range(1, 3).forEach(j -> {
                Department d = Department.builder().name(String.format("Department %d.%d", i, j))
                    .company(c).build();
                c.departments.add(d);
            });

            IntStream.range(1, 3).forEach(j -> {
                Owner o = Owner.builder().name(String.format("Owner %d.%d", i, j)).build();
                ownerRepository.save(o);

                CompanyOwner co = CompanyOwner.builder().owner(o).company(c).noOfStocks(i*100).build();
                o.companyOwners.add(co);
                c.companyOwners.add(co);
            });

            c.address = Address.builder().address("Address " + i).company(c).build();

            return c;
        });

        companyRepository.saveAll(companies.collect(Collectors.toList()));
    }

    public List<String> findNames() {
        return companyRepository.findFirst10ByOrderByNameAsc().stream()
            .map(c -> c.name).collect(Collectors.toList());
    }

    @Transactional
    public List<CompanyIdName> findCompanyIdNames() {
        return companyRepository.findFirst10ByOrderByNameAsc().stream()
            .map(CompanyIdName::of)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<CompanyProjection> findCompanyIdNamesWithDTO() {
        return companyRepository.findFirst10ByOrderByIdDesc();
    }
}

@Service
@RequiredArgsConstructor
class EmployeeService {
    final EmployeeRepository employeeRepository;

    public void delete() {
        employeeRepository.deleteByCompanyId(1);
    }
}

@SpringBootApplication @RequiredArgsConstructor
public class CompanyApplication implements CommandLineRunner {
    private final CompanyService companyService;
    private final EmployeeService employeeService;

    public void run(String... args) {
        companyService.create();
        //companyService.findNames().forEach(System.out::println);
        //companyService.findCompanyIdNamesWithDTO();
//        companyService.findCompanyIdNamesWithDTO().forEach(c -> {
//            System.out.println(c.getName());
//            c.getEmployees().forEach(e -> {
//                System.out.println(e.getName());
//            });
//        });
        employeeService.delete();
    }

    public static void main(String[] args) {
        SpringApplication.run(QuoteApplication.class, args);
    }
}
