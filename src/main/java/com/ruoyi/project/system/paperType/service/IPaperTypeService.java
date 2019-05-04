package com.ruoyi.project.system.paperType.service;

import com.ruoyi.project.system.paperType.domain.PaperType;

import java.util.List;

/**
 * 论文类型 服务层
 *
 * @author ruoyi
 * @date 2019-05-04
 */
public interface IPaperTypeService {
    /**
     * 查询论文类型信息
     *
     * @param id 论文类型ID
     * @return 论文类型信息
     */
    public PaperType selectPaperTypeById(Integer id);

    /**
     * 查询论文类型列表
     *
     * @param paperType 论文类型信息
     * @return 论文类型集合
     */
    public List<PaperType> selectPaperTypeList(PaperType paperType);

    /**
     * 新增论文类型
     *
     * @param paperType 论文类型信息
     * @return 结果
     */
    public int insertPaperType(PaperType paperType);

    /**
     * 修改论文类型
     *
     * @param paperType 论文类型信息
     * @return 结果
     */
    public int updatePaperType(PaperType paperType);

    /**
     * 删除论文类型信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePaperTypeByIds(String ids);

}
