package com.lucas.refacotring.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lucas
 * @Description 影片
 * @Date 2019/12/17
 **/
public class Movie {

    public static final int CHILDRENS = 2;

    public static final int REGULAR = 0;

    public static final int NEW_RELEASE =1;

    @Getter
    @Setter
    private String _title;

    @Getter
    @Setter
    private Integer _priceCode;


    public Movie(String title,int priceCode){
        _title=title;
        _priceCode=priceCode;
    }


    public double getCharge(int daysRented){
        double result =0;
        switch (get_priceCode()){
            case Movie.REGULAR:
                result+=2;
                if (daysRented>2){
                    result+=(daysRented-2)*1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result+=daysRented+3;
                break;
            case Movie.CHILDRENS:
                result+=1.5;
                if (daysRented>3){
                    result+=(daysRented-3)*1.5;
                }
                break;
        }
        return result;
    }

    public int getFrequentRenterPoints(int dayRented){
        if (get_priceCode()==Movie.NEW_RELEASE && dayRented > 1){
            return 2;
        }else {
            return 1;
        }
    }




}
