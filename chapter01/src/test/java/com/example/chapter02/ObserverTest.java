package com.example.chapter02;

import com.example.redo.chapter02.observer.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class ObserverTest {
    @Test
    public void observersHandleEventsFromSubjectWithAssertions() {
        // given
        Subject<String> subject = new ConcreteSubject();
        Observer<String> observerA = Mockito.spy(new ConcreteObserverA());
        Observer<String> observerB = Mockito.spy(new ConcreteObserverB());

        // when
        subject.notifyObservers("No listeners"); /* 출력이 안되겠지? 관찰자가 현재 시점에 없으므로 */

        subject.registerObserver(observerA); /* 구독 등록 */
        subject.notifyObservers("Message for A"); /* 출력이 되겠지 */

        /** observer : observerA */

        subject.registerObserver(observerB); /* 구독 등록 */

        /** observer : observerA, observerB */
        subject.notifyObservers("Message for A & B"); /* 출력 O (2번 출력, 구독자가 2명임) */

        subject.unregisterObserver(observerA); /* 구독 취소 */
        subject.notifyObservers("Message for B"); /* 출력 O */

        subject.unregisterObserver(observerB); /* 구독 취소 */

        /** observer : X */
        subject.notifyObservers("No listeners");  /* 출력 X */

        // then
        Mockito.verify(observerA, times(1)).observe("Message for A");
        Mockito.verify(observerA, times(1)).observe("Message for A & B");
        Mockito.verifyNoMoreInteractions(observerA);

        Mockito.verify(observerB, times(1)).observe("Message for A & B");
        Mockito.verify(observerB, times(1)).observe("Message for B");
        Mockito.verifyNoMoreInteractions(observerB);
    }
}
