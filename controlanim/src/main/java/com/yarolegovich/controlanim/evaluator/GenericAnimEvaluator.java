package com.yarolegovich.controlanim.evaluator;

import android.animation.TypeEvaluator;
import android.support.annotation.NonNull;
import android.util.Property;

/**
 * Created by yarolegovich on 1/20/18.
 */

public class GenericAnimEvaluator<Target, Prop> extends AnimEvaluator {

  private Target target;
  private Property<Target, Prop> property;
  private TypeEvaluator<Prop> evaluator;
  private Prop startValue;
  private Prop endValue;

  public GenericAnimEvaluator(@NonNull Target target,
                              @NonNull Property<Target, Prop> property,
                              @NonNull TypeEvaluator<Prop> evaluator,
                              @NonNull Prop startValue,
                              @NonNull Prop endValue) {
    this.target = target;
    this.property = property;
    this.evaluator = evaluator;
    this.startValue = startValue;
    this.endValue = endValue;
  }

  public void evaluate(float fraction) {
    Prop result = evaluator.evaluate(fraction, startValue, endValue);
    property.set(target, result);
  }

}
