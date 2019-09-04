package kevin.repository

import io.reactivex.Observable
import kevin.domain.model.Item
import kevin.domain.model.repository.ItemRepository
import javax.inject.Inject


class ItemRepositoryStub @Inject constructor() : ItemRepository {

    override fun getItemFullList(): Observable<List<Item>> {
        val itemList = mutableListOf(
                Item(1, 1, "ananas", "fake1"),
                Item(1, 2, "abrico", "fake2")
        )
        return Observable.just(itemList)
    }
}