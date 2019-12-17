package com.lucas.refacotring.entity;

import lombok.Getter;

/**
 * @author lucas
 * @Description 租赁
 * @Date 2019/12/17
 **/
public class Rental {

    /**
     * rental of movie
     */
    @Getter
    private Movie _movie;

    /**
     * 租期长度
     */
    @Getter
    private int _dayRentied;

    public Rental(Movie movie,int dayRentied){
        _movie=movie;
        _dayRentied=dayRentied;
    }


    /**
     * Problem:
     *  最好不要在另一个对象的属性基础上运用switch语句
     *  如果不得不使用 应该在对象自己的数据上使用(即在操作数据类本身创建函数
     *  getCharge 应该在 Movie 类中
     * @return
     */
    public double getCharge(){
        double result =0;
        switch (get_movie().get_priceCode()){
            case Movie.REGULAR:
                result+=2;
                if (get_dayRentied()>2){
                    result+=(get_dayRentied()-2)*1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result+=get_dayRentied()+3;
                break;
            case Movie.CHILDRENS:
                result+=1.5;
                if (get_dayRentied()>3){
                    result+=(get_dayRentied()-3)*1.5;
                }
                break;
        }
        return result;
    }


    public double refactoring_GetCharge(){
        return _movie.getCharge(_dayRentied);
    }

    /**
     * Problem:
     *      问题和getCharge一样
     * @return
     */
    public int getFrequentRenterPoints(){
        if (get_movie().get_priceCode()==Movie.NEW_RELEASE && get_dayRentied()>1){
            return 2;
        }else {
            return 1;
        }
    }


    public int refactoring_GetFrequentRenterPoints(){
        return _movie.getFrequentRenterPoints(_dayRentied);
    }




}
