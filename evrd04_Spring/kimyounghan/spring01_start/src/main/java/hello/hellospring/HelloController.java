package hello.hellospring;

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

    @GetMapping("hello-mvc")    // localhost:9001/hello-mvc?namee=hi
    public String helloMvc(@RequestParam("namee") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";    // hello hi
        // hello2 null ->model에서 넣어주는 값이 없기때문에 ${namee}는 null로 나옴
    }


    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("bodyJSON") String nameStr) {
        Hello hello = new Hello();
        hello.setName(nameStr);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
