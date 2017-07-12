package de.darkdevs.cp.utils.punishment;

import java.util.ArrayList;
import java.util.List;

/**
 * This class was created by Front. aka Scryptex on 28.06.2017.
 */
public enum BanUnit {

    SECOND("Sekunde(n)", 1, "sec"),
    MINUTE("Minute(n)", 60, "min"),
    HOUR("Stunde(n)", 60*60, "hour"),
    DAY("Tag(e)", 24*60*60, "day"),
    WEEK("Woche(n)", 7*24*60*60, "week");

    private String name;
    private int toSecond;
    private String shortcut;

    BanUnit(String name, int toSecond, String shortcut) {
        this.name = name;
        this.toSecond = toSecond;
        this.shortcut = shortcut;
    }

    public String getName() {
        return name;
    }

    public int getToSecond() {
        return toSecond;
    }

    public String getShortcut() {
        return shortcut;
    }

    public static List<String> getUnitsAsString() {
        List<String> units = new ArrayList<String>();
        for(BanUnit unit : BanUnit.values()) {
            units.add(unit.getShortcut());
        }
        return units;
    }
}
