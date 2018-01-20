package com.yarolegovich.controlanim;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;

import com.yarolegovich.controlanim.evaluator.AnimEvaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yarolegovich on 1/20/18.
 */
public class AnimController implements ValueAnimator.AnimatorUpdateListener {
  
  private static final TimeInterpolator DEFAULT_INTERPOLATOR = new LinearInterpolator();
  
  public static AnimController of(AnimEvaluator evaluator,
                                  AnimEvaluator ...evaluators) {
    List<AnimEvaluator> allEvaluators = new ArrayList<>();
    allEvaluators.add(evaluator);
    allEvaluators.addAll(Arrays.asList(evaluators));
    return new AnimController(allEvaluators);
  }

  private @Nullable ValueAnimator animator;
  private TimeInterpolator sharedInterpolator;

  private float currentProgress;

  private List<AnimEvaluator> evaluators;

  private AnimController(List<AnimEvaluator> evaluators) {
    this.evaluators = evaluators;
    this.sharedInterpolator = DEFAULT_INTERPOLATOR;
    this.currentProgress = 0f;
  }

  @Override public void onAnimationUpdate(ValueAnimator animation) {
    currentProgress = (float) animation.getAnimatedValue();
    evaluate();
  }
  
  public void animateTo(float targetProgress) {
    cancelAnimator();
    animator = ValueAnimator.ofFloat(currentProgress, targetProgress);
    animator.addUpdateListener(this);
    animator.start();
  }
  
  public void setProgress(float progress) {
    cancelAnimator();
    currentProgress = progress;
    evaluate();
  }

  public void cancel() {
    cancelAnimator();
  }

  private void evaluate() {
    float sharedFraction = sharedInterpolator.getInterpolation(currentProgress);
    for (int i = 0; i < evaluators.size(); i++) {
      AnimEvaluator evaluator = evaluators.get(i);
      float evaluatorFraction = sharedFraction;
      TimeInterpolator interpolator = evaluator.getOverrideInterpolator();
      if (interpolator != null) {
        evaluatorFraction = interpolator.getInterpolation(currentProgress);
      }
      evaluator.evaluate(evaluatorFraction);
    }
  }

  
  private void cancelAnimator() {
    if (animator != null && animator.isRunning()) {
      animator.cancel();
    }
  }
}
