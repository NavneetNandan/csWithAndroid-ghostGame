package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        Button challange=(Button)findViewById(R.id.challenge);

        challange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView label=(TextView)findViewById(R.id.gameStatus);
                TextView text = (TextView) findViewById(R.id.ghostText);
                if (dictionary.isWord(text.getText().toString())&&text.getText().length()>=4)
                    label.setText("You Won");
                else
                    label.setText("Computer Won");
            }
        });
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new SimpleDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        Log.e("in","me");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        // Do computer turn stuff then make it the user's turn again
        TextView text = (TextView) findViewById(R.id.ghostText);
        if (text.length()>=4&&dictionary.isWord(text.getText().toString())){
            label.setText("Computer Won");
        }else {
            String h=dictionary.getAnyWordStartingWith(text.getText().toString());
            Log.e("ph",h);
            if (h==null){
                Log.e("not","foud");
                label.setText("Computer Won");
            }
            else {
                text.setText(h.substring(0, text.length() + 1));
                userTurn = true;
                label.setText(USER_TURN);
            }

        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if ((event.getUnicodeChar()>='a'&&event.getUnicodeChar()<='z')||(event.getUnicodeChar()>='A'&&event.getUnicodeChar()<='Z')){
            TextView text = (TextView) findViewById(R.id.ghostText);
            text.append(String.valueOf((char)event.getUnicodeChar()));
            Log.e("wors",text.getText().toString());
            computerTurn();
            return true;
        }
        else{
            return super.onKeyUp(keyCode, event);
        }
    }
}
