package com.ruoyi.project.system.paperReview.service;

import java.util.List;

/**
 * 论文 服务层
 *
 * @author ruoyi
 * @date 2019-05-04
 */
public interface IPaperReviewService {

    /**
     * 删除论文信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePaperCommentByPaperIdsAndUser(String ids);


}
