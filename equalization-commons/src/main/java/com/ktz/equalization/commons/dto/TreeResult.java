package com.ktz.equalization.commons.dto;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/15 下午2:25
 */
public class TreeResult {

    private Long id;
    private String title;
    private Boolean checked;
    private List<TreeResult> children;

    public TreeResult() {
        this.checked = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<TreeResult> getChildren() {
        return children;
    }

    public void setChildren(List<TreeResult> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeResult{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", checked=" + checked +
                ", children=" + children +
                '}';
    }
}
