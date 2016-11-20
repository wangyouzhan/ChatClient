package com.imooc.wangyouzhan.chatclient.TreeWiget;

import com.imooc.wangyouzhan.chatclient.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyouzhan on 16/11/13.
 */

public class TreeHelper {


    public static <T> List<Node> convertDatas2Nodes(List<T> datas) {
        List<Node> nodes = new ArrayList<Node>();
        Node node = null;
        for (T t : datas) {

            int id = -1;
            int pid = -1;
            String label = null;
            node = new Node();
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            try {
                for (Field field : fields) {
                    if (field.getAnnotation(TreeNodeId.class) != null) {
                        field.setAccessible(true);
                        id = field.getInt(t);
                    }
                    if (field.getAnnotation(TreeNodePid.class) != null) {
                        field.setAccessible(true);
                        pid = field.getInt(t);
                    }
                    if (field.getAnnotation(TreeNodeLabel.class) != null) {
                        field.setAccessible(true);
                        label = (String) field.get(t);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            node = new Node(id, pid, label);
            nodes.add(node);

        }

        /**
         * 设置node间的节点关系
         */
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);
                if (m.getPid() == n.getId()) {
                    n.getChildren().add(m);
                    m.setParent(n);
                } else if (m.getId() == n.getPid()) {
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }


        for (Node n : nodes) {
            setNodeIcon(n);
        }


        return nodes;
    }

    public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel) throws IllegalArgumentException{

        List<Node> result = new ArrayList<Node>();
        List<Node> nodes = convertDatas2Nodes(datas);
        //获取根节点
        List<Node> rootNodes = getRootNodes(nodes);

        for (Node node : rootNodes){
            addNode(result,node, defaultExpandLevel,1);
        }
        return result;
    }

    /**
     * 把一个节点的所有孩子节点都放入result中
     * @param result
     * @param node
     * @param defaultExpandLevel
     * @param i
     */
    private static void addNode(List<Node> result, Node node, int defaultExpandLevel, int currentLevel) {

        result.add(node);
        if (defaultExpandLevel >= currentLevel){
            node.setExpand(true);
        }
        if (node.isLeaf()){
            return;
        }

        for (int i = 0; i < node.getChildren().size(); i++)
        {
            addNode(result,node.getChildren().get(i), defaultExpandLevel, currentLevel + 1);
        }

    }

    /**
     * 过滤
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNodes(List<Node> nodes){
        List<Node> result = new ArrayList<Node>();

        for (Node node : nodes){
            if (node.isRoot() || node.isParentExpand()){
                setNodeIcon(node);
                result.add(node);
            }
        }

        return result;
    }


    /**
     * 从所有节点中获取root
     * @param nodes
     * @return
     */
    private  static  List<Node> getRootNodes(List<Node> nodes){
        List<Node> root = new ArrayList<Node>();

        for (Node node : nodes){
            if (node.isRoot()){
                root.add(node);
            }
        }
        return root;
    }



    /**
     * 为Node设置图标
     * @param n
     */
    private static void setNodeIcon(Node n) {

        if (n.getChildren().size() > 0 && n.isExpand()){
            n.setIcon(R.mipmap.arrow_down);
        }else if (n.getChildren().size() > 0 && !n.isExpand()){
            n.setIcon(R.mipmap.arrow_left);
        }else{
            n.setIcon(-1);
        }
    }

}
