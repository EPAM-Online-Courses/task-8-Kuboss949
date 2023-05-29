package efs.task.unittests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenUserHeightNotValid() {
        //given
        double weight = 69.5;
        double height = 0.0;

        //when and then
        assertThrows(IllegalArgumentException.class,
                () ->{
                    FitCalculator.isBMICorrect(weight, height);
                });
    }

    @ParameterizedTest(name = "Test case [{index}]: weight = {0}")
    @ValueSource(doubles = {75.0, 80.5, 92.0})
    void shouldReturnTrue_whenBMINotCorrect(double weight){
        //given
        double height = 1.62;

        //when
        boolean isCorrect = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(isCorrect);
    }

    @ParameterizedTest(name = "Test case [{index}]: weight = {0}, height = {1}")
    @CsvSource({"54.0,162.5","82.2,190.0","73.2,1.75"})
    void shouldReturnFalse_whenBMICorrect(double weight, double height){

        //when
        boolean isCorrect = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(isCorrect);
    }
    @ParameterizedTest(name = "Test case [{index}]: weight = {0}, height = {1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_whenBMICorrect2(double weight, double height){

        //when
        boolean isCorrect = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(isCorrect);
    }


    @Test
    void shouldReturnUser1_79h_97_3w_whenTEST_USERS_LISTGiven(){
        //given
        var userList = TestConstants.TEST_USERS_LIST;
        //when
        User marek = new User(1.79, 97.3);
        User leszek = FitCalculator.findUserWithTheWorstBMI(userList);

        //then
        assertTrue(marek.getHeight() == leszek.getHeight() && marek.getWeight() == leszek.getWeight());

    }

    @Test
    void shouldReturnNull_whenEmptyListGiven(){
        //given
        var userList = new ArrayList<User>();
        //when
        User leszek = FitCalculator.findUserWithTheWorstBMI(userList);

        //then
        assertNull(leszek);
    }

    @Test
    void shouldReturnTEST_USERS_BMI_SCORE_whenTEST_USERS_LISTGiven(){
        //given
        var userList = TestConstants.TEST_USERS_LIST;
        //when
        double[] bmiList = TestConstants.TEST_USERS_BMI_SCORE;
        double[] result = FitCalculator.calculateBMIScore(userList);

        //then
        assertArrayEquals(bmiList, result);
    }

}