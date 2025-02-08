package com.example.ex33_configureddialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity is the entry point for the application. It provides various dialog functionalities
 * including single choice, multi-choice, input fields, and background color changes.
 * It also offers a menu option to navigate to the credits screen.
 *
 * @author Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version 1.0
 * @since 08/02/2025
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Dialog builder used to create various types of AlertDialogs.
     */
    private AlertDialog.Builder dlgBuilder;

    /**
     * Layout whose background color will be changed dynamically.
     */
    private LinearLayout bgLayout;

    /**
     * RGB values to represent colors.
     */
    private int[] rgbVals = new int[3];

    /**
     * Array containing color names for the dialogs.
     */
    private final String[] colors = {"Red", "Green", "Blue"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bgLayout = findViewById(R.id.bl);
    }

    /**
     * Displays a dialog allowing the user to select one color, modifying the background color.
     *
     * @param v The view triggering the dialog.
     */
    public void onColorSelect(View v) {
        rgbVals = new int[]{0, 0, 0};

        dlgBuilder = new AlertDialog.Builder(this);
        dlgBuilder.setCancelable(false);
        dlgBuilder.setTitle("Choose a color");
        dlgBuilder.setItems(colors, (dialog, which) -> {
            rgbVals[which] = 255;
            bgLayout.setBackgroundColor(Color.rgb(rgbVals[0], rgbVals[1], rgbVals[2]));
        });
        dlgBuilder.setNeutralButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog dlg = dlgBuilder.create();
        dlg.show();
    }


    /**
     * Displays a dialog allowing the user to select multiple colors, modifying the background color.
     *
     * @param v The view triggering the dialog.
     */
    public void onMultiColorSelect(View v) {
        rgbVals = new int[]{0, 0, 0};

        dlgBuilder = new AlertDialog.Builder(this);
        dlgBuilder.setCancelable(false);
        dlgBuilder.setTitle("Select colors");
        dlgBuilder.setMultiChoiceItems(colors, null, (dialog, which, isChecked) -> {
            rgbVals[which] = isChecked ? 255 : 0;
            bgLayout.setBackgroundColor(Color.rgb(rgbVals[0], rgbVals[1], rgbVals[2]));
        });
        dlgBuilder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        dlgBuilder.setNeutralButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog dlg = dlgBuilder.create();
        dlg.show();
    }

    /**
     * Resets the background color to white.
     *
     * @param v The view triggering this action.
     */
    public void onResetColor(View v) {
        bgLayout.setBackgroundColor(Color.WHITE);
    }

    /**
     * Displays a dialog with an input field, and shows the entered text in a Toast.
     *
     * @param v The view triggering the dialog.
     */
    public void onInput(View v) {
        dlgBuilder = new AlertDialog.Builder(this);
        dlgBuilder.setCancelable(false);
        dlgBuilder.setTitle("Enter Text");

        final EditText inputField = new EditText(this);
        inputField.setHint("Type something here");
        dlgBuilder.setView(inputField);

        dlgBuilder.setPositiveButton("Copy", (dialog, which) -> {
            String inputText = inputField.getText().toString();
            Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
        });
        dlgBuilder.setNeutralButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog dlg = dlgBuilder.create();
        dlg.show();
    }

    /**
     * Initializes the options menu with a credits option.
     *
     * @param menu The menu to initialize.
     * @return true if the menu was created successfully.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.creditsmain, menu);
        return true;
    }

    /**
     * Handles selection of items from the options menu. Redirects to the credits screen
     * if the corresponding menu item is selected.
     *
     * @param item The selected menu item.
     * @return true if the selection was handled.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuCredits) {
            Intent creditsIntent = new Intent(this, mainCredits.class);
            startActivity(creditsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}