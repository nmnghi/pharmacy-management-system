package com.example.pharmacy;

import java.sql.Date;

public class medicineData {
    private Integer medicineId;
    private String productName;
    private String category;
    private String status;
    private Integer price;
    private Date date;

    public medicineData(Integer medicineId, String productName, String category, String status , Integer price, Date date){
        this.medicineId = medicineId;
        this.productName = productName;
        this.category = category;
        this.status = status;
        this.price = price;
        this.date = date;
    }

    public Integer getMedicineId(){
        return medicineId;
    }

    public String getProductName(){
        return productName;
    }

    public String getCategory(){
        return category;
    }
    public String getStatus(){
        return status;
    }

    public Integer getPrice(){
        return price;
    }

    public Date getDate(){
        return date;
    }

}
