package com.cs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Salesforce CDC 事件日志实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("salesforce_cdc_log")
@Schema(name = "SalesforceCdcLog", description = "Salesforce CDC 事件日志表")
public class SalesforceCdcLog extends Model<SalesforceCdcLog> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联的连接 ID")
    @TableField("store_id")
    private Long storeId;

    @Schema(description = "对象类型: Account/Contact/Lead/Opportunity")
    @TableField("object_type")
    private String objectType;

    @Schema(description = "记录 ID")
    @TableField("record_id")
    private String recordId;

    @Schema(description = "变更类型: create/update/delete/undelete")
    @TableField("change_type")
    private String changeType;

    @Schema(description = "变更事件时间")
    @TableField("change_event_date")
    private LocalDateTime changeEventDate;

    @Schema(description = "重放 ID")
    @TableField("replay_id")
    private Long replayId;

    @Schema(description = "CDC 负载")
    @TableField("payload")
    private String payload;

    @Schema(description = "请求头")
    @TableField("headers")
    private String headers;

    @Schema(description = "处理状态: pending/success/failed")
    @TableField("processed_status")
    private String processedStatus;

    @Schema(description = "错误信息")
    @TableField("error_message")
    private String errorMessage;

    @Schema(description = "接收时间")
    @TableField("received_time")
    private LocalDateTime receivedTime;

    @Schema(description = "处理时间")
    @TableField("processed_time")
    private LocalDateTime processedTime;

    @Schema(description = "重试次数")
    @TableField("retry_count")
    private Integer retryCount;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
