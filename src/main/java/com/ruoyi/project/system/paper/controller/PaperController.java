package com.ruoyi.project.system.paper.controller;

import java.io.File;
import java.util.List;

import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.paperComment.domain.PaperComment;
import com.ruoyi.project.system.paperComment.service.PaperCommentRepository;
import com.ruoyi.project.system.paperType.domain.PaperType;
import com.ruoyi.project.system.paperType.service.IPaperTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ruoyi.project.system.paper.service.IPaperService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 论文信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Controller
@RequestMapping("/system/paper")
public class PaperController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(PaperController.class);
    private String prefix = "system/paper";

    @Autowired
    private IPaperService paperService;
    @Autowired
    private IPaperTypeService paperTypeService;
    @Autowired
    private PaperCommentRepository paperCommentRepository;
    @RequiresPermissions("system:paper:view")
    @GetMapping()
    public String paper(ModelMap mmap) {

        List<PaperType> paperTypeList = paperTypeService.selectPaperTypeList(new PaperType());
        mmap.put("paperTypeList",paperTypeList);
        return prefix + "/paper";
    }

    /**
     * 查询论文列表
     */
    @RequiresPermissions("system:paper:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Paper paper) {
        startPage();
        //默认上传页仅显示登陆者自己的论文
        paper.setUser(ShiroUtils.getSysUser().getUserName());
        List<Paper> list = paperService.selectPaperList(paper);
        return getDataTable(list);
    }


    /**
     * 导出论文列表
     */
    @RequiresPermissions("system:paper:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Paper paper) {
        List<Paper> list = paperService.selectPaperList(paper);
        ExcelUtil<Paper> util = new ExcelUtil<Paper>(Paper.class);
        return util.exportExcel(list, "paper");
    }

    /**
     * 新增论文
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        List<PaperType> paperTypeList = paperTypeService.selectPaperTypeList(new PaperType());
        mmap.put("paperTypeList",paperTypeList);
        mmap.put("paper",new Paper());
        return prefix + "/add";
    }

    /**
     * 新增保存论文
     */
    @RequiresPermissions("system:paper:add")
    @Log(title = "论文", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MultipartFile file, Paper paper) {
        int rtn = 0;
       // logger.info(paper.toString());
        try {
            if (file == null) {
                logger.info("file is null");
            }
            else{
                String filePath = FileUploadUtils.upload(FileUploadUtils.getDefaultBaseDir(),file,".pdf");
                logger.info("uploadFilePath="+filePath);
                paper.setPaperUrl("/profile/"+filePath);
                paper.setFileUrl(FileUploadUtils.getDefaultBaseDir()+filePath);
            }
            // 修改
            if (paper.getUpdateFlag() == 1 && paper.getId() > 0) {
                paper = paperService.updatePaperInfoByPaper(paper);
                rtn = paperService.updatePaper(paper);
            } else {//新增
                rtn = paperService.insertPaper(paper);
            }
        } catch (Exception e) {
            logger.error("保存失败，请检查后重试！", e);
            return error(e.getMessage());
        }
        return toAjax(rtn);
    }

    /**
     * 修改论文
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Paper paper = paperService.selectPaperById(id);
        mmap.put("paper", paper);
        List<PaperType> paperTypeList = paperTypeService.selectPaperTypeList(new PaperType());
        mmap.put("paperTypeList",paperTypeList);
        String ip = IpUtils.getHostIp();
        mmap.put("PaperUrl",ip+paper.getPaperUrl());
        return prefix + "/add";
    }

    /**
     * 修改保存论文
     */
    @RequiresPermissions("system:paper:edit")
    @Log(title = "论文", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Paper paper) {
        return toAjax(paperService.updatePaper(paper));
    }
//    private Files dealFile(MultipartFile file, Files files) throws Exception {
//        //if (file == null || "2".equals(files.getType())) return files;
//        if (file == null ){
//            return files;
//        }
//        String suffix = FileUploadUtils.dealName(file.getOriginalFilename());
//        if (StringUtils.isEmpty(suffix)){
//            throw new Exception();
//        }
//        String name = Save_Url + files.getFileName() + "." + suffix;
//        files.setUrl(name);
//        files.setSuffix(suffix);
//
//        return files;
//    }
    /**
     * 删除论文
     */
    @RequiresPermissions("system:paper:remove")
    @Log(title = "论文", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(paperService.deletePaperByIds(ids));
    }

    /**
     * 预览论文
     */
    @GetMapping("/preview/{id}")
    public String preview(@PathVariable("id") Integer id, ModelMap mmap) {
        Paper paper = paperService.selectPaperById(id);
        //论文预览数+1
        int previewTimes = paper.getPreviewTimes();
        paper.setPreviewTimes(previewTimes+1);
        paperService.updatePaper(paper);

        mmap.put("paper", paper);
        return prefix + "/preview";
    }
    /**
     * 下载论文数+1
     */
    @PostMapping("/download")
    @ResponseBody
    public AjaxResult download(Integer paperId) {
        Paper paper = paperService.selectPaperById(paperId);
        paper.setDownloadTimes(paper.getDownloadTimes()+1);
        return toAjax(paperService.updatePaper(paper));
    }
    /**
     * 查看论文评论
     */
    @GetMapping("/viewComments/{id}")
    public String viewComments(@PathVariable("id") Integer id, ModelMap mmap) {
        List<PaperComment> paperCommentList = paperCommentRepository.findByPaperId(id);

        mmap.put("paperCommentList",paperCommentList);
        mmap.put("commentsNumber",paperCommentList.size());
        return prefix + "/comments";
    }
}
