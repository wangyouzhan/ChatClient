package com.imooc.wangyouzhan.chatclient.previewphotos;

/**
 * Created by wangyouzhan on 2016/11/20.
 * Email 936804097@qq.com
 */

public class FolderBean {


    /**
     * 当前文件夹路径
     */
    private String dir;
    private String firstImgPath;
    private String name;
    private int count;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;

        int lastIndexOf = this.dir.lastIndexOf("/");
        this.name = this.dir.substring(lastIndexOf);
    }

    public String getFirstImgPath() {
        return firstImgPath;
    }

    public void setFirstImgPath(String firstImgPath) {
        this.firstImgPath = firstImgPath;
    }

    public String getName() {
        return name;
    }


}
























