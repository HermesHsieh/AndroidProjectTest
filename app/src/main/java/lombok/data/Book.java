package lombok.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by hermes on 2017/8/31.
 */
@Data
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
