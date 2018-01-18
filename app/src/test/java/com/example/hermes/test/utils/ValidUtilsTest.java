package com.example.hermes.test.utils;

import android.text.TextUtils;
import android.util.Patterns;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tw.android.test.utils.ValidUtils;

import static org.mockito.Matchers.any;

/**
 * Created by hermes.hsieh on 2018/1/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Matcher.class, Patterns.class, TextUtils.class})
public class ValidUtilsTest {

    @Before
    public void setupTextUtils() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
    }

    @Test
    public void is_valid_pattern_for_EMALL_ADDRESS_FORMAT_not_match() throws Exception {
        String email = "#@^T@($*GU@Q(&Y";
        Assert.assertEquals("Email : " + email + " is match the email address, please try other strings to dis-match email address.",
                false,
                ValidUtils.isValidPattern(Patterns.EMAIL_ADDRESS, email)
        );
    }

    @Test
    public void is_valid_pattern_for_EMALL_ADDRESS_FORMAT_not_match_when_input_is_empty() throws Exception {
        String email = "";
        Assert.assertEquals("Email : " + email + " is match the email address verify rule, please try other strings to dis-match email address.",
                false,
                ValidUtils.isValidPattern(Patterns.EMAIL_ADDRESS, email)
        );
    }

    @Test
    public void is_valid_pattern_for_EMALL_ADDRESS_FORMAT_not_match_when_input_is_null() throws Exception {
        String email = null;
        Assert.assertEquals("Email : " + email + " is match the email address verify rule, please try other strings to dis-match email address.",
                false,
                ValidUtils.isValidPattern(Patterns.EMAIL_ADDRESS, email)
        );
    }

    @Test
    public void is_valid_pattern_for_EMALL_ADDRESS_FORMAT_match() throws Exception {
        String email = "hello.test@gmail.com";
        Assert.assertEquals("Email : " + email + " is not match the email address verify rule, please try other strings to match email address.",
                true,
                ValidUtils.isValidPattern(Patterns.EMAIL_ADDRESS, email)
        );
    }

    @Test
    public void is_valid_pattern_for_PASSWORD_FORMAT_not_match_when_input_is_empty() throws Exception {
        String pwd = "";
        Assert.assertEquals("Password : " + pwd + " length : " + pwd.length() + " is match the password rule [not empty or null], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void is_valid_pattern_for_PASSWORD_FORMAT_not_match_when_input_is_null() throws Exception {
        String pwd = null;
        Assert.assertEquals("Password : " + pwd + " is match the password rule [not empty or null], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void is_valid_pattern_for_PASSWORD_FORMAT_not_match_when_input_is_too_short() throws Exception {
        String pwd = "wgre";
        Assert.assertEquals("Password : " + pwd + " length : " + pwd.length() + " is match the password rule [length range 6-20], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void is_valid_pattern_for_PASSWORD_FORMAT_not_match_when_input_is_too_long() throws Exception {
        String pwd = "qp3948fjLUVHWEGIUHIEUWHF@0923r97weyf";
        Assert.assertEquals("Password : " + pwd + " length : " + pwd.length() + " is match the password rule [length range 6-20], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void is_valid_pattern_for_PASSWORD_FORMAT_not_match_when_input_is_only_letter() throws Exception {
        String pwd = "qertoEOFIJ";
        Assert.assertEquals("Password : " + pwd + " is match the password rule [combination with letter and number], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void is_valid_pattern_for_PASSWORD_FORMAT_not_match_when_input_is_only_number() throws Exception {
        String pwd = "23458976";
        Assert.assertEquals("Password : " + pwd + " is match the password rule [combination with letter and number], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void is_valid_pattern_for_PASSWORD_FORMAT_not_match_when_input_contain_space() throws Exception {
        String pwd = "OWEF H4576";
        Assert.assertEquals("Password : " + pwd + " is match the password rule [didn't contain space], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void is_valid_pattern_for_PASSWORD_FORMAT_match_input() throws Exception {
        String pwd = "aweoij2397";
        Assert.assertEquals("Password : " + pwd + " is didn't match the password rule [length 6-20, didn't contain space, at least one letter and one number], please try other strings to match password rule.",
                true,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    protected static class Patterns {
        private static final String EMAIL_PATTERN =
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+";
        private static final Pattern EMAIL_ADDRESS = Pattern.compile(EMAIL_PATTERN);

        private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-z]+)(?=.*[0-9]+)(?!(?=.*[^a-zA-Z0-9]+))(?=\\S+$).{6,20}$";
        private static final Pattern PASSWORD = Pattern.compile(PASSWORD_PATTERN);
    }

}
