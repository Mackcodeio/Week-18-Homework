package com.studentAPI.utils;

import net.bytebuddy.utility.RandomString;

import java.util.Random;

public class TestUtils {
    public static String getRandomValue() {
        Random random = new Random();
        int randomInt = random.nextInt(10000);
        return Integer.toString(randomInt);
    }

    public static String getRandomText() {
        String rndText;
        RandomString rnd = new RandomString(10);
        return rndText = rnd.nextString();
    }
}
