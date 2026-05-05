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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Salesforce Contact（联系人）实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("salesforce_contact")
@Schema(name = "SalesforceContact", description = "Salesforce Contact（联系人）表")
public class SalesforceContact extends Model<SalesforceContact> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联的连接 ID")
    @TableField("store_id")
    private Long storeId;

    @Schema(description = "Salesforce Contact ID")
    @TableField("contact_id")
    private String contactId;

    @Schema(description = "关联的 Account ID")
    @TableField("account_id")
    private String accountId;

    @Schema(description = "名字")
    @TableField("first_name")
    private String firstName;

    @Schema(description = "姓氏")
    @TableField("last_name")
    private String lastName;

    @Schema(description = "全名")
    @TableField("name")
    private String name;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "电话")
    @TableField("phone")
    private String phone;

    @Schema(description = "手机")
    @TableField("mobile_phone")
    private String mobilePhone;

    @Schema(description = "家庭电话")
    @TableField("home_phone")
    private String homePhone;

    @Schema(description = "职位")
    @TableField("title")
    private String title;

    @Schema(description = "部门")
    @TableField("department")
    private String department;

    @Schema(description = "邮寄街道")
    @TableField("mailing_street")
    private String mailingStreet;

    @Schema(description = "邮寄城市")
    @TableField("mailing_city")
    private String mailingCity;

    @Schema(description = "邮寄省/州")
    @TableField("mailing_state")
    private String mailingState;

    @Schema(description = "邮寄邮编")
    @TableField("mailing_postal_code")
    private String mailingPostalCode;

    @Schema(description = "邮寄国家")
    @TableField("mailing_country")
    private String mailingCountry;

    @Schema(description = "所有者 ID")
    @TableField("owner_id")
    private String ownerId;

    @Schema(description = "所有者姓名")
    @TableField("owner_name")
    private String ownerName;

    @Schema(description = "线索来源")
    @TableField("lead_source")
    private String leadSource;

    @Schema(description = "生日")
    @TableField("birthdate")
    private LocalDate birthdate;

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
