package com.ruoyi.project.system.paperType.service;


import com.ruoyi.project.system.paper.domain.Paper;
import com.ruoyi.project.system.paperType.domain.PaperType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: paper；类的jpa
 * Date: 2018/8/22
 */
public interface PaperTypeRepository extends JpaRepository<PaperType,Integer> {

}
