package com.ruoyi.project.system.paper.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.paperComment.mapper.PaperCommentMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.paper.mapper.PaperMapper;
import com.ruoyi.project.system.paper.domain.Paper;
import com.ruoyi.project.system.paper.service.IPaperService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.transaction.annotation.Transactional;

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
  @Autowired
  private PaperCommentMapper paperCommentMapper;

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
    User user = ShiroUtils.getSysUser();
    paper.setUser(user.getUserName());
    paper.setIsPass(3);
    paper.setIsReview(0);
    paper.setDownloadTimes(0);
    paper.setAppraisalTimes(0);
    paper.setPreviewTimes(0);
    paper.setCreateTime(new Date());
    paper.setUpdateTime(new Date());
    //paper.setStatus(3);
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
    paper.setUpdateTime(new Date());
    return paperMapper.updatePaper(paper);
  }

  /**
   * 删除论文对象
   *
   * @param ids 需要删除的数据ID
   * @return 结果
   */
  @Override
  @Transactional
  public int deletePaperByIds(String ids) {
    paperMapper.deletePaperByIds(Convert.toStrArray(ids));
    paperCommentMapper.deletePaperCommentByPaperIds(Convert.toStrArray(ids));
    return 1;
  }


  /**
   * 修改文件上传
   *
   * @param files 文件上传信息
   * @return 结果
   */
  @Override
  @Transactional
  public int updatePaper(Paper files, Boolean isFile) {
    int rtn = 0;
    //修改新数据
    rtn = paperMapper.updatePaper(files);
    if (rtn > 0) {
      //先判断有没有文件
      Paper oldFiles = paperMapper.selectPaperById(files.getId());
      File file = new File(oldFiles.getFileUrl());
      if (file.isFile()) {
        if (files != null && isFile) {
          file.delete();
        } else {
          file.renameTo(new File(files.getFileUrl()));
        }
      }
    }
    return rtn;
  }

  @Override
  public Paper updatePaperInfoByPaper(Paper paper) {

    Paper paper1 = paperMapper.selectPaperById(paper.getId());
    paper1.setFileUrl(paper.getFileUrl());
    paper1.setPaperUrl(paper.getPaperUrl());
    paper1.setTitle(paper.getTitle());
    paper1.setAuthor(paper.getAuthor());
    paper1.setPaperType(paper.getPaperType());
    paper1.setIsPublic(paper.getIsPublic());
    paper1.setStatus(paper.getStatus());
    return paper1;
  }
}
