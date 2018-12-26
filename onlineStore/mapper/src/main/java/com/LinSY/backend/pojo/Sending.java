package com.LinSY.backend.pojo;

import java.io.Serializable;

public class Sending implements Serializable {
    private Long sendingId;

    private Long itemId;

    private String address;

    private Integer num;

    private Long phone;

    private String name;

    private Integer status;

    private Long userId;

    private static final long serialVersionUID = 1L;

    public Long getSendingId() {
        return sendingId;
    }

    public void setSendingId(Long sendingId) {
        this.sendingId = sendingId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sendingId=").append(sendingId);
        sb.append(", itemId=").append(itemId);
        sb.append(", address=").append(address);
        sb.append(", num=").append(num);
        sb.append(", phone=").append(phone);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}