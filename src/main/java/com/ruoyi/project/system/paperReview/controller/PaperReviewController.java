package com.ruoyi.project.system.paperReview.controller;

import java.util.List;

import com.ruoyi.project.system.paper.domain.Paper;
import com.ruoyi.project.system.paper.service.IPaperService;
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
public class PaperReviewController extends BaseController
{
    private String prefix = "system/paperReview";
	
	@Autowired
	private IPaperService paperReviewService;
	
	@RequiresPermissions("system:paperReview:view")
	@GetMapping()
	public String paperReview()
	{
	    return prefix + "/paperReview";
	}
	
	/**
	 * 查询论文列表
	 */
	@RequiresPermissions("system:paperReview:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Paper paperReview)
	{
		startPage();
        List<Paper> list = paperReviewService.selectPaperList(paperReview);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出论文列表
	 */
	@RequiresPermissions("system:paperReview:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Paper paperReview)
    {
    	List<Paper> list = paperReviewService.selectPaperList(paperReview);
        ExcelUtil<Paper> util = new ExcelUtil<Paper>(Paper.class);
        return util.exportExcel(list, "paperReview");
    }
	
	/**
	 * 新增论文
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存论文
	 */
	@RequiresPermissions("system:paperReview:add")
	@Log(title = "论文", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Paper paperReview)
	{		
		return toAjax(paperReviewService.insertPaper(paperReview));
	}

	/**
	 * 修改论文
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Paper paperReview = paperReviewService.selectPaperById(id);
		mmap.put("paperReview", paperReview);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存论文
	 */
	@RequiresPermissions("system:paperReview:edit")
	@Log(title = "论文", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Paper paperReview)
	{		
		return toAjax(paperReviewService.updatePaper(paperReview));
	}
	
	/**
	 * 删除论文
	 */
	@RequiresPermissions("system:paperReview:remove")
	@Log(title = "论文", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(paperReviewService.deletePaperByIds(ids));
	}
	
}
