package ba.etf.rma22.projekat

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.PositionAssertions.isAbove
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class Test1 {
    @get:Rule
    val mainLayout= ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun TestUradene() {
        Espresso.onView(withId(R.id.filterAnketa)).perform(ViewActions.click())
        Espresso.onData(
            CoreMatchers.allOf(
                CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("Urađene ankete")
            )
        ).perform(ViewActions.click())

        Assert.assertEquals(0, AnketaRepository.getDone().size)
    }

    @Test
    fun TestPozicija(){
        onView(withId(R.id.filterAnketa)).check(isAbove(withId(R.id.upisDugme)))
        Espresso.onView(withId(R.id.upisDugme)).perform(ViewActions.click())
        onView(withId(R.id.odabirGodina)).check(isAbove(withId(R.id.odabirIstrazivanja)))
        onView(withId(R.id.odabirIstrazivanja)).check(isAbove(withId(R.id.odabirGrupa)))
    }

    @Test
    fun prebaciAktivnost(){
        Intents.init()
        onView(withId(R.id.upisDugme)).perform(click())
        intended(hasComponent(UpisIstrazivanje::class.java.name))
        Intents.release()
    }

    @Test
    fun TestUpis(){
        Espresso.onView(withId(R.id.upisDugme)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.odabirGodina)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.allOf(
            CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
            CoreMatchers.`is`("3")
        )).perform(ViewActions.click())

        Espresso.onView(withId(R.id.odabirIstrazivanja)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.allOf(
            CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
            CoreMatchers.`is`("Provođenje slobodnog vremena")
        )).perform(ViewActions.click())

        Espresso.onView(withId(R.id.odabirGrupa)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.allOf(
            CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
            CoreMatchers.`is`("grupa1")
        )).perform(ViewActions.click())

        Espresso.onView(withId(R.id.dodajIstrazivanjeDugme)).perform(ViewActions.click())
        Assert.assertEquals(2,AnketaRepository.getMyAnkete().size)
    }

}