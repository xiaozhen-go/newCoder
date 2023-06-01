package com.community.controller;


import com.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames(); //得到所有请求行的key
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name +":"+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter()){ //java7以后 写在try小括号里会自动close

            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //GET请求

    // /students?current=1&limit=20
        @RequestMapping(path = "/student", method = RequestMethod.GET)
        @ResponseBody
        public String getStudents(@RequestParam(name = "current", required = false, defaultValue = "1") int current,
                                  @RequestParam(name = "limit", required = false, defaultValue = "1") int limit){
            System.out.println(current);
            System.out.println(limit);

        return "some students";
    }



    // /student/123 Restful风格
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){


        System.out.println(id);

        return "a student";
    }

    //Post请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){ //参数的名字与表单中的名字一致即可传过来

        System.out.println(name);
        System.out.println(age);

        return "success";
    }


    //响应html数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getteacher(){ //返回model与view
        ModelAndView mov = new ModelAndView();
        mov.addObject("name","张三");
        mov.addObject("age",30);
        mov.setViewName("/demo/view"); //将数据放在视图中

        return mov;

    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){

        model.addAttribute("name","北京大学");
        model.addAttribute("age","80");

        return "/demo/view";
    }


   //响应JSON数据(异步请求)
    //java对象 --> json字符串 --> js对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.0);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object> >getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.0);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 38);
        emp.put("salary", 10000.0);
        list.add(emp);
        return list;
    }
}
