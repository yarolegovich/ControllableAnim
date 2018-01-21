package com.yarolegovich.controlanim.evaluator;

import android.support.annotation.NonNull;

import com.yarolegovich.compatanim.IntPropertyCompat;

/**
 * Created by yarolegovich on 1/20/18.
 */

public class IntAnimEvaluator<Target> extends AnimEvaluator {

  private Target target;
  private IntPropertyCompat<Target> property;
  private int startValue;
  private int delta;

  public IntAnimEvaluator(@NonNull Target target,
                          @NonNull IntPropertyCompat<Target> property,
                          int startValue,
                          int endValue) {
    this.target = target;
    this.property = property;
    this.startValue = startValue;
    this.delta = endValue - startValue;
  }

  public void evaluate(float fraction) {
    int result = (int) (startValue + fraction * delta);
    property.setValue(target, result);
  }

}
