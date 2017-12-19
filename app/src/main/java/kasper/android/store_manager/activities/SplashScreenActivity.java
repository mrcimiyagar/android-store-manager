package kasper.android.store_manager.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kasper.android.store_manager.R;
import kasper.android.store_manager.core.Core;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final long startMillis = System.currentTimeMillis();

        Core.getInstance().startMachine(new Runnable() {
            @Override
            public void run() {
                long finishMillis = System.currentTimeMillis();
                long timeTaken = finishMillis - startMillis;
                if (timeTaken > 1500) {
                    gotoDashboardActivity();
                }
                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gotoDashboardActivity();
                        }
                    }, 1500 - timeTaken);
                }
            }
        });
    }

    private void gotoDashboardActivity() {
        startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
        SplashScreenActivity.this.finish();
    }
}
