# Salesforce 连接器 v1.2

Salesforce CRM 数据同步连接器，支持 Account、Contact、Lead、Opportunity 等核心对象的数据同步，以及 CDC（Change Data Capture）实时数据变更订阅。

## 功能特性

- **多对象同步**：支持 Account（客户）、Contact（联系人）、Lead（线索）、Opportunity（商机）
- **CDC 实时订阅**：支持 Salesforce CDC 实时数据变更捕获
- **定时任务**：内置定时同步任务，可自定义 Cron 表达式
- **手动同步**：支持按日期范围手动触发数据同步
- **连接管理**：支持多 Salesforce 组织连接配置
- **同步日志**：完整的同步日志记录和错误追踪

## 技术栈

| 组件 | 版本 |
|------|------|
| Spring Boot | 3.5.10 |
| JDK | 17 |
| MyBatis-Plus | 3.5.16 |
| PostgreSQL | 15 |
| Nacos | 2.5.1 |
| Redis | 7.x |
| Docker Compose | v2.39.3 |

## 数据库表结构

- `salesforce_store` - Salesforce 连接配置表
- `salesforce_account` - Account（客户）数据表
- `salesforce_contact` - Contact（联系人）数据表
- `salesforce_lead` - Lead（线索）数据表
- `salesforce_opportunity` - Opportunity（商机）数据表
- `salesforce_cdc_log` - CDC 事件日志表
- `salesforce_sync_log` - 同步日志表

## 快速开始

### 1. 数据库初始化

```bash
# 创建数据库
createdb -U postgres salesforce_connector_db

# 执行初始化脚本
psql -U postgres -d salesforce_connector_db -f src/main/resources/db/schema.sql
```

### 2. 配置 Nacos

在 Nacos 控制台创建配置：
- Data ID: `salesforce-open-connector-server-dev.properties`
- Group: `cs`
- 配置内容：参考部署指南

### 3. 启动应用

```bash
# Maven 编译打包
mvn clean package -DskipTests

# Docker 方式启动
docker compose up -d

# 或本地启动
java -jar target/livepulse-connector-salesforce-open.jar
```

### 4. 访问 Swagger UI

```
http://localhost:23008/swagger-ui.html
```

## API 文档

### 连接管理

- `GET /salesforce/store` - 查询所有连接
- `POST /salesforce/store` - 创建连接
- `PUT /salesforce/store/{id}` - 更新连接
- `DELETE /salesforce/store/{id}` - 删除连接
- `POST /salesforce/store/{id}/test` - 测试连接

### 数据同步

- `POST /salesforce/store/{id}/sync/accounts` - 同步 Account
- `POST /salesforce/store/{id}/sync/contacts` - 同步 Contact
- `POST /salesforce/store/{id}/sync/leads` - 同步 Lead
- `POST /salesforce/store/{id}/sync/opportunities` - 同步 Opportunity

## 配置说明

### Salesforce API 配置

```yaml
salesforce:
  api:
    version: 60.0
    timeout: 30
    connect-timeout: 10
```

### 定时任务配置

```yaml
salesforce:
  scheduled:
    enabled: true
    account-sync:
      enabled: true
      cron: "0 0 */4 * * ?"
    contact-sync:
      enabled: true
      cron: "0 0 */4 * * ?"
    lead-sync:
      enabled: true
      cron: "0 0 */4 * * ?"
    opportunity-sync:
      enabled: true
      cron: "0 0 */4 * * ?"
```

### CDC 配置

```yaml
salesforce:
  cdc:
    enabled: false
    objects:
      - Account
      - Contact
      - Lead
      - Opportunity
```

## 部署指南

详细部署文档请参考：[部署指南.md](./部署指南.md)

## 技术支持

- **技术支持邮箱**：question@creasourse.com
- **在线文档**：https://docs.livepulse.com

## 版本历史

### v1.2.0 (2026-05-04)
- 初始版本
- 支持 Account、Contact、Lead、Opportunity 同步
- 支持 CDC 实时订阅
- 支持定时同步任务
