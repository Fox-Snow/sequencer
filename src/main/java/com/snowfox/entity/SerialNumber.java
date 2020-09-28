package com.snowfox.entity;

import java.io.Serializable;

/**
 * @program: sequencer
 * @description:
 * @author: snowfox
 * @create: 2020-09-28 15:17
 */
public class SerialNumber implements Serializable {

    private String type;
    private Long num;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
