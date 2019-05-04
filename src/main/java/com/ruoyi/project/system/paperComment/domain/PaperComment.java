package com.ruoyi.project.system.paperComment.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 论文评论表 pr_paper_comment
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Entity
@Table(name = "pr_paper_comment")
public class PaperComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 评论论文
     */
    @Column(name = "paper_id")
    private Integer paperId;
    /**
     * 评论者
     */
    @Column(name = "user_id")
    private Integer userId;
    /**
     * 评论专家
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 是否公开评论
     */
    @Column(name = "is_public")
    private Integer isPublic;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("paperId", getPaperId())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("content", getContent())
                .append("createTime", getCreateTime())
                .append("isPublic", getIsPublic())
                .toString();
    }
}
