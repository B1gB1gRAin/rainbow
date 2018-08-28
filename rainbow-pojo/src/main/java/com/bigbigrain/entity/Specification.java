package com.bigbigrain.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@TableName("tb_specification")
public class Specification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    private String specName;

    @TableField(exist=false)
    private List<SpecificationOption> options;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public List<SpecificationOption> getOptions() {
		return options;
	}

	public void setOptions(List<SpecificationOption> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Specification [id=" + id + ", specName=" + specName + ", options=" + options + "]";
	}

}
