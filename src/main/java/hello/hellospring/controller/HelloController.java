package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // 외부에서 파라미터를 받을 수 있도록 함. 파라미터를 받을 때는 @RequestParam 어노테이션을 사용하여, 요청에서 전달된 값을 메서드에 매핑할 수 있음.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name); // 파라미터로 넘어온 name을 넘김.
        return "hello-template"; // hello-template.html으로 감.
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체 생성.
        hello.setName(name); // 파라미터로 넘어온 네임을 넣음.
        return hello; // 문자가 아닌 객체를 넘김.
    }

    // Hello 라는 객체 하나를 만듦.
    static class Hello { // static class로 만들면 HelloController 이 클래스 안에서 클래스를 또 쓸 수 있음.
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
