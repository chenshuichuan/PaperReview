package com.ruoyi.project.system.paperReview.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.paper.domain.Paper;
import com.ruoyi.project.system.paper.service.IPaperService;
import com.ruoyi.project.system.paperComment.domain.PaperComment;
import com.ruoyi.project.system.paperComment.service.IPaperCommentService;
import com.ruoyi.project.system.paperComment.service.PaperCommentRepository;
import com.ruoyi.project.system.paperType.domain.PaperType;
import com.ruoyi.project.system.paperType.service.IPaperTypeService;
import com.ruoyi.project.system.user.domain.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.paperReview.service.IPaperReviewService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 论文信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Controller
@RequestMapping("/system/paperReview")
public class PaperReviewController extends BaseController {
    private String prefix = "system/paperReview";
    @Autowired
    private IPaperTypeService paperTypeService;

    @Autowired
    private IPaperService paperService;
    @Autowired
    private PaperCommentRepository paperCommentRepository;
    @Autowired
    private IPaperCommentService paperCommentService;
    @Autowired
    private IPaperReviewService paperReviewService;

    @RequiresPermissions("system:paperReview:view")
    @GetMapping()
    public String paperReview(ModelMap mmap) {

        List<PaperType> paperTypeList = paperTypeService.selectPaperTypeList(new PaperType());
        mmap.put("paperTypeList",paperTypeList);
        return prefix + "/paperReview";
    }

    /**
     * 查询论文列表
     */
    @RequiresPermissions("system:paperReview:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Paper paperReview) {
        startPage();
        //仅获取审核通过的论文
        paperReview.setIsPass(1);
        List<Paper> list = paperService.selectPaperList(paperReview);

        User user = ShiroUtils.getSysUser();
        List<Paper> paperList = new ArrayList<>();
        for (Paper paper:list){
            //去除user自己的论文
            if(paper.getUser().equals(user.getUserName())){

            }else{
                paperList.add(paper);
            }
            //去除草稿论文，草稿的肯定是不通过的
        }
        for (int i=0; i<paperList.size();i++){
            //设置user自己已经评论过的
            int number = paperCommentRepository.countByPaperIdAndUserId(paperList.get(i).getId(),user.getUserId().intValue());
            if(number>0){
                paperList.get(i).setIsReview(1);
            }
        }
        return getDataTable(paperList);
    }


    /**
     * 导出论文列表
     */
    @RequiresPermissions("system:paperReview:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Paper paperReview) {
        List<Paper> list = paperService.selectPaperList(paperReview);
        ExcelUtil<Paper> util = new ExcelUtil<Paper>(Paper.class);
        return util.exportExcel(list, "paperReview");
    }

    /**
     * 新增论文评价
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
      //List<PaperType> paperTypeList = paperTypeService.selectPaperTypeList(new PaperType());
      // mmap.put("paperTypeList",paperTypeList);
        mmap.put("paperComment", new PaperComment());
        return prefix + "/add";
    }

    /**
     * 新增保存论文
     */
    @RequiresPermissions("system:paperReview:add")
    @Log(title = "论文", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Paper paperReview) {
        return toAjax(paperService.insertPaper(paperReview));
    }

    /**
     * 修改论文评价
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        User user = ShiroUtils.getSysUser();
        Paper paper = paperService.selectPaperById(id);
        PaperComment paperComment = paperCommentRepository.findByPaperIdAndUserId(id,user.getUserId().intValue());
        if(paperComment==null){
            paperComment = new PaperComment();
            paperComment.setId(0);
            paperComment.setPaperId(id);
        }
        mmap.put("paperComment",paperComment);
        mmap.put("paperTitle",paper.getTitle());

        return prefix + "/add";
    }

    /**
     * 修改保存论文
     */
    @RequiresPermissions("system:paperReview:edit")
    @Log(title = "论文", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PaperComment paperComment) {
        int flag =0;

        User user = ShiroUtils.getSysUser();
        paperComment.setUserId(user.getUserId().intValue());
        paperComment.setUserName(user.getUserName());
        paperComment.setCreateTime(new Date());
        //更新
        if(paperComment.getId()!=null && paperComment.getId()>0){
            flag = paperCommentService.updatePaperComment(paperComment);
        }
        //新增
        else{
            //评论数加一
            Paper paper = paperService.selectPaperById(paperComment.getPaperId());
            paper.setAppraisalTimes(paper.getAppraisalTimes()+1);
            paperService.updatePaper(paper);

            paperComment.setId(null);
            flag = paperCommentService.insertPaperComment(paperComment);
        }
        return toAjax(flag);
    }

    /**
     * 删除论文评论，根据登录用户和paperIds
     * @param ids paperIds
     */
    @RequiresPermissions("system:paperReview:remove")
    @Log(title = "论文评论", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(paperReviewService.deletePaperCommentByPaperIdsAndUser(ids));
    }

}
