package com.sszz.CBT.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "NOTE_QuesDab_TB")
public class QuesDabVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quesDabId;
    private String questionId;
    private String dap;

    //영속성 전이 설정
    @ManyToOne(fetch=FetchType.EAGER) //Lazy Loading 설정
    @JoinColumn(name="cbtHistId") //조인 컬럼 설정 DB에서 가져오기!!
    private CbtHistVO cbtHistId;

    //1:N으로 영속성 전이 설정 mappedBy = CommentVO에서 QuesDabVO 클래스 변수명
    @OneToMany(mappedBy="quesDabId", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    private List<CommentVO> commentVOs;

    public QuesDabVO() {
    }

    public QuesDabVO(String questionId, String dap, CbtHistVO cbtHistId) {
        this.questionId = questionId;
        this.dap = dap;
        this.cbtHistId = cbtHistId;
    }

    public QuesDabVO(Integer quesDabId, String questionId, String dap, CbtHistVO cbtHistId) {
        this.quesDabId = quesDabId;
        this.questionId = questionId;
        this.dap = dap;
        this.cbtHistId = cbtHistId;
    }

    public Integer getQuesDabId() {
        return quesDabId;
    }

    public void setQuesDabId(Integer quesDabId) {
        this.quesDabId = quesDabId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getDap() {
        return dap;
    }

    public void setDap(String dap) {
        this.dap = dap;
    }

    public CbtHistVO getCbtHistId() {
        return cbtHistId;
    }

    public void setCbtHistId(CbtHistVO cbtHistId) {
        this.cbtHistId = cbtHistId;
    }

    public List<CommentVO> getCommentVOs() { return commentVOs; }

    public void setCommentVOs(List<CommentVO> commentVOs) { this.commentVOs = commentVOs; }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
