package com.yarolegovich.compatanim;

import android.util.Property;

/**
 * Created by yarolegovich on 1/20/18.
 */

public abstract class FloatPropertyCompat<T> extends Property<T, Float> {

  public FloatPropertyCompat(String name) {
    super(Float.class, name);
  }

  public abstract void setValue(T object, float value);

  @Override public void set(T object, Float value) {
    setValue(object, value);
  }
}
