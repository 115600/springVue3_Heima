package org.wineky.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
//使用Lombok(@Data)生成setter getter ToString
@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    // 返回的响应不包含password
    @JsonIgnore
    private String password;//密码

    // 考虑是否在前端校验，前端校验更适用于用户
    @NotEmpty  // 自定义规则
    @Pattern(regexp = "^\\S{1,10}$") //1-10非空字符
    private String nickname;//昵称
    @NotEmpty
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
