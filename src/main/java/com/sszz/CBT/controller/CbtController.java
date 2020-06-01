package com.sszz.CBT.controller;

import com.sszz.CBT.domain.LoginVO;
import com.sszz.CBT.domain.WrittenTestVO;
import com.sszz.CBT.service.CbtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//스프링이 com.sszz.CBT 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것은 아니구요.
//특정 어노테이션이 붙어있는 클래스 파일들을 new해서(IOC) 스프링 컨테이너에 관리해줍니다.
@Controller
public class CbtController {

    @Autowired
    CbtService cbtService;


    /*@GetMapping("/main")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }*/

    //login
    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute LoginVO loginVO, HttpServletRequest request) throws  Exception{
        return new ModelAndView("login");
    }

    //login 후
    @RequestMapping("/login.do")
    public ModelAndView doLogin(@Valid LoginVO loginVO, Model model, BindingResult result,
                                RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("---------------------> login!!!!!!!!!!!");
        System.out.println("---------------------> " + loginVO.getEmail());
        System.out.println("---------------------> " + loginVO.getPwd());
        System.out.println("---------------------> login!!!!!!!!!!!");

        if(cbtService.checkLogin(loginVO.getEmail(),loginVO.getPwd())){
            model.addAttribute("allSubject", cbtService.getAllSubject());
            model.addAttribute("allHoecha", cbtService.getAllHoecha());

            return new ModelAndView("cbtChoice");
        }

        return new ModelAndView("login");
    }

    //모든 문제 조회
    //@RequestMapping(value = "cbtPlay", method = RequestMethod.GET)
    @GetMapping("/cbtPlay")
    public String cbtPlay(Model model, String condition){
        //List<WrittenTestVO> WrittenTests = cbtService.findAll();
        //model.addAttribute("model",WrittenTests);

        System.out.println("---------------------> cbtPlay!!!!!!!!!!!"+condition);
        System.out.println("---------------------> cbtPlay!!!!!!!!!!!"+condition.charAt(0));

        //조건에 맞게 찾아야G
        if(condition.charAt(0) == 's' ){ //과목
            System.out.println(cbtService.getSCondiQuestion(condition).toString());
            model.addAttribute("messages",cbtService.getSCondiQuestion(condition));
        }else if(condition.charAt(0) == 'h'){ //회차
            System.out.println(cbtService.getHCondiQuestion(condition).toString());
            model.addAttribute("messages",cbtService.getHCondiQuestion(condition));
        }

        //model.addAttribute("messages", cbtService.getAllQuestion());
        return "cbtPlay";
    }


}
