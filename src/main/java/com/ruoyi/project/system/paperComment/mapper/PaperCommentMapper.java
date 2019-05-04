package com.ruoyi.project.system.paperComment.mapper;

import com.ruoyi.project.system.paperComment.domain.PaperComment;

import java.util.List;

/**
 * 论文评论 数据层
 *
 * @author ruoyi
 * @date 2019-05-04
 */
public interface PaperCommentMapper {
    /**
     * 查询论文评论信息
     *
     * @param id 论文评论ID
     * @return 论文评论信息
     */
    public PaperComment selectPaperCommentById(Integer id);

    /**
     * 查询论文评论列表
     *
     * @param paperComment 论文评论信息
     * @return 论文评论集合
     */
    public List<PaperComment> selectPaperCommentList(PaperComment paperComment);

    /**
     * 新增论文评论
     *
     * @param paperComment 论文评论信息
     * @return 结果
     */
    public int insertPaperComment(PaperComment paperComment);

    /**
     * 修改论文评论
     *
     * @param paperComment 论文评论信息
     * @return 结果
     */
    public int updatePaperComment(PaperComment paperComment);

    /**
     * 删除论文评论
     *
     * @param id 论文评论ID
     * @return 结果
     */
    public int deletePaperCommentById(Integer id);

    /**
     * 批量删除论文评论
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePaperCommentByIds(String[] ids);

}