package com.LinSY.backend.utils.pojo;

/**
 * @ClassName EasyUITreeNode
 * @Author LinSY
 * @Date 2018/9/26 0:45
 * @Version 1.0
 * @Description EasyUI框架树的节点
 */

public class EasyUITreeNode {

    private long id;
    private String text;
    private String state;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

}
