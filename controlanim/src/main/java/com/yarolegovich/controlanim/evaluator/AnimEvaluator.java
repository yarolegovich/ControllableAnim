package com.yarolegovich.controlanim.evaluator;

import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.util.Property;

import com.yarolegovich.compatanim.FloatPropertyCompat;
import com.yarolegovich.compatanim.IntPropertyCompat;


/**
 * Created by yarolegovich on 1/20/18.
 */

public abstract class AnimEvaluator {

  public static <Target>AnimEvaluator ofFloat(Target target,
                                              FloatPropertyCompat<Target> property,
                                              float endValue) {
    return ofFloat(target, property, property.get(target), endValue);
  }

  public static <Target>AnimEvaluator ofFloat(Target target,
                                              FloatPropertyCompat<Target> property,
                                              float startValue,
                                              float endValue) {
    return new FloatAnimEvaluator<>(target, property, startValue, endValue);
  }

  public static <Target>AnimEvaluator ofInt(Target target,
                                            IntPropertyCompat<Target> property,
                                            int endValue) {
    return ofInt(target, property, property.get(target), endValue);
  }

  public static <Target>AnimEvaluator ofInt(Target target,
                                            IntPropertyCompat<Target> property,
                                            int startValue,
                                            int endValue) {
    return new IntAnimEvaluator<>(target, property, startValue, endValue);
  }

  public static <Target, Prop>AnimEvaluator ofProperty(Target target,
                                                       Property<Target, Prop> property,
                                                       TypeEvaluator<Prop> evaluator,
                                                       Prop endValue) {
    return ofProperty(target, property, evaluator, property.get(target), endValue);
  }

  public static <Target, Prop>AnimEvaluator ofProperty(Target target,
                                                       Property<Target, Prop> property,
                                                       TypeEvaluator<Prop> evaluator,
                                                       Prop startValue,
                                                       Prop endValue) {
    return new GenericAnimEvaluator<>(target, property, evaluator, startValue, endValue);
  }

  private TimeInterpolator overrideInterpolator;

  public abstract void evaluate(float fraction);

  public void setOverrideInterpolator(TimeInterpolator overrideInterpolator) {
    this.overrideInterpolator = overrideInterpolator;
  }

  public TimeInterpolator getOverrideInterpolator() {
    return overrideInterpolator;
  }
}
