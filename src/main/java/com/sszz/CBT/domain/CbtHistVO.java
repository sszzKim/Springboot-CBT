package com.sszz.CBT.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "NOTE_CbtHist_TB")
public class CbtHistVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "cbtHistId", nullable = false, unique = true)
    private  Integer cbtHistId;
    private  String email;
    @Temporal(TemporalType.DATE)
    private Date createDate;

    //1:N으로 영속성 전이 설정 mappedBy = QuesDabVO에서 CbtHistVO 클래스 변수명
    @OneToMany(mappedBy="cbtHistId", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<QuesDabVO> quesDabVOs;

    public CbtHistVO() {
    }

    public CbtHistVO(String email, Date createDate, List<QuesDabVO> quesDabVOs) {
        this.email = email;
        this.createDate = createDate;
        this.quesDabVOs = quesDabVOs;
    }

    public CbtHistVO(String email, Date createDate) {
        this.email = email;
        this.createDate = createDate;
    }

    public CbtHistVO(Integer cbtHistId, String email, Date createDate, List<QuesDabVO> quesDabVOs) {
        cbtHistId = cbtHistId;
        this.email = email;
        this.createDate = createDate;
        this.quesDabVOs = quesDabVOs;
    }

    public Integer getCbtHistId() {
        return cbtHistId;
    }

    public void setCbtHistId(Integer cbtHistId) {
        cbtHistId = cbtHistId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<QuesDabVO> getQuesDabVOs() {
        if(this.quesDabVOs == null) this.quesDabVOs = new ArrayList<QuesDabVO>();
        return quesDabVOs;
    }

    public void setQuesDabVOs(List<QuesDabVO> quesDabVOs) {
        this.quesDabVOs = quesDabVOs;
    }

    //배열에 답VO 넣는 메소드
    public void addQuesDabVO(QuesDabVO quesDabVO){
        this.quesDabVOs.add(quesDabVO);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}