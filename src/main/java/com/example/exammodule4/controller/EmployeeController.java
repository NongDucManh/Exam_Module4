package com.example.exammodule4.controller;

import com.example.exammodule4.model.Department;
import com.example.exammodule4.model.Employee;
import com.example.exammodule4.service.IDepartmentService;
import com.example.exammodule4.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private IEmployeeService iEmployeeService;
    @ModelAttribute(name = "department")
    public Iterable<Department> findAdd() {
        return iDepartmentService.findAll();
    }

    @GetMapping
    public ModelAndView showAll(@PageableDefault(value = 5) Pageable pageable,
                                @RequestParam Optional<String> search) {
        ModelAndView modelAndView = new ModelAndView("employee/list");
        Page<Employee> employees;
        if (search.isPresent()) {
            employees = iEmployeeService.findAllByName(pageable, search.get());
            modelAndView.addObject("search", search.get());
        } else {
            employees = iEmployeeService.findAll(pageable);

        }
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }
    @GetMapping("sort-by-age")
    public ModelAndView sortByAge(@PageableDefault(value = 5) Pageable pageable,
                                  @RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("employee/listsorted");
        Page<Employee> employees;
        employees = iEmployeeService.findAllAndSort(pageable, search);
        modelAndView.addObject("search", search);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("employee/create");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView creatEmployee(@Valid @ModelAttribute("employee") Employee employee,
                                       BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("employee/create");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("employee", employee);
            return modelAndView;
        }
        iEmployeeService.save(employee);
        modelAndView.addObject("status", true);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        Optional<Employee> employee = iEmployeeService.findById(id);
        employee.ifPresent(value -> modelAndView.addObject("employee", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editEmployee(@Valid @ModelAttribute("employee") Employee employee,
                                     BindingResult bindingResult,
                                     @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("employee", employee);
            return modelAndView;
        }
        employee.setId(id);
        iEmployeeService.save(employee);
        modelAndView.addObject("statusEdit", true);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        iEmployeeService.delete(id);

        return "redirect:/employees";
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/detail");
        Optional<Employee> employee = iEmployeeService.findById(id);
        employee.ifPresent(value -> modelAndView.addObject("employee", value));
        return modelAndView;
    }

}
