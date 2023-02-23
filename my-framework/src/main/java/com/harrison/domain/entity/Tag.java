package com.harrison.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签(Tag)表实体类
 *
 * @author Harrison
 * @date 2023-02-21 23:23:17
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hs_tag")
public class Tag {
    @TableId
    private Long id;
    /**
     * 标签名
     */
    private String name;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
    /**
     * 备注
     */
    private String remark;
}

