package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Product implements Parcelable {
    private String num;
    private String img_url;
    private String name;
    private String cost;
    private String size;
    private boolean check = false;

    public Product(){}

    public Product(String num,String img, String name, String cost, String size) {
        this.num = num;
        this.img_url = img;
        this.name = name;
        this.cost = cost;
        this.size = size;
    }

    protected Product(Parcel in) {
        num = in.readString();
        img_url = in.readString();
        name = in.readString();
        cost = in.readString();
        size = in.readString();
        check = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getImg() {
        return img_url;
    }

    public void setImg(String img) {
        this.img_url = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    @Override
    public String toString() {
        return "Product{" +
                "img='" + img_url + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +"원"+
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(num);
        dest.writeString(img_url);
        dest.writeString(name);
        dest.writeString(cost);
        dest.writeString(size);
        dest.writeByte((byte) (check ? 1 : 0));
    }

    public Map<String, Object> Product_Map(){
        Map<String, Object> map = new HashMap<>();
        map.put("num",num);
        map.put("name",name);
        map.put("cost",cost);
        map.put("size",size);
        map.put("img_url",img_url);
        return map;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
