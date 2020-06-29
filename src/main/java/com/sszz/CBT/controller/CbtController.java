package com.sszz.CBT.controller;

import com.sszz.CBT.domain.*;
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
        model.addAttribute("cbtHistId", cbtHistVO.getCbtHistId());

        return new ModelAndView("cbtResult");
    }

    @GetMapping("/ICNote.do")
    public ModelAndView accessICNote( Model model ){
        //list가져오기
        //임시로 하드코딩
        String email = "suji20th@naver.com";
        model.addAttribute("ICNoteList", cbtService.getCbtHistList(email));
        return new ModelAndView("IncorrectNoteMain");
    }

    @GetMapping("/ICNoteDetail")
    public ModelAndView accessICNoteDetail( Model model, String cbtHistId ){

        //지역변수
        List<Integer> quesDapIdList = new ArrayList<>();

        //cbt
        CbtHistVO cbtHistVO =  cbtService.getCbtHistVO(cbtHistId);
        List<WrittenTestVO> WTVL =  cbtService.getICQuestionList(cbtHistVO);
        List<QuesDabVO> quesDabList = cbtService.getICUserDapList(cbtHistVO,WTVL);

        //틀린 문제만 뿌리고  +정답 뿌리고
        model.addAttribute("ICQuestionList",WTVL);

        //내가 체크한 답 뿌리고
        model.addAttribute("quesDabList", quesDabList);
        //List<QuesDabVO> testQuesDabVOs = cbtService.getICUserDapList(cbtHistVO,WTVL);

        //quesDapId 추출
        for( QuesDabVO quesDabVO: quesDabList){
            quesDapIdList.add(quesDabVO.getQuesDabId());
        }

        //comment 긁어오기 QuesDabVO F/K를 가지고 comment를 가져오쟈
        ArrayList<ArrayList<CommentVO>> commentListList = cbtService.getCommentByQuesDabVO(quesDabList);

        //--------------------------------------------------------------
        // 뎃글 뿌림
        int index = 0;
        for(List<CommentVO> commentVOList: commentListList){
            model.addAttribute("commentVOList"+index,commentVOList);
            index++;
        }
        //--------------------------------------------------------------

        //comment 등록할 때 쓸 객체 보내고
        model.addAttribute("CommentVO",new CommentVO());

        return new ModelAndView("IncorrectNoteDetail");
    }

    @PostMapping("/saveComment.do")
    public String saveComment( Model model, CommentVO commentVO, @RequestParam String cbtHistId ) {

        commentVO.setCreateData(new Date());
        System.out.println(commentVO.toString());

        //저장
        cbtService.commentSave(commentVO);

        return "redirect:/ICNoteDetail?cbtHistId="+cbtHistId;
    }

    @GetMapping("/main.do")
    public String main( Model model ){
        return "main";
    }

}
