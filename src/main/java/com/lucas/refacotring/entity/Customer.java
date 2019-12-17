package com.lucas.refacotring.entity;

import lombok.Getter;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author lucas
 * @Description 客户
 * @Date 2019/12/17
 **/
public class Customer {

    @Getter
    private String _name;

    private Vector _rentals = new Vector();

    public Customer(String name){
        _name=name;
    }

    public void addRental(Rental arg){
        _rentals.addElement(arg);
    }

    /**
     * 生成详单
     * @return
     */
    public String statement(){
        double totalAmout = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Recordd for "+ get_name()+"\n";
        while(rentals.hasMoreElements()){
            double thisAmount = 0;
            Rental each = (Rental)rentals.nextElement();
            switch (each.get_movie().get_priceCode()){
                case Movie.REGULAR:
                    thisAmount+=2;
                    if (each.get_dayRentied()>2){
                        thisAmount+=(each.get_dayRentied()-2)*1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount+=each.get_dayRentied()+3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount+=1.5;
                    if (each.get_dayRentied()>3){
                        thisAmount+=(each.get_dayRentied()-3)*1.5;
                    }
                    break;
            }
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus  for a two day new release rental
            if (each.get_movie().get_priceCode()==Movie.NEW_RELEASE && each.get_dayRentied()>1){
                frequentRenterPoints++;
            }
            // show figures for this rental
            result += "\t"+each.get_movie().get_title()+"\t"+String.valueOf(thisAmount)+"\n";
            totalAmout+=thisAmount;
        }
        // add footer line
        result += "Amount owed is "+ String.valueOf(totalAmout) + "\n";
        result+=" You earned "+String.valueOf(frequentRenterPoints)+" frequent renter points";
        return result;
    }


    public String refacotring_statement(){
        double totalAmout = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Recordd for "+ get_name()+"\n";
        while(rentals.hasMoreElements()){
            double thisAmount = 0;
            Rental each = (Rental)rentals.nextElement();

            thisAmount = refactoring_amountFor(each);

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus  for a two day new release rental
            if (each.get_movie().get_priceCode()==Movie.NEW_RELEASE && each.get_dayRentied()>1){
                frequentRenterPoints++;
            }
            // show figures for this rental
            result += "\t"+each.get_movie().get_title()+"\t"+String.valueOf(thisAmount)+"\n";
            totalAmout+=thisAmount;
        }
        // add footer line
        result += "Amount owed is "+ String.valueOf(totalAmout) + "\n";
        result+=" You earned "+String.valueOf(frequentRenterPoints)+" frequent renter points";
        return result;
    }

    /**
     * 剥离出了复杂的swich语句
     * @return
     */
    public String refacotring2_statement(){
        double totalAmout = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Recordd for "+ get_name()+"\n";
        while(rentals.hasMoreElements()){
            double thisAmount = 0;
            Rental each = (Rental)rentals.nextElement();
            // thisAmount = refactoring_amountFor(each)
            thisAmount = each.getCharge();

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus  for a two day new release rental
            if (each.get_movie().get_priceCode()==Movie.NEW_RELEASE && each.get_dayRentied()>1){
                frequentRenterPoints++;
            }
            // show figures for this rental
            result += "\t"+each.get_movie().get_title()+"\t"+String.valueOf(thisAmount)+"\n";
            totalAmout+=thisAmount;
        }
        // add footer line
        result += "Amount owed is "+ String.valueOf(totalAmout) + "\n";
        result+=" You earned "+String.valueOf(frequentRenterPoints)+" frequent renter points";
        return result;
    }

    /**
     * 取出多余的thisAmount 变量
     * TODO
     *  提炼常客积分计算的代码
     * @return
     */
    public String refacotring3_statement(){
        double totalAmout = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Recordd for "+ get_name()+"\n";
        while(rentals.hasMoreElements()){
            Rental each = (Rental)rentals.nextElement();

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus  for a two day new release rental
            if (each.get_movie().get_priceCode()==Movie.NEW_RELEASE && each.get_dayRentied()>1){
                frequentRenterPoints++;
            }

            // show figures for this rental
            result += "\t"+each.get_movie().get_title()+"\t"+String.valueOf(each.getCharge())+"\n";
            totalAmout+= each.getCharge();;
        }
        // add footer line
        result += "Amount owed is "+ String.valueOf(totalAmout) + "\n";
        result+=" You earned "+String.valueOf(frequentRenterPoints)+" frequent renter points";
        return result;
    }


    /**
     * 提炼常客积分计算的代码
     * TODO
     *      去除临时变量
     *
     * @return
     */
    public String refacotring4_statement(){
        double totalAmout = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Recordd for "+ get_name()+"\n";
        while(rentals.hasMoreElements()){
            Rental each = (Rental)rentals.nextElement();

            // add frequent renter points
            frequentRenterPoints+=each.getFrequentRenterPoints();

            // show figures for this rental
            result += "\t"+each.get_movie().get_title()+"\t"+String.valueOf(each.getCharge())+"\n";
            totalAmout+= each.getCharge();;
        }
        // add footer line
        result += "Amount owed is "+ String.valueOf(totalAmout) + "\n";
        result+=" You earned "+String.valueOf(frequentRenterPoints)+" frequent renter points";
        return result;
    }

    /**
     * 去除临时变量
     *  新增 getTotalCharge 函数
     *  新增 getTotalPrequentRenterPointe 函数
     * @return
     */
    public String refacotring5_statement(){
        double totalAmout = 0;
        // int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Recordd for "+ get_name()+"\n";
        while(rentals.hasMoreElements()){
            Rental each = (Rental)rentals.nextElement();

            // add frequent renter points
            // frequentRenterPoints += each.getFrequentRenterPoints();

            // show figures for this rental
            result += "\t"+each.get_movie().get_title()+"\t"+String.valueOf(each.getCharge())+"\n";

            // totalAmout+= each.getCharge();
        }

        // add footer line
        result += "Amount owed is "+ String.valueOf(getTotalCharge()) + "\n";
        result+=" You earned "+String.valueOf(getTotalFrequentRenterPoints())+" frequent renter points";
        return result;
    }





    public double amountFor(Rental each){
        double thisAmount = 0;
        switch (each.get_movie().get_priceCode()){
            case Movie.REGULAR:
                thisAmount+=2;
                if (each.get_dayRentied()>2){
                    thisAmount+=(each.get_dayRentied()-2)*1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount+=each.get_dayRentied()+3;
                break;
            case Movie.CHILDRENS:
                thisAmount+=1.5;
                if (each.get_dayRentied()>3){
                    thisAmount+=(each.get_dayRentied()-3)*1.5;
                }
                break;
        }
        return thisAmount;
    }

    /**
     * 并没有修改业务逻辑 而是修改变量名
     *  增强代码的可读性
     * @param aRental
     * @return
     */
    public double refactoring_amountFor(Rental aRental){
        double result =0;
        switch (aRental.get_movie().get_priceCode()){
            case Movie.REGULAR:
                result+=2;
                if (aRental.get_dayRentied()>2){
                    result+=(aRental.get_dayRentied()-2)*1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result+=aRental.get_dayRentied()+3;
                break;
            case Movie.CHILDRENS:
                result+=1.5;
                if (aRental.get_dayRentied()>3){
                    result+=(aRental.get_dayRentied()-3)*1.5;
                }
                break;
        }
        return result;
    }

    private double getTotalCharge(){
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while(rentals.hasMoreElements()){
            Rental each = (Rental)rentals.nextElement();
            result+=each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result = 0 ;
        Enumeration rentals = _rentals.elements();
        while(rentals.hasMoreElements()){
            Rental each = (Rental)rentals.nextElement();
            result+=each.getFrequentRenterPoints();
        }
        return result;
    }

}
