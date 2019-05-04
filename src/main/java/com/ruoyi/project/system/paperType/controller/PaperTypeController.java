package com.ruoyi.project.system.paperType.controller;

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
import com.ruoyi.project.system.paperType.domain.PaperType;
import com.ruoyi.project.system.paperType.service.IPaperTypeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 论文类型信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Controller
@RequestMapping("/system/paperType")
public class PaperTypeController extends BaseController {
    private String prefix = "system/paperType";

    @Autowired
    private IPaperTypeService paperTypeService;

    @RequiresPermissions("system:paperType:view")
    @GetMapping()
    public String paperType() {
        return prefix + "/paperType";
    }

    /**
     * 查询论文类型列表
     */
    @RequiresPermissions("system:paperType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PaperType paperType) {
        startPage();
        List<PaperType> list = paperTypeService.selectPaperTypeList(paperType);
        return getDataTable(list);
    }


    /**
     * 导出论文类型列表
     */
    @RequiresPermissions("system:paperType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PaperType paperType) {
        List<PaperType> list = paperTypeService.selectPaperTypeList(paperType);
        ExcelUtil<PaperType> util = new ExcelUtil<PaperType>(PaperType.class);
        return util.exportExcel(list, "paperType");
    }

    /**
     * 新增论文类型
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存论文类型
     */
    @RequiresPermissions("system:paperType:add")
    @Log(title = "论文类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PaperType paperType) {
        return toAjax(paperTypeService.insertPaperType(paperType));
    }

    /**
     * 修改论文类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        PaperType paperType = paperTypeService.selectPaperTypeById(id);
        mmap.put("paperType", paperType);
        return prefix + "/edit";
    }

    /**
     * 修改保存论文类型
     */
    @RequiresPermissions("system:paperType:edit")
    @Log(title = "论文类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PaperType paperType) {
        return toAjax(paperTypeService.updatePaperType(paperType));
    }

    /**
     * 删除论文类型
     */
    @RequiresPermissions("system:paperType:remove")
    @Log(title = "论文类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(paperTypeService.deletePaperTypeByIds(ids));
    }

}
