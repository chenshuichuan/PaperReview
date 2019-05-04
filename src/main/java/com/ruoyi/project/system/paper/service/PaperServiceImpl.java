package com.ruoyi.project.system.paper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.paper.mapper.PaperMapper;
import com.ruoyi.project.system.paper.domain.Paper;
import com.ruoyi.project.system.paper.service.IPaperService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 论文 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Service
public class PaperServiceImpl implements IPaperService {
    @Autowired
    private PaperMapper paperMapper;

    /**
     * 查询论文信息
     *
     * @param id 论文ID
     * @return 论文信息
     */
    @Override
    public Paper selectPaperById(Integer id) {
        return paperMapper.selectPaperById(id);
    }

    /**
     * 查询论文列表
     *
     * @param paper 论文信息
     * @return 论文集合
     */
    @Override
    public List<Paper> selectPaperList(Paper paper) {
        return paperMapper.selectPaperList(paper);
    }

    /**
     * 新增论文
     *
     * @param paper 论文信息
     * @return 结果
     */
    @Override
    public int insertPaper(Paper paper) {
        return paperMapper.insertPaper(paper);
    }

    /**
     * 修改论文
     *
     * @param paper 论文信息
     * @return 结果
     */
    @Override
    public int updatePaper(Paper paper) {
        return paperMapper.updatePaper(paper);
    }

    /**
     * 删除论文对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePaperByIds(String ids) {
        return paperMapper.deletePaperByIds(Convert.toStrArray(ids));
    }

}
