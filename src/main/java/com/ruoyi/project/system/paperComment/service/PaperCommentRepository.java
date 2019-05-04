package com.ruoyi.project.system.paperComment.service;


import com.ruoyi.project.system.paper.domain.Paper;
import com.ruoyi.project.system.paperComment.domain.PaperComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: paper；类的jpa
 * Date: 2018/8/22
 */
public interface PaperCommentRepository extends JpaRepository<PaperComment,Integer> {

}
