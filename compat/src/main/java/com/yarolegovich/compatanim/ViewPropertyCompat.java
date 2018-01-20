package com.yarolegovich.compatanim;

import android.view.View;

/**
 * Created by yarolegovich on 1/20/18.
 */

public class ViewPropertyCompat {

  public static FloatPropertyCompat<View> TRANSLATION_X = new FloatPropertyCompat<View>("translationX") {
    @Override public void setValue(View object, float value) {
      object.setTranslationX(value);
    }

    @Override public Float get(View object) {
      return object.getTranslationX();
    }
  };

  public static FloatPropertyCompat<View> TRANSLATION_Y = new FloatPropertyCompat<View>("translationY") {
    @Override public void setValue(View object, float value) {
      object.setTranslationY(value);
    }

    @Override public Float get(View object) {
      return object.getTranslationY();
    }
  };

  public static FloatPropertyCompat<View> SCALE_X = new FloatPropertyCompat<View>("scaleX") {
    @Override public void setValue(View object, float value) {
      object.setScaleX(value);
    }

    @Override public Float get(View object) {
      return object.getScaleX();
    }
  };

  public static FloatPropertyCompat<View> SCALE_Y = new FloatPropertyCompat<View>("scaleY") {
    @Override public void setValue(View object, float value) {
      object.setScaleY(value);
    }

    @Override public Float get(View object) {
      return object.getScaleY();
    }
  };

  public static FloatPropertyCompat<View> ALPHA = new FloatPropertyCompat<View>("alpha") {
    @Override public void setValue(View object, float value) {
      object.setAlpha(value);
    }

    @Override public Float get(View object) {
      return object.getAlpha();
    }
  };


}
