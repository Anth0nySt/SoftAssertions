package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAsertionsTest {


        @BeforeAll
        static void beforeAll() {
            Configuration.browserSize = "3840 x 2160";
            Configuration.pageLoadStrategy = "eager";
            Configuration.baseUrl = "https://github.com";
        }

        @Test
        void softAssertionsTest(){
            open("/selenide");
            $$(".js-pinned-items-reorder-container").first().$("a").click();
            $("#wiki-tab").click();
            $("#wiki-body").shouldHave(Condition.text("Soft assertions"));
            $("#wiki-body").$(byText("Soft assertions")).click();
            $("#wiki-body").shouldHave(Condition.text("Using JUnit5"));
            $("#wiki-body").shouldHave(Condition.text(
                    "class Tests {\n" +
                            "@RegisterExtension \n" +
                            "static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                            "\n" +
                            "@Test\n" +
                            "void test() {\n" +
                            "Configuration.assertionMode = SOFT;\n" +
                            "open(\"page.html\");\n" +
                            "\n" +
                            "$(\"#first\").should(visible).click();\n" +
                            "$(\"#second\").should(visible).click();\n" +
                            "}\n" +
                            "}"));
        }

}

