package lombok.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Created by hermes on 2017/8/31.
 */
@Data
@Log4j
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @NonNull
    private int x;
    @NonNull
    private int y;
    @NonNull
    private String name;

    private String title;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoArgsExample {
        @NonNull
        private String field;
    }
}
