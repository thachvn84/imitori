package imitori.controller;

import java.util.Date;
import java.util.List;

import imitori.mongodb.entity.ENWordEntity;
import imitori.mongodb.entity.Employee;
import imitori.mongodb.entity.JAWordEntity;
import imitori.mongodb.repository.JAWordRepository;
import imitori.mongodb.repository.ENWordCrudRepository;
import imitori.mongodb.repository.ENWordRepository;
import imitori.mongodb.repository.EmployeeRepository;
import imitori.mongodb.repository.EmployeeRepositoryCustom;
import imitori.mongodb.repository.JAWordCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sub/")
public class SubMainController {

    private static final String[] NAMES = { "Tom", "Jerry", "Donald" };

    @Autowired
    private EmployeeRepositoryCustom employeeRepositoryCustom;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    JAWordRepository jaWordRepository;

    @Autowired
    JAWordCrudRepository jaWordCrudRepository;

    @Autowired
    ENWordRepository enWordRepository;

    @Autowired
    ENWordCrudRepository enWordCrudRepository;

    static int id;

    @ResponseBody
    @RequestMapping("/")
    public String home() {

        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAllEmployee'>Show All Employee</a></li>";
        html += " <li><a href='/showFullNameLikeTom'>Show All 'Tom'</a></li>";
        html += " <li><a href='/deleteAllEmployee'>Delete All Employee</a></li>";
        html += "</ul>";
        // JAWordRepository.insertWord(id++);
        // html += jaWordRepository.getWordCount();
        return html;
    }

    @PostMapping("/add")
    public String add(@RequestBody JAWordEntity word) {
        String html = "OK";
        long id = this.jaWordRepository.getMaxWordId() + 1;
        word.setid(id);
        this.jaWordCrudRepository.insert(word);
        return html;
    }

    @PostMapping("/addEn")
    public String add(@RequestBody ENWordEntity word) {
        String html = "OK";
        long id = this.enWordRepository.getMaxWordId() + 1;
        word.setid(id);
        this.enWordCrudRepository.insert(word);
        return html;
    }

    @ResponseBody
    @RequestMapping("/testInsert")
    public String testInsert() {
        Employee employee = new Employee();

        long id = this.employeeRepositoryCustom.getMaxEmpId() + 1;
        int idx = (int) (id % NAMES.length);
        String fullName = NAMES[idx] + " " + id;

        employee.setId(id);
        employee.setEmpNo("E" + id);
        employee.setFullName(fullName);
        employee.setHireDate(new Date());
        this.employeeRepository.insert(employee);

        return "Inserted: " + employee;
    }

    @ResponseBody
    @RequestMapping("/showAllEmployee")
    public String showAllEmployee() {

        List<Employee> employees = this.employeeRepository.findAll();

        String html = "";
        for (Employee emp : employees) {
            html += emp + "<br>";
        }

        return html;
    }

    @ResponseBody
    @RequestMapping("/showFullNameLikeTom")
    public String showFullNameLikeTom() {

        List<Employee> employees = this.employeeRepository.findByFullNameLike("Tom");

        String html = "";
        for (Employee emp : employees) {
            html += emp + "<br>";
        }

        return html;
    }

    @ResponseBody
    @RequestMapping("/deleteAllEmployee")
    public String deleteAllEmployee() {

        this.employeeRepository.deleteAll();
        return "Deleted!";
    }

}