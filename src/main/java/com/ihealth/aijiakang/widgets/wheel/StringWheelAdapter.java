package com.ihealth.aijiakang.widgets.wheel;

import android.util.Log;
/**
 * The string adapter for the wheel 
 * @author xiao FENG
 * @version v1.0
 */
public class StringWheelAdapter implements WheelAdapter {
    
    /** The default value */
   // public static final String[] DEFAULT_VALUE = {""};
    
    // Values
    private String[] value;
    private int itemLength;
    
    /**
     * Constructor
     * @param value the wheel value
     */
    public StringWheelAdapter(String[] value) {
        this.value  = new String[value.length];
        for(int i=0; i<value.length;i++)
        {     
              Log.i("String Wheel Adapt",value[i]);
              this.value[i] = value[i];
        }
    }


    @Override
    public String getItem(int index) {
        if (index >= 0 && index < getItemsCount()) 
            return value[index];
    return null;
    }

    @Override
    public int getItemsCount() {
        return this.value.length;
    }
    
    @Override
    public int getMaximumLength() {
        this.itemLength = 0;
        for(int i=0; i<value.length;i++){
            this.itemLength = (this.itemLength > value[i].length()) ? this.itemLength:value[i].length();
        }
        return this.itemLength;
    }
}

