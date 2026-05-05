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
 * Salesforce Lead（线索）实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("salesforce_lead")
@Schema(name = "SalesforceLead", description = "Salesforce Lead（线索）表")
public class SalesforceLead extends Model<SalesforceLead> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联的连接 ID")
    @TableField("store_id")
    private Long storeId;

    @Schema(description = "Salesforce Lead ID")
    @TableField("lead_id")
    private String leadId;

    @Schema(description = "名字")
    @TableField("first_name")
    private String firstName;

    @Schema(description = "姓氏")
    @TableField("last_name")
    private String lastName;

    @Schema(description = "全名")
    @TableField("name")
    private String name;

    @Schema(description = "公司")
    @TableField("company")
    private String company;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "电话")
    @TableField("phone")
    private String phone;

    @Schema(description = "手机")
    @TableField("mobile_phone")
    private String mobilePhone;

    @Schema(description = "职位")
    @TableField("title")
    private String title;

    @Schema(description = "行业")
    @TableField("industry")
    private String industry;

    @Schema(description = "状态")
    @TableField("status")
    private String status;

    @Schema(description = "评级")
    @TableField("rating")
    private String rating;

    @Schema(description = "年收入")
    @TableField("annual_revenue")
    private BigDecimal annualRevenue;

    @Schema(description = "街道")
    @TableField("street")
    private String street;

    @Schema(description = "城市")
    @TableField("city")
    private String city;

    @Schema(description = "省/州")
    @TableField("state")
    private String state;

    @Schema(description = "邮编")
    @TableField("postal_code")
    private String postalCode;

    @Schema(description = "国家")
    @TableField("country")
    private String country;

    @Schema(description = "所有者 ID")
    @TableField("owner_id")
    private String ownerId;

    @Schema(description = "所有者姓名")
    @TableField("owner_name")
    private String ownerName;

    @Schema(description = "线索来源")
    @TableField("lead_source")
    private String leadSource;

    @Schema(description = "转换的 Account ID")
    @TableField("converted_account_id")
    private String convertedAccountId;

    @Schema(description = "转换的 Contact ID")
    @TableField("converted_contact_id")
    private String convertedContactId;

    @Schema(description = "转换的 Opportunity ID")
    @TableField("converted_opportunity_id")
    private String convertedOpportunityId;

    @Schema(description = "是否已转换")
    @TableField("is_converted")
    private Boolean isConverted;

    @Schema(description = "转换日期")
    @TableField("converted_date")
    private LocalDateTime convertedDate;

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
