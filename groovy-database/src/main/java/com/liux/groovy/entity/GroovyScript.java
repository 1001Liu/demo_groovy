package com.liux.groovy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * groovy脚本
 * </p>
 *
 * @author liuxin
 * @since 2022-07-28
 */
@Getter
@Setter
@TableName("groovy_script")
public class GroovyScript implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 脚本名称
     */
    private String groovyName;

    /**
     * 脚本名称
     */
    private String beanName;

    /**
     * 脚本路径
     */
    private String path;

    /**
     * 脚本内容
     */
    private String groovyContent;

    /**
     * 是否被删除，0：正常；1：删除
     */
    private Integer isDelete;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    private LocalDateTime updatedTime;


}
