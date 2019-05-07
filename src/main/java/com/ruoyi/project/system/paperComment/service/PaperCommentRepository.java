package com.ruoyi.project.system.paperComment.service;


import com.ruoyi.project.system.paper.domain.Paper;
import com.ruoyi.project.system.paperComment.domain.PaperComment;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: paper；类的jpa
 * Date: 2018/8/22
 */
public interface PaperCommentRepository extends JpaRepository<PaperComment,Integer> {

    /**
     * 每个专家对每篇论文，仅能作一个评论
     * @param paperId
     * @param userId
     * @return
     */
    PaperComment findByPaperIdAndUserId(Integer paperId, Integer userId);
    /**
     * 每个专家对每篇论文，仅能作一个评论
     * @param paperId
     * @param userId
     * @return
     */
    int countByPaperIdAndUserId(Integer paperId, Integer userId);

    /**
     * 每个专家对每篇论文，仅能作一个评论
     * @param paperId
     * @param userId
     * @return
     */
    int deleteByPaperIdAndUserId(Integer paperId, Integer userId);
}
