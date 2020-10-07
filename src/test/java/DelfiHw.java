import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DelfiHw {
    private final By ARTICLE = By.tagName("article");
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By COMMENTS_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");

    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'd-print')]");

    private final By COMMENTS_PAGE_TITLE = By.xpath(".//h1[(@class = 'article-title')]");
    private final By COMMENTS_PAGE_COMMENTS_COUNT = By.xpath(".//span[(@class = 'type-cnt')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Test
    public void articleTitleCommentsCheck() {
        LOGGER.info("This test is checking titles and comments count");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");

        LOGGER.info("Opening browser");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        LOGGER.info("Opening home page");
        driver.get("https://delfi.lv");

        LOGGER.info("Finding all articles on home page");
        List<WebElement> articles = driver.findElements(ARTICLE);

        LOGGER.info("Finding 3rd article on home page");
        WebElement article = articles.get(2);

        LOGGER.info("Finding title of the 3rd article on home page");
        String homePageTitle = article.findElement(TITLE).getText();

        LOGGER.info("Finding comments count of the 3rd article on home page");
        int homePageCommentsCount = 0;
        if (!article.findElements(COMMENTS_COUNT).isEmpty()) {
            homePageCommentsCount = parseCommentCount(article.findElement(COMMENTS_COUNT).getText());
        }

        LOGGER.info("Clicking on the title of the 3rd article");
        article.findElement(TITLE).click();

        LOGGER.info("Waiting for the title");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ARTICLE_PAGE_TITLE));

        LOGGER.info("Finding title on article page");
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();

        LOGGER.info("Finding comments count on article page");
        int articlePageCommentCount = parseCommentCount(driver.findElement(ARTICLE_PAGE_COMMENTS)
                .getText());

        LOGGER.info("Checking titles and comments count on home page and article page");
        Assertions.assertTrue(homePageTitle.startsWith(articlePageTitle), "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentCount, "Wrong comment count!");

        LOGGER.info("Opening comments page");
        driver.findElement(ARTICLE_PAGE_COMMENTS).click();

        LOGGER.info("Finding title om comments page");
        String commentsPageTitle = driver.findElement(COMMENTS_PAGE_TITLE).getText().trim();

        LOGGER.info("Finding comments count on comments page");
        int commentsPageCommentCount = Integer.parseInt(driver.findElement(COMMENTS_PAGE_COMMENTS_COUNT).getText());

        LOGGER.info("Checking");
        Assertions.assertTrue(homePageTitle.startsWith(commentsPageTitle));
        Assertions.assertEquals(homePageCommentsCount, commentsPageCommentCount, "Wrong comment count");
    }

    private int parseCommentCount(String textToParse) {
        textToParse = textToParse.substring(1, textToParse.length() - 1);
        return Integer.parseInt(textToParse);
    }
}

