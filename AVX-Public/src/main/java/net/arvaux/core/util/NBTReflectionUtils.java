package net.arvaux.core.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

import static java.lang.Class.forName;

public class NBTReflectionUtils {
    private static String version;

    static {
        if (version == null) {
            String packageName = Bukkit.getServer().getClass().getPackage().getName();
            version = packageName.substring(packageName.lastIndexOf('.') + 1);
        }
    }

    @SuppressWarnings("rawtypes")
    private static Class getCraftItemstack() {
        try {
            return forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    private static Object getNewNBTTag() {
        try {
            Class c = forName("net.minecraft.server." + version + ".NBTTagCompound");
            return c.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static Object setNBTTag(Object nbtTag, Object nmsItem) {
        try {
            Method method;
            method = nmsItem.getClass().getMethod("setTag", nbtTag.getClass());
            method.invoke(nmsItem, nbtTag);
            return nmsItem;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Object getNMSItemStack(ItemStack item) {
        Class cis = getCraftItemstack();
        try {
            Method method = cis.getMethod("asNMSCopy", ItemStack.class);
            Object answer = method.invoke(cis, item);
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static ItemStack getBukkitItemStack(Object item) {
        Class cis = getCraftItemstack();
        try {
            Method method = cis.getMethod("asBukkitCopy", item.getClass());
            Object answer = method.invoke(cis, item);
            return (ItemStack) answer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Object getNBTTagCompound(Object nmsitem) {
        Class c = nmsitem.getClass();
        Method method;
        try {
            method = c.getMethod("getTag");
            Object answer = method.invoke(nmsitem);
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static ItemStack setString(ItemStack item, String key, String Text) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) {
            return null;
        }
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) {
            nbttag = getNewNBTTag();
        }
        Method method;
        try {
            method = nbttag.getClass().getMethod("setString", String.class, String.class);
            method.invoke(nbttag, key, Text);
            nmsitem = setNBTTag(nbttag, nmsitem);
            return getBukkitItemStack(nmsitem);
        } catch (Exception ex) {
            ex.printStackTrace();
            return item;
        }
    }

    public static String getString(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) {
            return null;
        }
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) {
            nbttag = getNewNBTTag();
        }
        Method method;
        try {
            method = nbttag.getClass().getMethod("getString", String.class);
            return (String) method.invoke(nbttag, key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ItemStack setInt(ItemStack item, String key, Integer i) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) {
            return null;
        }
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) {
            nbttag = getNewNBTTag();
        }
        Method method;
        try {
            method = nbttag.getClass().getMethod("setInt", String.class, int.class);
            method.invoke(nbttag, key, i);
            nmsitem = setNBTTag(nbttag, nmsitem);
            return getBukkitItemStack(nmsitem);
        } catch (Exception ex) {
            ex.printStackTrace();
            return item;
        }
    }

    public static Integer getInt(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) {
            return null;
        }
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) {
            nbttag = getNewNBTTag();
        }
        Method method;
        try {
            method = nbttag.getClass().getMethod("getInt", String.class);
            return (Integer) method.invoke(nbttag, key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ItemStack setDouble(ItemStack item, String key, Double d) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) {
            return null;
        }
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) {
            nbttag = getNewNBTTag();
        }
        Method method;
        try {
            method = nbttag.getClass().getMethod("setDouble", String.class, double.class);
            method.invoke(nbttag, key, d);
            nmsitem = setNBTTag(nbttag, nmsitem);
            return getBukkitItemStack(nmsitem);
        } catch (Exception ex) {
            ex.printStackTrace();
            return item;
        }
    }

    public static Double getDouble(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) {
            return null;
        }
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) {
            nbttag = getNewNBTTag();
        }
        Method method;
        try {
            method = nbttag.getClass().getMethod("getDouble", String.class);
            return (Double) method.invoke(nbttag, key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ItemStack setBoolean(ItemStack item, String key, Boolean d) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) {
            return null;
        }
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) {
            nbttag = getNewNBTTag();
        }
        Method method;
        try {
            method = nbttag.getClass().getMethod("setBoolean", String.class, boolean.class);
            method.invoke(nbttag, key, d);
            nmsitem = setNBTTag(nbttag, nmsitem);
            return getBukkitItemStack(nmsitem);
        } catch (Exception ex) {
            ex.printStackTrace();
            return item;
        }
    }

    public static Boolean getBoolean(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) {
            return null;
        }
        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) {
            nbttag = getNewNBTTag();
        }
        Method method;

        try {
            method = nbttag.getClass().getMethod("getBoolean", String.class);
            return (Boolean) method.invoke(nbttag, key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Boolean hasKey(ItemStack item, String key) {
        Object nmsitem = getNMSItemStack(item);
        if (nmsitem == null) {
            return false;
        }

        Object nbttag = getNBTTagCompound(nmsitem);
        if (nbttag == null) {
            return false;
        }

        Method method;
        try {
            method = nbttag.getClass().getMethod("hasKey", String.class);
            return (Boolean) method.invoke(nbttag, key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
