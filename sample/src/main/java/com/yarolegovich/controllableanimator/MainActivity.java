package com.yarolegovich.controllableanimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.yarolegovich.controlanim.AnimController;

import static com.yarolegovich.compatanim.ViewPropertyCompat.ALPHA;
import static com.yarolegovich.compatanim.ViewPropertyCompat.SCALE_X;
import static com.yarolegovich.compatanim.ViewPropertyCompat.SCALE_Y;
import static com.yarolegovich.compatanim.ViewPropertyCompat.TRANSLATION_X;
import static com.yarolegovich.compatanim.ViewPropertyCompat.TRANSLATION_Y;
import static com.yarolegovich.controlanim.evaluator.AnimEvaluator.ofFloat;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
    SwitchCompat.OnCheckedChangeListener, View.OnClickListener {

  private AnimController animController;

  private SeekBar animProgressSeekBar;
  private SwitchCompat updateModeSwitch;
  private View startAnimButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    animProgressSeekBar = findViewById(R.id.anim_progress_bar);
    animProgressSeekBar.setOnSeekBarChangeListener(this);

    updateModeSwitch = findViewById(R.id.update_mode_switch);
    updateModeSwitch.setOnCheckedChangeListener(this);

    startAnimButton = findViewById(R.id.start_anim_btn);
    startAnimButton.setOnClickListener(this);

    final View animViewHost = findViewById(R.id.animated_view_host);
    animViewHost.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override public void onGlobalLayout() {
        animViewHost.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        initAnimController(animViewHost);
      }
    });
  }

  private void initAnimController(View host) {
    View translationXTarget = host.findViewById(R.id.translation_x_target);
    View translationYTarget = host.findViewById(R.id.translation_y_target);
    View scaleTarget = host.findViewById(R.id.scale_target);
    View alphaTarget = host.findViewById(R.id.alpha_target);
    float targetTranslationX = host.getWidth() * 0.5f - translationXTarget.getWidth() * 0.5f;
    float targetTranslationY = host.getHeight() * 0.5f - translationYTarget.getHeight() * 0.5f;
    animController = AnimController.of(
        ofFloat(translationXTarget, TRANSLATION_X, targetTranslationX),
        ofFloat(translationYTarget, TRANSLATION_Y, targetTranslationY),
        ofFloat(scaleTarget, SCALE_X, 2f), ofFloat(scaleTarget, SCALE_Y, 2f),
        ofFloat(alphaTarget, ALPHA, 0f));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (animController != null) {
      animController.cancel();
    }
  }

  @Override public void onClick(View v) {
    if (animController != null) {
      animController.animateTo(getSelectedProgress());
    }
  }

  @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    boolean isInPostponedMode = !isInImmediateUpdateMode();
    startAnimButton.setEnabled(isInPostponedMode);
  }

  @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    if (isInImmediateUpdateMode() && animController != null) {
      animController.setProgress(getSelectedProgress());
    }
  }

  private float getSelectedProgress() {
    return animProgressSeekBar.getProgress() / (float) animProgressSeekBar.getMax();
  }

  private boolean isInImmediateUpdateMode() {
    return updateModeSwitch.isChecked();
  }

  @Override public void onStartTrackingTouch(SeekBar seekBar) {

  }

  @Override public void onStopTrackingTouch(SeekBar seekBar) {

  }
}
