package com.github.syanfox.entity;

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
    private String prefix;

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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null?"":prefix;
    }

    @Override
    public int hashCode() {
        String result = type + num + prefix;
        return result.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        SerialNumber sn = (SerialNumber) obj;
        return this.getType().equals(sn.type) && (this.num.equals(sn.num))&& (this.prefix.equals(sn.prefix));
    }
}
