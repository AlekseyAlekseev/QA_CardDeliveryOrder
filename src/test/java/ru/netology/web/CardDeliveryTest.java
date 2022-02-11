package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {


    private String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    public void shouldRegByAccount() {

        Configuration.holdBrowserOpen = true;

        String planningDate = generateDate(10);

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Володин Евгений");
        $("[data-test-id=phone] input").setValue("+79218032784");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate));
    }

}
