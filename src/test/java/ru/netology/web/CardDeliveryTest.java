package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    Calendar calendar = new GregorianCalendar();

    @Test
    public void shouldRegByAccount() {

        calendar.add(Calendar.DAY_OF_YEAR, 3);
        String date = formatter.format(calendar.getTime());

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").shouldHave(value(date));
        $("[data-test-id=name] input").setValue("Володин Евгений");
        $("[data-test-id=phone] input").setValue("+79218032784");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date));
    }

}
