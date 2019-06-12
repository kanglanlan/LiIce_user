package com.meida.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * PreferencesUtils, easy to get or put data
 * <ul>
 * <strong>Preference Name</strong>
 * <li>you can change preference name by {@link #PREFERENCE_NAME}</li>
 * </ul>
 * <ul>
 * <strong>Put Value</strong>
 * <li>put string {@link #putString(Context, String, String)}</li>
 * <li>put int {@link #putInt(Context, String, int)}</li>
 * <li>put long {@link #putLong(Context, String, long)}</li>
 * <li>put float {@link #putFloat(Context, String, float)}</li>
 * <li>put boolean {@link #putBoolean(Context, String, boolean)}</li>
 * </ul>
 * <ul>
 * <strong>Get Value</strong>
 * <li>get string {@link #getString(Context, String)},
 * {@link #getString(Context, String, String)}</li>
 * <li>get int {@link #getInt(Context, String)},
 * {@link #getInt(Context, String, int)}</li>
 * <li>get long {@link #getLong(Context, String)},
 * {@link #getLong(Context, String, long)}</li>
 * <li>get float {@link #getFloat(Context, String)},
 * {@link #getFloat(Context, String, float)}</li>
 * <li>get boolean {@link #getBoolean(Context, String)},
 * {@link #getBoolean(Context, String, boolean)}</li>
 * </ul>
 *
 * @author wgs
 */
public class PreferencesUtils {

    public static String PREFERENCE_NAME = "TrineaAndroidCommon";

    /**
     * put string preferences
     *
     * @param context
     * @param key
     *            The name of the preference to modify
     * @param value
     *            The new value for the preference
     * @return True if the new values were successfully written to persistent
     *         storage.
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a string
     * @see #getString(Context, String, String)
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @param defaultValue
     *            Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a string
     */
    public static String getString(Context context, String key,
                                   String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * put int preferences
     *
     * @param context
     * @param key
     *            The name of the preference to modify
     * @param value
     *            The new value for the preference
     * @return True if the new values were successfully written to persistent
     *         storage.
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a int
     * @see #getInt(Context, String, int)
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @param defaultValue
     *            Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a int
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * put long preferences
     *
     * @param context
     * @param key
     *            The name of the preference to modify
     * @param value
     *            The new value for the preference
     * @return True if the new values were successfully written to persistent
     *         storage.
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a long
     * @see #getLong(Context, String, long)
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @param defaultValue
     *            Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a long
     */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     *
     * @param context
     * @param key
     *            The name of the preference to modify
     * @param value
     *            The new value for the preference
     * @return True if the new values were successfully written to persistent
     *         storage.
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a float
     * @see #getFloat(Context, String, float)
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @param defaultValue
     *            Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a float
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     *
     * @param context
     * @param key
     *            The name of the preference to modify
     * @param value
     *            The new value for the preference
     * @return True if the new values were successfully written to persistent
     *         storage.
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean preferences, default is false
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @return The preference value if it exists, or false. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a boolean
     * @see #getBoolean(Context, String, boolean)
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * get boolean preferences
     *
     * @param context
     * @param key
     *            The name of the preference to retrieve
     * @param defaultValue
     *            Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a boolean
     */
    public static boolean getBoolean(Context context, String key,
                                     boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

/*****************************************保存对象相关******************************************/
    /**
     * 保存序列化对象到本地（使用时记得在bean类中实现Serializable接口）
     *
     * @param context
     * @param key
     * @param object
     */
    public static void putObject(Context context, String key, Object object) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        //先将序列化结果写到byte缓存中，其实就分配一个内存空间
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(bos);
            os.writeObject(object);//将对象序列化写入byte缓存
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将序列化的数据转为16进制保存
        String bytesToHexString = bytesToHexString(bos.toByteArray());
        //保存该16进制数组
        editor.putString(key, bytesToHexString);
        editor.commit();
    }

    /**
     * desc:将数组转为16进制
     *
     * @param bArray
     * @return
     */
    public static String bytesToHexString(byte[] bArray) {
        if (bArray == null) {
            return null;
        }
        if (bArray.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 从本地反序列化获取对象
     *
     * @param context
     * @param key
     * @return
     */
    public static Object getObject(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);

        if (settings.contains(key)) {
            String string = settings.getString(key, "");
            if (TextUtils.isEmpty(string)) {
                return null;
            } else {
                //将16进制的数据转为数组，准备反序列化
                byte[] stringToBytes = StringToBytes(string);
                ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
                ObjectInputStream is = null;
                //返回反序列化得到的对象
                Object readObject = null;
                try {
                    is = new ObjectInputStream(bis);
                    readObject = is.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return readObject;
            }
        }
        return null;
    }

    /**
     * desc:将16进制的数据转为数组
     *
     * @param data
     * @return
     */
    public static byte[] StringToBytes(String data) {
        String hexString = data.toUpperCase().trim();
        if (hexString.length() % 2 != 0) {
            return null;
        }
        byte[] retData = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i++) {
            int int_ch;  // 两位16进制数转化后的10进制数
            char hex_char1 = hexString.charAt(i); //两位16进制数中的第一位(高位*16)
            int int_ch1;
            if (hex_char1 >= '0' && hex_char1 <= '9')
                int_ch1 = (hex_char1 - 48) * 16;   // 0 的Ascll - 48
            else if (hex_char1 >= 'A' && hex_char1 <= 'F')
                int_ch1 = (hex_char1 - 55) * 16; // A 的Ascll - 65
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i); //两位16进制数中的第二位(低位)
            int int_ch2;
            if (hex_char2 >= '0' && hex_char2 <= '9')
                int_ch2 = (hex_char2 - 48); // 0 的Ascll - 48
            else if (hex_char2 >= 'A' && hex_char2 <= 'F')
                int_ch2 = hex_char2 - 55; // A 的Ascll - 65
            else
                return null;
            int_ch = int_ch1 + int_ch2;
            retData[i / 2] = (byte) int_ch;//将转化后的数放入Byte里
        }
        return retData;
    }


}