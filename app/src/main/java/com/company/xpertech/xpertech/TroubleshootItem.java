package com.company.xpertech.xpertech;

/**
 * Created by Skylar Gail on 7/28/2018.
 */

public class TroubleshootItem {

    int index;
    String item;

    public TroubleshootItem() {}

    public TroubleshootItem(int index, String item) {
        this.index = index;
        this.item = item;
    }

    public TroubleshootItem(int index){
        this.index = index;

    }

    public TroubleshootItem(String item) {
        this.item = item;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
