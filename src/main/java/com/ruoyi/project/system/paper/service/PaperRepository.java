package com.ruoyi.project.system.paper.service;


import com.ruoyi.project.system.paper.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author  ricardo
 * Description: paper；类的jpa
 * Date: 2018/8/22
 */
public interface PaperRepository extends JpaRepository<Paper,Integer> {

}
