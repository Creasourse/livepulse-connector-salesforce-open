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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Salesforce Opportunity（商机）实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("salesforce_opportunity")
@Schema(name = "SalesforceOpportunity", description = "Salesforce Opportunity（商机）表")
public class SalesforceOpportunity extends Model<SalesforceOpportunity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联的连接 ID")
    @TableField("store_id")
    private Long storeId;

    @Schema(description = "Salesforce Opportunity ID")
    @TableField("opportunity_id")
    private String opportunityId;

    @Schema(description = "关联的 Account ID")
    @TableField("account_id")
    private String accountId;

    @Schema(description = "商机名称")
    @TableField("opportunity_name")
    private String opportunityName;

    @Schema(description = "类型")
    @TableField("type")
    private String type;

    @Schema(description = "阶段")
    @TableField("stage_name")
    private String stageName;

    @Schema(description = "金额")
    @TableField("amount")
    private BigDecimal amount;

    @Schema(description = "概率（百分比）")
    @TableField("probability")
    private BigDecimal probability;

    @Schema(description = "预期收入")
    @TableField("expected_revenue")
    private BigDecimal expectedRevenue;

    @Schema(description = "预计关闭日期")
    @TableField("close_date")
    private LocalDate closeDate;

    @Schema(description = "线索来源")
    @TableField("lead_source")
    private String leadSource;

    @Schema(description = "营销活动 ID")
    @TableField("campaign_id")
    private String campaignId;

    @Schema(description = "所有者 ID")
    @TableField("owner_id")
    private String ownerId;

    @Schema(description = "所有者姓名")
    @TableField("owner_name")
    private String ownerName;

    @Schema(description = "描述")
    @TableField("description")
    private String description;

    @Schema(description = "Salesforce 创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "Salesforce 更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "是否已处理")
    @TableField("processed")
    private Boolean processed;

    @Schema(description = "处理时间")
    @TableField("processed_time")
    private LocalDateTime processedTime;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
