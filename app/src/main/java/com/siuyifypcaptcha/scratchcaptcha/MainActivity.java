package com.siuyifypcaptcha.scratchcaptcha;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cooltechworks.views.ScratchImageView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    final Random rnd = new Random();
    Button button;
    RadioButton elephant, bear, kolabear;
    RadioGroup radioGroup;
    ScratchImageView scratchImageView;


    @Override
    protected void onCreate( final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        scratchImageView = (ScratchImageView) findViewById(R.id.scratchView1);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        elephant = (RadioButton) findViewById(R.id.Elephant);
        bear = (RadioButton) findViewById(R.id.Bear);
        kolabear = (RadioButton) findViewById(R.id.KolaBear);

        // enable images to be randomly generated and set them as the scratchimageview
        final String str = "img_" + rnd.nextInt(3);
        scratchImageView.setImageDrawable(getResources().getDrawable(getResourceID(str, "drawable", getApplicationContext())));

        // listener for revealing scratch image
        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView scratchImageView) {
                //on reveal
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // if scratch is revealed
                if (!scratchImageView.isRevealed()){
                    Toast.makeText(MainActivity.this, "Please scratch the whole image first.", Toast.LENGTH_LONG).show();
                }
                else{
                    switch (radioGroup.getCheckedRadioButtonId()) {
                        // if the radio button selected is bear and the current image equals to img_0
                        // else bring user to new activity page to try again.
                        case R.id.Bear:
                            if (scratchImageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_0).getConstantState())){
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intent);
                                break;
                            }
                            else{
                                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                                startActivity(intent);
                                break;
                            }

                        // if the radio button selected is kolabear and the current image equals to img_2
                        // else bring user to new activity page to try again.
                        case R.id.KolaBear:
                            if (scratchImageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_2).getConstantState())){
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intent);
                                break;
                            }
                            else{
                                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                                startActivity(intent);
                                break;
                            }

                         //if the radio button selected is elephant and the current image equals to img_1
                        //else bring user to new activity page to try again.
                        case R.id.Elephant:
                            if (scratchImageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_1).getConstantState())){
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intent);
                                break;
                            }
                            else{
                                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                                startActivity(intent);
                                break;
                            }

                        default:
                            break;
                    }
                }
            }
        });

    }

    // to return value
    protected final static int getResourceID(final String resName, final String resType, final Context ctx){
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);

        if (ResourceID == 0){
            throw new IllegalArgumentException("No such file");
        }
        else{
            return ResourceID;
        }
    }
}