package marshi.streamhelper.filter;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * {@link java.util.stream.Stream#filter(Predicate)}の引数として指定するPredicateを生成する.
 */
public class FilterUtils {

    /**
     * streamの要素の特定のプロパティの値で存在判定を行う.
     * @param keyExtractor 要素からプロパティを返却するFunction.
     * @param <T> streamの要素の型.
     * @return
     */
    public static <T> Predicate<T> distinctByProperty(Function<T, ?> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * streamの要素が{@code collection}の要素の特定のプロパティへ含まれているか判定を行う.
     * @param collection 包含判定対象の集合.
     * @param extractor {@code collection}の要素から特定のプロパティを指定するFunction.
     * @param <T>
     * @param <P>
     * @return
     */
    public static <T, P> Predicate<P> containsByProperty(Collection<T> collection, Function<T, P> extractor) {
        Set<P> set = collection.stream()
                .map(extractor)
                .collect(Collectors.toSet());
        return set::contains;
    }

}
