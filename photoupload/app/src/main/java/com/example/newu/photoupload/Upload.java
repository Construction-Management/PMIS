package com.example.newu.photoupload;

public class Upload {
    private String mName;
    private String mImageUrl;
    public Upload(){}
    public Upload(String nname,String nimageurl)
    {
        if(nname.trim().equals(""))
        {
            nname="No Name";
        }
    mName=nname;
    mImageUrl=nimageurl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
