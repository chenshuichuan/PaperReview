package com.ruoyi.project.system.paperType.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.paperType.mapper.PaperTypeMapper;
import com.ruoyi.project.system.paperType.domain.PaperType;
import com.ruoyi.project.system.paperType.service.IPaperTypeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 论文类型 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Service
public class PaperTypeServiceImpl implements IPaperTypeService {
    @Autowired
    private PaperTypeMapper paperTypeMapper;

    /**
     * 查询论文类型信息
     *
     * @param id 论文类型ID
     * @return 论文类型信息
     */
    @Override
    public PaperType selectPaperTypeById(Integer id) {
        return paperTypeMapper.selectPaperTypeById(id);
    }

    /**
     * 查询论文类型列表
     *
     * @param paperType 论文类型信息
     * @return 论文类型集合
     */
    @Override
    public List<PaperType> selectPaperTypeList(PaperType paperType) {
        return paperTypeMapper.selectPaperTypeList(paperType);
    }

    /**
     * 新增论文类型
     *
     * @param paperType 论文类型信息
     * @return 结果
     */
    @Override
    public int insertPaperType(PaperType paperType) {
        return paperTypeMapper.insertPaperType(paperType);
    }

    /**
     * 修改论文类型
     *
     * @param paperType 论文类型信息
     * @return 结果
     */
    @Override
    public int updatePaperType(PaperType paperType) {
        return paperTypeMapper.updatePaperType(paperType);
    }

    /**
     * 删除论文类型对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePaperTypeByIds(String ids) {
        return paperTypeMapper.deletePaperTypeByIds(Convert.toStrArray(ids));
    }

}
