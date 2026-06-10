package utilities;

public class CommonMethods {
    public static ExpectedCondition<WebElement> visibilityOfElementLocated(String object) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    WebElement getElement =
                    return elementIfVisible(driver.findElement(locator));
                } catch (StaleElementReferenceException | NoSuchElementException e) {
                    // Returns null because the element is no longer or not present in DOM.
                    return null;
                }
            }

            @Override
            public String toString() {
                return "visibility of element located by " + locator;
            }
        };
    }
}