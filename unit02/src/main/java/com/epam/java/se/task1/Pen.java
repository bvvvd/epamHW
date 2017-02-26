package com.epam.java.se.task1;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Created by chris on 26.02.2017.
 */
public class Pen {

    private final String inkColor;

    private final int inkThickness;

    private final PenType type;

    private final int cost;

    private final String manufacturer;

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
}