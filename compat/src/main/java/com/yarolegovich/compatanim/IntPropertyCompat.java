package com.yarolegovich.compatanim;

import android.util.Property;

/**
 * Created by yarolegovich on 1/20/18.
 */
public abstract class IntPropertyCompat<T> extends Property<T, Integer> {

  public IntPropertyCompat(String name) {
    super(Integer.class, name);
  }

  public abstract void setValue(T object, int value);

  @Override public void set(T object, Integer value) {
    setValue(object, value);
  }
}
