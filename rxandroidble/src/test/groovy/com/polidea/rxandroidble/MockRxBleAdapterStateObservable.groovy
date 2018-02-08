package com.polidea.rxandroidble

import com.polidea.rxandroidble.internal.util.DisposableUtil
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.annotations.NonNull
import io.reactivex.subjects.ReplaySubject

class MockRxBleAdapterStateObservable {

    public final ReplaySubject relay = ReplaySubject.create()

    public Observable<RxBleAdapterStateObservable.BleAdapterState> asObservable() {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            void subscribe(@NonNull ObservableEmitter observableEmitter) throws Exception {
                def subscription = relay.subscribeWith(DisposableUtil.disposableEmitter(observableEmitter))
                observableEmitter.setDisposable(subscription)
            }
        })
    }

    def disableBluetooth() {
        relay.onNext(RxBleAdapterStateObservable.BleAdapterState.STATE_OFF)
    }
}
