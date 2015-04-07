package gezhi.gekson.com.expandlistviewdemo;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Created by yanchangsen on 15/4/7.
 */
public class tool {

    /**
     * 展开
     *
     * @param v
     */
    public static void expand(final View v) {
        // item显示的条目
        final LinearLayout textLayout = (LinearLayout) v.findViewById(R.id.text_layout);
        final LinearLayout ImageLayout = (LinearLayout) v.findViewById(R.id.image_layout);

        ImageLayout.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = ImageLayout.getMeasuredHeight();
        final int textMissHeight = textLayout.getMeasuredHeight();

        ImageLayout.getLayoutParams().height = 0;
        ImageLayout.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                ImageLayout.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                textLayout.getLayoutParams().height = textMissHeight - (int) (textMissHeight * interpolatedTime);
                if (interpolatedTime == 1) {
                    textLayout.setVisibility(View.GONE);
                }
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    /**
     * 收缩
     *
     * @param v
     */
    public static void collapse(final View v) {
        // item显示的条目
        final LinearLayout textLayout = (LinearLayout) v.findViewById(R.id.text_layout);
        final LinearLayout ImageLayout = (LinearLayout) v.findViewById(R.id.image_layout);

        textLayout.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final int initialHeight = ImageLayout.getMeasuredHeight();
        final int targetHeight = textLayout.getMeasuredHeight();

        textLayout.getLayoutParams().height = 0;
        textLayout.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                textLayout.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                if (interpolatedTime == 1) {
                    ImageLayout.setVisibility(View.GONE);
                    textLayout.setVisibility(View.VISIBLE);
                } else {
                    ImageLayout.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


}
