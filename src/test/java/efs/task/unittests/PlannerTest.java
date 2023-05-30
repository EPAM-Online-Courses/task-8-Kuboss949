package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    private Planner testPlanner;


    @BeforeEach
    void setUp() {
        testPlanner = new Planner();
    }
    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldReturnCALORIES_ON_ACTIVITY_LEVELValues_whenTEST_USERGiven(ActivityLevel activityLevel){
        //given
        User marcin = TestConstants.TEST_USER;
        int expectedValue = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);
        //when
        int result = testPlanner.calculateDailyCaloriesDemand(marcin, activityLevel);
        //then
        assertEquals(expectedValue, result);
    }

    @Test
    void shouldReturnTEST_USER_DAILY_INTAKE_whenTEST_USERGiven(){
        //given
        User marcin = TestConstants.TEST_USER;
        //when
        DailyIntake expected = TestConstants.TEST_USER_DAILY_INTAKE;
        DailyIntake result = testPlanner.calculateDailyIntake(marcin);
        //then
        assertEquals(expected.getCalories(), result.getCalories());
        assertEquals(expected.getProtein(), result.getProtein());
        assertEquals(expected.getFat(), result.getFat());
        assertEquals(expected.getCarbohydrate(), result.getCarbohydrate());

    }


}