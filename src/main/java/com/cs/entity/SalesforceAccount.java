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
 * Salesforce Account（客户）实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("salesforce_account")
@Schema(name = "SalesforceAccount", description = "Salesforce Account（客户）表")
public class SalesforceAccount extends Model<SalesforceAccount> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联的连接 ID")
    @TableField("store_id")
    private Long storeId;

    @Schema(description = "Salesforce Account ID")
    @TableField("account_id")
    private String accountId;

    @Schema(description = "客户名称")
    @TableField("account_name")
    private String accountName;

    @Schema(description = "客户编号")
    @TableField("account_number")
    private String accountNumber;

    @Schema(description = "类型")
    @TableField("type")
    private String type;

    @Schema(description = "行业")
    @TableField("industry")
    private String industry;

    @Schema(description = "账单街道")
    @TableField("billing_street")
    private String billingStreet;

    @Schema(description = "账单城市")
    @TableField("billing_city")
    private String billingCity;

    @Schema(description = "账单省/州")
    @TableField("billing_state")
    private String billingState;

    @Schema(description = "账单邮编")
    @TableField("billing_postal_code")
    private String billingPostalCode;

    @Schema(description = "账单国家")
    @TableField("billing_country")
    private String billingCountry;

    @Schema(description = "发货街道")
    @TableField("shipping_street")
    private String shippingStreet;

    @Schema(description = "发货城市")
    @TableField("shipping_city")
    private String shippingCity;

    @Schema(description = "发货省/州")
    @TableField("shipping_state")
    private String shippingState;

    @Schema(description = "发货邮编")
    @TableField("shipping_postal_code")
    private String shippingPostalCode;

    @Schema(description = "发货国家")
    @TableField("shipping_country")
    private String shippingCountry;

    @Schema(description = "电话")
    @TableField("phone")
    private String phone;

    @Schema(description = "传真")
    @TableField("fax")
    private String fax;

    @Schema(description = "网站")
    @TableField("website")
    private String website;

    @Schema(description = "所有者 ID")
    @TableField("owner_id")
    private String ownerId;

    @Schema(description = "所有者姓名")
    @TableField("owner_name")
    private String ownerName;

    @Schema(description = "年收入")
    @TableField("annual_revenue")
    private BigDecimal annualRevenue;

    @Schema(description = "员工数量")
    @TableField("number_of_employees")
    private Integer numberOfEmployees;

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
