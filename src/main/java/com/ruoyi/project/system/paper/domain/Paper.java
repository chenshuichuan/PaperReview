package com.ruoyi.project.system.paper.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 论文表 pr_paper
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Entity
@Table(name = "pr_paper")
public class Paper extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 论文标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 上传者
     */
    @Column(name = "user")
    private String user;
    /**
     * 论文类型
     */
    @Column(name = "paper_type")
    private String paperType;
    /**
     * 作者
     */
    @Column(name = "author")
    private String author;
    /**
     * 是否审核
     */
    @Column(name = "is_review")
    private Integer isReview;
    /**
     * 是否通过
     */
    @Column(name = "is_pass")
    private Integer isPass;
    /**
     * 下载次数
     */
    @Column(name = "download_times")
    private Integer downloadTimes;
    /**
     * 评价次数
     */
    @Column(name = "appraisal_times")
    private Integer appraisalTimes;
    /**
     * 预览次数
     */
    @Column(name = "preview_times")
    private Integer previewTimes;
    /**
     * 论文文件路径
     */
    @Column(name = "paper_url")
    private String paperUrl;
    /**
     * 论文状态
     */
    @Column(name = "status")
    private Integer status;

    public Paper() {

    }
    public Paper(String title, String user, String paperType, String author,
                 Integer isReview, Integer isPass, Integer downloadTimes,
                 Integer appraisalTimes, Integer previewTimes, String paperUrl, Integer status) {
        this.title = title;
        this.user = user;
        this.paperType = paperType;
        this.author = author;
        this.isReview = isReview;
        this.isPass = isPass;
        this.downloadTimes = downloadTimes;
        this.appraisalTimes = appraisalTimes;
        this.previewTimes = previewTimes;
        this.paperUrl = paperUrl;
        this.status = status;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    public void setAppraisalTimes(Integer appraisalTimes) {
        this.appraisalTimes = appraisalTimes;
    }

    public Integer getAppraisalTimes() {
        return appraisalTimes;
    }

    public void setPreviewTimes(Integer previewTimes) {
        this.previewTimes = previewTimes;
    }

    public Integer getPreviewTimes() {
        return previewTimes;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("user", getUser())
                .append("author", getAuthor())
                .append("isReview", getIsReview())
                .append("isPass", getIsPass())
                .append("downloadTimes", getDownloadTimes())
                .append("appraisalTimes", getAppraisalTimes())
                .append("previewTimes", getPreviewTimes())
                .append("paperUrl", getPaperUrl())
                .append("updateTime", getUpdateTime())
                .append("createTime", getCreateTime())
                .append("status", getStatus())
                .toString();
    }
}
