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

    /**
     * 价格代号
     *
     * Further：
     *  由Price类所取代
     */
    @Getter
    @Setter
    private Integer _priceCode;


    private Price _price;



    public Movie(String title,int priceCode){
        _title=title;
        _priceCode=priceCode;
    }


    public int getPriceCode(){
        return _price.getPriceCode();
    }

    /**
     *
     * Further:
     *   I  move to use getCharge of Price class  ---sucessful
     *
     * @param daysRented
     * @return
     */
    public double getCharge(int daysRented){
        return _price.getCharge(daysRented);
    }

    /**
     * 获得常客积分
     * @param dayRented
     * @return
     */
    public int getFrequentRenterPoints(int dayRented){
        /*if (get_priceCode()==Movie.NEW_RELEASE && dayRented > 1){
            return 2;
        }else {
            return 1;
        }*/
        return _price.getFrequentRenterPoints(dayRented);
    }




}
