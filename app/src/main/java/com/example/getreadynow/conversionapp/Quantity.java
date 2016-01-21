package com.example.getreadynow.conversionapp;

import java.text.DecimalFormat;
/**
 * Created by AlHassaneAgne on 1/19/16.
 */
public class Quantity {

    final double value;
    final Unit unit;

    public static enum Unit {
        tsp(1.0d), tbs(0.3333d), cup(0.0208d), oz(0.1666d), pint(0.0104d), quart(0.0052d),
        gallon(0.0013d), pound(0.0125d), ml(4.9289d), liter(0.0049d), mg(5687.5d), kg(0.0057d);

        //tsp is the base unit that we will be using to convert to and from
        final static Unit baseUnit = tsp;

        //Will hold the number of tsp the original unit converts to
        final double byBaseUnit;

        //Receives the number of tsps the starting Unit equals
        private Unit (double inTsp) {
            this.byBaseUnit = inTsp;
        }

        //Convert unit to tsp
        public double toBaseUnit(double value) {
            return value / byBaseUnit;
        }
        //Convert from tsp
        public double fromBaseUnit (double value) {
            return value * byBaseUnit;
        }
    }

    //constructor
    public Quantity(double value, Unit unit) {
        super();
        this.value = value;
        this.unit = unit;
    }


    //converts from tsp to the desired unit type
    public Quantity to(Unit newUnit) {
        Unit oldUnit = this.unit;
        return new Quantity(newUnit.fromBaseUnit(oldUnit.toBaseUnit(value)), newUnit);
    }

     //Prints out to screen the unit amount and unit type
     public String toString() {
         DecimalFormat df = new DecimalFormat("#.0000");
         return df.format(value) + " " + unit.name();
     }
}
