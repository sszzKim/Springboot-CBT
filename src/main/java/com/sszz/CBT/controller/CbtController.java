package com.sszz.CBT.controller;

import com.sszz.CBT.domain.CbtHistVO;
import com.sszz.CBT.domain.LoginVO;
import com.sszz.CBT.domain.QuesDabVO;
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
import java.util.ArrayList;
import java.util.List;

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
        /*System.out.println("---------------------> login!!!!!!!!!!!");
        System.out.println("---------------------> " + loginVO.getEmail());
        System.out.println("---------------------> " + loginVO.getPwd());
        System.out.println("---------------------> login!!!!!!!!!!!");*/

        if(cbtService.checkLogin(loginVO.getEmail(),loginVO.getPwd())){
            model.addAttribute("allSubject", cbtService.getAllSubject());
            model.addAttribute("allHoecha", cbtService.getAllHoecha());

            return new ModelAndView("cbtChoice");
        }

        return new ModelAndView("login");
    }

    //조건에 맞는 문제 조회
    //@RequestMapping(value = "cbtPlay", method = RequestMethod.GET)
    @GetMapping("/cbtPlay")
    public String cbtPlay( Model model, String condition ){

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


        CbtHistVO cbtHistVO = new CbtHistVO();
        model.addAttribute("cbtHistVO", cbtHistVO);

        //List<QuesDabVO> quesDabVOs = ;
        //model.addAttribute("quesDabVOs", quesDabVOs);

        return "cbtPlay";
    }

    //login 후
    //@RequestMapping("/scoring.do")
    @PostMapping("/scoring.do")
    public ModelAndView doScoring(@ModelAttribute ("cbtHistVO") @Valid CbtHistVO cbtHistVO, Model model, BindingResult result,
                                RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception {


        //CbtHistVO cbtHistVO =   new CbtHistVO();
        //cbtHistVO.setEmail("suji20th@naver.com");

        cbtHistVO.setCreateDate(new java.text.SimpleDateFormat("yyyyMMdd").parse("20200609"));

        int count =0;

        System.out.println("이메일:" + cbtHistVO.getEmail());
        System.out.println("날짜 :" + cbtHistVO.getCreateDate());
        for( QuesDabVO quesDabVO :  cbtHistVO.getQuesDabVOs() ){
            System.out.println("count :" + ++count);
            System.out.println("quesDabVO.getQuesDabId() :" + quesDabVO.getQuesDabId());
            System.out.println("quesDabVO.getQuestionId() :" + quesDabVO.getQuestionId());
            System.out.println("quesDabVO.getDap() :" + quesDabVO.getDap());
            System.out.println("quesDabVO.getCbtHistId() :" + quesDabVO.getCbtHistId());
        }

        //cbtService.scoringSave(cbtHistVO);

        return new ModelAndView("cbtResult");
        //return "cbtResult";
    }


}
