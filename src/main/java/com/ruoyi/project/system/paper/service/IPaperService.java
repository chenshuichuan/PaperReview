package com.ruoyi.project.system.paper.service;

import com.ruoyi.project.system.paper.domain.Paper;

import java.util.List;

/**
 * 论文 服务层
 *
 * @author ruoyi
 * @date 2019-05-04
 */
public interface IPaperService {
    /**
     * 查询论文信息
     *
     * @param id 论文ID
     * @return 论文信息
     */
    public Paper selectPaperById(Integer id);

    /**
     * 查询论文列表
     *
     * @param paper 论文信息
     * @return 论文集合
     */
    public List<Paper> selectPaperList(Paper paper);

    /**
     * 新增论文
     *
     * @param paper 论文信息
     * @return 结果
     */
    public int insertPaper(Paper paper);

    /**
     * 修改论文
     *
     * @param paper 论文信息
     * @return 结果
     */
    public int updatePaper(Paper paper);

    /**
     * 删除论文信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePaperByIds(String ids);

}
