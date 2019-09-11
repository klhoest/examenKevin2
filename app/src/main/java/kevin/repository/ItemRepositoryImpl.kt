package kevin.repository

import io.reactivex.Observable
import kevin.datasource.ItemService
import kevin.datasource.ItemStorage
import kevin.domain.model.Item
import kevin.domain.model.repository.ItemRepository

class ItemRepositoryImpl(val service: ItemService, val storage: ItemStorage) : ItemRepository {

    override fun getItemFullList(): Observable<List<Item>> {
        val storageObservable = Observable.just(storage.fullList)

        val networkObservable = service.getFullItemList()
                .retry(2)
                .doOnNext { list -> storage.fullList = list }

        return storageObservable.mergeWith(networkObservable)
    }
}