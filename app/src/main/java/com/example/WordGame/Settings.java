package com.example.WordGame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private static final String PREFS_NAME = "SettingsPrefs";
    private static final String KEY_SECONDS = "seconds";
    private static final String KEY_CHECKBOX_ERETZ = "checkbox_eretz";
    private static final String KEY_CHECKBOX_CHAY = "checkbox_chay";
    private static final String KEY_CHECKBOX_MAACHAL = "checkbox_maachal";
    private static final String KEY_CHECKBOX_DOMEM = "checkbox_domem";
    private static final String KEY_CHECKBOX_CITY_IL = "checkbox_city_il";
    private static final String KEY_CHECKBOX_CITY_WORLD = "checkbox_city_world";
    private static final String KEY_CHECKBOX_TZOMEACH = "checkbox_tzomeach";
    private static final String KEY_CHECKBOX_BOY_NAME = "checkbox_boy_name";
    private static final String KEY_CHECKBOX_GIRL_NAME = "checkbox_girl_name";
    private static final String KEY_CHECKBOX_CAR = "checkbox_car";
    private static final String KEY_CHECKBOX_PROFESSION = "checkbox_profession";
    private static final String KEY_CHECKBOX_CELEB_IL_MAN = "checkbox_celeb_il_man";
    private static final String KEY_CHECKBOX_CELEB_IL_WOMAN = "checkbox_celeb_il_woman";
    private static final String KEY_CHECKBOX_CELEB_WORLD_MAN = "checkbox_celeb_world_man";
    private static final String KEY_CHECKBOX_CELEB_WORLD_WOMAN = "checkbox_celeb_world_woman";
    private static final String KEY_CHECKBOX_SONG = "checkbox_song";
    private static final String KEY_CHECKBOX_BOOK = "checkbox_book";
    private static final String KEY_CHECKBOX_MOVIE = "checkbox_movie";
    private static final String KEY_CHECKBOX_CLOTHING = "checkbox_clothing";
    private static final String KEY_CHECKBOX_BIBLE = "checkbox_bible";
    private static final String KEY_CHECKBOX_CARTOON = "checkbox_cartoon";
    private static final String KEY_CHECKBOX_BRANDS = "checkbox_brands";
    private static final String KEY_CHECKBOX_INSTRUMENT = "checkbox_instrument";
    private static final String KEY_CHECKBOX_ELECTRIC = "checkbox_electric";
    private static final String KEY_CHECKBOX_SETTLEMENT = "checkbox_settlement";
    private static final String KEY_CHECKBOX_ATHLETE = "checkbox_athlete";
    private static final String KEY_CHECKBOX_POLITICS = "checkbox_politics";
    private static final String KEY_CHECKBOX_KITCHEN = "checkbox_kitchen";

    private int seconds = 1;
    private TextView tvSeconds;
    private CheckBox checkBoxAll;
    private CheckBox checkBoxEretz;
    private CheckBox checkBoxChay;
    private CheckBox checkBoxMaachal;
    private CheckBox checkBoxDomem;
    private CheckBox checkBoxCityIL;
    private CheckBox checkBoxCityWorld;
    private CheckBox checkBoxTzomeach;
    private CheckBox checkBoxBoyName;
    private CheckBox checkBoxGirlName;
    private CheckBox checkBoxCar;
    private CheckBox checkBoxProfession;
    private CheckBox checkBoxCelebILman;
    private CheckBox checkBoxCelebILwoman;
    private CheckBox checkBoxCelebWorldman;
    private CheckBox checkBoxCelebWorldwoman;
    private CheckBox checkBoxSong;
    private CheckBox checkBoxBook;
    private CheckBox checkBoxMovie;
    private CheckBox checkBoxClothing;
    private CheckBox checkBoxBible;
    private CheckBox checkBoxCartoon;
    private CheckBox checkBoxBrands;
    private CheckBox checkBoxInstrument;
    private CheckBox checkBoxElectric;
    private CheckBox checkBoxSettlement;
    private CheckBox checkBoxAthlete;
    private CheckBox checkBoxPolitics;
    private CheckBox checkBoxKitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tvSeconds = findViewById(R.id.tvSeconds);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnDiscard = findViewById(R.id.btnDiscard);

        // Initialize checkboxes
        checkBoxAll = findViewById(R.id.checkBoxAll);
        checkBoxEretz = findViewById(R.id.checkBoxEretz);
        checkBoxChay = findViewById(R.id.checkBoxChay);
        checkBoxMaachal = findViewById(R.id.checkBoxMaachal);
        checkBoxDomem = findViewById(R.id.checkBoxDomem);
        checkBoxCityIL = findViewById(R.id.checkBoxCityIL);
        checkBoxCityWorld = findViewById(R.id.checkBoxCityWorld);
        checkBoxTzomeach = findViewById(R.id.checkBoxTzomeach);
        checkBoxBoyName = findViewById(R.id.checkBoxBoyName);
        checkBoxGirlName = findViewById(R.id.checkBoxGirlName);
        checkBoxCar = findViewById(R.id.checkBoxCar);
        checkBoxProfession = findViewById(R.id.checkBoxProfession);
        checkBoxCelebILman = findViewById(R.id.checkBoxCelebILman);
        checkBoxCelebILwoman = findViewById(R.id.checkBoxCelebILwoman);
        checkBoxCelebWorldman = findViewById(R.id.checkBoxCelebWorldman);
        checkBoxCelebWorldwoman = findViewById(R.id.checkBoxCelebWorldwoman);
        checkBoxSong = findViewById(R.id.checkBoxSong);
        checkBoxBook = findViewById(R.id.checkBoxBook);
        checkBoxMovie = findViewById(R.id.checkBoxMovie);
        checkBoxClothing = findViewById(R.id.checkBoxClothing);
        checkBoxBible = findViewById(R.id.checkBoxBible);
        checkBoxCartoon = findViewById(R.id.checkBoxCartoon);
        checkBoxBrands = findViewById(R.id.checkBoxBrands);
        checkBoxInstrument = findViewById(R.id.checkBoxInstrument);
        checkBoxElectric = findViewById(R.id.checkBoxElectric);
        checkBoxSettlement = findViewById(R.id.checkBoxSettlement);
        checkBoxAthlete = findViewById(R.id.checkBoxAthlete);
        checkBoxPolitics = findViewById(R.id.checkBoxPolitics);
        checkBoxKitchen = findViewById(R.id.checkBoxKitchen);

        // Rest of the initialization code...
        restoreSettings();
        tvSeconds.setText(String.valueOf(seconds));

        // Button listeners remain the same...

        checkBoxAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateOtherCheckboxes(isChecked);
        });

        btnMinus.setOnClickListener(v -> {
            if (seconds > 1) {
                seconds--;
                tvSeconds.setText(String.valueOf(seconds));
            }
        });

        btnPlus.setOnClickListener(v -> {
            seconds++;
            tvSeconds.setText(String.valueOf(seconds));
        });

        // Set listener for "הכל" checkbox
        checkBoxAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateOtherCheckboxes(isChecked);
        });

        btnSave.setOnClickListener(v -> {
            // Save settings
            saveSettings();
            // Go back to the main page
            finish(); // This will close the Settings activity and return to the previous one
        });

        btnDiscard.setOnClickListener(v -> {
            // Just go back to the main page without saving
            finish(); // This will close the Settings activity and return to the previous one
        });
    }

    private void updateOtherCheckboxes(boolean isChecked) {
        checkBoxEretz.setChecked(isChecked);
        checkBoxChay.setChecked(isChecked);
        checkBoxMaachal.setChecked(isChecked);
        checkBoxDomem.setChecked(isChecked);
        checkBoxCityIL.setChecked(isChecked);
        checkBoxCityWorld.setChecked(isChecked);
        checkBoxTzomeach.setChecked(isChecked);
        checkBoxBoyName.setChecked(isChecked);
        checkBoxGirlName.setChecked(isChecked);
        checkBoxCar.setChecked(isChecked);
        checkBoxProfession.setChecked(isChecked);
        checkBoxCelebILman.setChecked(isChecked);
        checkBoxCelebILwoman.setChecked(isChecked);
        checkBoxCelebWorldman.setChecked(isChecked);
        checkBoxCelebWorldwoman.setChecked(isChecked);
        checkBoxSong.setChecked(isChecked);
        checkBoxBook.setChecked(isChecked);
        checkBoxMovie.setChecked(isChecked);
        checkBoxClothing.setChecked(isChecked);
        checkBoxBible.setChecked(isChecked);
        checkBoxCartoon.setChecked(isChecked);
        checkBoxBrands.setChecked(isChecked);
        checkBoxInstrument.setChecked(isChecked);
        checkBoxElectric.setChecked(isChecked);
        checkBoxSettlement.setChecked(isChecked);
        checkBoxAthlete.setChecked(isChecked);
        checkBoxPolitics.setChecked(isChecked);
        checkBoxKitchen.setChecked(isChecked);
    }

    private void saveSettings() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_SECONDS, seconds);
        editor.putBoolean(KEY_CHECKBOX_ERETZ, checkBoxEretz.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CHAY, checkBoxChay.isChecked());
        editor.putBoolean(KEY_CHECKBOX_MAACHAL, checkBoxMaachal.isChecked());
        editor.putBoolean(KEY_CHECKBOX_DOMEM, checkBoxDomem.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CITY_IL, checkBoxCityIL.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CITY_WORLD, checkBoxCityWorld.isChecked());
        editor.putBoolean(KEY_CHECKBOX_TZOMEACH, checkBoxTzomeach.isChecked());
        editor.putBoolean(KEY_CHECKBOX_BOY_NAME, checkBoxBoyName.isChecked());
        editor.putBoolean(KEY_CHECKBOX_GIRL_NAME, checkBoxGirlName.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CAR, checkBoxCar.isChecked());
        editor.putBoolean(KEY_CHECKBOX_PROFESSION, checkBoxProfession.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CELEB_IL_MAN, checkBoxCelebILman.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CELEB_IL_WOMAN, checkBoxCelebILwoman.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CELEB_WORLD_MAN, checkBoxCelebWorldman.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CELEB_WORLD_WOMAN, checkBoxCelebWorldwoman.isChecked());
        editor.putBoolean(KEY_CHECKBOX_SONG, checkBoxSong.isChecked());
        editor.putBoolean(KEY_CHECKBOX_BOOK, checkBoxBook.isChecked());
        editor.putBoolean(KEY_CHECKBOX_MOVIE, checkBoxMovie.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CLOTHING, checkBoxClothing.isChecked());
        editor.putBoolean(KEY_CHECKBOX_BIBLE, checkBoxBible.isChecked());
        editor.putBoolean(KEY_CHECKBOX_CARTOON, checkBoxCartoon.isChecked());
        editor.putBoolean(KEY_CHECKBOX_BRANDS, checkBoxBrands.isChecked());
        editor.putBoolean(KEY_CHECKBOX_INSTRUMENT, checkBoxInstrument.isChecked());
        editor.putBoolean(KEY_CHECKBOX_ELECTRIC, checkBoxElectric.isChecked());
        editor.putBoolean(KEY_CHECKBOX_SETTLEMENT, checkBoxSettlement.isChecked());
        editor.putBoolean(KEY_CHECKBOX_ATHLETE, checkBoxAthlete.isChecked());
        editor.putBoolean(KEY_CHECKBOX_POLITICS, checkBoxPolitics.isChecked());
        editor.putBoolean(KEY_CHECKBOX_KITCHEN, checkBoxKitchen.isChecked());
        editor.apply();
    }

    private void restoreSettings() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        seconds = prefs.getInt(KEY_SECONDS, 1);
        checkBoxEretz.setChecked(prefs.getBoolean(KEY_CHECKBOX_ERETZ, false));
        checkBoxChay.setChecked(prefs.getBoolean(KEY_CHECKBOX_CHAY, false));
        checkBoxMaachal.setChecked(prefs.getBoolean(KEY_CHECKBOX_MAACHAL, false));
        checkBoxDomem.setChecked(prefs.getBoolean(KEY_CHECKBOX_DOMEM, false));
        checkBoxCityIL.setChecked(prefs.getBoolean(KEY_CHECKBOX_CITY_IL, false));
        checkBoxCityWorld.setChecked(prefs.getBoolean(KEY_CHECKBOX_CITY_WORLD, false));
        checkBoxTzomeach.setChecked(prefs.getBoolean(KEY_CHECKBOX_TZOMEACH, false));
        checkBoxBoyName.setChecked(prefs.getBoolean(KEY_CHECKBOX_BOY_NAME, false));
        checkBoxGirlName.setChecked(prefs.getBoolean(KEY_CHECKBOX_GIRL_NAME, false));
        checkBoxCar.setChecked(prefs.getBoolean(KEY_CHECKBOX_CAR, false));
        checkBoxProfession.setChecked(prefs.getBoolean(KEY_CHECKBOX_PROFESSION, false));
        checkBoxCelebILman.setChecked(prefs.getBoolean(KEY_CHECKBOX_CELEB_IL_MAN, false));
        checkBoxCelebILwoman.setChecked(prefs.getBoolean(KEY_CHECKBOX_CELEB_IL_WOMAN, false));
        checkBoxCelebWorldman.setChecked(prefs.getBoolean(KEY_CHECKBOX_CELEB_WORLD_MAN, false));
        checkBoxCelebWorldwoman.setChecked(prefs.getBoolean(KEY_CHECKBOX_CELEB_WORLD_WOMAN, false));
        checkBoxSong.setChecked(prefs.getBoolean(KEY_CHECKBOX_SONG, false));
        checkBoxBook.setChecked(prefs.getBoolean(KEY_CHECKBOX_BOOK, false));
        checkBoxMovie.setChecked(prefs.getBoolean(KEY_CHECKBOX_MOVIE, false));
        checkBoxClothing.setChecked(prefs.getBoolean(KEY_CHECKBOX_CLOTHING, false));
        checkBoxBible.setChecked(prefs.getBoolean(KEY_CHECKBOX_BIBLE, false));
        checkBoxCartoon.setChecked(prefs.getBoolean(KEY_CHECKBOX_CARTOON, false));
        checkBoxBrands.setChecked(prefs.getBoolean(KEY_CHECKBOX_BRANDS, false));
        checkBoxInstrument.setChecked(prefs.getBoolean(KEY_CHECKBOX_INSTRUMENT, false));
        checkBoxElectric.setChecked(prefs.getBoolean(KEY_CHECKBOX_ELECTRIC, false));
        checkBoxSettlement.setChecked(prefs.getBoolean(KEY_CHECKBOX_SETTLEMENT, false));
        checkBoxAthlete.setChecked(prefs.getBoolean(KEY_CHECKBOX_ATHLETE, false));
        checkBoxPolitics.setChecked(prefs.getBoolean(KEY_CHECKBOX_POLITICS, false));
        checkBoxKitchen.setChecked(prefs.getBoolean(KEY_CHECKBOX_KITCHEN, false));
    }
}