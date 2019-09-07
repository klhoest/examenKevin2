package kevin.repository

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kevin.datasource.ItemApi
import kevin.datasource.ItemStorage
import kevin.domain.model.Item
import kevin.domain.model.repository.ItemRepository
import java.lang.Exception

class ItemRepositoryImpl(val api: ItemApi, val storage: ItemStorage) : ItemRepository {

    override fun getItemFullList(): Observable<List<Item>> {
        val networkObservable = api.getFullItemList().retry(2)
        return Observable.create<List<Item>>(ObservableOnSubscribe { resultEmitter ->
            resultEmitter.onNext(storage.fullList)
            networkObservable.subscribe({ networkList ->
                storage.fullList = networkList //we update storage with network data
                resultEmitter.onNext(networkList)
                resultEmitter.onComplete()
            }, { networkThrowable: Throwable? ->
                if (networkThrowable != null)
                    resultEmitter.onError(networkThrowable)
                else
                    resultEmitter.onError(Exception("network failed without information"))
            })

        })
    }
}