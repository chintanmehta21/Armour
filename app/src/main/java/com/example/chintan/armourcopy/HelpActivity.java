package com.example.chintan.armourcopy;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{

    private int counter = 0;
    private ShowcaseView showcaseView;
    Button terms;
    Button connect;
    Button history;
    Button configure;
    Button lockunlock;
    Button help;
    Button aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        showcaseView = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(findViewById(R.id.btn_connect)))
                .setOnClickListener((View.OnClickListener) this)
                .build();
        showcaseView.setButtonText(getString(R.string.next));
        showcaseView.setContentTitle("Connect");
        showcaseView.setContentText("This is the first button you shall click.It will establish the connection between your phone and Armour... Note:You must pair the HC-05 device with your phone before itself");
        terms = (Button) findViewById(R.id.btn_terms);
        connect = (Button) findViewById(R.id.btn_connect);
        history = (Button) findViewById(R.id.btn_history);
        configure = (Button) findViewById(R.id.btn_configure);
        lockunlock = (Button) findViewById(R.id.btn_lock_unlock);
        help = (Button) findViewById(R.id.btn_help);
        aboutus = (Button) findViewById(R.id.btn_aboutus);

    }

    private void setAlpha(float alpha, View... views) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (counter) {
            case 0:
                showcaseView.setShowcase(new ViewTarget(lockunlock), true);
                showcaseView.setContentTitle("Lock/Unlock Armour");
                showcaseView.setContentText("This shall be your next step.After you receive the connection-successfully-established message, on clicking this button you must provide your fingerprint and password to unlock the door");
                break;

            case 1:
                showcaseView.setShowcase(new ViewTarget(history), true);
                showcaseView.setContentTitle("Your Records");
                showcaseView.setContentText("This Button will show you the time and date, when your Armour was opened");
                break;

            case 2:
                showcaseView.setShowcase(new ViewTarget(aboutus), true);
                showcaseView.setContentTitle("Armour creators");
                showcaseView.setContentText("Clicking this button will connect you to the Armour family and give you all the information about it");
                break;

            case 3:
                showcaseView.setShowcase(new ViewTarget(terms), true);
                showcaseView.setContentTitle("Terms and Conditions/Privacy Policy");
                showcaseView.setContentText("This button will redirect you the terms and conditions and the privacy policy of Armour. We promise you its not as boring as you think. We recommend you to read (or hear) it atleast once");
                showcaseView.setButtonText(getString(R.string.close));
                setAlpha(0.4f, lockunlock, connect, configure, history, help, terms);
                break;

            case 4:
                showcaseView.hide();
                setAlpha(1.0f, lockunlock, connect, configure, history, help, terms);
                onStop();
                break;
        }
        counter++;
    }
}
