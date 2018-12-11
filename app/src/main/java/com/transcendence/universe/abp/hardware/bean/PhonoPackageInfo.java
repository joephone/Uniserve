package com.transcendence.universe.abp.hardware.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Joephone on 2018/10/24 15:50
 * E-Mail Address：joephonechen@gmail.com
 */

public class PhonoPackageInfo {
    private String pkgname;
    private String versionName;
    private int versionId;
    private Drawable icon;
    private CharSequence label;

    /**
     * 无参的构造方法
     */
    public PhonoPackageInfo() {

    }

    /**
     * 有参的构造方法
     */
    public PhonoPackageInfo(String pkgname, String versionName, int versionId,
                            Drawable icon, CharSequence label) {
        this.pkgname = pkgname;
        this.versionName = versionName;
        this.versionId = versionId;
        this.icon = icon;
        this.label = label;
    }


    public String getPkgname() {
        return pkgname;
    }

    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public CharSequence getLabel() {
        return label;
    }

    public void setLabel(CharSequence label) {
        this.label = label;
    }
}
