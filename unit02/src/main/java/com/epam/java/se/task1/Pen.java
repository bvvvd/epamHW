package com.epam.java.se.task1;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * The {@code Pen} class describe writing pen.
 *
 * @author Valeriy Burmistrov
 */
public class Pen {

    private final String inkColor;

    private final int inkThickness;

    private final PenType type;

    private final int cost;

    private final String manufacturer;

    /**
     * Create a new exemplar of Pen, with specified parameters.
     *
     * @param inkColor ink color of pen
     * @param inkThickness thickness of ink trace, measured in mm
     * @param type type of pen
     * @param cost cost, measured in cents
     * @param manufacturer the name of the company manufacturer
     */
    public Pen(@Nonnull String inkColor, @Nonnegative int inkThickness,
               @Nonnull PenType type, @Nonnegative int cost, @Nonnull String manufacturer) {
        this.inkColor = inkColor;
        this.inkThickness = inkThickness;
        this.type = type;
        this.cost = cost;
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object otherPen) {

        if (otherPen == null || getClass() != otherPen.getClass()) {
            return false;
        }

        if (this == otherPen) {
            return true;
        }

        Pen other = (Pen) otherPen;
        if (!inkColor.equalsIgnoreCase(other.inkColor) ||
                inkThickness != other.inkThickness||
                type != other.type ||
                cost != other.cost ||
                !manufacturer.equalsIgnoreCase(other.manufacturer)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += hash * 13 + inkColor.hashCode();
        hash += hash * 13 + inkThickness;
        hash += hash * 13 + type.hashCode();
        hash += hash * 13 + cost;
        hash += hash * 13 + manufacturer.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        String result = "%s. Type: %s, color of ink: %s, thickness of ink: %d, cost: $%d.%02d, manufactured by: %s";

        return String.format(result, this.getClass().getSimpleName(), type.toString(),
                inkColor, inkThickness, cost / 100, cost % 100, manufacturer);
    }

}