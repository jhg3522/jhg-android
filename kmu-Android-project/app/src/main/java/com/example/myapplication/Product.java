package com.example.myapplication;

public class Product {
    private String img_url;
    private String name;
    private String cost;
    private String size;

    public Product(String img, String name, String cost, String size) {
        this.img_url = img;
        this.name = name;
        this.cost = cost;
        this.size = size;
    }

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

    @Override
    public String toString() {
        return "Product{" +
                "img='" + img_url + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +"원"+
                ", size='" + size + '\'' +
                '}';
    }
}
