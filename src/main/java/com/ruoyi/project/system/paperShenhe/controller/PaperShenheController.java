package com.ruoyi.project.system.paperShenhe.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.project.system.paper.service.IPaperService;
import com.ruoyi.project.system.user.domain.User;
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
import com.ruoyi.project.system.paper.domain.Paper;
import com.ruoyi.project.system.paperShenhe.service.IPaperShenheService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 论文审核信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-07
 */
@Controller
@RequestMapping("/system/paperShenhe")
public class PaperShenheController extends BaseController {
    private String prefix = "system/paperShenhe";

    @Autowired
    private IPaperShenheService paperShenheService;
    @Autowired
    private IPaperService paperService;

    @RequiresPermissions("system:paperShenhe:view")
    @GetMapping()
    public String paperShenhe() {
        return prefix + "/paperShenhe";
    }

    /**
     * 查询论文审核列表
     */
    @RequiresPermissions("system:paperShenhe:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Paper paperShenhe) {
        startPage();
        List<Paper> list = paperService.selectPaperList(paperShenhe);
        //去除草稿论文
        List<Paper> paperList = new ArrayList<>();
        for (Paper paper:list){
            //去除草稿论文，草稿的肯定是不通过的
            if(paper.getStatus()==2){

            }else{
                paperList.add(paper);
            }
        }
        return getDataTable(paperList);
    }


    /**
     * 导出论文审核列表
     */
    @RequiresPermissions("system:paperShenhe:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Paper paperShenhe) {
        List<Paper> list = paperService.selectPaperList(paperShenhe);
        ExcelUtil<Paper> util = new ExcelUtil<Paper>(Paper.class);
        return util.exportExcel(list, "paperShenhe");
    }

    /**
     * 新增论文审核
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存论文审核
     */
    @RequiresPermissions("system:paperShenhe:add")
    @Log(title = "论文审核", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Paper paperShenhe) {
        return toAjax(paperService.insertPaper(paperShenhe));
    }

    /**
     * 修改论文审核
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Paper paperShenhe = paperService.selectPaperById(id);
        mmap.put("paperShenhe", paperShenhe);
        return prefix + "/edit";
    }

    /**
     * 修改保存论文审核
     */
    @RequiresPermissions("system:paperShenhe:edit")
    @Log(title = "论文审核", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Paper paperShenhe) {
        Paper paper = paperService.selectPaperById(paperShenhe.getId());
        int flag = 1;
        //仅更新审核
        if(null !=paper){
            paper.setIsPass(paperShenhe.getIsPass());
            paper.setStatus(paperShenhe.getIsPass());
            flag = paperService.updatePaper(paper);
        }else{
            flag=0;
        }
        return toAjax(flag);
    }

    /**
     * 删除论文审核
     */
    @RequiresPermissions("system:paperShenhe:remove")
    @Log(title = "论文审核", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(paperService.deletePaperByIds(ids));
    }

}
