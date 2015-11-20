package org.mm.balance.business.dto.tools;

public enum EventImage {

    IMAGE1(1),
    IMAGE2(2);

    private int mImageId;

    EventImage(int id) {
        mImageId = id;
    }

    public int getImageId() {
        return mImageId;
    }
}
