package marshi.streamhelper.filter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;

/**
 *
 */
class FilterUtilsTest {

    @Test
    void distinctByProperty() {
        Element apple = new Element();
        apple.setProperty("apple");
        Element banana = new Element();
        banana.setProperty("banana");
        Element kiwi = new Element();
        kiwi.setProperty("kiwi");
        Element kiwi2 = new Element();
        kiwi2.setProperty("kiwi");
        List<Element> elements = Arrays.asList(apple, banana, kiwi, kiwi2);

        List<Element> collect = elements.stream()
                .filter(FilterUtils.distinctByProperty(Element::getProperty))
                .collect(Collectors.toList());
        Assert.assertThat(collect.size(), is(3));
        Assert.assertThat(collect.get(0).getProperty(), is("apple"));
        Assert.assertThat(collect.get(1).getProperty(), is("banana"));
        Assert.assertThat(collect.get(2).getProperty(), is("kiwi"));
    }

    @Test
    void containsByProperty() {
        Element apple = new Element();
        apple.setProperty("apple");
        Element banana = new Element();
        banana.setProperty("banana");
        List<String> fruits = Arrays.asList("apple", "banana", "dummy");
        List<Element> elements = Arrays.asList(apple, banana);

        List<String> collect = fruits.stream()
                .filter(FilterUtils.containsByProperty(elements, Element::getProperty))
                .collect(Collectors.toList());
        Assert.assertThat(collect.size(), is(2));
        Assert.assertThat(collect.get(0), is("apple"));
        Assert.assertThat(collect.get(1), is("banana"));
    }

    static class Element {
        private String property;

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }

}