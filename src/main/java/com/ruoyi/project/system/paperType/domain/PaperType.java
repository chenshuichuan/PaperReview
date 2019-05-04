package com.ruoyi.project.system.paperType.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;

/**
 * 论文类型表 pr_paper_type
 *
 * @author ruoyi
 * @date 2019-05-04
 */
@Entity
@Table(name = "pr_paper_type")
public class PaperType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 论文类型
     */
    @Column(name = "type_lable")
    private String typeLable;
    /**
     * 论文类型值
     */
    @Column(name = "type_value")
    private String typeValue;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTypeLable(String typeLable) {
        this.typeLable = typeLable;
    }

    public String getTypeLable() {
        return typeLable;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("typeLable", getTypeLable())
                .append("typeValue", getTypeValue())
                .toString();
    }
}
