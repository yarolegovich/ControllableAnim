package com.yarolegovich.controlanim.evaluator;

import android.support.annotation.NonNull;

import com.yarolegovich.compatanim.FloatPropertyCompat;

/**
 * Created by yarolegovich on 1/20/18.
 */

public class FloatAnimEvaluator<Target> extends AnimEvaluator {

  private Target target;
  private FloatPropertyCompat<Target> property;
  private float startValue;
  private float delta;

  public FloatAnimEvaluator(@NonNull Target target,
                            @NonNull FloatPropertyCompat<Target> property,
                            float startValue,
                            float endValue) {
    this.target = target;
    this.property = property;
    this.startValue = startValue;
    this.delta = endValue - startValue;
  }

  public void evaluate(float fraction) {
    float result = startValue + fraction * delta;
    property.set(target, result);
  }

}
