package com.imooc.wangyouzhan.chatclient.TreeWiget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyouzhan on 16/11/13.
 */

public class Node {

    private int id;

    public Node() {
    }

    public Node(int id, int pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    /**
     * 根节点的pid = 0
     */
    private int pid = 0;

    private  String name;

    /**
     * 树的层级
     */
    private int leve;


    /**
     * 是否展开
     */
    private boolean isExpand = false;

    private int icon;


    private boolean root;


    private boolean parentExpand;

    public boolean isParentExpand() {
        if (parent == null) {
            return false;
        }
        return parent.isExpand();
    }


    public boolean isLeaf(){
        return children.size() == 0;
    }



    public boolean isRoot() {
        return parent == null;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    private Node parent;
    private List<Node> children = new ArrayList<Node>();


    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeve() {
        return parent == null ? 0: parent.getLeve() + 1;
    }

    public void setLeve(int leve) {
        this.leve = leve;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
        if (!isExpand){
            for(Node node : children){
                node.setExpand(false);
            }
        }
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
