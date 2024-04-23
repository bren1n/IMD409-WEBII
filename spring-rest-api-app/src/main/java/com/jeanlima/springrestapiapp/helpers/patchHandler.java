package com.jeanlima.springrestapiapp.helpers;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

public class patchHandler {
    public static String[] getNullProperties(Object object) {
        BeanWrapper wrapper = new BeanWrapperImpl(object);
        return Arrays.stream(wrapper.getPropertyDescriptors())
                .map(PropertyDescriptor::getName)
                .filter(propertyName -> wrapper.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
