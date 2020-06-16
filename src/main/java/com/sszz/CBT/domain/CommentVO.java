package com.sszz.CBT.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "com_comment_tb")
public class CommentVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String commentId;
    String comment;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createData;

    //영속성 전이 설정
    @ManyToOne(fetch=FetchType.EAGER) //Lazy Loading 설정
    @JoinColumn(name="quesDabId") //조인 컬럼 설정 DB에서 가져오기!!
    private QuesDabVO quesDabId;

    public CommentVO() {
    }

    public CommentVO(String commentId, String comment, Date createData, QuesDabVO quesDabId) {
        this.commentId = commentId;
        this.comment = comment;
        this.createData = createData;
        this.quesDabId = quesDabId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateData() {
        return createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }

    public QuesDabVO getQuesDabId() {
        return quesDabId;
    }

    public void setQuesDabId(QuesDabVO quesDabId) {
        this.quesDabId = quesDabId;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "commentId='" + commentId + '\'' +
                ", comment='" + comment + '\'' +
                ", createData=" + createData +
                ", quesDabId=" + quesDabId +
                '}';
    }

}
