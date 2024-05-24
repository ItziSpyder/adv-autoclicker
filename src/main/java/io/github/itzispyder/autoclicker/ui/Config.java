package io.github.itzispyder.autoclicker.ui;

import io.github.itzispyder.autoclicker.Global;
import io.github.itzispyder.improperui.config.ConfigKey;
import io.github.itzispyder.improperui.config.Properties;
import io.github.itzispyder.improperui.script.ScriptParser;

public class Config implements Global {

    public static boolean left, right;
    public static boolean leftSpam, rightSpam;
    public static boolean leftOnlyHold, rightOnlyHold;
    public static int leftCps, rightCps;
    public static double leftChance, rightChance;

    public static boolean onlyWhenTarget;
    public static boolean noBabies;
    public static double maxAttackCooldown;
    public static boolean stopWhenMove;
    public static boolean stopWhenDamage;
    public static boolean stopWhenTarget;
    public static boolean debug;

    public static void update() {
        left = readBool("left", false);
        right = readBool("right", false);
        leftSpam = readBool("left-spam", true);
        rightSpam = readBool("right-spam", true);
        leftOnlyHold = readBool("left-hold", true);
        rightOnlyHold = readBool("right-hold", true);
        leftCps = readInt("left-cps", 10);
        rightCps = readInt("right-cps", 10);
        leftChance = readDouble("left-chance", 1.0);
        rightChance = readDouble("right-chance", 1.0);

        onlyWhenTarget = readBool("only-when-target", false);
        noBabies = readBool("no-babies", true);
        maxAttackCooldown = readDouble("max-attack-cooldown", 0.0);
        stopWhenMove = readBool("stop-when-move", false);
        stopWhenDamage = readBool("stop-when-damage", false);
        stopWhenTarget = readBool("stop-when-target", false);
        debug = readBool("debug", false);
    }

    public static ConfigKey getKey(String property) {
        return new ConfigKey(modId, "config.properties", property);
    }

    public static Properties.Value read(String property) {
        return ScriptParser.getCache(modId).getProperty(getKey(property));
    }

    public static void write(String property, Object value) {
        ScriptParser.getCache(modId).setProperty(getKey(property), value, true);
    }

    public static boolean readBool(String property, boolean def) {
        var o = read(property);
        if (o == null)
            write(property, def);
        return o != null ? o.first().toBool() : def;
    }

    public static int readInt(String property, int def) {
        return (int)readDouble(property, def);
    }

    public static double readDouble(String property, double def) {
        var o = read(property);
        if (o == null)
            write(property, def);
        return o != null ? o.first().toDouble() : def;
    }

    public static String readStr(String property, String def) {
        var o = read(property);
        if (o == null)
            write(property, def);
        return o != null ? o.first().toString() : def;
    }
}
