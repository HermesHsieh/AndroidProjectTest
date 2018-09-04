package tw.android.test.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

/**
 * Created by hermes on 2017/10/23.
 */

@Getter
public class User {
    public final String firstName;
    public final String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @SerializedName("vip")
    private boolean isVip;

    public boolean isVip() {
        return true;
    }

    public Integer getPoints() {
        return 5000;
    }

    public Integer getRainbowDiamonds() {
        return 5000;
    }

    public static class Balances implements Serializable {

        private Integer mPoints;
        private Integer mRainbowDiamonds;

        public Integer getPoints() {
            return 5000;
        }

        public Integer getRainbowDiamonds() {
            return 5000;
        }

        public void setPoints(Integer paramInteger) {
            this.mPoints = paramInteger;
        }

        public void setRainbowDiamonds(Integer paramInteger) {
            this.mRainbowDiamonds = paramInteger;
        }
    }
}
