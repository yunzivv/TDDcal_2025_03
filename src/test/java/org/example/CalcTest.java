package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTest {

    @Test
    @DisplayName("1 + 1 = 2") // 실행시켰을 때 보이는 제목
    public void test1(){
        assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("2 + 1 = 3")
    public void test2(){
        assertThat(Calc.run("2 + 1")).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 = 4")
    public void test3(){
        assertThat(Calc.run("2 + 2")).isEqualTo(4);
    }

    @Test
    @DisplayName("50 - 30 = 20")
    public void test4(){
        assertThat(Calc.run("50 - 30")).isEqualTo(20);
    }

    @Test
    @DisplayName("10 + 20 + 30")
    public void test5(){
        assertThat(Calc.run("10 + 20 + 30")).isEqualTo(60);
    }

//    @Test
//    @DisplayName("10 - 20 + 30")
//    public void test6(){
//        assertThat(Calc.run("10 - 20 + 30")).isEqualTo(20);
//    }

}


