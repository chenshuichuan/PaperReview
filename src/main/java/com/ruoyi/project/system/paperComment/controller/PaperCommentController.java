package com.ruoyi.project.system.paperComment.controller;

import java.util.List;

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
import com.ruoyi.project.system.paperComment.domain.PaperComment;
import com.ruoyi.project.system.paperComment.service.IPaperCommentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 论文评论信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Controller
@RequestMapping("/system/paperComment")
public class PaperCommentController extends BaseController {
    private String prefix = "system/paperComment";

    @Autowired
    private IPaperCommentService paperCommentService;

    @RequiresPermissions("system:paperComment:view")
    @GetMapping()
    public String paperComment() {
        return prefix + "/paperComment";
    }

    /**
     * 查询论文评论列表
     */
    @RequiresPermissions("system:paperComment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PaperComment paperComment) {
        startPage();
        List<PaperComment> list = paperCommentService.selectPaperCommentList(paperComment);
        return getDataTable(list);
    }


    /**
     * 导出论文评论列表
     */
    @RequiresPermissions("system:paperComment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PaperComment paperComment) {
        List<PaperComment> list = paperCommentService.selectPaperCommentList(paperComment);
        ExcelUtil<PaperComment> util = new ExcelUtil<PaperComment>(PaperComment.class);
        return util.exportExcel(list, "paperComment");
    }

    /**
     * 新增论文评论
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存论文评论
     */
    @RequiresPermissions("system:paperComment:add")
    @Log(title = "论文评论", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PaperComment paperComment) {
        return toAjax(paperCommentService.insertPaperComment(paperComment));
    }

    /**
     * 修改论文评论
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        PaperComment paperComment = paperCommentService.selectPaperCommentById(id);
        mmap.put("paperComment", paperComment);
        return prefix + "/edit";
    }

    /**
     * 修改保存论文评论
     */
    @RequiresPermissions("system:paperComment:edit")
    @Log(title = "论文评论", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PaperComment paperComment) {
        return toAjax(paperCommentService.updatePaperComment(paperComment));
    }

    /**
     * 删除论文评论
     */
    @RequiresPermissions("system:paperComment:remove")
    @Log(title = "论文评论", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(paperCommentService.deletePaperCommentByIds(ids));
    }

}
