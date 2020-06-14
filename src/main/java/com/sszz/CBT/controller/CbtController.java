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
import java.util.Date;
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
            model.addAttribute("condition",cbtService.getSCondiName(condition));
        }else if(condition.charAt(0) == 'h'){ //회차
            System.out.println(cbtService.getHCondiQuestion(condition).toString());
            model.addAttribute("messages",cbtService.getHCondiQuestion(condition));
            model.addAttribute("condition",cbtService.getHCondiName(condition));
        }

        //값을 담을 엔티티 셋팅
        CbtHistVO cbtHistVO = new CbtHistVO();
        model.addAttribute("cbtHistVO", cbtHistVO);

        //이렇게 하면 안된다...ㅜ
        //List<QuesDabVO> quesDabVOs = new ArrayList<>();
        //model.addAttribute("quesDabVOs", quesDabVOs);

        return "cbtPlay";
    }

    //login 후
    //@RequestMapping("/scoring.do")
    @PostMapping("/scoring.do")
    public ModelAndView doScoring(@ModelAttribute ("cbtHistVO") @Valid CbtHistVO cbtHistVO, Model model, BindingResult result,
                                RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // session 넣기 전 임시로 셋팅!
        cbtHistVO.setEmail("suji20th@naver.com");
        //문제 푼 날짜 셋팅
        //cbtHistVO.setCreateDate(new java.text.SimpleDateFormat("yyyyMMdd").parse("20200614"));
        cbtHistVO.setCreateDate(new Date());

        //log용 count
        int count =0;

        System.out.println("이메일:" + cbtHistVO.getEmail());
        System.out.println("날짜 :" + cbtHistVO.getCreateDate());

        for( QuesDabVO quesDabVO :  cbtHistVO.getQuesDabVOs() ){
            System.out.println("count :" + ++count);
            System.out.println("quesDabVO.getQuesDabId() :" + quesDabVO.getQuesDabId());
            System.out.println("quesDabVO.getQuestionId() :" + quesDabVO.getQuestionId());
            System.out.println("quesDabVO.getDap() :" + quesDabVO.getDap());
            System.out.println("quesDabVO.getCbtHistId() :" + quesDabVO.getCbtHistId());
            //관계설정!!
            quesDabVO.setCbtHistId(cbtHistVO);
        }

        //cbtHistVO를 DB insert
        //String nextQuestionId = Integer.toString(cbtService.getNextQuestionId());
        cbtService.scoringSave(cbtHistVO);

        //점수가져오기
        model.addAttribute("score", cbtService.getScore(cbtHistVO));
        model.addAttribute("cnt", cbtService.getQuestionCnt(cbtHistVO));

        return new ModelAndView("cbtResult");
    }

}
