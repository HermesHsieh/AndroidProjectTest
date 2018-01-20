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
    public void EMALL_ADDRESS_FORMAT_not_match() throws Exception {
        String email = "#@^T@($*GU@Q(&Y";
        Assert.assertEquals("Email : " + email + " is match the email address, please try other strings to dis-match email address.",
                false,
                ValidUtils.isValidPattern(Patterns.EMAIL_ADDRESS, email)
        );
    }

    @Test
    public void EMALL_ADDRESS_FORMAT_not_match_when_input_is_empty() throws Exception {
        String email = "";
        Assert.assertEquals("Email : " + email + " is match the email address verify rule, please try other strings to dis-match email address.",
                false,
                ValidUtils.isValidPattern(Patterns.EMAIL_ADDRESS, email)
        );
    }

    @Test
    public void EMALL_ADDRESS_FORMAT_not_match_when_input_is_null() throws Exception {
        String email = null;
        Assert.assertEquals("Email : " + email + " is match the email address verify rule, please try other strings to dis-match email address.",
                false,
                ValidUtils.isValidPattern(Patterns.EMAIL_ADDRESS, email)
        );
    }

    @Test
    public void EMALL_ADDRESS_FORMAT_match() throws Exception {
        String email = "hello.test@gmail.com";
        Assert.assertEquals("Email : " + email + " is not match the email address verify rule, please try other strings to match email address.",
                true,
                ValidUtils.isValidPattern(Patterns.EMAIL_ADDRESS, email)
        );
    }

    @Test
    public void PASSWORD_FORMAT_not_match_when_input_is_empty() throws Exception {
        String pwd = "";
        Assert.assertEquals("Password : " + pwd + " length : " + pwd.length() + " is match the password rule [not empty or null], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void PASSWORD_FORMAT_not_match_when_input_is_null() throws Exception {
        String pwd = null;
        Assert.assertEquals("Password : " + pwd + " is match the password rule [not empty or null], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void PASSWORD_FORMAT_not_match_when_input_is_too_short() throws Exception {
        String pwd = "wgre";
        Assert.assertEquals("Password : " + pwd + " length : " + pwd.length() + " is match the password rule [length range 6-20], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void PASSWORD_FORMAT_not_match_when_input_is_too_long() throws Exception {
        String pwd = "qp3948fjLUVHWEGIUHIEUWHF@0923r97weyf";
        Assert.assertEquals("Password : " + pwd + " length : " + pwd.length() + " is match the password rule [length range 6-20], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

//    @Test
//    public void PASSWORD_FORMAT_not_match_when_input_is_only_letter() throws Exception {
//        String pwd = "qertoEOFIJ";
//        Assert.assertEquals("Password : " + pwd + " is match the password rule [combination with letter and number], please try other strings to dis-match password rule.",
//                false,
//                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
//        );
//    }
//
//    @Test
//    public void PASSWORD_FORMAT_not_match_when_input_is_only_number() throws Exception {
//        String pwd = "23458976";
//        Assert.assertEquals("Password : " + pwd + " is match the password rule [combination with letter and number], please try other strings to dis-match password rule.",
//                false,
//                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
//        );
//    }

    @Test
    public void PASSWORD_FORMAT_not_match_when_input_include_space() throws Exception {
        String pwd = "OWEF H4576";
        Assert.assertEquals("Password : " + pwd + " is match the password rule [didn't include space], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void PASSWORD_FORMAT_not_match_when_input_include_undefine_sign() throws Exception {
        String pwd = "~ar576%)*";
        Assert.assertEquals("Password : " + pwd + " is match the password rule [include undefine sign (except:_!@#$%&.)], please try other strings to dis-match password rule.",
                false,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void PASSWORD_FORMAT_match_when_input_only_number() throws Exception {
        String pwd = "3405273468";
        Assert.assertEquals("Password : " + pwd + " is didn't match the password rule [length 6-20, include define sign :_!@#$%&.], please try other strings to match password rule.",
                true,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void PASSWORD_FORMAT_match_when_input_only_letter() throws Exception {
        String pwd = "aruhWRGHij";
        Assert.assertEquals("Password : " + pwd + " is didn't match the password rule [length 6-20, include define sign :_!@#$%&.], please try other strings to match password rule.",
                true,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void PASSWORD_FORMAT_match_when_input_only_sign() throws Exception {
        String pwd = "_!@#$%&.#@!&&";
        Assert.assertEquals("Password : " + pwd + " is didn't match the password rule [length 6-20, include define sign :_!@#$%&.], please try other strings to match password rule.",
                true,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void PASSWORD_FORMAT_match_when_input_mix_letter_number_sign() throws Exception {
        String pwd = "_287EGOdh2";
        Assert.assertEquals("Password : " + pwd + " is didn't match the password rule [length 6-20, include define sign :_!@#$%&.], please try other strings to match password rule.",
                true,
                ValidUtils.isValidPattern(Patterns.PASSWORD, pwd)
        );
    }

    @Test
    public void USER_NAME_FORMAT_no_match_when_input_is_empty() {
        String string = "";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is match the USER_NAME rule [not empty or null], please try other strings to dis-match USER_NAME rule.",
                false,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_no_match_when_input_is_null() {
        String string = null;
        Assert.assertEquals("UsesName : " + string + " is match the USER_NAME rule [not empty or null], please try other strings to dis-match USER_NAME rule.",
                false,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_no_match_when_input_contain_space() {
        String string = "aaw h4398";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is match the USER_NAME rule [contain space ' '], please try other strings to dis-match USER_NAME rule.",
                false,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_no_match_when_input_too_short() {
        String string = "123";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is match the USER_NAME rule [length 6-20], please try other strings to dis-match USER_NAME rule.",
                false,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_no_match_when_input_too_long() {
        String string = "123456789012345678901234567890";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is match the USER_NAME rule [length 6-20], please try other strings to dis-match USER_NAME rule.",
                false,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_no_match_when_input_first_letter_is_define_sign() {
        String string = "@12323456890";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is match the USER_NAME rule [first letter must be a letter/number/underline], please try other strings to dis-match USER_NAME rule.",
                false,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_no_match_when_input_include_undefine_sign() {
        String string = "aera2345&%890";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is match the USER_NAME rule [didn't include undefine sign (others:@.!)], please try other strings to dis-match USER_NAME rule.",
                false,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_match_when_input_first_letter_is_lowercase_and_include_define_sign() {
        String string = "aera2@348.90";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is dis-match the USER_NAME rule [first letter must be a letter/number/underline, others can include define sign:@!._], please try other strings to match USER_NAME rule.",
                true,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_match_when_input_first_letter_is_underline_and_include_define_sign() {
        String string = "_aer2d3SDO90";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is dis-match the USER_NAME rule [first letter must be a letter/number/underline, others can include define sign:@!._], please try other strings to match USER_NAME rule.",
                true,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_match_when_input_first_letter_is_uppercase() {
        String string = "R253aer90";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is dis-match the USER_NAME rule [first letter must be a letter/number/underline, others can include define sign:@!._], please try other strings to match USER_NAME rule.",
                true,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    @Test
    public void USER_NAME_FORMAT_match_when_input_first_letter_is_number() {
        String string = "253aer90";
        Assert.assertEquals("UsesName : " + string + " length : " + string.length() + " is dis-match the USER_NAME rule [first letter must be a letter/number/underline, others can include define sign:@!._], please try other strings to match USER_NAME rule.",
                true,
                ValidUtils.isValidPattern(Patterns.USER_NAME, string)
        );
    }

    protected static class Patterns {
        private static final String USER_NAME_PATTERN = "^[\\w][\\w@.!]{6,20}$";
        private static final Pattern USER_NAME = Pattern.compile(USER_NAME_PATTERN);

        private static final String EMAIL_PATTERN =
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+";
        private static final Pattern EMAIL_ADDRESS = Pattern.compile(EMAIL_PATTERN);

        //        private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-z]+)(?=.*[0-9]+)(?!(?=.*[^a-zA-Z0-9]+))(?=\\S+$).{6,20}$";
        private static final String PASSWORD_PATTERN = "^[\\w!@#$%&.]{6,20}$";
        private static final Pattern PASSWORD = Pattern.compile(PASSWORD_PATTERN);
    }

}
