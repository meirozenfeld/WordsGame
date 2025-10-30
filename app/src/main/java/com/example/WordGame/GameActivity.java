package com.example.WordGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SettingsPrefs";
    // Keys for each checkbox
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
    private static final String KEY_SECONDS = "seconds";

    private TextView tvSelectedCategory;
    private TextView tvSelectedLetter;
    private TextView tvCountdown;
    private Button btnRestart;
    private Button btnHome;
    private List<String> checkedCategories = new ArrayList<>();
    private Handler handler = new Handler();
    private Runnable updateCategoryRunnable;
    private Runnable updateLetterRunnable;
    private Runnable updateCountdownRunnable;
    private Random random = new Random();
    private String selectedCategory;
    private String selectedLetter;
    private int remainingDuration = 3000; // 3 seconds for letter draw
    private final int updateInterval = 100; // 100 milliseconds
    private int countdownDuration;
    private MediaPlayer mediaPlayer;
    private ImageView ivCategoryImage; // Declare ImageView variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initialize UI components
        tvSelectedCategory = findViewById(R.id.tvSelectedCategory);
        tvSelectedLetter = findViewById(R.id.tvSelectedLetter);
        tvCountdown = findViewById(R.id.tvCountdown);
        btnRestart = findViewById(R.id.btnRestart);
        btnHome = findViewById(R.id.btnHome);
        ivCategoryImage = findViewById(R.id.ivCategoryImage); // Initialize ImageView

        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.buzzer);

        // Set button listeners
        btnRestart.setOnClickListener(v -> restartGame());
        btnHome.setOnClickListener(v -> returnToHome());

        // Retrieve and prepare settings
        prepareCategories();

        // Start the category animation and letter draw
        startCategoryAnimation();
    }

    private void prepareCategories() {
        // Retrieve settings
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Collect checked categories
        checkedCategories.clear();
        // Collect checked categories
        if (prefs.getBoolean(KEY_CHECKBOX_ERETZ, false)) checkedCategories.add("ארץ");
        if (prefs.getBoolean(KEY_CHECKBOX_CHAY, false)) checkedCategories.add("חי");
        if (prefs.getBoolean(KEY_CHECKBOX_MAACHAL, false)) checkedCategories.add("מאכל");
        if (prefs.getBoolean(KEY_CHECKBOX_DOMEM, false)) checkedCategories.add("דומם");
        if (prefs.getBoolean(KEY_CHECKBOX_CITY_IL, false)) checkedCategories.add("עיר בישראל");
        if (prefs.getBoolean(KEY_CHECKBOX_CITY_WORLD, false)) checkedCategories.add("עיר בחול");
        if (prefs.getBoolean(KEY_CHECKBOX_TZOMEACH, false)) checkedCategories.add("צומח");
        if (prefs.getBoolean(KEY_CHECKBOX_BOY_NAME, false)) checkedCategories.add("שם של ילד מישראל");
        if (prefs.getBoolean(KEY_CHECKBOX_GIRL_NAME, false)) checkedCategories.add("שם של ילדה מישראל");
        if (prefs.getBoolean(KEY_CHECKBOX_CAR, false)) checkedCategories.add("סוג או חברת רכב");
        if (prefs.getBoolean(KEY_CHECKBOX_PROFESSION, false)) checkedCategories.add("מקצוע");
        if (prefs.getBoolean(KEY_CHECKBOX_CELEB_IL_MAN, false)) checkedCategories.add("מפורסם בארץ - גבר");
        if (prefs.getBoolean(KEY_CHECKBOX_CELEB_IL_WOMAN, false)) checkedCategories.add("מפורסמת בארץ - אישה");
        if (prefs.getBoolean(KEY_CHECKBOX_CELEB_WORLD_MAN, false)) checkedCategories.add("מפורסם בעולם - גבר");
        if (prefs.getBoolean(KEY_CHECKBOX_CELEB_WORLD_WOMAN, false)) checkedCategories.add("מפורסמת בעולם - אישה");
        if (prefs.getBoolean(KEY_CHECKBOX_SONG, false)) checkedCategories.add("שם של שיר");
        if (prefs.getBoolean(KEY_CHECKBOX_BOOK, false)) checkedCategories.add("שם של ספר");
        if (prefs.getBoolean(KEY_CHECKBOX_MOVIE, false)) checkedCategories.add("שם של סרט");
        if (prefs.getBoolean(KEY_CHECKBOX_CLOTHING, false)) checkedCategories.add("פריט לבוש");
        if (prefs.getBoolean(KEY_CHECKBOX_BIBLE, false)) checkedCategories.add("דמות מהתנך");
        if (prefs.getBoolean(KEY_CHECKBOX_CARTOON, false)) checkedCategories.add("דמות מצויירת");
        if (prefs.getBoolean(KEY_CHECKBOX_BRANDS, false)) checkedCategories.add("חברות ומותגים");
        if (prefs.getBoolean(KEY_CHECKBOX_INSTRUMENT, false)) checkedCategories.add("כלי נגינה");
        if (prefs.getBoolean(KEY_CHECKBOX_ELECTRIC, false)) checkedCategories.add("מוצר חשמל");
        if (prefs.getBoolean(KEY_CHECKBOX_SETTLEMENT, false)) checkedCategories.add("ישוב מושב או קיבוץ");
        if (prefs.getBoolean(KEY_CHECKBOX_ATHLETE, false)) checkedCategories.add("ספורטאים");
        if (prefs.getBoolean(KEY_CHECKBOX_POLITICS, false)) checkedCategories.add("פוליטיקאים");
        if (prefs.getBoolean(KEY_CHECKBOX_KITCHEN, false)) checkedCategories.add("כלי מטבח");

        // Perform lottery
        if (!checkedCategories.isEmpty()) {
            int index = random.nextInt(checkedCategories.size());
            selectedCategory = checkedCategories.get(index);
        } else {
            selectedCategory = "No categories selected";
        }

        // Get countdown duration from settings
        countdownDuration = prefs.getInt(KEY_SECONDS, 1);
    }

    private void startCategoryAnimation() {
        tvSelectedCategory.setVisibility(View.VISIBLE);
        tvSelectedLetter.setVisibility(View.GONE);
        tvCountdown.setVisibility(View.GONE);
        remainingDuration = 3000; // Reset to 3 seconds for category draw

        updateCategoryRunnable = new Runnable() {
            @Override
            public void run() {
                if (remainingDuration <= 0) {
                    // Stop the animation and set the selected category
                    updateCategoryTextView(selectedCategory, true); // Show final category with image
                    startLetterAnimation();
                    return;
                }

                // Update the TextView with a random category
                if (!checkedCategories.isEmpty()) {
                    int index = random.nextInt(checkedCategories.size());
                    String category = checkedCategories.get(index);
                    updateCategoryTextView(category, false); // Update with random category without image
                }

                // Decrease the remaining duration
                remainingDuration -= updateInterval;

                // Schedule the next update
                handler.postDelayed(this, updateInterval);
            }
        };

        handler.post(updateCategoryRunnable);
    }

    private void updateCategoryTextView(String category, boolean isFinal) {
        int imageResource = 0; // Default image resource

        // Determine the appropriate image resource based on the category
        switch (category) {
            case "סוג או חברת רכב":
                imageResource = R.drawable.car; // Replace with actual resource ID
                break;
            case "שם של ילד מישראל":
                imageResource = R.drawable.boy; // Replace with actual resource ID
                break;
            case "שם של ילדה מישראל":
                imageResource = R.drawable.girl; // Replace with actual resource ID
                break;
            case "חי":
                imageResource = R.drawable.animal; // Replace with actual resource ID
                break;
            case "עיר בישראל":
                imageResource = R.drawable.city; // Replace with actual resource ID
                break;
            case "עיר בחול":
                imageResource = R.drawable.city; // Replace with actual resource ID
                break;
            case "ישוב מושב או קיבוץ":
                imageResource = R.drawable.city; // Replace with actual resource ID
                break;
            case "ארץ":
                imageResource = R.drawable.country; // Replace with actual resource ID
                break;
            case "דומם":
                imageResource = R.drawable.domem; // Replace with actual resource ID
                break;
            case "מפורסם בארץ - גבר":
                imageResource = R.drawable.fam; // Replace with actual resource ID
                break;
            case "מפורסם בעולם - גבר":
                imageResource = R.drawable.fam; // Replace with actual resource ID
                break;
            case "מאכל":
                imageResource = R.drawable.food; // Replace with actual resource ID
                break;
            case "צומח":
                imageResource = R.drawable.plant; // Replace with actual resource ID
                break;
            case "מקצוע":
                imageResource = R.drawable.profession; // Replace with actual resource ID
                break;
            case "שם של שיר":
                imageResource = R.drawable.song; // Replace with actual resource ID
                break;
            // Add more cases for other categories as needed
            default:
                imageResource = 0; // No image for categories not listed
                break;
        }

        // Update the TextView with the category text
        tvSelectedCategory.setText(category);

        if (isFinal) {
            // Show image if it's the final category
            if (imageResource != 0) {
                ivCategoryImage.setImageResource(imageResource);
                ivCategoryImage.setVisibility(View.VISIBLE);
            } else {
                ivCategoryImage.setVisibility(View.GONE);
            }
        } else {
            // Hide image during the draw
            ivCategoryImage.setVisibility(View.GONE);
        }
    }
    private void startLetterAnimation() {
        tvSelectedLetter.setVisibility(View.VISIBLE);
        tvCountdown.setVisibility(View.GONE); // Hide countdown initially
        remainingDuration = 3000; // Reset to 3 seconds for letter draw

        updateLetterRunnable = new Runnable() {
            @Override
            public void run() {
                if (remainingDuration <= 0) {
                    // Stop the animation and set the selected letter
                    if (selectedLetter == null) {
                        // If selectedLetter is still null, assign a random letter
                        String[] letters = {"א", "ב", "ג", "ד", "ה", "ו", "ז", "ח", "ט", "י", "כ", "ל", "מ", "נ", "ס", "ע", "פ", "צ", "ק", "ר", "ש", "ת"};
                        selectedLetter = letters[random.nextInt(letters.length)];
                    }
                    tvSelectedLetter.setText(selectedLetter);
                    Log.d("GameActivity", "Selected Letter: " + selectedLetter); // Log the selected letter
                    startCountdown();
                    return;
                }

                // Update the TextView with a random letter
                String[] letters = {"א", "ב", "ג", "ד", "ה", "ו", "ז", "ח", "ט", "י", "כ", "ל", "מ", "נ", "ס", "ע", "פ", "צ", "ק", "ר", "ש", "ת"};
                int index = random.nextInt(letters.length);
                tvSelectedLetter.setText(letters[index]);

                // Decrease the remaining duration
                remainingDuration -= updateInterval;

                // Schedule the next update
                handler.postDelayed(this, updateInterval);
            }
        };

        handler.post(updateLetterRunnable);
    }




    private void startCountdown() {
        tvCountdown.setVisibility(View.VISIBLE);
        countdownDuration = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getInt(KEY_SECONDS, 1);

        updateCountdownRunnable = new Runnable() {
            @Override
            public void run() {
                if (countdownDuration <= 0) {
                    // Play the buzzer sound
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }

                    // Display "נגמר הזמן!" in red
                    tvCountdown.setText("נגמר הזמן!");
                    tvCountdown.setTextColor(getResources().getColor(R.color.custom_red)); // Use custom color

                    // Keep the countdown visible for 1 second before hiding it (if needed)
                    handler.postDelayed(() -> tvCountdown.setVisibility(View.VISIBLE), 1000);

                    return;
                }

                // Update the countdown TextView
                tvCountdown.setText(String.valueOf(countdownDuration));
                tvCountdown.setTextColor(getResources().getColor(R.color.custom_green)); // Use custom color

                // Decrease the countdown duration
                countdownDuration--;

                // Schedule the next update
                handler.postDelayed(this, 1000); // Update every second
            }
        };

        handler.post(updateCountdownRunnable);
    }

    private void restartGame() {
        // Stop the current runnables
        if (updateCategoryRunnable != null) handler.removeCallbacks(updateCategoryRunnable);
        if (updateLetterRunnable != null) handler.removeCallbacks(updateLetterRunnable);
        if (updateCountdownRunnable != null) handler.removeCallbacks(updateCountdownRunnable);

        // Reset countdown text and hide it immediately
        tvCountdown.setText("");
        tvCountdown.setVisibility(View.GONE);

        // Reset variables
        selectedCategory = null;
        selectedLetter = null;
        remainingDuration = 3000; // 3 seconds for letter draw

        // Restart the game
        prepareCategories();
        startCategoryAnimation();
    }

    private void returnToHome() {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
