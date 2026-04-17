package com.example.kalendarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RecyclerView listaDni;
    private Button buttonPrev, buttonNext;

    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        textView = findViewById(R.id.textView);
        listaDni = findViewById(R.id.listaDni);
        buttonPrev = findViewById(R.id.button);
        buttonNext = findViewById(R.id.button2);

        buttonPrev.setOnClickListener(v -> {
            selectedDate = selectedDate.minusMonths(1);
            setMonthView();
        });

        buttonNext.setOnClickListener(v -> {
            selectedDate = selectedDate.plusMonths(1);
            setMonthView();
        });
    }

    private void setMonthView() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL yyyy", new Locale("pl", "PL"));
        textView.setText(selectedDate.format(formatter));

        ArrayList<LocalDate> dni = daysInMonthArray(selectedDate);

        KalendarzAdapter adapter = new KalendarzAdapter(dni);
        listaDni.setLayoutManager(new GridLayoutManager(this, 7));
        listaDni.setAdapter(adapter);
    }

    private ArrayList<LocalDate> daysInMonthArray(LocalDate date) {
        ArrayList<LocalDate> days = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = date.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                days.add(null);
            } else {
                days.add(LocalDate.of(date.getYear(), date.getMonth(), i - dayOfWeek));
            }
        }
        return days;
    }
}