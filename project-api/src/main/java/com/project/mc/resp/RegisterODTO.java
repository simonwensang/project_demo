package com.project.mc.resp;

import lombok.Data;

@Data
public class RegisterODTO {

    private String machineCode;

    private String password;

    private String alias; //极光别名

}
