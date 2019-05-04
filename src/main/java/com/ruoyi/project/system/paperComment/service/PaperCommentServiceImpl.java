package com.ruoyi.project.system.paperComment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.paperComment.mapper.PaperCommentMapper;
import com.ruoyi.project.system.paperComment.domain.PaperComment;
import com.ruoyi.project.system.paperComment.service.IPaperCommentService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 论文评论 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Service
public class PaperCommentServiceImpl implements IPaperCommentService {
    @Autowired
    private PaperCommentMapper paperCommentMapper;

    /**
     * 查询论文评论信息
     *
     * @param id 论文评论ID
     * @return 论文评论信息
     */
    @Override
    public PaperComment selectPaperCommentById(Integer id) {
        return paperCommentMapper.selectPaperCommentById(id);
    }

    /**
     * 查询论文评论列表
     *
     * @param paperComment 论文评论信息
     * @return 论文评论集合
     */
    @Override
    public List<PaperComment> selectPaperCommentList(PaperComment paperComment) {
        return paperCommentMapper.selectPaperCommentList(paperComment);
    }

    /**
     * 新增论文评论
     *
     * @param paperComment 论文评论信息
     * @return 结果
     */
    @Override
    public int insertPaperComment(PaperComment paperComment) {
        return paperCommentMapper.insertPaperComment(paperComment);
    }

    /**
     * 修改论文评论
     *
     * @param paperComment 论文评论信息
     * @return 结果
     */
    @Override
    public int updatePaperComment(PaperComment paperComment) {
        return paperCommentMapper.updatePaperComment(paperComment);
    }

    /**
     * 删除论文评论对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePaperCommentByIds(String ids) {
        return paperCommentMapper.deletePaperCommentByIds(Convert.toStrArray(ids));
    }

}
