package com.grepthor.hyderabadmetro.object;

/**
 * Created by sachin on 14/4/17.
 */

public class GridViewRowItem {
    private int imageId;
    private String title;

    public GridViewRowItem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}