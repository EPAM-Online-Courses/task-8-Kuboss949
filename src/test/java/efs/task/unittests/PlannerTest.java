package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    private Planner testPlanner;

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldReturnCALORIES_ON_ACTIVITY_LEVELValues_whenTEST_USERGiven(ActivityLevel activityLevel){
        //given
        testPlanner = new Planner();
        User marcin = TestConstants.TEST_USER;
        //when
        int result = testPlanner.calculateDailyCaloriesDemand(marcin, activityLevel);
        //then
        assertEquals(TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel), result);
    }

    @Test
    void shouldReturnTEST_USER_DAILY_INTAKE_whenTEST_USERGiven(){
        //given
        testPlanner = new Planner();
        User marcin = TestConstants.TEST_USER;
        //when
        DailyIntake expected = TestConstants.TEST_USER_DAILY_INTAKE;
        DailyIntake result = testPlanner.calculateDailyIntake(marcin);
        //then
        assertTrue(expected.getCalories()==result.getCalories() &&
                expected.getFat()==result.getFat() &&
                expected.getCarbohydrate()==result.getCarbohydrate() &&
                expected.getProtein()==result.getProtein());

    }


}