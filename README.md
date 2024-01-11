# Spring Boot使用JPA进行基于角色的用户认证和授权

### 1、了解 Spring Security 中的角色（Role）和权限(Authority)

- Role和Authority
在 Spring Security 中，角色和权限表示可以授予用户的权限或特权。虽然两者看起来是一样的，但是，两者之间有一个微妙的区别：
角色表示通常与一组用户关联的高级粗粒度权限。例如，我们可以定义“USER”、“ADMIN”、“MANAGER”等角色。可以为应用程序用户分配一个或多个角色，这些角色决定他们可以访问哪些操作或资源。
```
@Secured("ROLE_ADMIN")
public void deleteData() {
    …
}
```
权限（也称为特权或权限）表示应用程序中的细粒度访问权限。权限授予单个用户或特定用户组，与分配给一组用户的角色相反。它们允许访问应用程序中的特定功能或资源。例如，可以定义不同的权限以允许用户“创建”、“更新”和“删除”特定资源。
```
@PreAuthorize("hasAuthority('DELETE')")
public void deleteData() {
    …
}
```

- GrantedAuthority
身份验证讨论实现如何存储 GrantedAuthority 对象的列表，这些对象代表已授予委托人的权限。GrantedAuthority 对象由 AuthenticationManager 插入到 Authentication 对象中，再之后做授权决策时由 AccessDecisionManager 实例读取。
GrantedAuthority 接口只有一种方法：
```
String getAuthority（）;
```

在 Spring Security 中，我们可以将每个 GrantedAuthority 视为一个单独的权限。例如READ_AUTHORITY、WRITE_PRIVILEGE 甚至 CAN_EXECUTE_AS_ROOT，名称是任意的。
当直接使用 GrantedAuthority 时，例如通过使用 hasAuthority（'READ_AUTHORITY'） 表达式，我们会以细粒度的方式限制访问内容。
Spring Security 包含一个具体的 GrantedAuthority 实现：SimpleGrantedAuthority。此实现允许将任何用户指定的 String 转换为 GrantedAuthority。安全体系结构中包含的所有 AuthenticationProvider 实例都使用 SimpleGrantedAuthority 来填充 Authentication 对象。

- 基于角色的Authority
同样，在Spring Security中，我们可以将每个角色视为一个粗粒度的GrantedAuthority，它表示为 String 并以“ROLE”为前缀。当直接使用角色时，例如通过像hasRole（“ADMIN”）这样的表达式，我们会以粗粒度的方式限制访问。
默认情况下，基于角色的授权规则将ROLE_作为前缀。这意味着，如果存在要求安全上下文具有“ADMIN”角色的授权规则，则 Spring Security 将默认查找返回“ROLE_ADMIN”的 GrantedAuthority#getAuthority。
注意默认的“ROLE”前缀是可配置的，如下代码配置了自定义前缀MYPREEFIX。
```
@Bean
static GrantedAuthorityDefaults grantedAuthorityDefaults() {
	return new GrantedAuthorityDefaults("MYPREFIX_");
}
```

### 2、开发环境

当前项目使用以下开发环境：
- 操作系统：Windows 11
- JDK 17
- 数据库：PostgreSQL 15.2
- IDE：VS Code（版本1.83.1），并安装插件：Extension Pack for Java、Spring Boot Extension Pack