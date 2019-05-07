package com.ruoyi.project.system.paperReview.service;

import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.paper.domain.Paper;
import com.ruoyi.project.system.paper.mapper.PaperMapper;
import com.ruoyi.project.system.paperComment.mapper.PaperCommentMapper;
import com.ruoyi.project.system.paperComment.service.PaperCommentRepository;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.paperReview.service.IPaperReviewService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 论文 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Service
public class PaperReviewServiceImpl implements IPaperReviewService {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private PaperCommentMapper paperCommentMapper;
    @Autowired
    private PaperCommentRepository paperCommentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePaperCommentByPaperIdsAndUser(String ids) {
        String[] idArray = Convert.toStrArray(ids);
        User user = ShiroUtils.getSysUser();
        for (String strId: idArray){
            Integer paperId = Integer.parseInt(strId);
            paperCommentRepository.deleteByPaperIdAndUserId(paperId,user.getUserId().intValue());

            Paper paper = paperMapper.selectPaperById(paperId);
            int commentCount = paperCommentRepository.countByPaperIdAndUserId(paperId,user.getUserId().intValue());
            paper.setAppraisalTimes(commentCount);
            paperMapper.updatePaper(paper);
        }
        return 1;
    }
}
