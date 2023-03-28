package me.yifeiyuan.onepiece.dev;

import me.yifeiyuan.onepiece.arch.ability.AbilityManager;

/**
 * Created by 程序亦非猿 on 2023/3/28.
 */
public class TestJava {


    public static void testAbility( ) {
        AbilityManager.INSTANCE.register(ToastAbility.class, new ToastAbilityImpl());
    }
}
