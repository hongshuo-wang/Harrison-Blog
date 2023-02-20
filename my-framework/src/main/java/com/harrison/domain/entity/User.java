package com.harrison.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.harrison.validate.ValidGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用户表(SysUser)表实体类
 *
 * @author Harrison
 * @date 2023-02-17 00:21:52
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User {
    @TableId
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = ValidGroup.Crud.Create.class)
    private String userName;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称为必填项", groups = ValidGroup.Crud.Create.class)
    private String nickName;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = ValidGroup.Crud.Create.class)
    private String password;
    /**
     * 用户类型：0代表普通用户，1代表管理员
     */
    private String type;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    @Email(message = "请填写正确的邮箱地址", groups = ValidGroup.Crud.Create.class)
    private String email;
    /**
     * 手机号
     */
    private String phonenumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 创建人的用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
}

