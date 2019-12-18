package com.lucas.refacotring.entity;

/**
 * @author lucas
 * @Description Movie Of price
 * @Date 2019/12/18
 **/
public abstract class Price {

    abstract int getPriceCode();

    /**
     * Further:
     *  the case implement a move to  getCharge method  of subObject - success
     * @param daysRented
     * @return
     */
    abstract double getCharge(int daysRented);


    /**
     * Futher:
     *  NewReleasePrice need to override this method
     * @param dayRented
     * @return
     */
     public int getFrequentRenterPoints(int dayRented){
         return 1;
     }


}

class ChildrensPrice extends Price{

    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public  double getCharge(int daysRented){
        double result = 1.5;
        if (daysRented>3){
            result+=(daysRented-3)*1.5;
        }
        return result;
    }


}

class NewReleasePrice extends Price{

    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    double getCharge(int daysRented) {
        double result =0;
        result+=daysRented+3;
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int dayRented) {
        return (dayRented > 1)?2:1;
    }

}

class RegularPrice extends Price{

    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    double getCharge(int daysRented) {
        double result =0;
        result+=2;
        if (daysRented>2){
            result+=(daysRented-2)*1.5;
        }
        return result;
    }

}