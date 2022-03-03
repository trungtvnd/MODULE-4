package cg.controllers;

import cg.model.ClassRoom;
import cg.model.Student;
import cg.service.IClassRoomService;
import cg.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassRoomService classRoomService;

    private List<ClassRoom> classRoomList(){
        return classRoomService.findAll();
    }

    @GetMapping
    public ModelAndView display(@PageableDefault(3)Pageable pageable, @PathVariable("search")Optional<String> search){
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Student> students;
        if(search.isPresent()){
            students = studentService.findAllByName(search.get(), pageable);
            modelAndView.addObject("students", students);
        }else {
            students = studentService.findAll(pageable);
        }
        if ((students.isEmpty())){
            modelAndView.addObject("message", "Data not Found");
        }
        modelAndView.addObject("students", students);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create");
        List<ClassRoom> classRoomList = classRoomList();
        modelAndView.addObject("classRoomList", classRoomList);
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("create");
        if (bindingResult.hasErrors()){
            modelAndView = new ModelAndView("create");
            return modelAndView;
        }
        studentService.save(student);
        modelAndView.addObject("classRoomList", classRoomList());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Student student = studentService.findOne(id);
        modelAndView.addObject("student", student);
        modelAndView.addObject("classRoomList", classRoomList());
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit( @Valid Student student , BindingResult bindingResult, @PathVariable("id") int id ){
        ModelAndView modelAndView = new ModelAndView("edit");
        student.setId(id);
        if(bindingResult.hasErrors()){
            modelAndView = new ModelAndView("edit");
            return modelAndView;
        }
        studentService.save(student);
        modelAndView.addObject("classRoomList", classRoomList());
        return modelAndView;

    }


}
